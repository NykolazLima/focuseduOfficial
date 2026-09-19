package br.com.focusedu.focusedu.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class ActionBar {
	private final Button btnConcluir = new Button("Concluir / Reabrir");
	private final Button btnDeletar = new Button("Excluir");
	private final Button btnEditar = new Button("Editar");
	private final Button btnCriar = new Button("+ Nova Atividade");
	
	public ActionBar(Runnable alternarConclusao, Runnable editar, Runnable excluir, Runnable criar) {
		btnConcluir.setOnAction(e->alternarConclusao.run());
		btnEditar.setOnAction(e->editar.run());
		btnDeletar.setOnAction(e->excluir.run());
		btnDeletar.getStyleClass().add("botao-perigo");
		btnCriar.setOnAction(e->criar.run());
		btnCriar.getStyleClass().add("btn-primario");
	}
	
	public HBox montar() {
		Label titulo = new Label("Atividade e Projetos Escolares");
		titulo.getStyleClass().add("titulo-secao");
		
		Region espaco = new Region();
		HBox.setHgrow(espaco, Priority.ALWAYS);
		HBox barra = new HBox(10, titulo, espaco, btnConcluir, btnEditar, btnDeletar, btnCriar);
		barra.setAlignment(Pos.CENTER_LEFT);
		return barra;
	}
	
}
