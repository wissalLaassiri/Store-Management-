package Step_IHM;

import Step_IHM.ClientListWindow;
import Step_IHM.FormClientWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
	private BorderPane root = new BorderPane();
	private Scene scene = new Scene(root);
	// pour ajouter les événements je dois avoir l'acces aux items

	MenuItem nouveauProduitMenuItem = new MenuItem("Nouveau");
	MenuItem listeProduitMenuItem = new MenuItem("Liste");
	MenuItem nouveauClientMenuItem = new MenuItem("Nouveau");
	MenuItem listeClientMenuItem = new MenuItem("Liste");
	MenuItem nouveauVenteMenuItem = new MenuItem("Nouveau");
	MenuItem listeVenteMenuItem = new MenuItem("Liste");
	MenuItem nouveauPaiementMenuItem = new MenuItem("Nouveau");
	MenuItem listePaiementMenuItem = new MenuItem("Liste");
	MenuItem nouveauInventaireMenuItem = new MenuItem("Nouveau");
	MenuItem listeInventaireMenuItem = new MenuItem("Liste");
	MenuItem helpMenuItem = new MenuItem("?");

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/style.css");
		root.getStyleClass().add("mainWindow");
	}

	private void createMenu() {
		MenuBar menuBar = new MenuBar();
		Menu produitMenu = new Menu("Produits");
		Menu clientsMenu = new Menu("Clients");
		Menu ventesMenu = new Menu("Ventes");
		Menu paiementsMenu = new Menu("Paiements");
		Menu inventairesMenu = new Menu("Inventaire");
		Menu helpMenu = new Menu("Help");

		produitMenu.getItems().addAll(nouveauProduitMenuItem, listeProduitMenuItem);
		clientsMenu.getItems().addAll(nouveauClientMenuItem, listeClientMenuItem);
		ventesMenu.getItems().addAll(nouveauVenteMenuItem, listeVenteMenuItem);
		paiementsMenu.getItems().addAll(nouveauPaiementMenuItem, listePaiementMenuItem);
		inventairesMenu.getItems().addAll(nouveauInventaireMenuItem, listeInventaireMenuItem);
		helpMenu.getItems().add(helpMenuItem);

		// ajouter le menu à la barre menu menuBar.getMenus().add(produitMenu);
		// ajouter tous les menu au meme temps
		menuBar.getMenus().addAll(produitMenu, clientsMenu, ventesMenu, paiementsMenu, inventairesMenu, helpMenu);

		// ajouter le menu barre au top du scene par le border pane
		root.setTop(menuBar);

	}

	private void addEvents() {
		nouveauProduitMenuItem.setOnAction(event -> {
			new FormProduitWindow();
		});
		listeProduitMenuItem.setOnAction(event->{
			new ProduitsListWindow();
		});
		nouveauClientMenuItem.setOnAction(event -> {
			new FormClientWindow();
		});
		listeClientMenuItem.setOnAction(event->{
			new ClientListWindow();
		});
		listeVenteMenuItem.setOnAction(event ->{
			new BlListWindow();
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		// creer la fenêtre principale
		// 1- creer une scène dans le stage
		// a- créer un BorderPane pour l'envoyer comme paramètre dans le constructeur
		// ( BorderPane contient : Top, left, right, center, bottom)
		// 2- créer un conteneur qui appartient à une scène
		// 3- intégrer le style css
		// 4- créer le menu barre
		
		createMenu();
		addEvents();
		addStylesToNodes();

		window.setScene(scene);
		window.setWidth(1100);
		window.setHeight(650);
		window.setTitle("Gestion de magasin");
		window.getIcons().add(new Image("file:icon.png"));
		window.show();
	}
}
