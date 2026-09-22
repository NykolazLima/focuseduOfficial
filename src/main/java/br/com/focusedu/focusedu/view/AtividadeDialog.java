package br.com.focusedu.focusedu.view;

import java.time.LocalDate;

import br.com.focusedu.focusedu.model.Atividade;
import br.com.focusedu.focusedu.model.Materia;
import br.com.focusedu.focusedu.model.Prioridade;
import br.com.focusedu.focusedu.model.Status;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class AtividadeDialog extends Dialog<Atividade> {

	private final TextField campoTitulo = new TextField();
	private final ComboBox<Materia> campoMateria = new ComboBox<>();
	private final ComboBox<Status> campoStatus = new ComboBox<>();
	private final ComboBox<Prioridade> campoPrioridade = new ComboBox<>();
	private final DatePicker campoPrazo = new DatePicker();
	private final TextArea campoDescricao = new TextArea();
	private final Label erro = new Label();

	public AtividadeDialog(Atividade existente) {
		boolean edicao = existente != null;

		setTitle(edicao ? "Editar atividade" : "Nova atividade");
		setHeaderText(edicao
				? "Altere os dados da atividade."
				: "Preencha os dados da nova atividade.");

		ButtonType salvar = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(salvar, ButtonType.CANCEL);

		campoTitulo.setPromptText("Ex.: Resolver lista de Matemática");
		campoMateria.getItems().setAll(Materia.values());
		campoStatus.getItems().setAll(Status.values());
		campoPrioridade.getItems().setAll(Prioridade.values());
		campoDescricao.setPrefRowCount(3);
		campoDescricao.setWrapText(true);
		campoDescricao.setPromptText("Detalhes, páginas, links...");

		erro.getStyleClass().add("mensagem-erro");
		erro.setVisible(false);
		erro.setManaged(false);

		if (edicao) {
			campoTitulo.setText(existente.getTitulo());
			campoMateria.setValue(existente.getMateria());
			campoStatus.setValue(existente.getStatus());
			campoPrioridade.setValue(existente.getPrioridade());
			campoPrazo.setValue(existente.getPrazo());
			campoDescricao.setText(existente.getDescricao());
		} else {
			campoMateria.setValue(Materia.OUTRA);
			campoStatus.setValue(Status.A_FAZER);
			campoPrioridade.setValue(Prioridade.MEDIA);
			campoPrazo.setValue(LocalDate.now());
		}

		GridPane grade = new GridPane();
		grade.setHgap(12);
		grade.setVgap(10);
		grade.setPadding(new Insets(16, 16, 8, 16));

		grade.add(new Label("Título"), 0, 0);
		grade.add(campoTitulo, 1, 0, 3, 1);
		grade.add(new Label("Matéria"), 0, 1);
		grade.add(campoMateria, 1, 1);
		grade.add(new Label("Prazo"), 2, 1);
		grade.add(campoPrazo, 3, 1);
		grade.add(new Label("Status"), 0, 2);
		grade.add(campoStatus, 1, 2);
		grade.add(new Label("Prioridade"), 2, 2);
		grade.add(campoPrioridade, 3, 2);
		grade.add(new Label("Descrição"), 0, 3);
		grade.add(campoDescricao, 1, 3, 3, 1);
		grade.add(erro, 1, 4, 3, 1);

		GridPane.setHgrow(campoTitulo, Priority.ALWAYS);
		campoMateria.setMaxWidth(Double.MAX_VALUE);
		campoStatus.setMaxWidth(Double.MAX_VALUE);
		campoPrioridade.setMaxWidth(Double.MAX_VALUE);
		campoPrazo.setMaxWidth(Double.MAX_VALUE);

		getDialogPane().setContent(grade);
		getDialogPane().setPrefWidth(560);

		campoTitulo.textProperty().addListener((obs, a, b) -> esconderErro());

		getDialogPane().lookupButton(salvar).addEventFilter(
				javafx.event.ActionEvent.ACTION, evento -> {
					if (campoTitulo.getText() == null || campoTitulo.getText().isBlank()) {
						mostrarErro("Informe um título para a atividade.");
						campoTitulo.requestFocus();
						evento.consume();
					}
				});

		setResultConverter(botao -> {
			if (botao != salvar) {
				return null;
			}
			Atividade resultado = new Atividade();
			resultado.setTitulo(campoTitulo.getText().trim());
			resultado.setMateria(campoMateria.getValue());
			resultado.setPrioridade(campoPrioridade.getValue());
			resultado.setPrazo(campoPrazo.getValue());
			resultado.setDescricao(campoDescricao.getText());
			resultado.setStatus(campoStatus.getValue());
			return resultado;
		});
	}

	private void mostrarErro(String mensagem) {
		erro.setText(mensagem);
		erro.setVisible(true);
		erro.setManaged(true);
	}

	private void esconderErro() {
		erro.setVisible(false);
		erro.setManaged(false);
	}
}