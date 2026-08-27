package api.aprovacao.despesas.adapters.outboud.entities.repositories;

import api.aprovacao.despesas.adapters.outboud.entities.JpaFuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioJpaRepository extends JpaRepository<JpaFuncionarioEntity, Long> {

    Boolean existsByNomeFuncionario(String nomeFuncionario);
}
