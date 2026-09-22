package br.com.focusedu.focusedu.model;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Atividade {

    private static int proximoId = 1;

    private final int id;
    private final StringProperty titulo = new SimpleStringProperty("");
    private final StringProperty descricao = new SimpleStringProperty("");
    private final ObjectProperty<Materia> materia = new SimpleObjectProperty<>(Materia.OUTRA);
    private final ObjectProperty<Prioridade> prioridade = new SimpleObjectProperty<>(Prioridade.MEDIA);
    private final ObjectProperty<Status> status = new SimpleObjectProperty<>(Status.A_FAZER);
    private final ObjectProperty<LocalDate> prazo = new SimpleObjectProperty<>(LocalDate.now());
    private final BooleanProperty concluida = new SimpleBooleanProperty(false);
    
    public Atividade(){
    	this.id = proximoId ++;
    	
    	concluida.addListener((obs, antigo, novo)->{
    		if(novo && getStatus() != Status.CONCLUIDA) {
    			setStatus(Status.CONCLUIDA);
    		} else if(!novo && getStatus() == Status.CONCLUIDA) {
    			setStatus(Status.A_FAZER);
    		}
    	});
    	
    	status.addListener((obs, antigo, novo)->{
    		boolean deveEstarMarcada = novo == Status.CONCLUIDA;
    		if(isConcluida() != deveEstarMarcada) {
    			setConcluida(deveEstarMarcada);
    		}
    	});
    	
    }
    
	public Atividade(String titulo, String descricao, Materia materia, Prioridade prioridade, Status status, LocalDate prazo) {
		this();
		setTitulo(titulo);
		setMateria(materia);
		setDescricao(descricao);
		setPrioridade(prioridade);
		setStatus(status);
		setPrazo(prazo);
	}


	public void copiarDe(Atividade outra) {
        setTitulo(outra.getTitulo());
        setDescricao(outra.getDescricao());
        setMateria(outra.getMateria());
        setPrioridade(outra.getPrioridade());
        setPrazo(outra.getPrazo());
        setStatus(outra.getStatus());
	}


    public int getId() {
        return id;
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public String getTitulo() {
        return titulo.get();
    }

    public void setTitulo(String valor) {
        titulo.set(valor);
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String valor) {
        descricao.set(valor);
    }

    public ObjectProperty<Materia> materiaProperty() {
        return materia;
    }

    public Materia getMateria() {
        return materia.get();
    }

    public void setMateria(Materia valor) {
        materia.set(valor);
    }

    public ObjectProperty<Prioridade> prioridadeProperty() {
        return prioridade;
    }

    public Prioridade getPrioridade() {
        return prioridade.get();
    }

    public void setPrioridade(Prioridade valor) {
        prioridade.set(valor);
    }

    public ObjectProperty<Status> statusProperty() {
        return status;
    }

    public Status getStatus() {
        return status.get();
    }

    public void setStatus(Status valor) {
        status.set(valor);
    }

    public ObjectProperty<LocalDate> prazoProperty() {
        return prazo;
    }

    public LocalDate getPrazo() {
        return prazo.get();
    }

    public void setPrazo(LocalDate valor) {
        prazo.set(valor);
    }

    public BooleanProperty concluidaProperty() {
        return concluida;
    }

    public boolean isConcluida() {
        return concluida.get();
    }

    public void setConcluida(boolean valor) {
        concluida.set(valor);
    }
	
}

