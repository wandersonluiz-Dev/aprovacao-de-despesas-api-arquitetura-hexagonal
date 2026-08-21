package api.aprovacao.despesas.domain.despesa;

public class DespesaNaoEncontradaException extends RuntimeException {
    public DespesaNaoEncontradaException() {
        super("Despesa não encontrada");
    }
}
