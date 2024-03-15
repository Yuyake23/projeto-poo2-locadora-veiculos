import static locadora.TipoVeiculo.*;

import locadora.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // RN1: Os veículos não podem ser repetidos; Pode utilizar a placa como identificador de unicidade;
        // Os veículo não serão repetidos pois implementamos o equals e estamos utilizando u Set
        Set<Veiculo> veiculos = new HashSet<>();
        Set<Pessoa> pessoas = new HashSet<>();
        Set<Locacao> locacoes = new HashSet<>();

        // Cadastrar os veículos
        Veiculo corola = new Veiculo(1L, "KEG-3212", "Corola", SUV);
        veiculos.add(corola);
        Veiculo fiat = new Veiculo(2L, "NBD-8004", "Fiat 500", PEQUENO);
        veiculos.add(fiat);

        // Alterar um veiculo cadastrado
        veiculos.stream()
                .filter(v -> v.getId() == 1L)
                .findAny()
                .orElseThrow()
                .setTipo(MEDIO);

        // Buscar um veículo por parte do nome
        System.out.println(buscarVeiculos(veiculos, "coro"));

        // Cadastrar o cliente (pessoa física e jurídica)
        Endereco enderecoFabiola = new Endereco(1L, "Rua A", "São Paulo", "SP", "06266-220");
        Pessoa fabiola = new PessoaFisica(1L, "Fabiola Santos", enderecoFabiola, "134.537.870-05");
        pessoas.add(fabiola);

        Endereco enderecoWagner = new Endereco(2L, "Rua Recife", "Presidente Epitácio", "SP", "05420-002");
        Pessoa wagnerLtda = new PessoaJuridica(2L, "Shoque Informatica", enderecoWagner, "17.125.518/0001-54");
        pessoas.add(wagnerLtda);

        // Alterar o cliente (pessoa física e jurídica)
        pessoas.stream()
                .filter(p -> p.getId() == 1L)
                .findAny()
                .orElseThrow()
                .setNome("Fabiola Santana dos Santos");

        pessoas.stream()
                .filter(p -> p.getId() == 2L)
                .findAny()
                .orElseThrow()
                .setNome("Schock Informatica");

        // Alugar um veículo para pessoa física e jurídica
        Endereco enderecoLocadora = new Endereco(3L, "Rua das Árvores", "Urutái", "GO", "08230-470");
        alugarVeiculo(locacoes, corola, fabiola, enderecoLocadora);
        alugarVeiculo(locacoes, fiat, wagnerLtda, enderecoLocadora);

        // Devolver um veículo para pessoa física e jurídica
        devolverVeiculo(locacoes, corola);
        System.out.println();
        devolverVeiculo(locacoes, fiat);
    }

    public static Set<? extends Veiculo> buscarVeiculos(Set<? extends Veiculo> veiculos, String str) {
        return veiculos.stream()
                .filter(v -> v.getNome()
                        .toLowerCase()
                        .contains(str.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public static boolean alugarVeiculo(Set<Locacao> locacoes, Veiculo veiculo, Pessoa pessoa, Endereco endereco) {
        boolean alugado = locacoes.stream()
                .filter(l -> l.getVeiculo().equals(veiculo))
                .anyMatch(l -> l.getDataDevolucao() == null);

        if (alugado) {
            return false;
        }

        locacoes.add(new Locacao(1L, pessoa, veiculo, LocalDateTime.now(), endereco));
        return true;
    }

    public static boolean devolverVeiculo(Set<Locacao> locacoes, Veiculo veiculo){
        Optional<Locacao> locacaoOptional = locacoes.stream()
                .filter(l -> l.getVeiculo().equals(veiculo) && l.getDataDevolucao() == null)
                .findAny();

        if (locacaoOptional.isPresent()) {
            Locacao locacao = locacaoOptional.get();
            locacao.setDataDevolucao(LocalDateTime.now().plusMinutes(60 * 24 * 2 + 50)); // Mais dois dias e 50 minutos

            System.out.println("Quantidade diárias: " + locacao.quantidadeDeDias());
            System.out.printf("Valor a pagar: R$ %.2f%n", locacao.valorAPagar());
            return true;
        } else {
            return false;
        }
    }

}