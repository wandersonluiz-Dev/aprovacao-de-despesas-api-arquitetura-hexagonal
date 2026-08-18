package api.aprovacao.despesas.Infrastructure.Persistence;

import api.aprovacao.despesas.Domain.Despesa;
import api.aprovacao.despesas.Domain.DespesaRepositoryPort;
import api.aprovacao.despesas.Domain.Funcionario;
import api.aprovacao.despesas.Domain.StatusDespesa;
import api.aprovacao.despesas.Exception.FuncionarioNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DespesaRepsitoryAdapter implements DespesaRepositoryPort {

    private final DespesaJpaRepository jpaRepository;
    private final FuncionarioJpaRepository funcionarioJpaRepository;

    @Override
    public Despesa salvar(Despesa despesa) {

        DespesaJpaEntity entity = paraJpaEntity(despesa);
        DespesaJpaEntity salva = jpaRepository.save(entity);

        return paraDomain(salva);
    }

    @Override
    public Optional<Despesa> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(this::paraDomain);
    }

    @Override
    public List<Despesa> listarTodas() {
        return jpaRepository.findAll().
                stream().
                map(this::paraDomain).
                toList();
    }

    @Override
    public List<Despesa> listarPendentes() {
        return jpaRepository.findByStatusDespesa(StatusDespesa.PENDENTE).
                stream().
                map(this::paraDomain).
                toList();

    }

    @Override
    public List<Despesa> listarPorFuncionario(Long funcionarioId) {
        return jpaRepository.findBySolicitanteId(funcionarioId).stream().map(this::paraDomain).toList();
    }


    private DespesaJpaEntity paraJpaEntity(Despesa despesa) {

        DespesaJpaEntity entity = new DespesaJpaEntity();
        entity.setId(despesa.getId());
        entity.setValor(despesa.getValor());
        entity.setCategoria(despesa.getCategoria());
        entity.setDescricao(despesa.getDescricao());
        entity.setData(despesa.getData());
        entity.setStatusDespesa(despesa.getStatusDespesa());

        FuncionarioJpaEntity solicitante = funcionarioJpaRepository.findById(despesa.getSolicitante().getId())
                .orElseThrow(FuncionarioNaoEncontradoException::new);
        entity.setSolicitante(solicitante);

        return entity;
    }

    private Despesa paraDomain(DespesaJpaEntity entity) {

        Funcionario solicitante = paraFuncionarioDomain(entity.getSolicitante());

        return new Despesa(
                entity.getId(),
                entity.getValor(),
                entity.getCategoria(),
                entity.getDescricao(),
                entity.getData(),
                entity.getStatusDespesa(),
                solicitante,
                entity.getMotivoRejeicao()
        );
    }

    private Funcionario paraFuncionarioDomain(FuncionarioJpaEntity entity) {

        return new Funcionario(
                entity.getId(),
                entity.getNomeFuncionario(),
                entity.getCargo()
        );

    }

}
