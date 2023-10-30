
create table topicos(

                        id bigint not null auto_increment,
                        titulo varchar(100) not null,
                        mensaje varchar(600) not null ,
                        fecha_creacion date not null,
                        status varchar(100) not null,
                        autor_id BIGINT NOT NULL,
                        curso_id BIGINT NOT NULL,

                        PRIMARY KEY(id),
                        FOREIGN KEY(autor_id) REFERENCES usuarios(id),
                        FOREIGN KEY(curso_id) REFERENCES cursos(id)

);