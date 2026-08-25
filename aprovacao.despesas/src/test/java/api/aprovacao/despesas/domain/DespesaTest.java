package api.aprovacao.despesas.domain;

import api.aprovacao.despesas.domain.despesa.Despesa;
import api.aprovacao.despesas.domain.despesa.DespesaNaoPendenteException;
import api.aprovacao.despesas.domain.despesa.MotivoDaRejeicaoObrigatorioException;
import api.aprovacao.despesas.domain.despesa.StatusDespesa;
import api.aprovacao.despesas.domain.funcionario.Funcionario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DespesaTest {

    private final Funcionario solicitante = new Funcionario(1L, "luiz", "gerente");

    @Test
    void deveNascerAprovadaQuandoOValorForMenorQue500() {

        Despesa despesa = new Despesa(1L, BigDecimal.valueOf(300), "transporte", "99", LocalDate.now(), solicitante);

        assertEquals(StatusDespesa.APROVADO, despesa.getStatusDespesa());

    }

    @Test
    void deveNascerPendenteQuandoOValorForMaiorQue500() {

        Despesa despesa = new Despesa(1L, BigDecimal.valueOf(800), "equipamento", "monitor", LocalDate.now(), solicitante);

        assertEquals(StatusDespesa.PENDENTE, despesa.getStatusDespesa());

    }

    @Test
    void aprovarDespesaPendente() {

        Despesa despesa = new Despesa(1L, BigDecimal.valueOf(800), "equipamento", "monitor", LocalDate.now(), solicitante);

        despesa.aprovar();

        assertEquals(StatusDespesa.APROVADO, despesa.getStatusDespesa());

    }

    @Test
    void naoDeveAprovarDespesaJaAprovada() {

        Despesa despesa = new Despesa(1L, BigDecimal.valueOf(300), "transporte", "99", LocalDate.now(), solicitante);

        assertThrows(DespesaNaoPendenteException.class, despesa::aprovar);

    }

    @Test
    void deveRejeitarDespesaComMotivo() {

        Despesa despesa = new Despesa(1L, BigDecimal.valueOf(800), "equipamento", "monitor", LocalDate.now(), solicitante);

        despesa.rejeitar("monitor com defeito");

        assertEquals(StatusDespesa.REJEITADO, despesa.getStatusDespesa());
        assertEquals("monitor com defeito", despesa.getMotivoRejeicao());
    }

    @Test
    void naoDeveRejeitaSemMotivo() {

        Despesa despesa = new Despesa(1L, BigDecimal.valueOf(800), "equipamento", "monitor", LocalDate.now(), solicitante);

        assertThrows(MotivoDaRejeicaoObrigatorioException.class, ()-> despesa.rejeitar(""));
    }

    @Test
    void naoDeveRejeitarDespeseJaRejeitada() {

        Despesa despesa = new Despesa(1L, BigDecimal.valueOf(800), "equipamento", "monitor", LocalDate.now(), solicitante);

        despesa.rejeitar("monitor com defeito");

        assertThrows(DespesaNaoPendenteException.class,()-> despesa.rejeitar("monitor não liga"));

    }
}
