package api.aprovacao.despesas.domain.funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepositoryPort {

    Funcionario salvar(Funcionario funcionario);
    Optional<Funcionario> buscarPorId(Long id);
    List<Funcionario> listarTodos();
    Boolean existsByNomeFuncionario(String nomeFuncionario);
}
