package br.com.focusedu.focusedu.view;

import java.time.LocalDate;

import br.com.focusedu.focusedu.model.Atividade;
import br.com.focusedu.focusedu.model.Materia;
import br.com.focusedu.focusedu.model.Prioridade;
import br.com.focusedu.focusedu.model.Status;
import br.com.focusedu.focusedu.view.components.ActionBar;
import br.com.focusedu.focusedu.view.components.AtividadeCards;
import br.com.focusedu.focusedu.view.components.Header;
import br.com.focusedu.focusedu.view.components.MenuLateral;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TelaMain {
	public BorderPane montarTela() {
		ObservableList<Atividade> atividades = FXCollections.observableArrayList(dadosIniciais());

		AtividadeCards cards = new AtividadeCards(atividades);

		ActionBar actionBar = new ActionBar(
				() -> atividades.stream().filter(Atividade::isConcluida).forEach(a -> a.setConcluida(false)),
				() -> {},
				() -> {},
				() -> atividades.add(novaAtividade()));

		ScrollPane rolagem = new ScrollPane(cards.montar());
		rolagem.setFitToWidth(true);
		rolagem.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		rolagem.getStyleClass().add("cards-scroll");

		VBox centro = new VBox(12, actionBar.montar(), rolagem);
		centro.setMaxHeight(Double.MAX_VALUE);
		VBox.setVgrow(rolagem, Priority.ALWAYS);

		BorderPane raiz = new BorderPane();
		raiz.setTop(Header.montar());
		raiz.setLeft(MenuLateral.montar());
		raiz.setCenter(centro);
		return raiz;
	}

	private Atividade novaAtividade() {
		return new Atividade("Nova atividade", "", Materia.OUTRA, Prioridade.MEDIA, Status.A_FAZER, LocalDate.now().plusDays(7));
	}

	private java.util.List<Atividade> dadosIniciais() {
		return java.util.List.of(
				new Atividade("Resumo de frações", "Estudar capítulo 3", Materia.MATEMATICA, Prioridade.ALTA, Status.EM_PROGRESSO, LocalDate.now()),
				new Atividade("Redação tema livre", "Mínimo 20 linhas", Materia.PORTUGUES, Prioridade.MEDIA, Status.A_FAZER, LocalDate.now().plusDays(2)),
				new Atividade("Mapa da Revolução", "Mapa mental da Independência", Materia.HISTORIA, Prioridade.MEDIA, Status.CONCLUIDA, LocalDate.now().minusDays(1)));
	}
}