package com.lp2.dto.dispositivo;

import com.lp2.model.DispositivoInformatica;
import com.lp2.enums.Condicao;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoDispositivoDTO {

    private Long id;
    private String nome;
    private String marca;
    private Integer ano;
    private Condicao condicao;
    private Integer quantidade;

    public DadosExibicaoDispositivoDTO(){}
    public DadosExibicaoDispositivoDTO(DispositivoInformatica dispositivoInformatica){
        this.id = dispositivoInformatica.getId();
        this.nome = dispositivoInformatica.getNome();
        this.marca = dispositivoInformatica.getMarca();
        this.ano = dispositivoInformatica.getAno();
        this.condicao = dispositivoInformatica.getCondicao();
        this.quantidade = dispositivoInformatica.getQuantidade();
    }

}

