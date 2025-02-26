-- Insert sample data into t_autor
INSERT INTO biblioteca.t_autor (id_autor, nombre) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Gabriel García Márquez'),
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'J.K. Rowling'),
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'George Orwell');

-- Insert sample data into t_editorial
INSERT INTO biblioteca.t_editorial (id_editorial, nombre) VALUES
('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21', 'Penguin Random House'),
('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 'Scholastic'),
('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23', 'Secker & Warburg');

-- Insert sample data into t_libro
INSERT INTO biblioteca.t_libro (isbn, titulo, ejemplares, alta, id_autor, id_editorial) VALUES
(9780307476463, 'One Hundred Years of Solitude', 10, '1967-05-30', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a21'),
(9780439554930, 'Harry Potter and the Philosopher''s Stone', 15, '1997-06-26', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
(9780451524935, '1984', 20, '1949-06-08', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a23');