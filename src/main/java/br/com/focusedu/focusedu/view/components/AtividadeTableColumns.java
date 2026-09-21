package br.com.focusedu.focusedu.view.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import br.com.focusedu.focusedu.model.Atividade;
import br.com.focusedu.focusedu.model.Materia;
import br.com.focusedu.focusedu.model.Status;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.HBox;

public class AtividadeTableColumns {
	
	private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final Consumer<Atividade> aoEditar;
	private final Consumer<Atividade> aoExcluir;
	
	public AtividadeTableColumns(Consumer<Atividade> aoEditar, Consumer<Atividade> aoExcluir) {
		this.aoEditar = aoEditar;
		this.aoExcluir = aoExcluir;
	}
	
	public TableColumn<Atividade, Boolean> concluida(){
		TableColumn<Atividade, Boolean> coluna = new TableColumn<>("");
		coluna.setPrefWidth(40);
		coluna.setSortable(false);
		coluna.setResizable(false);
		coluna.setCellValueFactory(dados->dados.getValue().concluidaProperty());
		coluna.setCellFactory(CheckBoxTableCell.forTableColumn(coluna));
		return coluna;
	}
	
	public TableColumn<Atividade, String> titulo(){
		TableColumn<Atividade, String> coluna = new TableColumn<>("Título");
		coluna.setPrefWidth(320);
		coluna.setCellValueFactory(dados -> dados.getValue().tituloProperty());
		return coluna;
	}
	
	public TableColumn<Atividade, Materia> materia(){
		TableColumn<Atividade, Materia> coluna = new TableColumn<>("Matéria");
		coluna.setPrefWidth(130);
		coluna.setCellValueFactory(dados -> dados.getValue().materiaProperty());
		coluna.setCellFactory(c -> new TableCell<>() {
			private final Label etiqueta = new Label();
			
			@Override
			protected void updateItem(Materia materia, boolean vazio) {
				super.updateItem(materia, vazio);
				if(vazio || materia == null) {
					setGraphic(null);
					return;
				}
				etiqueta.setText(materia.getRotulo());
				etiqueta.setStyle("-fx-background-color: " + materia.getCorFundo() + ";"
                        + " -fx-text-fill: " + materia.getCorTexto() + ";"
                        + " -fx-background-radius: 6; -fx-padding: 2 8 2 8; -fx-font-size: 11px;");
				setGraphic(etiqueta);
			}
		});
		return coluna;
	}
	
    public TableColumn<Atividade, LocalDate> prazo() {
        TableColumn<Atividade, LocalDate> coluna = new TableColumn<>("Prazo");
        coluna.setPrefWidth(100);
        coluna.setCellValueFactory(dados -> dados.getValue().prazoProperty());
        coluna.setCellFactory(c -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate prazo, boolean vazio) {
                super.updateItem(prazo, vazio);
                getStyleClass().remove("prazo-vencido");
                if (vazio || prazo == null) {
                    setText(null);
                    return;
                }
                Atividade atividade = getTableRow() == null ? null : getTableRow().getItem();
                boolean pendente = atividade != null && !atividade.isConcluida();

                if (prazo.isEqual(LocalDate.now())) {
                    setText("Hoje");
                } else {
                    setText(prazo.format(FORMATO));
                }
                if (pendente && !prazo.isAfter(LocalDate.now())) {
                    getStyleClass().add("prazo-vencido");
                }
            }
        });
        return coluna;
    }

    public TableColumn<Atividade, Status> status() {
        TableColumn<Atividade, Status> coluna = new TableColumn<>("Status");
        coluna.setPrefWidth(120);
        coluna.setCellValueFactory(dados -> dados.getValue().statusProperty());
        coluna.setCellFactory(c -> new TableCell<>() {
            @Override
            protected void updateItem(Status status, boolean vazio) {
                super.updateItem(status, vazio);
                setText(vazio || status == null ? null : status.getRotulo());
            }
        });
        return coluna;
    }

    public TableColumn<Atividade, Void> acoes() {
        TableColumn<Atividade, Void> coluna = new TableColumn<>("Ações");
        coluna.setPrefWidth(150);
        coluna.setSortable(false);
        coluna.setCellFactory(c -> new TableCell<>() {
            private final Button botaoEditar = new Button("Editar");
            private final Button botaoExcluir = new Button("Excluir");
            private final HBox caixa = new HBox(6, botaoEditar, botaoExcluir);

            {
                caixa.setAlignment(Pos.CENTER_LEFT);
                botaoEditar.getStyleClass().add("botao-acao");
                botaoExcluir.getStyleClass().addAll("botao-acao", "botao-perigo");
                botaoEditar.setOnAction(e -> aoEditar.accept(getTableRow().getItem()));
                botaoExcluir.setOnAction(e -> aoExcluir.accept(getTableRow().getItem()));
            }

            @Override
            protected void updateItem(Void item, boolean vazio) {
                super.updateItem(item, vazio);
                setGraphic(vazio || getTableRow() == null || getTableRow().getItem() == null
                        ? null : caixa);
            }
        });
        return coluna;
    }
	
}
