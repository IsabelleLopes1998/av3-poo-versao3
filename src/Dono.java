import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Dono extends Pessoa{
    ArrayList<FuncionariosDaLoja> funcionariosDaLoja = new ArrayList<>();
    ArrayList<Vendedor> vendedores = new ArrayList<>();

    public Dono(String nome, String sexo, int idade, String cpf) {
        super(nome, sexo, idade, cpf);
    }

}
