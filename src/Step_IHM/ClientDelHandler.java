package Step_IHM;

import Step_DB.Client;
import Step_DB.ClientDAOImpl;
import Step_DB.IClientDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ClientDelHandler {
	public void delClick(Client obj) {
		IClientDAO cdao = new ClientDAOImpl();
		

		cdao.delete(obj);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setContentText("Good, Client deleted ");

		alert.showAndWait();
	}
}
