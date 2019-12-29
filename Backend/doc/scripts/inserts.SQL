/** INSERTS **/

INSERT INTO public.status(
	id_status, name, description)
	VALUES (1, 'ACTIVE', 'El usuario se encuentra actualmente habilitado.');
	
INSERT INTO public.status(
	id_status, name, description)
	VALUES (2, 'INACTIVE', 'El usuario se encuentra actualmente deshabilitado.');


INSERT INTO public.rol(
	id_rol, name, description)
	VALUES (1, 'READONLY','Rol de solo lectura.');
	
INSERT INTO public.rol(
	id_rol, name, description)
	VALUES (2, 'READWRITE','Rol de lectura y escritura.');

INSERT INTO public.person(
	id_person, "name", lastname, address, phone, date_birth)
	VALUES (1, 'Carlos', 'Aguirre', 'cc', '3105638030','2016-06-22 19:10:25-07');

INSERT INTO public.person(
	id_person, "name", lastname, address, phone, date_birth)
	VALUES (2, 'Pepe', 'Aguirre', 'cc', '3105638030','2016-06-22 19:10:25-07');


INSERT INTO public."user"(
	id_user, rol, status, person, username, email, "password")
	VALUES (1, 1, 1, 1, 'caaguirre194', 'caaguirre194@gmail.com', '123');