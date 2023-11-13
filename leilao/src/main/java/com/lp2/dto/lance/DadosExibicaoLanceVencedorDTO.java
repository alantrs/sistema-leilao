package com.lp2.dto.lance;

import com.lp2.dto.cliente.DadosExibicaoClienteDTO;
import com.lp2.model.Cliente;
import com.lp2.model.Lance;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoLanceVencedorDTO {

    private Long id;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private DadosExibicaoClienteDTO cliente;
    private Object produto;

    public DadosExibicaoLanceVencedorDTO(){}

    public DadosExibicaoLanceVencedorDTO(Lance lance){
        this.id = lance.getId();
        this.valor = lance.getValor();
        this.dataHora = lance.getDataHora();
        this.cliente = new DadosExibicaoClienteDTO(lance.getCliente());
    }
}
