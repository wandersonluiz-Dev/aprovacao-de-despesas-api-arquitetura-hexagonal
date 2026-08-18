package api.aprovacao.despesas.Domain;

import java.util.List;
import java.util.Optional;

public interface DespesaRepositoryPort {

    Despesa salvar(Despesa despesa);
    Optional<Despesa> buscarPorId(Long id);
    List<Despesa> listarTodas();
    List<Despesa> listarPendentes();
    List<Despesa> listarPorFuncionario(Long funcionarioId);
}
