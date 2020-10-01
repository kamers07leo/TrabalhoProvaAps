package trab.viewTrab;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import trab.daoTrab.JogadorDao;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import trab.modelTrab.Jogador;

public class ControllerJogador extends Application implements Initializable {

	@FXML
	private AnchorPane txtAMostraJogador;

	@FXML
	private Label lblCadastre;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtTime;

	@FXML
	private Button btnCadastraJogador;

	@FXML
	private TextField txtNome;

	@FXML
	private Button btnBuscaJogador;

	@FXML
	private TextField txtBuscaCpf;

	@FXML
	private Button btnExclui;

	@FXML
	private Button btncancell;

	@FXML
	private Button btnAtt;

	@FXML
	private Label lblAttu;

	@FXML
	private Button btnFecha;

	@FXML
	private TextArea areaListaJogadores;

	@FXML
	void inserir(ActionEvent event) {
		Jogador jogador = pegaDados();
		int qnd = new JogadorDao().inserir(jogador);
		limpaCampo();
		listarJogador();
		System.out.println(qnd);
	}

	@FXML
	void findbyCPF(ActionEvent event) {
		String CPF = txtBuscaCpf.getText();
		Jogador jogador = null;
		
		if (!CPF.equals("")) {
			try {
				jogador = new JogadorDao().findByCPF(CPF);
			} catch (Exception e) {

			}
			if (jogador != null) {

				/*
				 * lblCpf.setText(jogador.getCpf()); lblNome.setText(jogador.getNome());
				 * lblEmail.setText(jogador.getEmail()); lblTime.setText(jogador.getTime());
				 */

				lblCadastre.setVisible(false);
				lblAttu.setVisible(true);

				btnCadastraJogador.setVisible(false);
				btnAtt.setVisible(true);

				txtCPF.setText(jogador.getCpf());
				txtNome.setText(jogador.getNome());
				txtEmail.setText(jogador.getEmail());
				txtTime.setText(jogador.getTime());

				btncancell.setVisible(true);
				btnExclui.setVisible(true);
			}
		}
	}

	@FXML
	void AlterarJogador(ActionEvent event) {
		Jogador jogador = pegaDados();
		int when = new JogadorDao().alterar(jogador);
		listarJogador();
		limpaCampo();
	}

	@FXML
	void CancelarAcao(ActionEvent event) {
		limpaCampo();
	}

	@FXML
	void ExcluirJogador(ActionEvent event) {
		String CPF = txtBuscaCpf.getText();
		JogadorDao jogadorDao = new JogadorDao();
		jogadorDao.excluir(CPF);
		listarJogador();
		limpaCampo();
	}

	@FXML
	void SairAplicacao(ActionEvent event) {
		
		Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar Saida");
        alerta.setHeaderText("Voce esta prestes a sair do sistema");
        alerta.setContentText("Deseja realmente sair?");

        Optional<ButtonType> result = alerta.showAndWait();
		
        if (result.get() == ButtonType.OK) {
            System.out.println("Saindo");

		btnFecha.setOnAction(e -> Platform.exit());
		
        }else if (result.get() == ButtonType.CANCEL){
            System.out.println("Cancelado");
        }
	}

	private Jogador pegaDados() {
		return new Jogador(txtCPF.getText(), txtNome.getText(), txtEmail.getText(), txtTime.getText());
	}

	private void listarJogador() {
		areaListaJogadores.clear();
		List<Jogador> listaJogador = new JogadorDao().listarTodos();

		listaJogador.forEach(jogador -> {
			areaListaJogadores.appendText(jogador.toString() + "\n");
		});

	}

	private void limpaCampo() {
		txtCPF.clear();
		txtNome.clear();
		txtEmail.clear();
		txtBuscaCpf.clear();
		txtTime.clear();

		/*
		 * lblCpf.setText(""); lblNome.setText(""); lblEmail.setText("");
		 * lblTime.setText("");
		 */
		lblCadastre.setVisible(true);
		lblAttu.setVisible(false);

		btnCadastraJogador.setVisible(true);
		btnAtt.setVisible(false);

		txtCPF.requestFocus();

		btncancell.setVisible(false);
		btnExclui.setVisible(false);
	}

	public void execute() {
		launch();
	}

	@Override
	public void start(Stage stage) {

		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Jogador.fxml"));
			stage.setTitle("Tela um");
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarJogador();
	}

}