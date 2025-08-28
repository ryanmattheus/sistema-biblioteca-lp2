public class Livro {

    private String titulo;
    private String autor;
    private int anoPublicacao;

    public Livro(String titulo, String autor, int anoPublicacao) {  //metodo constutor

        setTitulo(titulo);  //this = um ponteiro para os atributos dessa classe com o metodo construtor.
        setAutor(autor);
        setAnoPublicacao(anoPublicacao);
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int ano){
        int ano_atual = 2025;
        if(ano <= ano_atual){
            this.anoPublicacao = ano;
            } else {
            System.out.println("O ano deve ser maior ou igual a 2025");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == "") {
            System.out.println("Erro: título inválido.");
        } else {
            this.titulo = titulo;
        }
    }

    public String getAutor() {return autor;}

    public void setAutor(String autor) {
        if (autor == "") {
            System.out.println("Erro: título inválido.");
        } else {
            this.autor = autor;
        }
    }

    @Override
    public String toString() {
        return "livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                '}';
    }


}
