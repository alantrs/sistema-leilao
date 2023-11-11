package com.lp2.dto.cliente;

import com.lp2.model.Cliente;
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

    public DadosExibicaoClienteDTO(){}

    public DadosExibicaoClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.documentoCpfCnpj = cliente.getDocumentoCpfCnpj();
    }
}
