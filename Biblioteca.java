
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervo;
    private List<Usuario> listaDeUsuarios;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
        this.listaDeUsuarios = new ArrayList<>();
    }

    public Livro pesquisarLivroPorTitulo(String titulo) {
        for(Livro livro : this.acervo) {
            if(livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> pesquisarLivroPorTermo(String termo) {
        List<Livro> listaDeLivros = new ArrayList<>();
        for(Livro livro : acervo) {  //vai percorrer todos os livros
            if(livro.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                listaDeLivros.add(livro);
            }
        }
        return listaDeLivros;
    }

    public void listarAcervo(){}

    public void cadastrarLivro(Livro livro) {
        this.acervo.add(livro);
        System.out.println("O livro " + livro.getTitulo() + " foi cadastrado.");
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.listaDeUsuarios.add(usuario);
        System.out.println("O usuário " + usuario.getNome() + " foi cadastrado.");
    }


    public static void main(String[] args) {
        Livro livroJava = new Livro("Java Como Programar", "Deitel", 2014);
        Usuario meuUsuario = new Usuario("Ryan", "123", "1234");
        Biblioteca minhaBiblioteca = new Biblioteca();
        minhaBiblioteca.cadastrarLivro(livroJava);
        minhaBiblioteca.cadastrarUsuario(meuUsuario);
        Livro livroEncontrado = minhaBiblioteca.pesquisarLivroPorTitulo("java como programar");
        System.out.println(livroEncontrado);
        List <Livro> resultado = minhaBiblioteca.pesquisarLivroPorTermo("java como programar");
        for(var livro : resultado) {
            System.out.println("Livros Encontrados: ");
            System.out.println(livro);
        }
    }
}