CREATE TABLE paciente (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          run VARCHAR(13) NOT NULL,
                          nombres VARCHAR(255) NOT NULL,
                          apellidos VARCHAR(255) NOT NULL,
                          fecha_nacimiento DATETIME NULL,
                          correo VARCHAR(255) NOT NULL,
                          PRIMARY KEY (id),
                          CONSTRAINT UQ_paciente_run UNIQUE (run)
)