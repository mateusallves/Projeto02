

import com.google.gson.Gson;


import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;


public class App {
    private static ArrayList<String> nomes = new ArrayList<>();
    private static final String arquivo = "nomes.json";
    private static final Scanner scanner = new Scanner(System.in);
   private static final Gson gson = new Gson();

    public static void main(String[] args) {
        carregarNomes();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Adicionar nome");
            System.out.println("2 - Listar nomes");
            System.out.println("3 - Remover nome");
            System.out.println("4 - Buscar nome");
            System.out.println("5 - Salvar nomes em arquivo JSON");
            System.out.println("6 - Carregar nomes de arquivo JSON");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: adicionarNome(); break;
                case 2: listarNomes(); break;
                case 3: removerNome(); break;
                case 4: buscarNome(); break;
                case 5: salvarNomes(); break;
               // case 6: carregarNomes(); break;
                case 0:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void adicionarNome() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Erro! O Nome está vazio");
        } else if (nomes.contains(nome)) {
            System.out.println("Nome já existe na lista");
        } else {
            nomes.add(nome);
            System.out.println("Nome adicionado");
        }
    }

    private static void listarNomes() {
        Collections.sort(nomes);
        System.out.println("Listas de nomes:");
        for (int i = 0; i < nomes.size(); i++) {
            System.out.println((i + 1) + ". " + nomes.get(i));
        }
    }

    private static void removerNome() {
        System.out.print("Digite o nome para remover: ");
        String nome = scanner.nextLine().trim();
        if (nomes.remove(nome)) {
            System.out.println("Nome removido.");
        } else {
            System.out.println("Nome não encontrado.");
        }
    }

    private static void buscarNome() {
        System.out.print("Digite o nome para buscar: ");
        String nome = scanner.nextLine().trim();
        if (nomes.contains(nome)) {
            System.out.println("Nome encontrado na lista.");
        } else {
            System.out.println("Nome não encontrado.");
        }
    }
     private static void salvarNomes() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(nomes, writer);
            System.out.println("Nomes salvos em " + arquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar nomes: " + e.getMessage());
        }
    }

    private static void carregarNomes() {
        try (FileReader reader = new FileReader(arquivo)) {
            nomes = gson.fromJson(reader, new TypeToken<ArrayList<String>>(){}.getType());
            if (nomes == null) nomes = new ArrayList<>();
            System.out.println("Nomes carregados de " + arquivo);
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado ou erro ao carregar. Iniciando com lista vazia.");
        }
    }

}
