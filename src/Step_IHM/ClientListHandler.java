package Step_IHM;


import java.util.List;

import Step_DB.Client;
import Step_DB.ClientDAOImpl;
import Step_DB.IClientDAO;

public class ClientListHandler {
	ClientListWindow listWindow = null;
	public ClientListHandler(ClientListWindow listWindow) {
		this.listWindow = listWindow;
	}
	public void updateListClientsWindow() {
		listWindow.clientObsList.clear();
		IClientDAO pdao = new ClientDAOImpl();
		List<Client> list = pdao.getAll();
		listWindow.clientObsList.addAll(list);
	}
	
	public void getSearchClient() {
		IClientDAO cdao = new ClientDAOImpl();
		listWindow.clientObsList.clear();
		List<Client> list = cdao.findAll(listWindow.searchClientTF.getText());
		listWindow.clientObsList.addAll(list);
	}
}
