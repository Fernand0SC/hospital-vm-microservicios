CREATE TABLE medico (
                    id BIGINT NOT NULL AUTO_INCREMENT,
                    run VARCHAR(13) NOT NULL,
                    nombres VARCHAR(255) NOT NULL,
                    apellidos VARCHAR(255) NOT NULL,
                    especialidad VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL,
                    PRIMARY KEY (id),
                    CONSTRAINT UQ_paciente_run UNIQUE (run)
)