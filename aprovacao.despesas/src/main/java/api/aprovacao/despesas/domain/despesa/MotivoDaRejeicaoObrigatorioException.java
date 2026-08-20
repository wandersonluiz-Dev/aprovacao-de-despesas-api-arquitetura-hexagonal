package api.aprovacao.despesas.domain.despesa;

public class MotivoDaRejeicaoObrigatorioException extends RuntimeException {
    public MotivoDaRejeicaoObrigatorioException() {
        super("É necessário informar um motivo para rejeitar a despesa");
    }
}
