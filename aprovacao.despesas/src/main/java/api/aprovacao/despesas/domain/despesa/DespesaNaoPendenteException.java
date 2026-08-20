package api.aprovacao.despesas.domain.despesa;

public class DespesaNaoPendenteException extends RuntimeException {
    public DespesaNaoPendenteException() {
        super("Só é possível aprovar ou rejeitar uma despesa com status PENDENTE");
    }
}
