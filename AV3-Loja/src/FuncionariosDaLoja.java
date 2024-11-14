import java.util.Scanner;

public class FuncionariosDaLoja extends Funcionario {

    public FuncionariosDaLoja(String nome, String sexo, int idade, String cpf, String cargo, float salario) throws IllegalArgumentException {
        super(nome, sexo, idade, cpf, cargo, salario);
    }



    @Override
    public String toString() {
        return "Funcionarios da Loja: " +
                "nome: " + nome +
                ", cargo: " + cargo +
                ", salario: " + salario +
                ", sexo: " + sexo +
                ", idade:"  + idade +
                ", cpf: " + cpf;
    }
}
