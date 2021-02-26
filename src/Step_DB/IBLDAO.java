package Step_DB;

import java.util.List;

public interface IBLDAO extends IDAO<BL> {

	List<BL> findAll(String num);
	Long getLastid();
	List<BL> getAll(Client c);
}
