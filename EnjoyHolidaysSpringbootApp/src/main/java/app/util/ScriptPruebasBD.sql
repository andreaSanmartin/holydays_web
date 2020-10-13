/**Author: Christian Mendieta*/

/*  ----------- DATOS FICTICIOS PARA PRUEBAS DEL SERVICIO ----------- */

/** DATA USUARIOS: -------------------------------------------------- */

insert into Usuarios(usu_id, usu_nombre, usu_correo, usu_genero, usu_fecha_nac, usu_telefono, usu_password, usu_estado) 
values(1, 'Christian Mendieta', 'cristian@gmail.com', 'Masculino', '06/09/1995', '0911111111', 'cristian', true);

insert into Usuarios(usu_id, usu_nombre, usu_correo, usu_genero, usu_fecha_nac, usu_telefono, usu_password, usu_estado) 
values(2, 'Andrea Sanmartin', 'andrea@gmail.com', 'Femenino', '01/05/2000', '0911111112','andrea', true);

insert into Usuarios(usu_id, usu_nombre, usu_correo, usu_genero, usu_fecha_nac, usu_telefono, usu_password, usu_estado) 
values(3, 'Kelly Farfan', 'kelly@gmail.com', 'Femenino', '03/07/2000', '0911111113', 'kelly', true);

/** DATA DE TIPO_ALOJAMIENTOS: -------------------------------------------------- */

insert into tipovivienda(tip_id, tip_tipo) values(1, 'Departamento');
insert into tipovivienda(tip_id, tip_tipo) values(2, 'Villa');
insert into tipovivienda(tip_id, tip_tipo) values(3, 'Casa');
insert into tipovivienda(tip_id, tip_tipo) values(4, 'Vivienda Anexa');
insert into tipovivienda(tip_id, tip_tipo) values(5, 'Finca');

/** DATA DE PROVINCIAS: -------------------------------------------------- */

insert into provincia(pro_id, pro_nombre) values(1, 'Azuay');
insert into provincia(pro_id, pro_nombre) values(2, 'Bolívar');
insert into provincia(pro_id, pro_nombre) values(3, 'Cañar');
insert into provincia(pro_id, pro_nombre) values(4, 'Carchi');
insert into provincia(pro_id, pro_nombre) values(5, 'Chimborazo');
insert into provincia(pro_id, pro_nombre) values(6, 'Cotopaxi');
insert into provincia(pro_id, pro_nombre) values(7, 'El Oro');
insert into provincia(pro_id, pro_nombre) values(8, 'Esmeraldas');
insert into provincia(pro_id, pro_nombre) values(9, 'Galápagos');
insert into provincia(pro_id, pro_nombre) values(10, 'Guayas');
insert into provincia(pro_id, pro_nombre) values(11, 'Imbabura');
insert into provincia(pro_id, pro_nombre) values(12, 'Loja');
insert into provincia(pro_id, pro_nombre) values(13, 'Los Ríos');
insert into provincia(pro_id, pro_nombre) values(14, 'Manabí');
insert into provincia(pro_id, pro_nombre) values(15, 'Morona Santiago');
insert into provincia(pro_id, pro_nombre) values(16, 'Napo');
insert into provincia(pro_id, pro_nombre) values(17, 'Orellana');
insert into provincia(pro_id, pro_nombre) values(18, 'Pastaza');
insert into provincia(pro_id, pro_nombre) values(19, 'Pichincha');
insert into provincia(pro_id, pro_nombre) values(20, 'Santa Elena');
insert into provincia(pro_id, pro_nombre) values(21, 'Santo Domingo');
insert into provincia(pro_id, pro_nombre) values(22, 'Sucumbíos');
insert into provincia(pro_id, pro_nombre) values(23, 'Tungurahua');
insert into provincia(pro_id, pro_nombre) values(24, 'Zamora Chinchipe');

/** DATA DE CIUDADES: -------------------------------------------------- */

insert into Ciudades(ciu_id, ciu_nombre, pro_id) values(1, 'Quito', 10);
insert into Ciudades(ciu_id, ciu_nombre, pro_id) values(2, 'Guayaquil', 19);
insert into Ciudades(ciu_id, ciu_nombre, pro_id) values(3, 'Cuenca', 1);
insert into Ciudades(ciu_id, ciu_nombre, pro_id) values(4, 'Azogues', 3);
insert into Ciudades(ciu_id, ciu_nombre, pro_id) values(5, 'Troncal', 3);
insert into Ciudades(ciu_id, ciu_nombre, pro_id) values(6, 'Paute', 1);
insert into Ciudades(ciu_id, ciu_nombre, pro_id) values(7, 'El pan', 1);
insert into Ciudades(ciu_id, ciu_nombre, pro_id) values(8, 'Gualaceo', 1);
insert into Ciudades(ciu_id, ciu_nombre, pro_id) values(9, 'Duran', 19);
insert into Ciudades(ciu_id, ciu_nombre, pro_id) values(10, 'Santo Domingo', 21);

