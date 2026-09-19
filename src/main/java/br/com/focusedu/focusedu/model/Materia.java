package br.com.focusedu.focusedu.model;

public enum Materia {
	
    MATEMATICA("Matemática", "#EEEDFE", "#3C3489"),
    PORTUGUES("L. Portuguesa", "#FBEAF0", "#72243E"),
    HISTORIA("História", "#FAEEDA", "#633806"),
    GEOGRAFIA("Geografia", "#E6F1FB", "#0C447C"),
    CIENCIAS("Ciências", "#EAF3DE", "#27500A"),
    FILOSOFIA("Filosofia", "#E1F5EE", "#085041"),
    OUTRA("Outra", "#F1EFE8", "#444441");
	
    private final String rotulo;
    private final String corFundo;
    private final String corTexto;

    Materia(String rotulo, String corFundo, String corTexto) {
        this.rotulo = rotulo;
        this.corFundo = corFundo;
        this.corTexto = corTexto;
    }

    public String getRotulo() {
        return rotulo;
    }

    public String getCorFundo() {
        return corFundo;
    }

    public String getCorTexto() {
        return corTexto;
    }

    @Override
    public String toString() {
        return rotulo;
    }
}