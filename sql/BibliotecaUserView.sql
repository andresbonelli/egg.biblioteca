USE biblioteca;
CREATE VIEW v_usuario AS
SELECT 
    HEX(id) AS user_id,
    email,
    nombre,
    password,
    rol,
    HEX(id_imagen) AS id_imagen
FROM 
    biblioteca.t_usuario;

SELECT * FROM v_usuario;

DROP VIEW v_usuario;


DROP TABLE t_usuario;
