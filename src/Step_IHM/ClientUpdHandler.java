package Step_IHM;

import Step_DB.Client;
import Step_DB.ClientDAOImpl;
import Step_DB.IClientDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ClientUpdHandler {
	ClientListWindow updWindow = null;

	public ClientUpdHandler(ClientListWindow updWindow) {
		this.updWindow = updWindow;
	}

	public void updateClick(Client obj) {
		IClientDAO cdao = new ClientDAOImpl();
		Client c = cdao.getOne(obj.getId());
		c.setNom(String.valueOf(updWindow.clientNomTF.getText()));
		c.setPrenom(String.valueOf(updWindow.clientPrenomTF.getText()));
		c.setTel(String.valueOf(updWindow.clientTelTF.getText()));
		c.setEmail(String.valueOf(updWindow.clientEmailTF.getText()));
		c.setAdresse(String.valueOf(updWindow.clientAdresseTF.getText()));

		cdao.update(c);
		clearTextField();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setContentText("Good, Client updated ");

		alert.showAndWait();

	}
	public void clearTextField() {
		updWindow.clientNomTF.clear();
		updWindow.clientPrenomTF.clear();
		updWindow.clientTelTF.clear();
		updWindow.clientEmailTF.clear();
		updWindow.clientAdresseTF.clear();
	}
}
