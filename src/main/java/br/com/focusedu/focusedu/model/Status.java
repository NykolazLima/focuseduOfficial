package br.com.focusedu.focusedu.model;

public enum Status {

    A_FAZER("A fazer"),
    EM_PROGRESSO("Em progresso"),
    CONCLUIDA("Concluída");

    private final String rotulo;

    Status(String rotulo) {
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