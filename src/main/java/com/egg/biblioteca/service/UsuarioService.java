package com.egg.biblioteca.service;

import com.egg.biblioteca.controller.dto.UserRegisterDTO;
import com.egg.biblioteca.domain.entity.Role;
import com.egg.biblioteca.domain.entity.Usuario;
import com.egg.biblioteca.domain.repository.UsuarioRepository;
import com.egg.biblioteca.exception.ValidationException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario user = usuarioRepository.buscarPorEmail(email);
        if (null != user) {
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" +
                    user.getRol().toString());
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", user);
            return new User(user.getEmail(), user.getPasswordHash(), permisos);
        } else {
            log.error("Usuario no encontrado: {}", email);
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    @Transactional
    public void registro(UserRegisterDTO usuario) {
        validar(usuario.nombre(), usuario.email(), usuario.password(), usuario.confirmPassword());
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuario.nombre());
        nuevoUsuario.setEmail(usuario.email());
        nuevoUsuario.setPasswordHash(new BCryptPasswordEncoder().encode(usuario.password()));
        nuevoUsuario.setRol(Role.USER);
        usuarioRepository.save(nuevoUsuario);
    }

    private void validar(String nombre, String email, String password, String confirmPassword) throws ValidationException {

        if (null == nombre || nombre.isBlank()) {
            throw new ValidationException("el nombre no puede ser nulo o estar vacío");
        }
        if (null == email || email.isBlank()) {
            throw new ValidationException("el email no puede ser nulo o estar vacío");
        }
        if (null == password || password.isBlank() || password.length() < 4) {
            throw new ValidationException("La contraseña no puede estar vacía, y debe tener más de 4 caracteres");
        }
        if (!password.equals(confirmPassword)) {
            throw new ValidationException("Las contraseñas ingresadas deben ser iguales");
        }
    }

}
