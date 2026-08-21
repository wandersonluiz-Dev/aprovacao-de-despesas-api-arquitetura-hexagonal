package api.aprovacao.despesas.domain.despesa;

public class DespesaNaoEncontraException extends RuntimeException {
    public DespesaNaoEncontraException() {
        super("Despesa não encontrada");
    }
}
