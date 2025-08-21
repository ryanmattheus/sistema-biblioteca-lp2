public class Biblioteca {
    public static void main(String[] args) {
        livro livro1 = new livro("af que bosta", "ryan butera", 2008);
        livro livro2 = new livro("Como destruir sua vida", "Carolline", 2025);
        livro2.setAno(2027);

        System.out.println(livro2.toString());
    }



}