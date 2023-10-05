package com.lp2.model.lance;

import com.lp2.model.cliente.Cliente;
import com.lp2.model.dispositivo.DispositivoInformatica;
import com.lp2.model.leilao.Leilao;
import com.lp2.model.veiculo.Veiculo;
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

    @ManyToOne
    @JoinColumn(name = "id_dispositivo")
    private DispositivoInformatica dispositivoInformatica;
    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    public Lance(){}
    public Lance(DadosEntradaLanceDTO lance){
        this.dataHora = LocalDateTime.now();
        this.valor = lance.getValor();
    }
}
