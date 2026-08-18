package api.aprovacao.despesas.Domain;

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


    public Despesa(Long id, BigDecimal valor, String categoria, String descricao, LocalDate data, StatusDespesa statusDespesa, Funcionario solicitante) {
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.data = data;
        this.statusDespesa = statusDespesa;
        this.solicitante = solicitante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public StatusDespesa getStatusDespesa() {
        return statusDespesa;
    }

    public void setStatusDespesa(StatusDespesa statusDespesa) {
        this.statusDespesa = statusDespesa;
    }

    public Funcionario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Funcionario solicitante) {
        this.solicitante = solicitante;
    }
}
