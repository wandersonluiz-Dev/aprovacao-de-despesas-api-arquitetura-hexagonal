package api.aprovacao.despesas.adapters.inbound.controller;

import api.aprovacao.despesas.application.CriarFuncionarioUseCase;
import api.aprovacao.despesas.domain.funcionario.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioRepositoryPort funcionarioRepositoryPort;
    private final CriarFuncionarioUseCase funcionarioUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioResponseDto criar(@RequestBody FuncionarioRequestDto request) {
        Funcionario funcionario = funcionarioUseCase.criarFuncionario(
                request.nomeFuncionario(),
                request.cargo()
        );

        return new FuncionarioResponseDto(funcionario);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FuncionarioResponseDto buscarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioRepositoryPort.buscarPorId(id)
                .orElseThrow(FuncionarioNaoEncontradoException::new);

        return new FuncionarioResponseDto(funcionario);
    }


}
