INSERT INTO main_tasks (id, name, iconUrl, duration, maincategory_id) VALUES
-- Tareas del día a día
    (1, 'Barrer / Pasar la aspiradora', 'http://localhost:8080/static/icon1.png', 60, 1),
    (2, 'Fregar el suelo', 'http://localhost:8080/static/icon1.png', 60, 1),
    (3, 'Hacer el desayuno', 'http://localhost:8080/static/icon1.png', 60, 1),
    (4, 'Hacer la comida', 'http://localhost:8080/static/icon1.png', 60, 1),
    (5, 'Hacer la merienda', 'http://localhost:8080/static/icon1.png', 60, 1),
    (6, 'Hacer la cena', 'http://localhost:8080/static/icon1.png', 60, 1),
    (7, 'Poner la lavadora', 'http://localhost:8080/static/icon1.png', 60, 1),
    (8, 'Tender la ropa', 'http://localhost:8080/static/icon1.png', 60, 1),
    (9, 'Recoger la ropa del tendedero y doblar', 'http://localhost:8080/static/icon1.png', 60, 1),
    (10, 'Limpiar el baño', 'http://localhost:8080/static/icon1.png', 60, 1),
    (11, 'Limpiar la cocina', 'http://localhost:8080/static/icon1.png', 60, 1),
    (12, 'Limpiar cristales', 'http://localhost:8080/static/icon1.png', 60, 1),
    (13, 'Planchar', 'http://localhost:8080/static/icon1.png', 60, 1),
    (14, 'Ordenar', 'http://localhost:8080/static/icon1.png', 60, 1),
    (15, 'Quitar el polvo', 'http://localhost:8080/static/icon1.png', 60, 1),
    (16, 'Lavar los platos', 'http://localhost:8080/static/icon1.png', 60, 1),
    (17, 'Poner el lavavajillas', 'http://localhost:8080/static/icon1.png', 60, 1),
    (18, 'Recoger el lavavajillas', 'http://localhost:8080/static/icon1.png', 60, 1),
    (19, 'Poner la mesa', 'http://localhost:8080/static/icon1.png', 60, 1),
    (20, 'Tirar la basura', 'http://localhost:8080/static/icon1.png', 60, 1),
    (21, 'Hacer la cama', 'http://localhost:8080/static/icon1.png', 60, 1),
    (22, 'Hacer la compra', 'http://localhost:8080/static/icon1.png', 60, 1),
    (23, 'Cambiar las sábanas', 'http://localhost:8080/static/icon1.png', 60, 1),
    (24, 'Arreglos en casa', 'http://localhost:8080/static/icon1.png', 60, 1),
    (25, 'Lavar el coche', 'http://localhost:8080/static/icon1.png', 60, 1),

-- Planificación y gestión
    (26, 'Planificar menú', 'http://localhost:8080/static/icon1.png', 60, 2),
    (27, 'Planificar vacaciones / viajes', 'http://localhost:8080/static/icon1.png', 60, 2),
    (28, 'Gestión con proveedores de la casa (presupuestos, contratos…)', 'http://localhost:8080/static/icon1.png', 60, 2),
    (29, 'Gestión de tareas del colegio / matrícula etc', 'http://localhost:8080/static/icon1.png', 60, 2),
    (30, 'Gestionar material y actividades escolares.', 'http://localhost:8080/static/icon1.png', 60, 2),
    (31, 'Gestión de ropa de niños (comprar nueva, guardar la de temporada)', 'http://localhost:8080/static/icon1.png', 60, 2),

-- Niños
    (32, 'Recoger los juguetes', 'http://localhost:8080/static/icon1.png', 60, 3),
    (33, 'Llevar a los niños al cole', 'http://localhost:8080/static/icon1.png', 60, 3),
    (34, 'Llevar/recoger a los niños', 'http://localhost:8080/static/icon1.png', 60, 3),
    (35, 'Preparar/vestir a los niños', 'http://localhost:8080/static/icon1.png', 60, 3),
    (36, 'Ayudar a hacer los deberes', 'http://localhost:8080/static/icon1.png', 60, 3),
    (37, 'Dar de comer', 'http://localhost:8080/static/icon1.png', 60, 3),
    (38, 'Cuidar a los niños', 'http://localhost:8080/static/icon1.png', 60, 3),
    (39, 'Bañar a los niños', 'http://localhost:8080/static/icon1.png', 60, 3),
    (40, 'Dormir a los niños', 'http://localhost:8080/static/icon1.png', 60, 3),
    (41, 'Llevar a los niños al médico', 'http://localhost:8080/static/icon1.png', 60, 3),


-- Cuidar a otros
    (42, 'Cuidar a otra persona', 'http://localhost:8080/static/icon1.png', 60, 4),
    (43, 'Bañar a otra persona', 'http://localhost:8080/static/icon1.png', 60, 4),
    (44, 'Dar de comer', 'http://localhost:8080/static/icon1.png', 60, 4),
    (45, 'Acompañar a gestiones a otra persona', 'http://localhost:8080/static/icon1.png', 60, 4),

-- Mascotas
    (46, 'Bañar a una mascota',  'http://localhost:8080/static/icon1.png', 60, 5),
    (47, 'Dar de comer',  'http://localhost:8080/static/icon1.png', 60, 5),
    (48, 'Pasear a una mascota',  'http://localhost:8080/static/icon1.png', 60, 5),
    (49, 'Veterinario',  'http://localhost:8080/static/icon1.png', 60, 5),
    (50, 'Recoger/limpiar accesorios',  'http://localhost:8080/static/icon1.png', 60, 5),

-- Jardín
    (51, 'Cortar el césped', 'http://localhost:8080/static/icon1.png', 60, 6),
    (52, 'Regar plantas', 'http://localhost:8080/static/icon1.png', 60, 6),
    (53, 'Recoger las hojas', 'http://localhost:8080/static/icon1.png', 60, 6),
    (54, 'Recoger/limpiar herramientas', 'http://localhost:8080/static/icon1.png', 60, 6);
