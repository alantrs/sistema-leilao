package com.lp2.model.cliente;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoClienteDTO {

    private Long id;
    private String nome;
    private String documentoCpfCnpj;
}
