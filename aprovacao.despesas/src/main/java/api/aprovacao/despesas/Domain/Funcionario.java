package api.aprovacao.despesas.Domain;

public class Funcionario {

    private String nomeFuncionario;
    private String cargo;

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

    public Funcionario(String nomeFuncionario, String cargo) {
        this.nomeFuncionario = nomeFuncionario;
        this.cargo = cargo;



    }
}
