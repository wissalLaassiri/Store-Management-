package Step_IHM;


import Step_DB.Client;
import Step_DB.ClientDAOImpl;
import Step_DB.IClientDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ClientAddHandler {
	FormClientWindow addWindow = null;
	public ClientAddHandler(FormClientWindow addWin) {
		this.addWindow = addWin;
	}
	public void addClick() {
		IClientDAO cdao = new ClientDAOImpl();

		Client c = new Client(0, addWindow.clientNomTF.getText(), addWindow.clientPrenomTF.getText(),addWindow.clientTelTF.getText(),addWindow.clientEmailTF.getText(),addWindow.clientAdresseTF.getText());
		cdao.add(c);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setContentText("Good, Client added ");

		alert.showAndWait();
		
	}
	
	
}
