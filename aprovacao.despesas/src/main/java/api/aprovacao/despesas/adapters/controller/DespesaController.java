package api.aprovacao.despesas.adapters.controller;

import api.aprovacao.despesas.application.AprovarDespesaUseCase;
import api.aprovacao.despesas.application.CriarDespesaUseCase;
import api.aprovacao.despesas.application.RejeitarDespesaUseCase;
import api.aprovacao.despesas.domain.despesa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesa")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaRepositoryPort despesaRepositoryPort;
    private final CriarDespesaUseCase criarDespesaUseCase;
    private final RejeitarDespesaUseCase rejeitarDespesaUseCase;
    private final AprovarDespesaUseCase aprovarDespesaUseCase;

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
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
public DespesaResponseDto aprovar(@PathVariable Long id) {
    Despesa despesa = aprovarDespesaUseCase.aprovar(id);
    return new DespesaResponseDto(despesa);

}

@PutMapping("/{id}/rejeitar")
@ResponseStatus(HttpStatus.OK)
public DespesaResponseDto rejeitar(@PathVariable Long id, @RequestBody RejeitarDespesaDto request) {
    Despesa despesa = rejeitarDespesaUseCase.rejeitar(id, request.motivo());
    return new DespesaResponseDto(despesa);
}

@GetMapping("/pendentes")
@ResponseStatus(HttpStatus.OK)
public List<DespesaResponseDto> listarPendentes() {
    List<Despesa> pendentes = despesaRepositoryPort.listarPendentes();
    return pendentes
            .stream()
            .map(DespesaResponseDto::new)
            .toList();
}

@GetMapping("/funcionario/{funcionarioId}")
@ResponseStatus(HttpStatus.OK)
public List<DespesaResponseDto> listarPorfuncionario(@PathVariable Long funcionarioId) {
    List<Despesa> despesas = despesaRepositoryPort.listarPorFuncionario(funcionarioId);
    return despesas
            .stream()
            .map(DespesaResponseDto::new)
            .toList();

}

@GetMapping
public List<DespesaResponseDto> listarTodas() {
    List<Despesa> despesas = despesaRepositoryPort.listarTodas();
    return despesas
            .stream()
            .map(DespesaResponseDto::new)
            .toList();
}

}
