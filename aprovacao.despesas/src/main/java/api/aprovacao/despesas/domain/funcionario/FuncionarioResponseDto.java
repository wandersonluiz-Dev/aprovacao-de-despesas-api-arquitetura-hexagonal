package api.aprovacao.despesas.domain.funcionario;

public record FuncionarioResponseDto(Long id,
                                     String nomeFuncionario,
                                     String cargo) {

    public FuncionarioResponseDto(Funcionario funcionario) {

        this(
                funcionario.getId(),
                funcionario.getNomeFuncionario(),
                funcionario.getCargo()
        );
    }
}
