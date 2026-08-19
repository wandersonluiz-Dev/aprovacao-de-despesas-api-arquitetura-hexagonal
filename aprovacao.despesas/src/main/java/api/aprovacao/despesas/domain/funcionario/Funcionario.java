package api.aprovacao.despesas.domain.funcionario;

public class Funcionario {

    private Long id;
    private String nomeFuncionario;
    private String cargo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nome) {
        this.nomeFuncionario = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Funcionario(Long id, String nomeFuncionario, String cargo) {
        this.nomeFuncionario = nomeFuncionario;
        this.cargo = cargo;



    }
}
