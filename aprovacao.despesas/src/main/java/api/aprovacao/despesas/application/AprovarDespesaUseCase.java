package api.aprovacao.despesas.application;

import api.aprovacao.despesas.domain.despesa.Despesa;
import api.aprovacao.despesas.domain.despesa.DespesaNaoEncontradaException;
import api.aprovacao.despesas.domain.despesa.DespesaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AprovarDespesaUseCase {

    private final DespesaRepositoryPort despesaRepositoryPort;

    public Despesa aprovar(Long despesaId) {

        Despesa despesa = despesaRepositoryPort.buscarPorId(despesaId)
                .orElseThrow(DespesaNaoEncontradaException::new);

        despesa.aprovar();

        return despesaRepositoryPort.salvar(despesa);
    }
}