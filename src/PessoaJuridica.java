import java.util.Objects;

public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica(Long id, String nome, Endereco endereco, String cnpj) {
        super(id, nome, endereco);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof PessoaJuridica that) {
            return Objects.equals(id, that.id) || Objects.equals(cnpj, that.cnpj);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", endereco=" + endereco +
                '}';
    }

}

