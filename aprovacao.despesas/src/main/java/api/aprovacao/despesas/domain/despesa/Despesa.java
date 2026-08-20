package api.aprovacao.despesas.domain.despesa;

import api.aprovacao.despesas.domain.funcionario.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Despesa {

    private Long id;
    private BigDecimal valor;
    private String categoria;
    private String descricao;
    private LocalDate data;
    private StatusDespesa statusDespesa;
    private Funcionario solicitante;
    private String motivoRejeicao;

    private static final BigDecimal LIMITE_AUTO_APROVACAO = new BigDecimal("500");


    public Despesa(Long id,
                   BigDecimal valor,
                   String categoria,
                   String descricao,
                   LocalDate data,
                   Funcionario solicitante) {

        this.id = id;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.data = data;
        this.solicitante = solicitante;
        this.statusDespesa = valor.compareTo(LIMITE_AUTO_APROVACAO) <= 0
                ? StatusDespesa.APROVADO
                : StatusDespesa.PENDENTE;
        this.motivoRejeicao = null;
    }

    public void aprovar() {
        if (this.statusDespesa != StatusDespesa.PENDENTE) {
            throw new DespesaNaoPendenteException();
        }
    }

    public void rejeitar(String motivo) {
        if (this.statusDespesa != StatusDespesa.PENDENTE) {
            throw new DespesaNaoPendenteException();
        }
        if (motivo == null || motivo.isBlank()) {
            throw new MotivoDaRejeicaoObrigatorioException();
        }
        this.statusDespesa = StatusDespesa.REJEITADO;
        this.motivoRejeicao = motivo;
    }


    public Long getId() {
        return id;
    }


    public BigDecimal getValor() {
        return valor;
    }


    public String getCategoria() {
        return categoria;
    }


    public String getDescricao() {
        return descricao;
    }


    public LocalDate getData() {
        return data;
    }


    public StatusDespesa getStatusDespesa() {
        return statusDespesa;
    }


    public Funcionario getSolicitante() {
        return solicitante;
    }


    public String getMotivoRejeicao() {
        return motivoRejeicao;
    }

}
