-- Create t_autor table
CREATE TABLE IF NOT EXISTS biblioteca.t_autor (
    id_autor CHAR(36) PRIMARY KEY, -- Store as CHAR(36)
    nombre VARCHAR(255) NOT NULL
);

-- Create t_editorial table
CREATE TABLE IF NOT EXISTS biblioteca.t_editorial (
    id_editorial CHAR(36) PRIMARY KEY, -- Store as CHAR(36)
    nombre VARCHAR(255) NOT NULL
);

-- Create t_libro table
CREATE TABLE IF NOT EXISTS biblioteca.t_libro (
    isbn BIGINT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    ejemplares INT,
    alta DATE,
    id_autor CHAR(36), -- Store as CHAR(36)
    id_editorial CHAR(36), -- Store as CHAR(36)
    FOREIGN KEY (id_autor) REFERENCES biblioteca.t_autor(id_autor),
    FOREIGN KEY (id_editorial) REFERENCES biblioteca.t_editorial(id_editorial)
);