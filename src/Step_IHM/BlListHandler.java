package Step_IHM;

import java.util.List;

import Step_DB.BL;
import Step_DB.BLDAOImpl;
import Step_DB.Client;
import Step_DB.ClientDAOImpl;
import Step_DB.IBLDAO;
import Step_DB.IClientDAO;

public class BlListHandler {
	BlListWindow blWindow = null;

	public BlListHandler(BlListWindow blWindow) {
		this.blWindow = blWindow;
	}

	public void updateListBLWindow() {
		blWindow.bLObsList.clear();
		IBLDAO bdao = new BLDAOImpl();
		List<BL> list;
		if (blWindow.selectedClient != null)
			list = bdao.getAll(blWindow.selectedClient);
		else
			list = bdao.getAll();

		blWindow.bLObsList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		for (BL b : blWindow.bLObsList)
			total += b.getTotal();
		blWindow.totalValueLabel.setText(total + "");
	}

	public void getSearchBL() {
		IBLDAO bdao = new BLDAOImpl();
		blWindow.bLObsList.clear();
		List<BL> list = bdao.findAll(blWindow.searchProduitTF.getText());
		blWindow.bLObsList.addAll(list);
	}

	public Client findById(long id) {
		IClientDAO cdao = new ClientDAOImpl();
		Client p = cdao.getOne(id);
		return p;
	}
}
