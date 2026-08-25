package api.aprovacao.despesas.adapters.inbound.controller.exception;

import api.aprovacao.despesas.domain.despesa.DespesaNaoEncontradaException;
import api.aprovacao.despesas.domain.despesa.DespesaNaoPendenteException;
import api.aprovacao.despesas.domain.despesa.MotivoDaRejeicaoObrigatorioException;
import api.aprovacao.despesas.domain.despesa.ValorInvalidoException;
import api.aprovacao.despesas.domain.funcionario.FuncionarioJaCadastradoException;
import api.aprovacao.despesas.domain.funcionario.FuncionarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({FuncionarioNaoEncontradoException.class, DespesaNaoEncontradaException.class})

    public ResponseEntity<ErroResponse> handlerNotFound(RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErroResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));

    }

    @ExceptionHandler({FuncionarioJaCadastradoException.class, DespesaNaoPendenteException.class,
            MotivoDaRejeicaoObrigatorioException.class})

    public ResponseEntity<ErroResponse> handlerComflict(RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErroResponse(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler({ValorInvalidoException.class})

    public ResponseEntity<ErroResponse> handlerBadRequest(RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErroResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}
