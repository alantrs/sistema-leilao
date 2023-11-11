package com.lp2.model;

import com.lp2.dto.dispositivo.*;
import com.lp2.enums.Condicao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DispositivoInformatica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generator")
    @TableGenerator(name = "table_generator", table = "id_generator_table", pkColumnName = "sequence_name", valueColumnName = "next_val", allocationSize = 1)
    private Long id;
    private String nome;
    private String marca;
    private Integer ano;
    private Condicao condicao;
    private Integer quantidade;
    private BigDecimal valorInicial;

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
        this.quantidade = cadastro.getQuantidade();
        this.valorInicial = cadastro.getValorInicial();
    }

    public DispositivoInformatica(DispositivoInformatica dispositivo, DadosAtualizacaoDispositivoDTO atualizacao){
        this.id = dispositivo.getId();
        this.nome = atualizacao.getNome() != null ? atualizacao.getNome() : dispositivo.getNome();
        this.marca = atualizacao.getMarca() != null ? atualizacao.getMarca() : dispositivo.getMarca();
        this.ano = atualizacao.getAno() != null ? atualizacao.getAno() : dispositivo.getAno();
        this.condicao = atualizacao.getCondicao() != null ? atualizacao.getCondicao() : dispositivo.getCondicao();
        this.quantidade = atualizacao.getQuantidade() != null ? atualizacao.getQuantidade() : dispositivo.getQuantidade();
        this.valorInicial = atualizacao.getValorInicial() != null ? atualizacao.getValorInicial() : dispositivo.getValorInicial();
        this.leilao = dispositivo.getLeilao();
    }

}
