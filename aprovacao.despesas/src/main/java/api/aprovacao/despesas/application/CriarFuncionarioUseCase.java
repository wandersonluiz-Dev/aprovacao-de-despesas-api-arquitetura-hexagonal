package api.aprovacao.despesas.application;

import api.aprovacao.despesas.domain.funcionario.Funcionario;
import api.aprovacao.despesas.domain.funcionario.FuncionarioRepositoryPort;
import api.aprovacao.despesas.domain.funcionario.FuncionarioJaCadastradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarFuncionarioUseCase {

    private final FuncionarioRepositoryPort funcionarioRepositoryPort;

    public Funcionario criarFuncionario(String nomeFuncionario, String cargo) {

        if(funcionarioRepositoryPort.existsByNomeFuncionario(nomeFuncionario)) {
            throw new FuncionarioJaCadastradoException();
        }

        Funcionario funcionario = new Funcionario(null, nomeFuncionario, cargo);
        return funcionarioRepositoryPort.salvar(funcionario);
    }
}
