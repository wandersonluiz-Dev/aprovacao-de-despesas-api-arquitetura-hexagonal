package api.aprovacao.despesas.application;

import api.aprovacao.despesas.domain.despesa.Despesa;
import api.aprovacao.despesas.domain.despesa.DespesaRepositoryPort;
import api.aprovacao.despesas.domain.funcionario.Funcionario;
import api.aprovacao.despesas.domain.funcionario.FuncionarioRepositoryPort;
import api.aprovacao.despesas.domain.funcionario.FuncionarioNaoEncontradoException;
import api.aprovacao.despesas.domain.despesa.ValorInvalidoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CriarDespesaUseCase {

    private final DespesaRepositoryPort despesaRepositoryPort;
    private final FuncionarioRepositoryPort funcionarioRepositoryPort;

    public Despesa criarDespesa(BigDecimal valor,
                                String categoria,
                                String descricao,
                                LocalDate data,
                                Long solicitanteId) {

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException();
        }

        Funcionario solicitante = funcionarioRepositoryPort.buscarPorId(solicitanteId)
                .orElseThrow(FuncionarioNaoEncontradoException::new);


        Despesa despesa = new Despesa(null, valor, categoria, descricao, data, solicitante);
        return despesaRepositoryPort.salvar(despesa);

    }



}
