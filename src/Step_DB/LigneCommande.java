package Step_DB;

public class LigneCommande {
	private long id;
	private int qte;
	private double sousTotal;
	private long idproduit;
	private long idBl;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public double getSousTotal() {
		return sousTotal;
	}
	public void setSousTotal(double sousTotal) {
		this.sousTotal = sousTotal;
	}
	
	public LigneCommande(long id, int qte, double sousTotal,long idproduit, long idBl) {
		super();
		this.id = id;
		this.qte = qte;
		this.sousTotal = sousTotal;
		this.idproduit = idproduit; 
		this.idBl = idBl;
	}
	
	public long getIdBl() {
		return idBl;
	}
	public long getIdproduit() {
		// TODO Auto-generated method stub
		return this.idproduit;
	}
	
	
}
