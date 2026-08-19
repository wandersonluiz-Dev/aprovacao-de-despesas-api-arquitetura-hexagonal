package api.aprovacao.despesas.adapters.outboud.entities.repositories;

import api.aprovacao.despesas.adapters.outboud.entities.FuncionarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioJpaRepository extends JpaRepository<FuncionarioJpaEntity, Long> {

    Boolean existsByNomeFuncionario(String nomeFuncionario);
}
