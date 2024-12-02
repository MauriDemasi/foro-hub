CREATE TABLE perfil (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE curso (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    perfil_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY unique_email (email),

    FOREIGN KEY (perfil_id) REFERENCES perfil(id),
    constraint fk_perfil_id FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

CREATE TABLE topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) UNIQUE NOT NULL,
    mensaje VARCHAR(255) UNIQUE NOT NULL,
    fecha_creacion DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    respuesta_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (autor_id) REFERENCES usuario(id),
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE respuesta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(255) NOT NULL,
    fecha_creacion DATE NOT NULL,
    autor_id BIGINT NOT NULL,
    solucion BOOLEAN NOT NULL,
    topico_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (autor_id) REFERENCES usuario(id),
    FOREIGN KEY (topico_id) REFERENCES topico(id)
);




