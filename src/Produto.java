public abstract class Produto {
   protected String nome;
   protected int codigo;
   protected int qtdDoProduto;
   protected float preco;


    public Produto(String nome, int codigo, int qtdDoProduto, float preco) {
        try{
            if(nome == null || nome.isEmpty()){
                throw new IllegalArgumentException("Você deve digitar o nome do produto");
            }
            if(codigo < 0){
                throw new IllegalArgumentException("O código deve ser um valor maior que 0");
            }
            if(qtdDoProduto < 0){
                throw new IllegalArgumentException("A quantidade deve ser um valor maior que 0");
            }
            if(qtdDoProduto < 0){
                throw new IllegalArgumentException("O preço o produto deve ser um valor maior que 0");
            }
            this.nome = nome;
            this.codigo = codigo;
            this.qtdDoProduto = qtdDoProduto;
            this.preco = preco;

        }catch (IllegalArgumentException e){
            System.out.println("Erro ao criar a produto: " + e.getMessage());
        }
    }


    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQtdDoProduto() {
        return qtdDoProduto;
    }

    public void setQtdDoProduto(int qtdDoProduto) {
        this.qtdDoProduto = qtdDoProduto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome: " + nome  +
                ", codigo: " + codigo +
                ", qtdDoProduto: " + qtdDoProduto +
                ", preco: " + preco +
                '}';
    }
}
