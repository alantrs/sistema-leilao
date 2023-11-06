package com.lp2.model;

import com.lp2.dto.veiculo.DadosAtualizacaoVeiculoDTO;
import com.lp2.dto.veiculo.DadosEntradaVeiculoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Veiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generator")
    @TableGenerator(name = "table_generator", table = "id_generator_table", pkColumnName = "sequence_name", valueColumnName = "next_val", allocationSize = 1)
    private Long id;
    private String modelo;
    private String marca;
    private Integer ano;
    private String renavam;
    private String chassi;

    @ManyToOne
    @JoinColumn(name = "id_leilao")
    private Leilao leilao;

    @OneToMany(mappedBy = "veiculo")
    private List<Lance> lances;

    public Veiculo(){}

    public Veiculo(DadosEntradaVeiculoDTO cadastro){
        this.modelo = cadastro.getModelo();
        this.marca = cadastro.getMarca();
        this.ano = cadastro.getAno();
        this.renavam = cadastro.getRenavam();
        this.chassi = cadastro.getChassi();
    }

    public Veiculo(Veiculo veiculo, DadosAtualizacaoVeiculoDTO atualizacao){
        this.id =veiculo.getId();
        this.modelo = atualizacao.getModelo() != null ? atualizacao.getModelo() : veiculo.getModelo();
        this.marca = atualizacao.getMarca() != null ? atualizacao.getMarca() : veiculo.getMarca();
        this.ano = atualizacao.getAno() != null ? atualizacao.getAno() : veiculo.getAno();
        this.renavam = atualizacao.getRenavam() != null ? atualizacao.getRenavam() : veiculo.getRenavam();
        this.chassi = atualizacao.getChassi() != null ? atualizacao.getChassi() : veiculo.getChassi();
    }
}
