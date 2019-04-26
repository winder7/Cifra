package cifra;

import javafx.scene.control.Alert;
/**
 * @Autor m159255
 * @Data 02/01/2019
 */
public class Exibir {

    public static void Mensagem(String mensagem) {
        Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
        dialogoAviso.setTitle("Mensagem");
        dialogoAviso.setHeaderText(null);
        dialogoAviso.setContentText(mensagem);
        dialogoAviso.showAndWait();
    }
}