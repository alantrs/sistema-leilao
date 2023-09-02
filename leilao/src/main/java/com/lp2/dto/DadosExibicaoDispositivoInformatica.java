package com.lp2.dto;

import com.lp2.dominio.DispositivoInformatica;
import com.lp2.dominio.TipoDispositivo;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Serializable
@Introspected
public class DadosExibicaoDispositivoInformatica {

    private Long id;
    private String nome;
    private String descricao;
    private TipoDispositivo tipoDispositivo;

    public DadosExibicaoDispositivoInformatica(DispositivoInformatica dispositivoInformatica) {
        this.id = dispositivoInformatica.getId();
        this.nome = dispositivoInformatica.getNome();
        this.descricao = dispositivoInformatica.getDescricao();
        this.tipoDispositivo = dispositivoInformatica.getTipoDispositivo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoDispositivo getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(TipoDispositivo tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }
}

