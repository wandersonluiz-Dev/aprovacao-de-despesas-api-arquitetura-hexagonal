package api.aprovacao.despesas.Infrastructure.Persistence;

import api.aprovacao.despesas.Domain.Funcionario;
import api.aprovacao.despesas.Domain.FuncionarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FuncionarioRepositoryAdapter implements FuncionarioRepositoryPort {

    private final FuncionarioJpaRepository jpaRepository;

    @Override
    public Funcionario salvar(Funcionario funcionario) {

        FuncionarioJpaEntity entity = paraJpaEntity(funcionario);
        FuncionarioJpaEntity salva = jpaRepository.save(entity);
        return paraDomain(salva);
    }

    @Override
    public Optional<Funcionario> buscarPorId(Long id) {
        return jpaRepository.findById(id).
                map(this::paraDomain);
    }

    @Override
    public List<Funcionario> listarTodos() {
        return jpaRepository.findAll().
                stream().
                map(this::paraDomain).
                toList();
    }

    private FuncionarioJpaEntity paraJpaEntity(Funcionario funcionario) {

        FuncionarioJpaEntity entity = new FuncionarioJpaEntity();
        entity.setId(funcionario.getId());
        entity.setNomeFuncionario(funcionario.getNomeFuncionario());
        entity.setCargo(funcionario.getCargo());

        return entity;
    }

    private Funcionario paraDomain(FuncionarioJpaEntity entity) {

        return new Funcionario(
                entity.getId(),
                entity.getNomeFuncionario(),
                entity.getCargo()
        );
    }
}
