INSERT INTO main_tasks (id, name, iconUrl, duration, maincategory_id) VALUES
-- Tareas del día a día
    (1, 'Barrer / Pasar aspiradora', 'http://localhost:8080/static/1/1_barrer_aspiradora.svg', 30, 1),
    (2, 'Fregar el suelo', 'http://localhost:8080/static/1/2_fregar.svg', 30, 1),
    (3, 'Hacer el desayuno', 'http://localhost:8080/static/1/3_desayuno.svg', 15, 1),
    (4, 'Hacer la comida', 'http://localhost:8080/static/1/4_comida.svg', 45, 1),
    (5, 'Hacer la merienda', 'http://localhost:8080/static/1/5_merienda.svg', 15, 1),
    (6, 'Hacer la cena', 'http://localhost:8080/static/1/6_cena.svg', 30, 1),
    (7, 'Poner lavadora', 'http://localhost:8080/static/1/7_lavadora.svg', 10, 1),
    (8, 'Lavar ropa a mano', 'http://localhost:8080/static/1/8_lavar_ropa.svg', 15, 1),
    (9, 'Tender ropa', 'http://localhost:8080/static/1/9_tender.svg', 20, 1),
    (10, 'Recoger ropa tendida', 'http://localhost:8080/static/1/10_doblar_ropa.svg', 30, 1),
    (11, 'Limpiar la cocina', 'http://localhost:8080/static/1/11_cocina.svg', 30, 1),
    (12, 'Limpiar cristales', 'http://localhost:8080/static/1/12_cristales.svg', 30, 1),
    (13, 'Planchar', 'http://localhost:8080/static/1/13_planchar.svg', 30, 1),
    (14, 'Ordenar', 'http://localhost:8080/static/1/14_ordenar.svg', 45, 1),
    (15, 'Quitar el polvo', 'http://localhost:8080/static/1/15_polvo.svg', 15, 1),
    (16, 'Lavar platos', 'http://localhost:8080/static/1/16_lavar_platos.svg', 15, 1),
    (17, 'Poner lavavajillas', 'http://localhost:8080/static/1/17_poner_lavavajillas.svg', 10, 1),
    (18, 'Recoger lavavajillas', 'http://localhost:8080/static/1/18_quitar_lavavajillas.svg', 10, 1),
    (19, 'Poner la mesa', 'http://localhost:8080/static/1/19_poner_mesa.svg', 5, 1),
    (20, 'Tirar la basura', 'http://localhost:8080/static/1/20_basura.svg', 10, 1),
    (21, 'Hacer la cama', 'http://localhost:8080/static/1/21_cama.svg', 5, 1),
    (22, 'Cambiar sábanas', 'http://localhost:8080/static/1/22_sabanas.svg', 10, 1),
    (23, 'Hacer la compra', 'http://localhost:8080/static/1/23_compra.svg', 60, 1),
    (24, 'Arreglos en casa', 'http://localhost:8080/static/1/24_arreglos.svg', 15, 1),
    (25, 'Lavar el coche', 'http://localhost:8080/static/1/25_lavar_coche.svg', 15, 1),
    (26, 'Limpiar el baño', 'http://localhost:8080/static/1/26_wc.svg', 20, 1),

-- Planificación y gestión
    (27, 'Planificar menú', 'http://localhost:8080/static/2/27_planificar_menu.svg', 10, 2),
    (28, 'Planificar vacaciones', 'http://localhost:8080/static/2/28_planificar_vacaciones.svg', 60, 2),
    (29, 'Gestiones de casa', 'http://localhost:8080/static/2/29_gestiones_casa.svg', 15, 2),
    (30, 'Gestión: temas de colegio', 'http://localhost:8080/static/2/30_gestion_cole.svg', 30, 2),
    (31, 'Gestión: material escolar.', 'http://localhost:8080/static/2/31_gestion_material.svg', 30, 2),
    (32, 'Gestión: ropa de niños', 'http://localhost:8080/static/2/32_gestion_ropa.svg', 60, 2),

-- Niños
    (33, 'Llevar al cole', 'http://localhost:8080/static/3/33_llevar_cole.svg', 15, 3),
    (34, 'Llevar / Recoger', 'http://localhost:8080/static/3/34_llevar_kids.svg', 15, 3),
    (35, 'Preparar / vestir', 'http://localhost:8080/static/3/35_vestir_kids.svg', 10, 3),
    (36, 'Ayudar con deberes', 'http://localhost:8080/static/3/36_deberes.svg', 60, 3),
    (37, 'Dar de comer', 'http://localhost:8080/static/3/37_alimentar_kids.svg', 30, 3),
    (38, 'Cuidar a los niños', 'http://localhost:8080/static/3/38_cuidar_kids.svg', 60, 3),
    (39, 'Bañar a los niños', 'http://localhost:8080/static/3/39_bañar_kids.svg', 30, 3),
    (40, 'Dormir a los niños', 'http://localhost:8080/static/3/40_dormir_kids.svg', 30, 3),
    (41, 'Llevar al médico', 'http://localhost:8080/static/3/41_doctor_kids.svg', 60, 3),
    (42, 'Ordenar el cuarto', 'http://localhost:8080/static/3/42_ordenar_kids.svg', 10, 3),
    (43, 'Recoger los juguetes', 'http://localhost:8080/static/3/43_juguetes.svg', 15, 3),

-- Cuidar a otros
    (44, 'Bañar a otra persona', 'http://localhost:8080/static/4/44_bañar.svg', 30, 4),
    (45, 'Dar de comer', 'http://localhost:8080/static/4/45_alimentar.svg', 30, 4),
    (46, 'Acompañar a gestiones', 'http://localhost:8080/static/4/46_acompañar.svg', 60, 4),
    (47, 'Cuidar a otra persona', 'http://localhost:8080/static/4/47_cuidar.svg', 60, 4),

-- Mascotas
    (48, 'Dar de comer',  'http://localhost:8080/static/5/48_comida_mascotas.svg', 10, 5),
    (49, 'Pasear a una mascota',  'http://localhost:8080/static/5/49_paseo_mascotas.svg', 60, 5),
    (50, 'Bañar a una mascota',  'http://localhost:8080/static/5/50_baño_mascotas.svg', 15, 5),
    (51, 'Limpieza de accesorios',  'http://localhost:8080/static/5/51_veterinario_mascotas.svg', 10, 5),
    (52, 'Veterinario',  'http://localhost:8080/static/5/52_limpieza_mascotas.svg', 60, 5),

-- Jardín
    (53, 'Cortar el césped', 'http://localhost:8080/static/6/53_cortacesped.svg', 30, 6),
    (54, 'Regar plantas', 'http://localhost:8080/static/6/54_regar.svg', 15, 6),
    (55, 'Recoger las hojas', 'http://localhost:8080/static/6/55_hojas.svg', 15, 6),
    (56, 'Recoger herramientas', 'http://localhost:8080/static/6/56_herramientas.svg', 10, 6);
