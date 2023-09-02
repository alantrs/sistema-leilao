package com.lp2.dominio;

import com.lp2.dto.DadosCadastroDispositivoInformatica;
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

    public DispositivoInformatica(DadosCadastroDispositivoInformatica cadastro){
        this.nome = cadastro.getNome();
        this.descricao = cadastro.getDescricao();
        this.tipoDispositivo = cadastro.getTipoDispositivo();
    }

}
