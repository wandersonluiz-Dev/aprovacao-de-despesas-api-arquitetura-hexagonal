package api.aprovacao.despesas.Domain;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepositoryPort {

    Funcionario salvar(Funcionario funcionario);
    Optional<Funcionario> buscarPorId(Long id);
    List<Funcionario> listarTodos();
}
