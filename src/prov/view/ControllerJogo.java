package prov.view;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import prov.dao.JogoDao;
import prov.model.Jogo;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ControllerJogo extends Application {

	@FXML
	private Label lblCadastra;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtGenero;

	@FXML
	private TextField txtData;

	@FXML
	private TextField txtValor;

	@FXML
	private Button btnCadastraJogo;

	@FXML
	private TextField txtBuscaID;

	@FXML
	private Button btnBuscaJogo;

	@FXML
	private Button btnFecha;

	@FXML
	private Button btnAtualizaJogo;

	@FXML
	private Button btncancelloperacao;

	@FXML
	private Button btnExcluiJogo;

	@FXML
	private TextArea areaListaJogos;

	@FXML
	private Label lblEdita;

	@FXML
	void inserir(ActionEvent event) {
		Jogo jogo = pegaDados();
		int qnd = new JogoDao().inserir(jogo);
		limpaCampo();
		listarJogo();
		System.out.println(qnd);
	}

	@FXML
	void findbyID(ActionEvent event) {
		String ID = txtBuscaID.getText();
		Jogo jogo = null;

		if (!ID.equals("")) {
			try {
				int id = Integer.parseInt(ID);
				jogo = new JogoDao().findByID(id);

			} catch (Exception e) {

			}
			if (jogo != null) {

				lblCadastra.setVisible(false);
				lblEdita.setVisible(true);

				btnCadastraJogo.setVisible(false);
				btnAtualizaJogo.setVisible(true);

				txtNome.setText(jogo.getNome_jogo());
				txtGenero.setText(jogo.getGenero_jogo());
				DateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
				String data = formataData.format(jogo.getData_lancamento().getTime());
				txtData.setText(data);
				txtValor.setText(jogo.getValor_jogo() + "");

				btncancelloperacao.setVisible(true);
				btnExcluiJogo.setVisible(true);
			}
		}
	}

	@FXML
	void AlterarJogador(ActionEvent event) {
		Jogo jogo = pegaDados();
		int when = new JogoDao().alterar(jogo);
		listarJogo();
		limpaCampo();
	}

	@FXML
	void CancelarAcao(ActionEvent event) {
		limpaCampo();
	}

	@FXML
	void ExcluirJogo(ActionEvent event) {
		
		Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar exclusao");
        alerta.setHeaderText("Voce esta prestes a excluir este registro");
        alerta.setContentText("Deseja realmente exclui-lo?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("excluido");
            int ID = Integer.parseInt(txtBuscaID.getText());
            JogoDao jogoDao = new JogoDao();
              jogoDao.excluir(ID); 
              listarJogo(); 
              limpaCampo();
        } else if (result.get() == ButtonType.CANCEL){
            System.out.println("cancelado");
        }
		
		
	}
		/*TextInputDialog alertaa = new TextInputDialog();
		alertaa.setTitle("Confirmar exclusao");
		alertaa.setHeaderText("Voce esta prestes a excluir esse jogo");
		alertaa.getDialogPane().setContentText("Tem certeza que deseja excluir?");
		
		 Optional<int> result = alertaa.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("excluido");
            int ID = Integer.parseInt(txtBuscaID.getText());
            JogoDao jogoDao = new JogoDao();
              jogoDao.excluir(ID); 
              listarJogo(); 
              limpaCampo();
        } else if (result.get() == ButtonType.CANCEL){
            System.out.println("cancelado");
        }
	*/
		
		
		
	@FXML
	void SairAplicacao(ActionEvent event) {
		btnFecha.setOnAction(e -> Platform.exit());
	}

	private Jogo pegaDados() {

		Float valor = Float.parseFloat(txtValor.getText());
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		Date data_lanc = java.sql.Date.valueOf(txtData.getText());

		return new Jogo(txtNome.getText(), txtGenero.getText(), data_lanc, valor);
	}

	private void listarJogo() {
		areaListaJogos.clear();
		List<Jogo> listaJogo = new JogoDao().listAll();

		listaJogo.forEach(jogo -> {
			areaListaJogos.appendText(jogo.toString() + "\n");
		});

	}

	private void limpaCampo() {
		txtNome.clear();
		txtGenero.clear();
		txtData.clear();
		txtValor.clear();

		lblCadastra.setVisible(true);
		lblEdita.setVisible(false);

		btnCadastraJogo.setVisible(true);
		btnAtualizaJogo.setVisible(false);

		txtNome.requestFocus();

		btncancelloperacao.setVisible(false);
		btnExcluiJogo.setVisible(false);
	}

	public void execute() {
		launch();
	}

	@Override
	public void start(Stage stage) {

		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Jogo.fxml"));
			stage.setTitle("Tela dois");
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
