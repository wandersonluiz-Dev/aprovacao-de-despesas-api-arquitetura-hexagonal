package api.aprovacao.despesas.Infrastructure.Persistence;

import api.aprovacao.despesas.Domain.StatusDespesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaJpaRepository extends JpaRepository<DespesaJpaEntity, Long> {

    List<DespesaJpaEntity> findByStatusDespesa(StatusDespesa statusDespesa);
    List<DespesaJpaEntity> findBySolicitanteId(Long funcionarioId);
}
