package cifra;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Winder Rezende
 */
public class TelaController implements Initializable {

    @FXML
    Button btnCrifrar;
    @FXML
    Button btnDecifrar;
    @FXML
    ComboBox boxTipo;
    @FXML
    TextArea txtMsgCifra;
    @FXML
    TextArea txtMsgDecifra;
    @FXML
    Text txtLabelChave;
    @FXML
    TextField txtChave;
    @FXML
    TextField txtPos;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boxTipo.getItems().add("César (Letras)");
        boxTipo.getItems().add("César (Números)");
        boxTipo.getItems().add("Vigenere");
        boxTipo.setValue("César (Letras)");
        txtPos.setText("3");
    }

    @FXML
    public void btnCrifrar_OnAction() {
        try {
            String mensagem = txtMsgCifra.getText();
            int K = Integer.parseInt(txtPos.getText());
            if (boxTipo.getValue().equals("César (Letras)")) {
                txtMsgDecifra.setText(Cifra.cifraCesar(mensagem, "cifrar", "letras", "letras", K));
            } else if (boxTipo.getValue().equals("César (Números)")) {
                txtMsgDecifra.setText(Cifra.cifraCesar(mensagem, "cifrar", "letras", "numeros", K));
            } else if (boxTipo.getValue().equals("Vigenere")) {
                String chave = txtChave.getText();
                txtMsgDecifra.setText(Cifra.cifraVigenere(mensagem, "cifrar", chave, K));
            }
        } catch (Exception e) {
            Exibir.Mensagem("Erro: \n" + e);
        }
    }

    @FXML
    public void btnDecrifrar_OnAction() {
        try {
            String mensagem = txtMsgDecifra.getText();
            int K = Integer.parseInt(txtPos.getText());
            if (boxTipo.getValue().equals("César (Letras)")) {
                txtMsgCifra.setText(Cifra.cifraCesar(mensagem, "decifrar", "letras", "letras", K));
            } else if (boxTipo.getValue().equals("César (Números)")) {
                txtMsgCifra.setText(Cifra.cifraCesar(mensagem, "decifrar", "numeros", "letras", K));
            } else if (boxTipo.getValue().equals("Vigenere")) {
                String chave = txtChave.getText();
                txtMsgCifra.setText(Cifra.cifraVigenere(mensagem, "decifrar", chave, K));
            }
        } catch (Exception e) {
            Exibir.Mensagem("Erro: \n" + e);
        }
    }

    @FXML
    public void btnLimpar_OnAction() {
        txtMsgDecifra.setText("");
        txtMsgCifra.setText("");
        txtChave.setText("");
        if (boxTipo.getValue().equals("Vigenere")) {
            txtPos.setText("0");
        } else {
            txtPos.setText("3");
        }
    }

    @FXML
    public void boxTipo_OnAction() {
        if (boxTipo.getValue().equals("César (Letras)")) {
            txtLabelChave.setVisible(false);
            txtChave.setVisible(false);
            txtPos.setText("3");
        } else if (boxTipo.getValue().equals("César (Números)")) {
            txtLabelChave.setVisible(false);
            txtChave.setVisible(false);
            txtPos.setText("3");
        } else if (boxTipo.getValue().equals("Vigenere")) {
            txtLabelChave.setVisible(true);
            txtChave.setVisible(true);
            txtChave.setText("chave");
            txtPos.setText("0");
        }
        System.out.println(boxTipo.getValue());
    }
}
