public abstract class Funcionario extends Pessoa {
    protected String cargo;
    float salario;

    public Funcionario(String nome, String sexo, int idade, String cpf, String cargo, float salario) throws IllegalArgumentException {
        super(nome, sexo, idade, cpf);
        try{
            if(cargo == null || cargo.isEmpty()){
                throw new IllegalArgumentException("Você deve digitar o cargo do funcionario");
            }
            if(salario <= 0){
                throw new IllegalArgumentException("O salário deve ser maior que 0");
            }
            this.cargo = cargo;
            this.salario = salario;
        }catch (IllegalArgumentException e){
            System.out.println("Erro ao criar a pessoa: " + e.getMessage());
        }

    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionario:" +
                " nome: " + nome +
                ", cargo: " + cargo +
                ", sexo: " + sexo  +
                ", idade: " + idade +
                ", cpf: " + cpf;
    }
}
