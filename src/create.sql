drop table if exists Domicilio;

create table Domicilio (
    id bigint auto_increment primary key,
    calle varchar(25) not null,
    numero int not null,
    localidad varchar(50) not null,
    provincia varchar(25) not null
    );

drop table if exists Paciente;
create table Paciente (
    id bigint auto_increment primary key,
    apellido varchar(25) not null,
    nombre varchar(25) not null,
    dni int not null,
    fecha_ingreso date not null,
    domicilio_id bigint not null,
    foreign key(domicilio_id) references Domicilio(id)
    );


-- Pueden agregar acá sentencias Insert para precargar datos.

--insert into medicamentos values(default, 123, 'Acrylarm', 'abbot', 10, 1500);