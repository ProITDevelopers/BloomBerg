package proitdevelopers.com.bloomberg.modelo;



import java.util.Comparator;


public class Revistas implements Comparator<Revistas> {


    public int id_jornal;
    public String nome;
    public String fotoJornal;
    public String link;
    public String categoria;
    public String dataEdicao;
    public String descricao;

    public Revistas() {}

    public Revistas(int id_jornal, String nome, String fotoJornal, String link, String categoria, String dataEdicao, String descricao) {
        this.id_jornal = id_jornal;
        this.nome = nome;
        this.fotoJornal = fotoJornal;
        this.link = link;
        this.categoria = categoria;
        this.dataEdicao = dataEdicao;
        this.descricao = descricao;
    }

    public int getId_jornal() {
        return id_jornal;
    }

    public void setId_jornal(int id_jornal) {
        this.id_jornal = id_jornal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFotoJornal() {
        return fotoJornal;
    }

    public void setFotoJornal(String fotoJornal) {
        this.fotoJornal = fotoJornal;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(String dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int compare(Revistas o1, Revistas o2) {
        // Order ascending.
        int ret = o1.getDataEdicao().compareTo(o2.getDataEdicao());

        return ret;
    }


}
