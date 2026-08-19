package api.aprovacao.despesas.adapters.outboud.entities.repositories;

import api.aprovacao.despesas.adapters.outboud.entities.DespesaJpaEntity;
import api.aprovacao.despesas.domain.despesa.StatusDespesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaJpaRepository extends JpaRepository<DespesaJpaEntity, Long> {

    List<DespesaJpaEntity> findByStatusDespesa(StatusDespesa statusDespesa);
    List<DespesaJpaEntity> findBySolicitanteId(Long funcionarioId);
}
