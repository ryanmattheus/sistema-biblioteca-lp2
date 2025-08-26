import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private livro[] acervo; //acervo é um vetor
    private List<usuario> usuariosCadastrados;

    private int acervo;

    public Biblioteca(){
        this.acervo = new ArrayList<>;
        this.acervo = 0;
        this.usuariosCadastrados = new ArrayList();
    }


    public livro pesquisarLivroPorTitulo(String titulo) {
        for(livro livro : this.acervo){
            if(livro.getTitulo().equalsIgnoreCase(titulo)){
                return livro;
            }
        }
        return null;

    }


    public usuario(){
        this.acervo = new ArrayList<>();
    }

    public void cadastrarUsuario(usuario usuario) {
        this.usuariosCadastrados.add(usuario);
        System.out.println("O usuário " + usuario.getNome() + " foi cadastrado com sucesso!");
    }







    public static void main(String[] args) {

        usuario meuUser = new usuario("Ryan", "teste", 12345);
        usuario user1 = new usuario("ryann", "ryan@gmail.com", 123456);


    }





}
