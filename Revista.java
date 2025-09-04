public class Revista extends ItemDoAcervo{

    private int edicao;

    public Revista(String titulo, int Ano, int edicao) {
        super(titulo, Ano);
        setEdicao(edicao);
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    @Override
    public String toString() {
        return "Revista '" + getTitulo() + "' (" + getAno() + ") - Status: " + getStatus() + " / Edição: " + edicao;
    }
}