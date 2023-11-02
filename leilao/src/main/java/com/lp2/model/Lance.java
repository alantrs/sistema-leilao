package com.lp2.model;

import com.lp2.dto.lance.DadosEntradaLanceDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Lance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

//    @ManyToOne
//    @JoinColumn(name = "id_dispositivo")
//    private DispositivoInformatica dispositivoInformatica;
    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    public Lance(){}
    public Lance(DadosEntradaLanceDTO lance){
        this.dataHora = LocalDateTime.now();
        this.valor = lance.getValor();
    }
}
