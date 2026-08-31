package api.aprovacao.despesas.adapters.inbound.controller;

import api.aprovacao.despesas.application.CriarFuncionarioUseCase;
import api.aprovacao.despesas.domain.funcionario.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
@Tag(name = "Funcionário", description = "Gerenciador de funcionários")
public class FuncionarioController {

    private final FuncionarioRepositoryPort funcionarioRepositoryPort;
    private final CriarFuncionarioUseCase funcionarioUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "cria um novo funcionário que não tenha cadastro")
    public FuncionarioResponseDto criar(@RequestBody FuncionarioRequestDto request) {
        Funcionario funcionario = funcionarioUseCase.criarFuncionario(
                request.nomeFuncionario(),
                request.cargo()
        );

        return new FuncionarioResponseDto(funcionario);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "busca funcionários por ID")
    public FuncionarioResponseDto buscarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioRepositoryPort.buscarPorId(id)
                .orElseThrow(FuncionarioNaoEncontradoException::new);

        return new FuncionarioResponseDto(funcionario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation( summary = "busca todos os funcionários que tem persistência no banco de dados")
    public List<FuncionarioResponseDto> listarTodos() {
        List<Funcionario> funcionarios = funcionarioRepositoryPort.listarTodos();

        return funcionarios
                .stream()
                .map(FuncionarioResponseDto::new).toList();

    }


}
