package com.lp2.dominio;

import com.lp2.dominio.dto.dispositivo.DadosEntradaDispositivo;
import com.lp2.dominio.enums.Condicao;
import com.lp2.dominio.enums.TipoDispositivo;
import io.micronaut.core.annotation.Introspected;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DispositivoInformatica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String marca;
    private Integer ano;
    private Condicao condicao;
    private TipoDispositivo tipoDispositivo;

    public DispositivoInformatica(){}

    public DispositivoInformatica(DadosEntradaDispositivo cadastro){
        this.nome = cadastro.getNome();
        this.marca = cadastro.getMarca();
        this.ano = cadastro.getAno();
        this.condicao = cadastro.getCondicao();
        this.tipoDispositivo = cadastro.getTipoDispositivo();
    }

    public DispositivoInformatica(DispositivoInformatica dispositivo, DadosEntradaDispositivo atualizacao){
        this.id = dispositivo.getId();
        this.nome = atualizacao.getNome() != null ? atualizacao.getNome() : dispositivo.getNome();
        this.marca = atualizacao.getMarca() != null ? atualizacao.getMarca() : dispositivo.getMarca();
        this.ano = atualizacao.getAno() != null ? atualizacao.getAno() : dispositivo.getAno();
        this.condicao = atualizacao.getCondicao() != null ? atualizacao.getCondicao() : dispositivo.getCondicao();
        this.tipoDispositivo = atualizacao.getTipoDispositivo() != null ? atualizacao.getTipoDispositivo() : dispositivo.getTipoDispositivo();
    }

}
