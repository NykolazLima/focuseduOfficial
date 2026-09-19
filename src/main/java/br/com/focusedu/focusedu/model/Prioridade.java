package br.com.focusedu.focusedu.model;

public enum Prioridade {

    BAIXA("Baixa"),
    MEDIA("Média"),
    ALTA("Alta");

    private final String rotulo;

    Prioridade(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }

    @Override
    public String toString() {
        return rotulo;
    }
}