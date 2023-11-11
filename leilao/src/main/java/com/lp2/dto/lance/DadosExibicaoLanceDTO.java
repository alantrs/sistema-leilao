package com.lp2.dto.lance;

import com.lp2.model.Lance;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoLanceDTO {

    private Long id;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String cliente;

    public DadosExibicaoLanceDTO(){}

    public DadosExibicaoLanceDTO(Lance lance){
        this.id = lance.getId();
        this.valor = lance.getValor();
        this.dataHora = lance.getDataHora();
        this.cliente = lance.getCliente().getNome();
    }


}
