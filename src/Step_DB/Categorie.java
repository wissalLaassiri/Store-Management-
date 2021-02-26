package Step_DB;


public class Categorie {
	private long id;
	private String code;
	private String intitule;

	private Produit[] Tabprod = new Produit[20];

	public Categorie(long id, String code, String intitule) {
		this.id = id;
		this.code = code;
		this.intitule = intitule;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Produit[] getTabprod() {
		return Tabprod;
	}

	public void setTabprod(Produit[] tabprod) {
		Tabprod = tabprod;
	}

	public void addProd(Produit p) {
		for (int i = 0; i < Tabprod.length; i++) {
			if (Tabprod[i] == null) {
				Tabprod[i] = p;
				break;
			}
		}

	}
}
