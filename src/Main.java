import static locadora.TipoVeiculo.*;

import locadora.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static Long idLocacao = 1L;

    public static void main(String[] args) {
        // RN1: Os veículos não podem ser repetidos; Pode utilizar a placa como identificador de unicidade;
        // Os veículo não serão repetidos pois implementamos o equals e estamos utilizando o Set
        Set<Veiculo> veiculos = new HashSet<>();
        // RN6: Clientes não podem estar duplicados;
        // Considerar CPF (Pessoa Física) e CNPJ (Pessoa Jurídica) como identificadores de unicidade;
        // Os veículo não serão repetidos pois implementamos o equals e estamos utilizando o Set
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
        System.out.println();
        devolverVeiculo(locacoes, corola);
        System.out.println();
        devolverVeiculo(locacoes, fiat);

        System.out.println("""
                Esta aplicação foi desenvolvida no programa Santander Coders aplicado pela Ada Tech no módulo de Programação Orientada II a Objetos com os objetivos de fixação de conteúdo e avaliação.

                Professor: Brunno Nogueira
                Desenvolvedores:
                    Bruno Samuel da Silva;
                    Fabiola Santana dos Santos;
                    Rafael Santos Isidoro; e
                    Wagner Costa Thomazini.
                Turma: Santander Coders 2023 | 2º Semestre - Java (1)|#1111
                Data: 15 de março de 2024.""");
    }

    public static Set<? extends Veiculo> buscarVeiculos(Set<? extends Veiculo> veiculos, String str) {
        return veiculos.stream()
                .filter(v -> v.getNome()
                        .toLowerCase()
                        .contains(str.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public static boolean alugarVeiculo(Set<Locacao> locacoes, Veiculo veiculo, Pessoa pessoa, Endereco endereco) {
        // RN5: Os veículos que estiverem alugados não poderão estar disponíveis;
        // Vai dar verdadeiro se tiver alugado
        boolean alugado = locacoes.stream()
                .filter(l -> l.getVeiculo().equals(veiculo))
                .anyMatch(l -> l.getDataDevolucao() == null);

        if (alugado) {
            System.out.printf("Veículo %s não pode ser alugado.%n", veiculo.getNome());
            return false;
        }

        locacoes.add(new Locacao(idLocacao++, pessoa, veiculo, LocalDateTime.now(), endereco));
        System.out.printf("Veículo %s alugado com sucesso.%n", veiculo.getNome());
        return true;
    }

    public static boolean devolverVeiculo(Set<Locacao> locacoes, Veiculo veiculo) {
        Optional<Locacao> locacaoOptional = locacoes.stream()
                .filter(l -> l.getVeiculo().equals(veiculo) && l.getDataDevolucao() == null)
                .findAny();

        if (locacaoOptional.isPresent()) {
            Locacao locacao = locacaoOptional.get();
            locacao.setDataDevolucao(LocalDateTime.now().plusMinutes(60 * 24 * (int) (Math.random() * 8 + 2))); // Mais valor entre 2 e 10 dias

            System.out.printf("Devolução: " + locacao);
            System.out.println("Quantidade diárias: " + locacao.quantidadeDeDias());
            System.out.printf("Valor a pagar: R$ %.2f%n", locacao.valorAPagar());
            return true;
        } else {
            System.out.println("Não foi possível devolver o veículo.");
            return false;
        }
    }
}
