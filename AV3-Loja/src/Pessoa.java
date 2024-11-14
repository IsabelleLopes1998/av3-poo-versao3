public abstract class Pessoa {
    protected String  nome;
    protected String sexo;
    protected int idade;
    protected String cpf;

    public Pessoa(String nome, String sexo, int idade, String cpf) {
        try {
            if (nome == null || nome.isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
            }
            if (!(sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F"))) {
                throw new IllegalArgumentException("Sexo deve ser 'M' ou 'F'.");
            }
            if (idade < 18 || idade > 70) {
                throw new IllegalArgumentException("Idade deve ser entre 18 e 70.");
            }
            this.nome = nome;
            this.sexo = sexo;
            this.idade = idade;
            this.cpf = cpf;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar a pessoa: " + e.getMessage());
    }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", idade=" + idade +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}

