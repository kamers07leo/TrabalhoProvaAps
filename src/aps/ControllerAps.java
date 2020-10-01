package aps;

import java.awt.event.ActionEvent;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class ControllerAps extends Application {


    @FXML
    private TextArea listagem;

    @FXML
    private Button btnTextImputDialog;

    @FXML
    private Button btnChoiceDialog;


    @FXML
    private Label lblNome;
    

    @FXML
    void imputa (ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Insira");
    	dialog.setHeaderText("Coloque seu nome");
    	dialog.setContentText("Nome: ");
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()) {
    		lblNome.setText(result.get());
    	}
    }
    
    
    
    @FXML
    void choicea(ActionEvent event) {
    	ChoiceDialog<String> dialog = new ChoiceDialog<>("opcao 1", "opcao 2", "opcao 3");
    	dialog.setTitle("Selecione");
    	dialog.setHeaderText("Opcoes disponiveis");
    	dialog.setContentText("Selecione sua opcao");
    	
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()) {
    		listagem.appendText(result.get() + "\n");
    	}
    }





	public void execute() {
		launch();
		
	}





	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
    


}
