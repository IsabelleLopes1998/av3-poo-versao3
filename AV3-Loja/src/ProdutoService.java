public interface ProdutoService {
    void cadastrarProduto();
    void excluirProduto(int codigo);
    Produto buscarProduto(int codigo);
    void mostrarProdutos();
    void verPrecoDoProduto();
    void mostrarUmProduto();
}

