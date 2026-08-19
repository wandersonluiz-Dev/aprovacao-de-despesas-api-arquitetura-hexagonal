package api.aprovacao.despesas.domain.funcionario;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException() {
        super("Funcionário não encontrado");
    }
}