/** DATA DE ALOJAMIENTOS: -------------------------------------------------- */
/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(1, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
7, 5, 2, 3, 21.50, true, 1,
1, 1);

/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(2, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
7, 5, 2, 3, 12.50, true, 1,
1, 1);

/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(3, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
7, 5, 2, 3, 8.00, true, 1,
1, 1);

/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(4, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
7, 5, 2, 3, 9.99, true, 1,
1, 1);

/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(5, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
7, 5, 2, 3, 17.40, true, 1,
2, 1);

/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(6, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
8, 5, 2, 3, 10.50, true, 1,
1, 1);

/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(7, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
5, 5, 2, 3, 10.00, true, 1,
2, 1);

/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(8, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
5, 5, 2, 3, 15.50, true, 1,
2, 1);

/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(9, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
7, 5, 2, 3, 50.00, false, 1,
2, 1);

/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(10, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
7, 5, 2, 3, 20.30, false, 1,
1, 1);

/*-----*/
insert into Alojamientos(alj_id, alj_descripcion, alj_ubicacion, alj_condiciones_uso, alj_servicios,
alj_num_huespedes, alj_num_camas, alj_num_baños, alj_num_habitaciones, alj_precio_noche, alj_disponibilidad, usu_id, 
ciu_id, tip_id)
values(11, 'El mejor alojamiento de la ciudad', 'Av españa y Remigio', 'No manchar las paredes', 'Agua Caliente, Internet',
7, 5, 2, 3, 11.50, false, 1,
1, 1);

/** DATA DE RESERVAS: -------------------------------------------------- */

insert into Reserva_Alojamiento(res_id, res_fecha_inicio, res_fecha_fin, res_num_dias, res_total, alj_id, usu_id)
values(1, '27/09/2020', '02/10/2020', 0, 0, 9, 1);

insert into Reserva_Alojamiento(res_id, res_fecha_inicio, res_fecha_fin, res_num_dias, res_total, alj_id, usu_id)
values(2, '05/10/2020', '20/10/2020', 0, 0, 10, 1);


insert into Reserva_Alojamiento(res_id, res_fecha_inicio, res_fecha_fin, res_num_dias, res_total, alj_id, usu_id)
values(3, '27/09/2020', '02/10/2020', 0, 0, 11, 1);

insert into Reserva_Alojamiento(res_id, res_fecha_inicio, res_fecha_fin, res_num_dias, res_total, alj_id, usu_id)
values(4, '03/11/2020', '02/12/2020', 0, 0, 11, 1);

insert into Reserva_Alojamiento(res_id, res_fecha_inicio, res_fecha_fin, res_num_dias, res_total, alj_id, usu_id)
values(5, '20/10/2020', '02/11/2020', 0, 0, 11, 1); /*04-10-2020   18-10-2020*/

/** DATA DE IMAGENES: -------------------------------------------------- */
/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(1, 'img_prueba_1', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817019/jeby1c8lc1sdofr2kasj.jpg',
 'jeby1c8lc1sdofr2kasj', 1);

/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(2, 'img_prueba_2', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817034/ceqjmpkrmjbvrp5llah9.jpg',
 'ceqjmpkrmjbvrp5llah9', 2);

/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(3, 'img_prueba_3', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817045/ewxcuwexykcaeslywge7.jpg',
 'ewxcuwexykcaeslywge7', 3);

/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(4, 'img_prueba_4', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817101/q1ylvsi9jqnqzhdk4dto.jpg',
 'jeby1c8lc1sdofr2kasj', 4);

/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(5, 'img_prueba_5', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817108/ebimovquuvgnzukqfcmu.jpg',
 'ebimovquuvgnzukqfcmu', 5);

/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(6, 'img_prueba_6', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817117/bw9hcno2kgn42zbuuvlx.jpg',
 'bw9hcno2kgn42zbuuvlx', 6);

/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(7, 'img_prueba_7', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817127/pztlsbepw25snwgdehtr.jpg',
 'pztlsbepw25snwgdehtr', 7);

/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(8, 'img_prueba_8', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817135/xrelvgloiwl6xvcwrwmh.jpg',
 'xrelvgloiwl6xvcwrwmh', 8);

/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(9, 'img_prueba_9', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817148/bcabkba0dlajp58tjyaa.jpg',
 'jeby1c8lc1sdofr2kasj', 9);

/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(10, 'img_prueba_10', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817162/x0gbwn58hmr3tl6ujtna.jpg',
 'x0gbwn58hmr3tl6ujtna', 10);

/*-----*/
insert into imagenes_alojamientos(img_id, img_nombre, img_url, img_cloudinary_id, alj_id)
values(11, 'img_prueba_11', 'http://res.cloudinary.com/dm7i4gtgv/image/upload/v1600817172/qdxt03pdluwbj5x02vrm.jpg',
 'qdxt03pdluwbj5x02vrm', 11);
