import java.util.ArrayList;
import java.util.Scanner;

public class LojaJoias implements FuncionarioService, ProdutoService{
    Dono dono = new Dono("Dono","M",40,"111");
    ArrayList<FuncionariosDaLoja> funcionariosDaLoja = new ArrayList<>();
    ArrayList<ProdutosDaLoja> produtos = new ArrayList<>();
    ArrayList<Vendedor> vendedores = new ArrayList<>();


    public void acoesDonoDaLoja(){
        Scanner res = new Scanner(System.in);
        boolean executando = true;
        while (executando) {
            System.out.println("\nDigite a ação que deseja realizar:" +
                    "\n[1]-Cadastrar funcionario " +
                    "\n[2]-Excluir um funcionario do sistema " +
                    "\n[3]-Calcular Bonus do vendedor " +
                    "\n[4]-Lista de funcionários " +
                    "\n[5]-Voltar");

            int resposta = res.nextInt();

            switch (resposta) {
                case 1:
                    cadastrarNovoFuncionario();
                    break;
                case 2:
                    exluirFuncionario();
                    break;
                case 3:
                    mostrarCalculoBonus();
                    break;
                case 4:
                    mostrarFuncionariosDaLoja();
                    break;
                case 5:
                    executando = false;
                    break;
                default:
                    System.out.println("Resposta inválida");
            }
        }
    }
    public void acoesFuncionario(){
        Scanner res = new Scanner(System.in);
        boolean executando = true;
        while (executando) {
            System.out.println("\nQual ação você deseja fazer?" +
                    "\n[1]-bater o ponto de entrada." +
                    "\n[2]-bater ponto de saída." +
                    "\n[3]-Ações sobre o pronduto." +
                    "\n[4]-Ações sobre o cliente" +
                    "\n[5]-Voltar");
            int resposta = res.nextInt();

            switch (resposta){
                case 1:
                    baterPontoEntrada();
                    break;
                case 2:
                    baterPontoSaida();
                case 3:
                    acoesSobreProduto();
                    break;
                case 4:
                    //acoesSobreCliente();
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    executando = false;
                default:
                    System.out.println("Resposta inválida");
            }
        }
    }

    public void iniciarSistema() {
        System.out.println("Deseja iniciar o sistema? \n[1]-Sim \n[2]-Não");
        Scanner res = new Scanner(System.in);
        int r = res.nextInt();
        res.nextLine();

        if (r == 1) {
            System.out.println("\n-------------------------Iniciando o sistema------------------------\n" +
                    "\n--------------------------------------------------------------------" +
                    "\n--------------------------------------------------------------------" +
                    "\n--------------------------------------------------------------------" +
                    "\n--------------------------------------------------------------------" +
                    "\n--------------------------------------------------------------------" +
                    "\n--------------------------------------------------------------------\n");
            //donoDaloja.criarFuncionario();
            //criarProduto();
            iniciarOperacoes();

        } else {
            System.out.println("\n-------------------Inicialização do sistema cancelada-------------------\n");
        }
    }

    private void iniciarOperacoes() {
        Scanner res = new Scanner(System.in);

        for (int i = 0; i < 10000; i++) {
            System.out.println("Qual o seu cargo? \n[1]-Dono \n[2]-Funcionário \n[3]-Vendedor");
            int resposta = res.nextInt();
            res.nextLine();

            switch (resposta) {
                case 1:
                    acoesDonoDaLoja();
                    break;
                case 2:
                    acoesFuncionario();
                    break;
                case 3:
                    //acoesVendedor();
                    break;
                default:
                    System.out.println("Resposta inválida");
            }
        }
    }
    public void exluirFuncionario() throws FuncionarioNaoEncontradoException{
        Scanner r = new Scanner(System.in);
        System.out.println("Digite o CPF do funcionario");
        String cpf = r.nextLine();
        Funcionario f = buscarFuncionario(cpf);
        try {
            if (f == null) {
                throw new FuncionarioNaoEncontradoException("Funcionario não existe");
            }

            funcionariosDaLoja.remove(f);
            System.out.println("Funcionario removido!");

        }catch (FuncionarioNaoEncontradoException e){
            System.out.println("Erro: " + e.getMessage());
        }


    }

