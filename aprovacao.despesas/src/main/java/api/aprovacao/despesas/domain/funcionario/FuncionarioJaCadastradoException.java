package api.aprovacao.despesas.domain.funcionario;

public class FuncionarioJaCadastradoException extends RuntimeException {
    public FuncionarioJaCadastradoException() {
        super("Funcionário já cadastrado");
    }
}
