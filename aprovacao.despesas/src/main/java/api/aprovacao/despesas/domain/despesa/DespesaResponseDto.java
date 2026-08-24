package api.aprovacao.despesas.domain.despesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaResponseDto(Long id,
                                 BigDecimal valor,
                                 String categoria,
                                 String descricao,
                                 LocalDate data,
                                 StatusDespesa statusDespesa,
                                 String solicitante,
                                 String motivoRejeicao) {

    public DespesaResponseDto(Despesa despesa) {
        this(
                despesa.getId(),
                despesa.getValor(),
                despesa.getCategoria(),
                despesa.getDescricao(),
                despesa.getData(),
                despesa.getStatusDespesa(),
                despesa.getSolicitante().getNomeFuncionario(),
                despesa.getMotivoRejeicao()
        );

    }

}
