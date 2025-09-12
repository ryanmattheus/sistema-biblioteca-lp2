import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/*
QUESTÃO DISCURSIVA: No contexto do projeto não faz sentido criar um objeto "Item" pois o mesmo fica muito genérico.
Por isso, é necessário instanciar, dentro do código, a classe daquele item específico, que, até o momento,
 pode ser classificado como Livro ou Revista.
*/


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
        Usuario usuarioDoEmprestimo = pesquisarUsuarioPorId(idUsuario);
        if(usuarioDoEmprestimo == null) {
            System.out.println("Erro: usuário não cadastrado.");
            return;
        }
        ItemDoAcervo itemDoEmprestimo = pesquisarItemPorTitulo(titulo);
        if(itemDoEmprestimo == null) {
            System.out.println("Erro: item não cadastrado.");
            return;
        }
        // 2 - Validar a regra de negocio (verificar se o livro está disponível)
        if(itemDoEmprestimo.getStatus() == StatusItem.EMPRESTADO) {
            System.out.println("Erro: Item já emprestrado.");
            return;
        }
        // 3 - Realizar o registro (Criar objeto do tipo Emprestimo e adiciona-lo ao registroDeEmprestimos)
        itemDoEmprestimo.setStatus(StatusItem.EMPRESTADO);
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(itemDoEmprestimo.getPrazo());
        Emprestimo emprestimo = new Emprestimo(itemDoEmprestimo, usuarioDoEmprestimo, dataEmprestimo, dataDevolucaoPrevista);
        registrosDeEmprestimos.add(emprestimo);
        System.out.println("Emprestimo realizado com sucesso!");
        System.out.println("O livro '"+itemDoEmprestimo.getTitulo()
                +"' foi emprestado para o usuário " + usuarioDoEmprestimo.getNome()
                +" na data " + emprestimo.getDataEmprestimo()
                +" e tem de ser devolvido em " + emprestimo.getDataDevolucaoPrevista());
    }

    public Emprestimo buscarEmprestimoAtivoPorItem(ItemDoAcervo item) {
        for (Emprestimo emprestimo : registrosDeEmprestimos) {
            if(emprestimo.getItem().getTitulo().equalsIgnoreCase(item.getTitulo())) {
                if(emprestimo.getDataDevolucaoReal() == null) {
                    return emprestimo;
                }
            }
        }
        return null;
    }

    public void realizarDevolucao(String titulo) {
        ItemDoAcervo item = pesquisarItemPorTitulo(titulo);
        if(item == null) {
            System.out.println("Erro: esse livro não está cadastrado.");
            return;
        }
        Emprestimo emprestimo = buscarEmprestimoAtivoPorItem(item);
        if(emprestimo == null) {
            System.out.println("Erro: esse emprestimo não existe.");
            return;
        }
        LocalDate hoje = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), hoje);

        if(dias > 0) {
            double multa = dias * item.getValorMultaPorDiaAtraso();
            System.out.println("Item devolvido. Você precisa pagar uma multa de R$" + multa);
        } else {
            System.out.println("Item devolvido.");
        }
        emprestimo.getItem().setStatus(StatusItem.DISPONIVEL);
        emprestimo.setDataDevolucaoReal(hoje);
    }

    public ItemDoAcervo pesquisarItemPorTitulo(String titulo) {
        for(ItemDoAcervo item : this.acervo) {
            if(item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        return null;
    }

    public Usuario pesquisarUsuarioPorId(String id) {
        for(Usuario usuario : this.listaDeUsuarios) {
            if(usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public void listarAcervo() {
        System.out.println("==== Itens no Acervo ====");
        for (var item : acervo) {
            System.out.println(item);
        }
    }

    public void cadastrarItem(ItemDoAcervo item) {
        this.acervo.add(item);
        System.out.println("O item " + item.getTitulo() + " foi cadastrado.");
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.listaDeUsuarios.add(usuario);
        System.out.println("O usuário " + usuario.getNome() + " foi cadastrado.");
    }

    public List<ItemDoAcervo> buscar(String termo) {
        String termoLowerCase = termo.toLowerCase();

        List<ItemDoAcervo> resultados = new ArrayList<>();
        for (ItemDoAcervo item : this.acervo) {
            boolean corresponde = false;

            if (item.getTitulo().toLowerCase().contains(termoLowerCase)) {
                corresponde = true;
            }
            if (item instanceof Livro) {
                Livro livro = (Livro) item;
                if (livro.getAutor().toLowerCase().contains(termoLowerCase)) {
                    corresponde = true;
                }
            }
            if (corresponde) {
                resultados.add(item);
            }
        }

        return resultados;
    }

    public static void main(String[] args) {

        Biblioteca minhaBiblioteca = new Biblioteca();
        Usuario meuUsuario = new Usuario("Ryan", "123");
        Livro livroJavaComoProgramar = new Livro("Java Como Programar", "Deitel", 2014);
        Livro livroMemoria = new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", 1881);

        minhaBiblioteca.cadastrarItem(livroJavaComoProgramar);
        minhaBiblioteca.cadastrarItem(livroMemoria);
        minhaBiblioteca.cadastrarUsuario(meuUsuario);
        minhaBiblioteca.listarAcervo();
        minhaBiblioteca.realizarEmprestimo("123", "Java Como Programar");
        minhaBiblioteca.listarAcervo();
        minhaBiblioteca.registrosDeEmprestimos.get(0).setDataDevolucaoPrevista(LocalDate.of(2025, 8, 31));
        minhaBiblioteca.realizarDevolucao("Java Como Programar");
        minhaBiblioteca.listarAcervo();
        Revista revistaVeja = new Revista("oiie", 2025, 1234);
        minhaBiblioteca.cadastrarItem(revistaVeja);
        System.out.println(revistaVeja);
        minhaBiblioteca.realizarEmprestimo("123", "oiie");

        // ATIVIDADE

        DVD dvd1 = new DVD("Wicked: For Good", 2025, 120);
        minhaBiblioteca.cadastrarItem(dvd1);
        minhaBiblioteca.listarAcervo();
        minhaBiblioteca.realizarEmprestimo("123", "Wicked: For Good");
        minhaBiblioteca.realizarEmprestimo("123", "Wicked: For Good");


        Emprestimo emprestimoDVD = minhaBiblioteca.buscarEmprestimoAtivoPorItem(dvd1);
        emprestimoDVD.setDataDevolucaoPrevista(LocalDate.now().minusDays(5));

        minhaBiblioteca.realizarDevolucao("Wicked: For Good");
        minhaBiblioteca.listarAcervo();

        List<ItemDoAcervo> resultado = minhaBiblioteca.buscar("Deitel");
        System.out.println(resultado);

    }
}
