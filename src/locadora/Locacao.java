package locadora;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Locacao {

    private Long id;
    private Pessoa pessoa;
    private Veiculo veiculo;
    private LocalDateTime dataLocacao;
    private LocalDateTime dataDevolucao;
    private Endereco endereco;

    public Locacao(Long id, Pessoa pessoa, Veiculo veiculo, LocalDateTime dataEntrada, LocalDateTime dataSaida, Endereco endereco) {
        this.id = id;
        this.pessoa = pessoa;
        this.veiculo = veiculo;
        this.dataLocacao = dataEntrada;
        this.dataDevolucao = dataSaida;
        this.endereco = endereco;
    }

    public Locacao(Long id, Pessoa pessoa, Veiculo veiculo, LocalDateTime dataEntrada, Endereco endereco) {
        this.id = id;
        this.pessoa = pessoa;
        this.veiculo = veiculo;
        this.dataLocacao = dataEntrada;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDateTime getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDateTime dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int quantidadeDeDias() {
        return (int) Math.ceil((double) ChronoUnit.MINUTES.between(dataLocacao, dataDevolucao) / 60.0 / 24.0);
    }

    public double valorAPagar() {
        return this.quantidadeDeDias() * this.veiculo.getValorDiaria();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locacao locacao = (Locacao) o;
        return Objects.equals(id, locacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Locacao{" +
                "id=" + id +
                ", pessoa=" + pessoa +
                ", veiculo=" + veiculo +
                ", dataLocacao=" + dataLocacao +
                ", dataDevolucao=" + dataDevolucao +
                ", endereco=" + endereco +
                '}';
    }

}
