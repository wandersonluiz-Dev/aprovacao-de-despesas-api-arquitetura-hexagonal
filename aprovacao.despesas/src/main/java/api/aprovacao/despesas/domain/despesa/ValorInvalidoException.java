package api.aprovacao.despesas.domain.despesa;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException() {
        super("Valor inválido");
    }
}
