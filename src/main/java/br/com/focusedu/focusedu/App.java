package br.com.focusedu.focusedu;

import java.util.Objects;

import br.com.focusedu.focusedu.view.TelaMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage palco) {
        Scene cena = new Scene(new TelaMain().montarTela());
        cena.getStylesheets().add(
        		getClass().getResource("/focusedu/css/style.css").toExternalForm());
        
        palco.setTitle("FocusEdu");
        palco.setScene(cena);
        palco.setMinHeight(560);
        palco.setMinWidth(900);
        palco.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}