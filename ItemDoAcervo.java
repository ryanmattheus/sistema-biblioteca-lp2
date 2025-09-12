public class ItemDoAcervo {

    protected String titulo;
    protected int ano;
    protected StatusItem status;

    public ItemDoAcervo(String titulo, int ano) {
        setTitulo(titulo);
        setAno(ano);
        this.status = StatusItem.DISPONIVEL;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        int ano_atual = 2025;
        if (ano > ano_atual) {
            System.out.println("Erro: ano inválido.");
        } else {
            this.ano = ano;
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

    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }

    public int getPrazo(){
        return 7;
    }

    public double getValorMultaPorDiaAtraso(){
        return 0.50;
    }
}
