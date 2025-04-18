package com.egg.biblioteca.service;

import com.egg.biblioteca.api.dto.UserRegisterDTO;
import com.egg.biblioteca.api.dto.UserResponseDTO;
import com.egg.biblioteca.domain.entity.Imagen;
import com.egg.biblioteca.domain.entity.Role;
import com.egg.biblioteca.domain.entity.Usuario;
import com.egg.biblioteca.domain.repository.UsuarioRepository;
import com.egg.biblioteca.exception.RegistroNoExisteException;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final ImagenService imagenService;

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
            session.setAttribute("subject", user);
            return new User(user.getEmail(), user.getPasswordHash(), permisos);
        } else {
            log.error("Usuario no encontrado: {}", email);
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    @Transactional
    public UserResponseDTO registro(UserRegisterDTO usuario, MultipartFile file) {
        validar(usuario.nombre(), usuario.email(), usuario.password(), usuario.confirmPassword());
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuario.nombre());
        nuevoUsuario.setEmail(usuario.email());
        nuevoUsuario.setPasswordHash(new BCryptPasswordEncoder().encode(usuario.password()));
        nuevoUsuario.setRol(Role.USER);
        if (null != file) {
            Imagen imagen = imagenService.guardar(file);
            nuevoUsuario.setImagen(imagen);
        }
        Usuario newUser = usuarioRepository.save(nuevoUsuario);
        return getUserResponseDTO(newUser);
    }

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(RegistroNoExisteException::new);
    }

    public List<UserResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(this::getUserResponseDTO).toList();
    }

    public void cambiarRol(UUID id) {
        Usuario usuario = buscarPorId(id);
        usuario.setRol(usuario.getRol().equals(Role.USER) ? Role.ADMIN : Role.USER);
        usuarioRepository.save(usuario);
    }

    public void actualizar(UUID id, String nombre, String email, String password, String confirmPassword, MultipartFile archivo) {
        Usuario usuario = buscarPorId(id);
        if (password.isBlank()) {
            actualizarSinPassword(archivo, id, nombre, email);
            return;
        }
        validar(nombre, email, password, confirmPassword);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPasswordHash(new BCryptPasswordEncoder().encode(password));
        if (archivo != null && !archivo.getName().isEmpty()) {
            Imagen imagen = imagenService.guardar(archivo);
            usuario.setImagen(imagen);
        }
        usuarioRepository.save(usuario);
    }

    public void eliminar(UUID id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    private UserResponseDTO getUserResponseDTO(Usuario newUser) {
        return new UserResponseDTO(
                newUser.getId().toString(),
                newUser.getNombre(),
                newUser.getEmail(),
                newUser.getRol().name(),
                newUser.getImagen() != null ? newUser.getImagen().getId().toString() : null
        );
    }

    private void actualizarSinPassword(MultipartFile archivo, UUID id, String nombre, String email) {
        Usuario usuario = buscarPorId(id);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        if (archivo != null && !archivo.getName().isEmpty()) {
            Imagen imagen = imagenService.guardar(archivo);
            usuario.setImagen(imagen);
        }
        usuarioRepository.save(usuario);
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
