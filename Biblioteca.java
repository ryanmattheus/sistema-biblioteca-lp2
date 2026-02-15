import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<ItemDoAcervo> acervo;
    private List<Usuario> listaDeUsuarios;
    private List<Emprestimo> registrosDeEmprestimos;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
        this.listaDeUsuarios = new ArrayList<>();
        this.registrosDeEmprestimos = new ArrayList<>();
    }

    public void realizarEmprestimo(String idUsuario, String titulo) {
        // 1 - Buscar os objetos usuario e livro
        Usuario usuarioDoEmprestimo = pesquisarUsuarioPorNome(idUsuario);
        if (usuarioDoEmprestimo == null) {
            System.out.println("Erro: usuário não cadastrado.");
            return;
        }
        ItemDoAcervo ItemDoEmprestimo = pesquisarItemPorTitulo(titulo);
        if (ItemDoEmprestimo == null) {
            System.out.println("Erro: livro não cadastrado.");
            return;
        }
        // 2 - Validar a regra de negocio (verificar se o livro está disponível)
        if (ItemDoEmprestimo.getStatus() == StatusItem.EMPRESTADO) {
            System.out.println("Erro: livro já emprestrado.");
            return;
        }
        // REGRA NOVA: se for reservável e estiver reservado, não pode emprestar
        if (ItemDoEmprestimo instanceof Reservavel) {
            Reservavel r = (Reservavel) ItemDoEmprestimo;

            if (((Livro) ItemDoEmprestimo).isReservado()) {
                System.out.println("Erro: item está reservado.");
                return;
            }
        }

        // 3 - Realizar o registro (Criar objeto do tipo Emprestimo e adiciona-lo ao
        // registroDeEmprestimos)
        ItemDoEmprestimo.setStatus(StatusItem.EMPRESTADO);
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(ItemDoEmprestimo.getPrazo());
        Emprestimo emprestimo = new Emprestimo(ItemDoEmprestimo, usuarioDoEmprestimo, dataEmprestimo,
                dataDevolucaoPrevista);
        registrosDeEmprestimos.add(emprestimo);
        System.out.println("Emprestimo realizado com sucesso!");
        System.out.println("O livro '" + ItemDoEmprestimo.getTitulo()
                + "' foi emprestado para o usuário " + usuarioDoEmprestimo.getNome()
                + " na data " + emprestimo.getDataEmprestimo()
                + " e tem de ser devolvido em " + emprestimo.getDataDevolucaoPrevista());
    }

    public Emprestimo buscarEmprestimoAtivoPorItem(ItemDoAcervo item) {
        for (Emprestimo emprestimo : registrosDeEmprestimos) {
            if (emprestimo.getItem().getTitulo().equalsIgnoreCase(item.getTitulo())) {
                if (emprestimo.getDataDevolucaoReal() == null) {
                    return emprestimo;
                }
            }
        }
        return null;
    }

    public void realizarDevolucao(String titulo) {
        ItemDoAcervo item = pesquisarItemPorTitulo(titulo);
        if (item == null) {
            System.out.println("Erro: esse livro não está cadastrado.");
            return;
        }
        Emprestimo emprestimo = buscarEmprestimoAtivoPorItem(item);
        if (emprestimo == null) {
            System.out.println("Erro: esse emprestimo não existe.");
            return;
        }
        LocalDate hoje = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), hoje);

        if (dias > 0) {
            double multa = dias * item.getMulta_Por_dia();
            System.out.println("Livro devolvido. Você precisa pagar uma multa de R$" + multa);
        } else {
            System.out.println("Livro devolvido.");
        }
        emprestimo.getItem().setStatus(StatusItem.DISPONIVEL);
        emprestimo.setDataDevolucaoReal(hoje);
    }

    public ItemDoAcervo pesquisarItemPorTitulo(String titulo) {
        for (ItemDoAcervo item : this.acervo) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        return null;
    }

    public Usuario pesquisarUsuarioPorNome(String Nome) {
        for (Usuario usuario : this.listaDeUsuarios) {
            if (usuario.getNome().equals(Nome)) {
                return usuario;
            }
        }
        return null;
    }

    public void listarAcervo() {
        System.out.println("Itens no Acervo");
        for (var item : acervo) {
            System.out.println(item);
        }
    }

    public void cadastrarUsuario(Usuario usuario) {

        if (!usuario.validar()) {
            System.out.println("Usuário inválido. Não cadastrado.");
            return;
        }

        this.listaDeUsuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso.");
    }

    public void imprimirDocumento(Imprimivel objeto) {
        System.out.println("-----------------------------");
        System.out.println(objeto.formatarParaEtiqueta());
        System.out.println("-----------------------------");
    }

    public void cadastrarItem(ItemDoAcervo item) {

        if (item instanceof Validavel) {
            Validavel v = (Validavel) item;

            if (!v.validar()) {
                System.out.println("Item inválido. Não cadastrado.");
                return;
            }
        }

        this.acervo.add(item);
        System.out.println("O item " + item.getTitulo() + " foi cadastrado.");
    }

    public static void main(String[] args) {

        // Biblioteca minhaBiblioteca = new Biblioteca();
        // Usuario meuUsuario = new Usuario("Ryan", "123");
        // Livro livroJavaComoProgramar = new Livro("Java Como Programar", "Deitel",
        // 2014);
        // Livro livroMemoria = new Livro("Memórias Póstumas de Brás Cubas", "Machado de
        // Assis", 1881);
        //
        // minhaBiblioteca.cadastrarLivro(livroJavaComoProgramar);
        // minhaBiblioteca.cadastrarLivro(livroMemoria);
        // minhaBiblioteca.cadastrarUsuario(meuUsuario);
        // minhaBiblioteca.listarAcervo();
        // minhaBiblioteca.realizarEmprestimo("123", "Java Como Programar");
        // minhaBiblioteca.listarAcervo();
        // minhaBiblioteca.registrosDeEmprestimos.get(0).setDataDevolucaoPrevista(LocalDate.of(2025,
        // 8, 31));
        // minhaBiblioteca.realizarDevolucao("Java Como Programar");
        // minhaBiblioteca.listarAcervo();
        // Revista revistaVeja = new Revista("oiie", 2025, 1234);
        // System.out.println(revistaVeja);
        // minhaBiblioteca.realizarEmprestimo("123", "oiie");
        //
        Biblioteca biblioteca = new Biblioteca();

        Livro livro1 = new Livro("Programação em Java", "Deitel", 2022, "1234567890123");
        DVD dvd1 = new DVD("Curso Java Completo", 2021, 120);

        Aluno aluno1 = new Aluno("Ryan Matheus", "2023123", "123");
        Professor professor1 = new Professor("Thiago", "P001", "123");

        biblioteca.cadastrarItem(livro1);
        biblioteca.cadastrarItem(dvd1);

        biblioteca.cadastrarUsuario(aluno1);
        biblioteca.cadastrarUsuario(professor1);

        biblioteca.imprimirDocumento(livro1);
        biblioteca.imprimirDocumento(aluno1);

        livro1.reservar();

        biblioteca.realizarEmprestimo("Ryan Matheus", "Programação em Java");

        livro1.cancelarReserva();
        biblioteca.realizarEmprestimo("Ryan Matheus", "Programação em Java");
        System.out.println(biblioteca);
    }
}
