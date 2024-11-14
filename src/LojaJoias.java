import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LojaJoias implements FuncionarioService, ProdutoService, PontoService, SecurityService {
    Dono dono = new Dono("Dono","M",40,"111", "1234AQWS");
    ArrayList<FuncionariosDaLoja> funcionariosDaLoja = new ArrayList<>();
    ArrayList<ProdutosDaLoja> produtos = new ArrayList<>();




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
                    System.out.println("......Voltando.....");
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
                    "\n[4]-Voltar");
            int resposta = res.nextInt();

            switch (resposta){
                case 1:
                    baterPontoEntrada();
                    break;
                case 2:
                    baterPontoSaida();
                    break;
                case 3:
                    acoesSobreProduto();
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...\n");
                    executando = false;
                    break;
                default:
                    System.out.println("Resposta inválida");
            }
        }
    }

    public void iniciarSistema() {
        System.out.println("Deseja iniciar o sistema? \n[1]-Sim \n[2]-Sair do Sistema");
        Scanner res = new Scanner(System.in);
        int r = res.nextInt();
        res.nextLine();
        switch (r){
            case 1:
                System.out.println(
                        "\n-------------------------Iniciando o sistema------------------------\n" +
                                "\n--------------------------------------------------------------------" +
                                "\n--------------------------------------------------------------------" +
                                "\n--------------------------------------------------------------------" +
                                "\n--------------------------------------------------------------------" +
                                "\n--------------------------------------------------------------------" +
                                "\n--------------------------------------------------------------------\n");
                criarFuncionario();
                criarProduto();
                iniciarOperacoes();
                break;

            case 2:
                System.out.println("\n-------------------SISTEMA DA LOJA FINALIZADO-------------------\n");
                break;
        }
    }

    private void iniciarOperacoes() {
        Scanner res = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("Qual o seu cargo? " +
                    "\n[1]-Dono" +
                    "\n[2]-Funcionário" +
                    "\n[3]-Finalizar Sistema");
            int resposta = res.nextInt();
            res.nextLine();

            switch (resposta) {
                case 1:
                    autenticarSenha();

                    break;
                case 2:
                    acoesFuncionario();
                    break;
                case 3:
                    System.out.println("\n-------------------SISTEMA DA LOJA FINALIZADO-------------------\n");
                    run = false;
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
            double salario = res.nextDouble();

            if(salario <= 0) {
                throw new IllegalArgumentException("O salário deve ser maior que 0");
            }

            FuncionariosDaLoja f = new FuncionariosDaLoja(nome, sexo, idade, cpf, cargo, salario);
            System.out.println("Novo funcionario(a) " + f.nome + " cadastrado com sucesso");
            funcionariosDaLoja.add(f);
        }catch (IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());
        }catch (InputMismatchException e) {
            System.out.println("Valor invalido. Se for número decimal digite com virgula e não ponto: Ex: 1448,58");
        }
    }

    public double calcularSalarioComBonusVendedor(double porcentagem, double salario) {
            try {
                if(porcentagem<=0 || salario <=0) {
                    throw new ValorInvalidoException("O valor deve ser maior que zero");
                }
            } catch (ValorInvalidoException e){
                    System.out.println("erro: " + e.getMessage());
            }
            return salario + (salario * (porcentagem / 100));
    }

    public void mostrarCalculoBonus(){
        try{
            Scanner r = new Scanner(System.in);
            System.out.println("Qual o salario do vendedor?");
            double salario = r.nextDouble();
            r.nextLine();

            if(salario <= 0){
                throw new ValorInvalidoException("Valo inválido. Digite um valor maior que 0");
            }
            System.out.println("Qual o percentual que deseja aplicar no salario dele?");
            double porcentagem = r.nextDouble();
            r.nextLine();

            if(porcentagem <= 0){
                throw new ValorInvalidoException("Valo inválido. Digite um valor maior que 0");
            }

            double salarioComBonus = calcularSalarioComBonusVendedor(porcentagem, salario);
            System.out.println("O salário com o bonus é:" + salarioComBonus);

        } catch (ValorInvalidoException e) {
            System.out.println("erro: " + e.getMessage());
        } catch (InputMismatchException e){
            System.out.println("Valor invalido. Se for número decimal digite com virgula e não ponto: Ex: 1448,58");
        }
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
        }catch (InputMismatchException e) {
            System.out.println("Valor invalido. Se for número decimal digite com virgula e não ponto('.'): Ex: 14,58");
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
        boolean run = true;
        while (run) {
            Scanner res = new Scanner(System.in);
            System.out.println("""
                    \nDigite uma das opções: \
                    
                    [0]-Vender Produto \
                    
                    [1]-Cadastrar \
                    
                    [2]-Excluir \
                    
                    [3]-Mostrar informações do produto \
                    
                    [4]-Lista de produtos \
                    
                    [5]-Voltar""");
            int resposta = res.nextInt();
            res.nextLine();

            switch (resposta){
                case 0:
                    System.out.println("Qual o código do produto?");
                    int codigo = res.nextInt();
                    venderProduto(codigo);
                    break;
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    try {
                        System.out.println("Qual o código do produto você deseja do excluir?");
                        int code = res.nextInt();
                        excluirProduto(code);
                        if(code < 0){
                            throw new IllegalArgumentException("Codigo invalido. O deve ser maior que 0");
                        }
                    }catch (IllegalArgumentException e){
                        System.out.println("erro: " + e.getMessage());
                    }
                    break;
                case 3:
                    mostrarUmProduto();
                    break;
                case 4:
                    mostrarProdutos();
                    break;
                case 5:
                    System.out.println("\n-------------Voltando...-------------");
                    run = false;
                    break;
                default:
                    System.out.println("\n-------------Sua reposta não é válida!-------------\n");
            }
        }
    }

    public void venderProduto(int codigo) {
        Produto produto = buscarProduto(codigo);
        if (produto != null) {
            Scanner res = new Scanner(System.in);
            System.out.println("Quantidade do produto que será vendida: ");
            int qtd = res.nextInt();
            res.nextLine();

            if (qtd < produto.qtdDoProduto) {
                System.out.println("Produto pronto para venda. Finalize com pagamento");
                confirmarVenda();
                int novaQtd = produto.qtdDoProduto - qtd;
                produto.qtdDoProduto = novaQtd;
                System.out.println("Nova quantidade em estoque do produto " + produto.nome + ": " + novaQtd);

            } else if (qtd == produto.qtdDoProduto) {
                System.out.println("\nAVISO: A quantidade da venda é igual ao estoque. Deseja continuar?\n[1]-Sim \n[2]-Não\n");
                int resposta = res.nextInt();
                res.nextLine();

                switch (resposta){
                    case 1:
                        System.out.println("Produto pronto para venda. Finalize com pagamento.");
                        confirmarVenda();
                        System.out.println("------\nAVISO: O ESTOQUE DESTE PRODUTO FOI ZERADO------\n");
                        produto.qtdDoProduto = 0;
                        System.out.println("Produto " + produto.nome + " esgotado.");
                        break;
                    case 2:
                        System.out.println("Venda cancelada.");
                        break;
                }
            } else {
                System.out.println("A quantidade do pedido ultrapassa o estoque. Estoque atual: " + produto.qtdDoProduto);
            }

        } else {
            System.out.println("Produto com o código " + codigo + " não encontrado!\n");
        }
    }
    public void confirmarVenda() throws ValorInvalidoException{
        Scanner res = new Scanner(System.in);
        System.out.println("O pagamento foi realizado com sucesso? [1]-Sim ou [2]-Não");
        int resposta = res.nextInt();
        if(resposta != 1 && resposta != 2){
            throw new ValorInvalidoException("Valor inválido. Selecione 1 ou 2 para a operação que deseja");
        }
        switch (resposta){
            case 1:
                System.out.println("Venda realizada com sucesso");
                break;
            case 2:
                System.out.println("Venda cancelada\n");
                break;
            default:
                System.out.println("Resposta inválida\n");
        }
    }

    public void criarProduto() {
        ProdutosDaLoja p1 = new ProdutosDaLoja("Cordão de rosas", 1, 20, 15.2f, "ouro");
        produtos.add(p1);
        ProdutosDaLoja p2 = new ProdutosDaLoja("Anel de rosas", 2, 12, 15.2f, "ouro");
        produtos.add(p2);
    }

    public void criarFuncionario(){
        FuncionariosDaLoja f1 = new FuncionariosDaLoja("João Rodrigues Silva", "M",25,"222", "Vendedor",1448.53);
        funcionariosDaLoja.add(f1);
        FuncionariosDaLoja f2 = new FuncionariosDaLoja("Maria Aline Silveira", "F",28,"333", "Vendedor",1448.53);
        funcionariosDaLoja.add(f2);
    }

    @Override
    public void autenticarSenha() {
        try{
            Scanner s = new Scanner(System.in);
            System.out.println("Qual a senha de acesso do Dono?");
            String senha = s.nextLine();

            if(senha.isEmpty()){
                throw new IllegalArgumentException("Valor inválido. Digite a senha novamente");
            }
            if(senha.equalsIgnoreCase("1234AQWS")){
                System.out.println("\n-----------ACESSO LIBERADO--------\n\n\n");
                acoesDonoDaLoja();
            }else if(!senha.equalsIgnoreCase("1234AQWS")){
                System.out.println("\n-----------SENHA INVÁLIDA--------\n\n\n");
            }
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }

    }
}
