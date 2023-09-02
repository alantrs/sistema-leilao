package com.lp2.dominio;

import com.lp2.dto.DadosEntradaDispositivo;
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
@Introspected
public class DispositivoInformatica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private TipoDispositivo tipoDispositivo;

    public DispositivoInformatica(){}

    public DispositivoInformatica(DadosEntradaDispositivo cadastro){
        this.nome = cadastro.getNome();
        this.descricao = cadastro.getDescricao();
        this.tipoDispositivo = cadastro.getTipoDispositivo();
    }

    public DispositivoInformatica(DispositivoInformatica dispositivo, DadosEntradaDispositivo atualizacao){
        this.id = dispositivo.getId();
        this.nome = atualizacao.getNome() != null ? atualizacao.getNome() : dispositivo.getNome();
        this.descricao = atualizacao.getDescricao() != null ? atualizacao.getDescricao() : dispositivo.getDescricao();
        this.tipoDispositivo = atualizacao.getTipoDispositivo() != null ? atualizacao.getTipoDispositivo() : dispositivo.getTipoDispositivo();
    }

}
