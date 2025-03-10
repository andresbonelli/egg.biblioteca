CREATE VIEW v_usuario AS
SELECT 
    HEX(id) AS user_id,
    email,
    nombre,
    password,
    rol
FROM 
    biblioteca.t_usuario;