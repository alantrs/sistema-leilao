package com.lp2.dto.leilao;


import com.lp2.dto.cliente.DadosExibicaoClienteDTO;
import com.lp2.dto.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.dto.entidadeFinanceira.DadosExibicaoEntidadeFinanceiraDTO;
import com.lp2.dto.lance.DadosExibicaoLanceDTO;
import com.lp2.dto.lance.DadosExibicaoLanceProdutoDTO;
import com.lp2.dto.veiculo.DadosExibicaoVeiculoDTO;
import com.lp2.enums.StatusLeilao;
import com.lp2.model.DispositivoInformatica;
import com.lp2.model.Leilao;
import com.lp2.model.Veiculo;
import com.lp2.util.CalculoStatusLeilao;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExportacaoLeilaoDTO {

    private Long id;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private BigDecimal valorMinimo;
    private String local;
    private StatusLeilao statusLeilao;
    private Integer quantidadeProdutos = 0;
    private List<DadosExibicaoDispositivoDTO> dispositivos;
    private List<DadosExibicaoVeiculoDTO> veiculos;
    private List<DadosExibicaoEntidadeFinanceiraDTO> entidades;
    private List<DadosExibicaoClienteDTO> clientesParticipantes;
    private List<DadosExibicaoLanceProdutoDTO> historicoLances;

    public DadosExportacaoLeilaoDTO(){}

    public DadosExportacaoLeilaoDTO(Leilao leilao){
        this.id = leilao.getId();
        this.dataOcorrencia = leilao.getDataOcorrencia();
        this.dataEncerramento = leilao.getDataEncerramento();
        this.dataVisitacao = leilao.getDataVisitacao();
        this.local = leilao.getLocal();
        this.statusLeilao = CalculoStatusLeilao.calcularStatusLeilao(LocalDateTime.now(), leilao);
        List<DadosExibicaoClienteDTO> clientes = new ArrayList<>();
        List<DadosExibicaoLanceProdutoDTO> historico = new ArrayList<>();

        if (!leilao.getDispositivos().isEmpty()) {
            this.dispositivos = leilao.getDispositivos().stream()
                    .sorted(Comparator.comparing(DispositivoInformatica::getNome))
                    .map(dispositivo -> new DadosExibicaoDispositivoDTO(dispositivo))
                    .toList();
            quantidadeProdutos += this.dispositivos.size();
        }

        if (!leilao.getVeiculos().isEmpty()) {
            this.veiculos = leilao.getVeiculos().stream()
                    .sorted(Comparator.comparing(Veiculo::getModelo))
                    .map(veiculo -> new DadosExibicaoVeiculoDTO(veiculo))
                    .toList();
            quantidadeProdutos += this.veiculos.size();
        }

        this.entidades = leilao.getEntidadesFinanceira().stream()
                .map(entidade -> new DadosExibicaoEntidadeFinanceiraDTO(entidade))
                .toList();

        clientes.addAll(
                leilao.getDispositivos().stream()
                        .flatMap(dispositivo -> dispositivo.getLances().stream())
                        .map(lance -> new DadosExibicaoClienteDTO(lance.getCliente()))
                        .toList()
        );

        clientes.addAll(
                leilao.getVeiculos().stream()
                        .flatMap(veiculo -> veiculo.getLances().stream())
                        .map(lance -> new DadosExibicaoClienteDTO(lance.getCliente()))
                        .toList()
        );
        this.clientesParticipantes = clientes;

        historico.addAll(
                leilao.getVeiculos().stream()
                        .map(veiculo -> {
                            DadosExibicaoLanceProdutoDTO dto = new DadosExibicaoLanceProdutoDTO(veiculo.getLances());
                            dto.setProduto(veiculo.getModelo());
                            return dto;
                        })
                        .toList());

        historico.addAll(
                leilao.getDispositivos().stream()
                        .map(dispositivoInformatica -> {
                            DadosExibicaoLanceProdutoDTO dto = new DadosExibicaoLanceProdutoDTO(dispositivoInformatica.getLances());
                            dto.setProduto(dispositivoInformatica.getNome());
                            return dto;
                        })
                        .toList());

        this.historicoLances = historico;
    }
}