    public void cadastrarNovoFuncionario(){
        try {
            Scanner res = new Scanner(System.in);

            System.out.println("Digite o nome do novo funcionário: ");
            String nome = res.nextLine();
            if (nome == null || nome.isEmpty()) {
                throw new IllegalArgumentException("O campo 'nome' está vazio. Preencha corretamente");
            }

            System.out.println("Digite o sexo do novo funcionário: ");
            String sexo = res.nextLine();
            if (sexo == null || sexo.isEmpty()) {
                throw new IllegalArgumentException("O campo 'sexo' está vazio. Preencha corretamente");
            }
            System.out.println("Digite o CPF do novo funcionário: ");
            String cpf = res.nextLine();

            if (cpf == null || cpf.isEmpty()) {
                throw new IllegalArgumentException("O campo 'cpf' está vazio. Preencha corretamente");
            }
            System.out.println("Qual o cargo do novo funcionário ");
            String cargo = res.nextLine();

            if (cargo == null || cargo.isEmpty()) {
                throw new IllegalArgumentException("O campo 'cargo'está vazio. Preencha corretamente");
            }

            System.out.println("Qual a idade do novo funcionário ");
            int idade = res.nextInt();
            res.nextLine();

            if (idade < 18 || idade > 70) {
                throw new IllegalArgumentException("Idade deve ser entre 18 e 70.");
            }

            System.out.println("Qual o salario do novo funcionário: ");
            float salario = res.nextFloat();

            if(salario <= 0) {
                throw new IllegalArgumentException("O salário deve ser maior que 0");
            }

            FuncionariosDaLoja f1 = new FuncionariosDaLoja(nome, sexo, idade, cpf, cargo, salario);
            System.out.println("Novo funcionario(a) " + f1.nome + " cadastrado com sucesso");
            funcionariosDaLoja.add(f1);
        }catch (IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());
        }

    }

    public double calcularSalarioComBonusVendedor(double porcentagem, float salario) {
        if(porcentagem<=0 || salario <=0) {
            try {
                throw new ValorInvalidoException("O valor deve ser maior que zero");
            } catch (ValorInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
        return salario + (salario * (porcentagem / 100));
    }

    public void mostrarCalculoBonus(){
        Scanner r = new Scanner(System.in);
        System.out.println("Qual o salario do vendedor?");
        float salario = r.nextFloat();
        r.nextLine();
        System.out.println("Qual o percentual que deseja aplicar no salario dele?");
        double porcentagem = r.nextDouble();
        double salarioComBonus = calcularSalarioComBonusVendedor(porcentagem, salario);
        System.out.println("O salário com o bonus é:" + salarioComBonus);
    }
    public Funcionario buscarFuncionario(String cpf){
        Funcionario f = null;
        for(FuncionariosDaLoja aux:funcionariosDaLoja){
            if(aux.cpf.equalsIgnoreCase(cpf)){
                return aux;
            }
        }
        return f;
    }

    public void mostrarFuncionariosDaLoja(){
        for (FuncionariosDaLoja aux:funcionariosDaLoja){
            System.out.println(aux);
        }
    }
    public boolean verificarFuncionarioExiste(String cpf) {
        Funcionario f = buscarFuncionario(cpf);
        if(f != null){
            return true;
        }
        return false;
    }

    @Override
    public void baterPontoEntrada() throws FuncionarioNaoEncontradoException {
        Scanner r = new Scanner(System.in);
        System.out.println("Digite seu cpf");
        String cpf = r.nextLine();
        if(!verificarFuncionarioExiste(cpf)){
            throw new FuncionarioNaoEncontradoException("Funcionario não encontrado!");
        }
        System.out.println("Ponto de entrada feito com sucesso!");

    }

    @Override
    public void baterPontoSaida() throws FuncionarioNaoEncontradoException{
        Scanner r = new Scanner(System.in);
        System.out.println("Digite seu cpf");
        String cpf = r.nextLine();
        if(!verificarFuncionarioExiste(cpf)){
            throw new FuncionarioNaoEncontradoException("Funcionario não encontrado!");
        }

        System.out.println("Ponto de saída feito com sucesso!");

    }

    @Override
    public void cadastrarProduto (){
        try{
            Scanner res = new Scanner(System.in);
            System.out.println("Digite o cógido do produto: ");
            int codigo = res.nextInt();
            res.nextLine();
            if(codigo <= 0){
                throw new IllegalArgumentException("Código inválido. Deve ser maior que 0");
            }

            System.out.println("Qual o nome do produto: ");
            String nome = res.nextLine();
            if(nome == null || nome.isEmpty()){
                throw new IllegalArgumentException("O nome está incorreto");
            }

            System.out.println("Qual o preço do produto: ");
            float preco = res.nextFloat();
            if(preco <= 0){
                throw new IllegalArgumentException("O preço está incorreto. Deve ser maior que 0");
            }

            System.out.println("Digite a quantidade do produto: ");
            int qtdDoProduto = res.nextInt();
            if(qtdDoProduto <=0){
                throw new IllegalArgumentException("A quantidade de produtos deve ser maior que 0");
            }
            res.nextLine();

            System.out.println("Qual o material do produto?");
            String material = res.nextLine();
            if(material == null || material.isEmpty()){
                throw new IllegalArgumentException("Material invalido. Digite uma das opções: Ouro, prata ou inox");
            }
            System.out.println("Produto criado com sucesso!");
            ProdutosDaLoja p = new ProdutosDaLoja(nome,codigo,qtdDoProduto,preco,material);
            produtos.add(p);

        }catch (IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());
        }



    }

    @Override
    public void excluirProduto(int codigo) {
        if(buscarProduto(codigo) != null){
            produtos.remove((buscarProduto(codigo)));
            System.out.println("Produto removido com sucesso");
        }
        try{
            if(buscarProduto(codigo) == null){
                throw new ProdutoNaoEncontradoException("Produto não encontrado");
            }

        } catch (ProdutoNaoEncontradoException e){
            System.out.println("error: " + e.getMessage() );
        }
    }

    @Override
    public Produto buscarProduto(int codigo) {
        ProdutosDaLoja f = null;
        for(ProdutosDaLoja aux:produtos){
            if(aux.getCodigo() == codigo){
                return aux;
            }
        }
        return f;
    }

    @Override
    public void mostrarProdutos() {
        for (ProdutosDaLoja aux:produtos){
            System.out.println(aux);
        }
    }

    @Override
    public void verPrecoDoProduto() throws ProdutoNaoEncontradoException {
        Scanner r = new Scanner(System.in);
        System.out.println("Qual o código do produto?");
        int codigo = r.nextInt();
        r.nextLine();
        if(!verificarProdutoExiste(codigo)){
            throw  new ProdutoNaoEncontradoException("Produto não encontrado");
        }
        for (ProdutosDaLoja aux:produtos){
            if(aux.codigo == codigo){
                System.out.println("O preço do produto " + aux.nome + " é: " + aux.preco);
            }
        }

    }

    @Override
    public void mostrarUmProduto() {
        Scanner r = new Scanner(System.in);
        System.out.println("Qual o código do produto?");
        int codigo = r.nextInt();
        r.nextLine();

        if(verificarProdutoExiste(codigo)){
            for (ProdutosDaLoja aux:produtos){
                if(aux.codigo == codigo){
                    System.out.println(aux);
                }
            }
        }
    }

    public boolean verificarProdutoExiste(int codigo){
        if(buscarProduto(codigo) == null){
            try{
                throw new ProdutoNaoEncontradoException("Não existe um produto com esse código");
            } catch (ProdutoNaoEncontradoException e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public void acoesSobreProduto(){
        boolean executando = true;
        while (executando) {
            Scanner res = new Scanner(System.in);
            System.out.println("\nDigite uma das opções: " +
                    "\n[1]-Cadastrar " +
                    "\n[2]-Excluir " +
                    "\n[3]-Mostrar informações do produto " +
                    "\n[4]-Lista de produtos " +
                    "\n[5]-Voltar");
            String resposta = res.nextLine();
            if (resposta.equalsIgnoreCase("1")) {
                cadastrarProduto();
            } else if (resposta.equalsIgnoreCase("2")) {
                System.out.println("Qual o código do produto você deseja do excluir?");
                int codigo = res.nextInt();
                excluirProduto(codigo);
            } else if (resposta.equalsIgnoreCase("3")) {
                mostrarUmProduto();
            } else if(resposta.equalsIgnoreCase("4")){
                mostrarProdutos();
            }else if (resposta.equalsIgnoreCase("5")) {
                System.out.println("\n-------------Voltando...-------------");
                executando = false;
                break;
            } else {
                System.out.println("\n-------------Sua reposta não é válida!-------------\n");
            }
        }
    }
}
