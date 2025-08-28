import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervo;
    private List<Usuario> usuariosCadastrados;

    public Biblioteca(){
        this.acervo = new ArrayList<>();
        this.usuariosCadastrados = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro){
        this.acervo.add(livro);
        System.out.println("O livro " + livro.getTitulo() + " foi cadastrado com sucesso!");
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.usuariosCadastrados.add(usuario);
        System.out.println("O usuário " + usuario.getNome() + " foi cadastrado com sucesso!");
    }

    public Livro pesquisarLivroPorTitulo(String titulo) {
        for(Livro livro : this.acervo){
            if(livro.getTitulo().equalsIgnoreCase(titulo)){
                return livro;
            }
        }
        return null;

    }

    public static void main(String[] args) {

        Livro livroteste = new Livro("minha vida em marte", "paulo gustavo", 2019);
        Usuario usuario = new Usuario("Ryan", "ryan@gmail.com","ryan");
        Biblioteca minhaBiblioteca = new Biblioteca();
        minhaBiblioteca.cadastrarLivro(livroteste);
        minhaBiblioteca.cadastrarUsuario(usuario);


    }





}
