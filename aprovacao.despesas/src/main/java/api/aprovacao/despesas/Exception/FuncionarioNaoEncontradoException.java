package api.aprovacao.despesas.Exception;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException() {
        super("FUncionário não encontrado");
    }
}
