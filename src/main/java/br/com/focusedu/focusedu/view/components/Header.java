package br.com.focusedu.focusedu.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class Header {
	
	public static HBox montar() {
		Label logoFocus = new Label("Focus");
		logoFocus.getStyleClass().add("logo-focus");
		
		Label logoEdu = new Label("Edu");
		logoEdu.getStyleClass().add("logo-edu");
		HBox.setMargin(logoEdu, new Insets(0,0,0,-10));
		
		Region espaco = new Region();
		HBox.setHgrow(espaco, Priority.ALWAYS);
		
		Label usuario = new Label("@user");
		usuario.getStyleClass().add("usuario");
		
		HBox cabecalho = new HBox (10, logoFocus, logoEdu, espaco, usuario);
		
		cabecalho.getStyleClass().add("cabecalho");
		cabecalho.setAlignment(Pos.CENTER_LEFT);
		cabecalho.setPadding(new Insets(12,20,12,20));
		return cabecalho;
		
	}
}
