public class DVD extends ItemDoAcervo {

    private int duracaoMinutos;

    public DVD(String titulo, int ano, int duracaoMinutos) {
        super(titulo, ano);
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public int getPrazo() {
        return 3;
    }

    @Override
    public double getValorMultaPorDiaAtraso() {
        return 2.0;
    }

    @Override
    public String toString() {
        return "DVD '" + titulo + "', de (" + ano + ") - " + duracaoMinutos + " minutos - STATUS: " + getStatus();
    }

}
