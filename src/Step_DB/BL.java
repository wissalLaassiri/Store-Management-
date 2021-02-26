package Step_DB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BL {
	private long id;
	private LocalDate date;
	private double total;
	private long idclient;
	private List<LigneCommande> ligneTab = new ArrayList<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	public long getIdclient() {
		return idclient;
	}
	
	public BL(long id,  LocalDate date, double total,long idclient) {
		this.id = id;
		this.date = date;
		this.total = total;
		this.idclient = idclient;
	}
	
	public String toString() {
		return "";
	}
	public List<LigneCommande> getLigneTab() {
		return ligneTab;
	}
	public void setLigneTab(List<LigneCommande> ligneTab) {
		this.ligneTab = ligneTab;
	}
}
