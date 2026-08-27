package api.aprovacao.despesas.adapters.outboud.entities.repositories;

import api.aprovacao.despesas.adapters.outboud.entities.JpaDespesaEntity;
import api.aprovacao.despesas.domain.despesa.StatusDespesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaJpaRepository extends JpaRepository<JpaDespesaEntity, Long> {

    List<JpaDespesaEntity> findByStatusDespesa(StatusDespesa statusDespesa);
    List<JpaDespesaEntity> findBySolicitanteId(Long funcionarioId);
}
