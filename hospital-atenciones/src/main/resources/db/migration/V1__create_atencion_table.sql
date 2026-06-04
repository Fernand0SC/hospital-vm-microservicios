CREATE TABLE atencion (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          fecha_atencion DATE NOT NULL,
                          hora_atencion TIME NOT NULL,
                          costo DECIMAL(10,2) NOT NULL,
                          comentario VARCHAR(255) NULL,
                          id_paciente BIGINT NOT NULL, -- Guardamos el ID del paciente como un número común, sin FK real
                          id_medico BIGINT NOT NULL,  -- Guardamos el ID del medico
                          PRIMARY KEY (id)
);