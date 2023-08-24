import model.Usuario;
import dao.DAO;
import dao.UsuarioDAO;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();
        List<Usuario> usuarios = new ArrayList<>();

        int opcao = 0;
        do {
            System.out.println("Menu:");
            System.out.println("1. Listar");
            System.out.println("2. Inserir");
            System.out.println("3. Excluir");
            System.out.println("4. Atualizar");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            switch (opcao) {
                case 1:
                    // Operação de Listar
                	usuarios = usuarioDAO.getOrderByCodigo();
            		for (Usuario u: usuarios) {
            			System.out.println(u.toString());
            		}
                    break;
                case 2:
                    // Operação de Inserir
                	System.out.println("Código do usuário: ");
                    int codigo = scanner.nextInt();
                	 System.out.println("Login do usuário: ");
                	 scanner.nextLine();
                	 String login = scanner.nextLine();
                     System.out.println("Senha do usuário: ");
                     String senha = scanner.nextLine();
                     System.out.println("Sexo do usuário: ");
                     String input = scanner.next();
                     char sexo =  input.charAt(0);
                	 usuario = new Usuario(codigo, login, senha,sexo);
            		if(usuarioDAO.insert(usuario) == true) {
            			System.out.println("Inserção com sucesso -> " + usuario.toString());
            		}
            		scanner.nextLine(); // Limpar o buffer
                    break;
                case 3:
                    // Operação de Excluir
                	codigo = 0;
                	System.out.println("Código do usuário: ");
                    codigo = scanner.nextInt();
            		usuarioDAO.delete(codigo);
                    break;
                case 4:
                    // Operação de Atualizar
                	System.out.println("Código do usuário: ");
                    codigo = scanner.nextInt();
                    scanner.nextLine();
                	System.out.println("Nova senha do usuário: ");
                	senha = scanner.nextLine();for (Usuario u: usuarios) {
                        if (u.getCodigo() == codigo) {
                            u.setSenha(senha);
                            break;
                        }
                    }
            		usuario.setSenha(DAO.toMD5(senha));
            		usuarioDAO.update(usuario);
            		System.out.println("Senha atualizada com sucesso!");
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}
