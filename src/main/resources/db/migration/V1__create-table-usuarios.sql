    CREATE TABLE usuarios(

        id bigint not null auto_increment,
        nombre VARCHAR(150) NOT NULL,
        email  VARCHAR(100) NOT NULL UNIQUE,
        contrasena VARCHAR(200) NOT NULL,

        primary key (id)
    );