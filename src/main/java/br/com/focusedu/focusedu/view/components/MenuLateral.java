package br.com.focusedu.focusedu.view.components;

import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class MenuLateral {
	public static VBox montar() {
		ToggleGroup grupo = new ToggleGroup();
		
		ToggleButton atividades = itemMenu("Atividades", grupo, true, false);
		ToggleButton materias = itemMenu("Matérias", grupo, false, false);
		ToggleButton calendario = itemMenu("Calendário", grupo, false, false);
		ToggleButton perfil = itemMenu("Perfil", grupo, false, false);
		
		VBox menu = new VBox(4, atividades, materias, calendario, perfil);
		menu.getStyleClass().add("menu-lateral");
		menu.setPrefWidth(170);
		menu.setPadding(new Insets(16, 10, 16, 10));
		return menu;
		
	}
	public static ToggleButton itemMenu(String texto, ToggleGroup grupo, boolean selecionado, boolean desabilitado) {
		
		ToggleButton btn = new ToggleButton(texto);
		btn.setToggleGroup(grupo);
		btn.setSelected(selecionado);
		btn.setDisable(desabilitado);
		btn.setMaxWidth(Double.MAX_VALUE);
		btn.getStyleClass().add("item-menu");
		return btn;
	}
}
