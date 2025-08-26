public class livro {

    private String titulo;
    private String autor;
    private int anoPublicacao;

    public livro(String titulo, String autor,int anoPublicacao) {  //metodo constutor

        this.titulo = titulo;  //this = um ponteiro para os atributos dessa classe com o metodo construtor.
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }
    public void setAno(int ano){
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
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
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
