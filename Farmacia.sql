create database Farmacia;
use Farmacia;

create table Productos (
id int (3) not null,
nombre varchar (25) not null,
descripcion varchar (50) not null,
cantidad int (3) not null,
cantidad_vencidad int (3) not null
);
Insert into Productos (id, nombre, descripcion, cantidad, cantidad_vencidad)
values (001, "Neostrata Skin", "Cema antiedad", 5, 3),
		(002, "Aceite antiestrias", "Tratamiento de cicatrices", 5, 0),
        (003, "Loción aqua infini", "Estimula la eficacia de tratamientos", 5, 10);

SELECT * FROM Productos