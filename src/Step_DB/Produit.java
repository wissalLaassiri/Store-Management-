package Step_DB;

import java.time.LocalDate;

public class Produit {
	private long id;
	private String designation;
	private int qte;
	private double prix;
	private LocalDate date;
	private double total;
	private long idCategorie;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getTotal() {
		calculerTotal();
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
		calculerTotal();
	}
	private void calculerTotal() {
		total = this.qte * this.prix;
	}
	public Produit(long id,String des, double p, int q,LocalDate d, long idCategorie) {
		this.id = id;
		this.designation = des;
		this.qte = q;
		this.prix = p;
		this.date = d;
		this.idCategorie = idCategorie;
	}
	
	public Produit(long id,String des, double p) {
		this.id = id;
		this.designation = des;
		this.prix = p;
	}
	public String toString() {
		return designation +"\t"+qte+"\t"+prix+"\t"+date;
	}
	
	public long getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}
}
