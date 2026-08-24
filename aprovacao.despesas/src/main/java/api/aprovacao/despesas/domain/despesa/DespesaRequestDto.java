package api.aprovacao.despesas.domain.despesa;


import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaRequestDto(BigDecimal valor,
                                String categoria,
                                String descricao,
                                LocalDate data,
                                Long solicitante) {
}
