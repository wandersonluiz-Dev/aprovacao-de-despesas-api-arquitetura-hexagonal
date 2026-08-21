package api.aprovacao.despesas.application;

import api.aprovacao.despesas.domain.despesa.Despesa;
import api.aprovacao.despesas.domain.despesa.DespesaNaoEncontradaException;
import api.aprovacao.despesas.domain.despesa.DespesaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejeitarDespesaUseCase {

    private final DespesaRepositoryPort despesaRepositoryPort;

    public Despesa rejeitar(Long despesaId, String motivo) {

        Despesa despesa = despesaRepositoryPort.buscarPorId(despesaId)
                .orElseThrow(DespesaNaoEncontradaException::new);

        despesa.rejeitar(motivo);

        return despesaRepositoryPort.salvar(despesa);

    }


}
