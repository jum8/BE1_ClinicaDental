drop table if exists Paciente;
drop table if exists Domicilio;
--drop table if exists Odontologo;

create table Domicilio (
    id int auto_increment primary key,
    calle varchar(25) not null,
    numero int not null,
    localidad varchar(50) not null,
    provincia varchar(25) not null
    );

create table Paciente (
    id int auto_increment primary key,
    apellido varchar(25) not null,
    nombre varchar(25) not null,
    dni int not null,
    fecha_ingreso date not null,
    domicilio_id bigint not null,
    foreign key(domicilio_id) references Domicilio(id)
    );

create table if not exists Odontologo (
    id int auto_increment primary key,
    numero_matricula int not null,
    nombre varchar(25) not null,
    apellido varchar(25) not null
);


-- Pueden agregar acá sentencias Insert para precargar datos.

--insert into medicamentos values(default, 123, 'Acrylarm', 'abbot', 10, 1500);