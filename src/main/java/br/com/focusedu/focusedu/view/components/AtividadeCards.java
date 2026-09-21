package br.com.focusedu.focusedu.view.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.focusedu.focusedu.model.Atividade;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AtividadeCards {

	private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final VBox painel = new VBox(12);

	public AtividadeCards(ObservableList<Atividade> atividades) {
		painel.getStyleClass().add("cards-container");
		rebuild(atividades);
		atividades.addListener((ListChangeListener<Atividade>) mudanca -> rebuild(atividades));
	}

	public VBox montar() {
		return painel;
	}

	private void rebuild(ObservableList<Atividade> atividades) {
		painel.getChildren().clear();
		for (Atividade atividade : atividades) {
			painel.getChildren().add(card(atividade));
		}
	}

	private VBox card(Atividade atividade) {
		Region espaco = new Region();
		HBox.setHgrow(espaco, Priority.ALWAYS);

		Label etiquetaMateria = new Label(atividade.getMateria().getRotulo());
		etiquetaMateria.setStyle("-fx-background-color: " + atividade.getMateria().getCorFundo() + ";"
				+ " -fx-text-fill: " + atividade.getMateria().getCorTexto() + ";"
				+ " -fx-background-radius: 6; -fx-padding: 2 8 2 8;"
				+ " -fx-font-size: 11px; -fx-font-weight: bold;");

		Label status = new Label(atividade.getStatus().getRotulo());
		status.getStyleClass().add("card-status");

		HBox topo = new HBox(8, etiquetaMateria, espaco, status);

		Label titulo = new Label(atividade.getTitulo());
		titulo.getStyleClass().add("card-titulo");

		Label descricao = new Label(atividade.getDescricao());
		descricao.getStyleClass().add("card-descricao");
		descricao.setWrapText(true);

		Label prazo = new Label(prazoTexto(atividade));
		prazo.getStyleClass().add("card-prazo");
		if (atividade.getPrazo() != null && !atividade.isConcluida() && !atividade.getPrazo().isAfter(LocalDate.now())) {
			prazo.getStyleClass().add("prazo-vencido");
		}

		VBox card = new VBox(8, topo, titulo, descricao, prazo);
		card.getStyleClass().add("card");
		return card;
	}

	private String prazoTexto(Atividade atividade) {
		LocalDate prazo = atividade.getPrazo();
		if (prazo == null) {
			return "Sem prazo";
		}
		String texto = prazo.isEqual(LocalDate.now()) ? "Hoje" : prazo.format(FORMATO);
		return "Prazo: " + texto;
	}
}