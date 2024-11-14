import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Dono extends Pessoa{
    String senhaDeAcesso;

    ArrayList<FuncionariosDaLoja> funcionariosDaLoja = new ArrayList<>();
    ArrayList<Vendedor> vendedores = new ArrayList<>();

    public Dono(String nome, String sexo, int idade, String cpf, String senhaDeAcesso) {
        super(nome, sexo, idade, cpf);
        this.senhaDeAcesso = "1234AQWS";
    }

}
