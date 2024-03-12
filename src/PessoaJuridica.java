public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica(String id, String nome, Endereco endereco, String cnpj) {
        super(id, nome, endereco);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}

