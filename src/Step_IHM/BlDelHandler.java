package Step_IHM;

import Step_DB.BL;
import Step_DB.BLDAOImpl;
import Step_DB.IBLDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BlDelHandler {
	public void delClick(BL obj) {
		IBLDAO bdao = new BLDAOImpl();
		bdao.delete(obj);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Delete Bon de livraison");
		alert.setContentText("Done, BL deleted ");

		alert.showAndWait();
	}
}
