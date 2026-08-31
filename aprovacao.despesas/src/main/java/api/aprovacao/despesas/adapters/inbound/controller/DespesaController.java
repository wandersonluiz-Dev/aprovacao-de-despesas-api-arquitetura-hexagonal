package api.aprovacao.despesas.adapters.inbound.controller;

import api.aprovacao.despesas.application.AprovarDespesaUseCase;
import api.aprovacao.despesas.application.CriarDespesaUseCase;
import api.aprovacao.despesas.application.RejeitarDespesaUseCase;
import api.aprovacao.despesas.domain.despesa.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesa")
@RequiredArgsConstructor
@Tag(name = "Despesa", description = "Gerenciador de despesas")
public class DespesaController {

    private final DespesaRepositoryPort despesaRepositoryPort;
    private final CriarDespesaUseCase criarDespesaUseCase;
    private final RejeitarDespesaUseCase rejeitarDespesaUseCase;
    private final AprovarDespesaUseCase aprovarDespesaUseCase;

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
@Operation(summary = "cria uma nova despesa informando o ID do solicitante")
public DespesaResponseDto criar(@RequestBody DespesaRequestDto request) {
    Despesa despesa = criarDespesaUseCase.criarDespesa(
            request.valor(),
            request.categoria(),
            request.descricao(),
            request.data(),
            request.solicitante()
    );

    return new DespesaResponseDto(despesa);
}

@PutMapping("/{id}/aprovar")
@ResponseStatus(HttpStatus.OK)
@Operation(summary = "aprova despesas ainda pendentes")
public DespesaResponseDto aprovar(@PathVariable Long id) {
    Despesa despesa = aprovarDespesaUseCase.aprovar(id);
    return new DespesaResponseDto(despesa);

}

@PutMapping("/{id}/rejeitar")
@ResponseStatus(HttpStatus.OK)
@Operation(summary = "rejeita uma despesa informando o motivo da rejeição")
public DespesaResponseDto rejeitar(@PathVariable Long id, @RequestBody RejeitarDespesaDto request) {
    Despesa despesa = rejeitarDespesaUseCase.rejeitar(id, request.motivo());
    return new DespesaResponseDto(despesa);
}

@GetMapping("/pendentes")
@ResponseStatus(HttpStatus.OK)
@Operation(summary = "lista todas as despesas com status PENDENTE")
public List<DespesaResponseDto> listarPendentes() {
    List<Despesa> pendentes = despesaRepositoryPort.listarPendentes();
    return pendentes
            .stream()
            .map(DespesaResponseDto::new)
            .toList();
}

@GetMapping("/funcionario/{funcionarioId}")
@ResponseStatus(HttpStatus.OK)
@Operation(summary = "lista todas as despesas de um funcionário")
public List<DespesaResponseDto> listarPorfuncionario(@PathVariable Long funcionarioId) {
    List<Despesa> despesas = despesaRepositoryPort.listarPorFuncionario(funcionarioId);
    return despesas
            .stream()
            .map(DespesaResponseDto::new)
            .toList();

}

@GetMapping
@Operation(summary = "lista todas as despesas")
public List<DespesaResponseDto> listarTodas() {
    List<Despesa> despesas = despesaRepositoryPort.listarTodas();
    return despesas
            .stream()
            .map(DespesaResponseDto::new)
            .toList();
}

}
