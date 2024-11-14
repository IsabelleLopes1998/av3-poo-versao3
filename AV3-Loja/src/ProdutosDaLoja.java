public class ProdutosDaLoja extends Produto{
    private  String material;

    public ProdutosDaLoja(String nome, int codigo, int qtdDoProduto, float preco, String material) {
        super(nome, codigo, qtdDoProduto, preco);
        try{
            if(material == null || material.isEmpty()){
                throw new IllegalArgumentException("Você deve digitar qual o tipo do material: Ouro, Prata, Inox");
            }
        } catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
        this.material = material;
    }

    @Override
    public String toString() {
        return "ProdutosDaLoja: " +
                "nome:" + nome +
                ", codigo: " + codigo +
                ", qtdDoProduto: " + qtdDoProduto +
                ", preco: " + preco +
                ", material: " + material;
    }
}
