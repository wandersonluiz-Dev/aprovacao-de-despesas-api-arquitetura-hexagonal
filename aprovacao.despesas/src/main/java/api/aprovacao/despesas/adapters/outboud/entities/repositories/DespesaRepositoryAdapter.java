package api.aprovacao.despesas.adapters.outboud.entities.repositories;

import api.aprovacao.despesas.adapters.outboud.entities.JpaDespesaEntity;
import api.aprovacao.despesas.adapters.outboud.entities.JpaFuncionarioEntity;
import api.aprovacao.despesas.domain.despesa.Despesa;
import api.aprovacao.despesas.domain.despesa.DespesaRepositoryPort;
import api.aprovacao.despesas.domain.funcionario.Funcionario;
import api.aprovacao.despesas.domain.despesa.StatusDespesa;
import api.aprovacao.despesas.domain.funcionario.FuncionarioNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DespesaRepositoryAdapter implements DespesaRepositoryPort {

    private final DespesaJpaRepository jpaRepository;
    private final FuncionarioJpaRepository funcionarioJpaRepository;

    @Override
    public Despesa salvar(Despesa despesa) {

        JpaDespesaEntity entity = paraJpaEntity(despesa);
        JpaDespesaEntity salva = jpaRepository.save(entity);

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


    private JpaDespesaEntity paraJpaEntity(Despesa despesa) {

        JpaDespesaEntity entity = new JpaDespesaEntity();
        entity.setId(despesa.getId());
        entity.setValor(despesa.getValor());
        entity.setCategoria(despesa.getCategoria());
        entity.setDescricao(despesa.getDescricao());
        entity.setData(despesa.getData());
        entity.setStatusDespesa(despesa.getStatusDespesa());

        JpaFuncionarioEntity solicitante = funcionarioJpaRepository.findById(despesa.getSolicitante().getId())
                .orElseThrow(FuncionarioNaoEncontradoException::new);
        entity.setSolicitante(solicitante);

        return entity;
    }

    private Despesa paraDomain(JpaDespesaEntity entity) {

        Funcionario solicitante = paraFuncionarioDomain(entity.getSolicitante());

        Despesa despesa = new Despesa(
                entity.getId(),
                entity.getValor(),
                entity.getCategoria(),
                entity.getDescricao(),
                entity.getData(),
                solicitante
        );

        despesa.restaurarStatus(entity.getStatusDespesa(), entity.getMotivoRejeicao());

        return despesa;
    }

    private Funcionario paraFuncionarioDomain(JpaFuncionarioEntity entity) {

        return new Funcionario(
                entity.getId(),
                entity.getNomeFuncionario(),
                entity.getCargo()
        );

    }

}
