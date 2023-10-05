package com.lp2.model.dispositivo;

import com.lp2.model.enums.Condicao;
import com.lp2.model.enums.TipoDispositivo;
import com.lp2.model.lance.Lance;
import com.lp2.model.leilao.Leilao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_leilao")
    private Leilao leilao;

    @OneToMany(mappedBy = "dispositivoInformatica")
    private List<Lance> lances;

    public DispositivoInformatica(){}

    public DispositivoInformatica(DadosEntradaDispositivoDTO cadastro){
        this.nome = cadastro.getNome();
        this.marca = cadastro.getMarca();
        this.ano = cadastro.getAno();
        this.condicao = cadastro.getCondicao();
        this.tipoDispositivo = cadastro.getTipoDispositivo();
        this.quantidade = cadastro.getQuantidade();
    }

    public DispositivoInformatica(DispositivoInformatica dispositivo, DadosAtualizacaoDispositivoDTO atualizacao){
        this.id = dispositivo.getId();
        this.nome = atualizacao.getNome() != null ? atualizacao.getNome() : dispositivo.getNome();
        this.marca = atualizacao.getMarca() != null ? atualizacao.getMarca() : dispositivo.getMarca();
        this.ano = atualizacao.getAno() != null ? atualizacao.getAno() : dispositivo.getAno();
        this.condicao = atualizacao.getCondicao() != null ? atualizacao.getCondicao() : dispositivo.getCondicao();
        this.tipoDispositivo = atualizacao.getTipoDispositivo() != null ? atualizacao.getTipoDispositivo() : dispositivo.getTipoDispositivo();
        this.quantidade = atualizacao.getQuantidade() != null ? atualizacao.getQuantidade() : dispositivo.getQuantidade();
        this.leilao = dispositivo.getLeilao();
    }

}
