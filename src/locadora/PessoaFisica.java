package locadora;

import java.util.Objects;

public class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica(Long id, String nome, Endereco endereco, String cpf) {
        super(id, nome, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof PessoaFisica that) {
            return Objects.equals(id, that.id) || Objects.equals(cpf, that.cpf);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                '}';
    }

}

