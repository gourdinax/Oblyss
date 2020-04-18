package controler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Hero;
import model.HotBar;
import model.Inventory;
import model.Item;
import model.Sky;
import model.Tile;
import model.map;
import javafx.animation.TranslateTransition;

public class SampleControler implements Initializable, ListChangeListener<Tile> {

	private double deplacement = 32;
	private double rangeEnemyNear = 200;
	private map m;
	private Inventory inventory;
	private HotBar hotBar;
	private int temps;
	private Timeline gameLoop;
	private int compt;
	private double posClicX, posClicY;
	private int indexTile;
	private int resteXH;
	private int resteYH;
	private int countTestHotBar;
	private int countTestAll;
	private int caseCurrent;
	private Tile currentTile;
	private boolean isRemove;
	private boolean isFullHotBar;
	private Hero hero;
	private double heroXH;
	private double heroYH;
	
	private int countStone;
	private int countOak;
	private int caseStone;
	private int caseOak;
	private int count;

	//FXML pour la scène principale
	@FXML private ImageView heroView;
	@FXML private TilePane tilePane;
	@FXML private SubScene subSceneHero;
	@FXML private Tab tabPaneMap;
	@FXML private ImageView enemyNear;
	@FXML private Tab tabPaneInventaire;
	@FXML private Button Quitter;
	@FXML private Button jouer;
	@FXML private Tab tabPaneMenu;
	@FXML private Button Options1;
	@FXML private VBox vBoxOptions;
	@FXML private Button Retour;
	@FXML private ProgressBar barExperience;
	@FXML private ProgressBar barLife;
	@FXML private ProgressBar barLifeEnemy;
	@FXML private TabPane tabPane;
	@FXML private ImageView gobelin;
	@FXML private ImageView necromancian;
	@FXML private AnchorPane anchorPaneMap;
	@FXML private GridPane hotBarGrid;

	//attributs côté modèle pour inventaire
	private ArrayList<TextField> hotBarCount;
	private ArrayList<ImageView> hotBarImage;
	private Tile hotBarObject[];
	private ArrayList<TextField> hotBarCountI;
	private ArrayList<ImageView> hotBarImageI;
	private Tile hotBarObjectI[];
	private ArrayList<TextField> inventoryCount;
	private ArrayList<ImageView> inventoryImage;
	private Tile inventoryObject[];
	private ArrayList<ImageView> inventoryImageItem;
	
	//FXML pour l'inventaire
	@FXML private AnchorPane baseAnchor;
	@FXML private AnchorPane leftAnchor;
	@FXML private TextField resourceField;
	@FXML private Pane backItemIV;
	@FXML private ImageView itemIV;
	@FXML private Button craftButton;
	@FXML private ScrollPane listScroll;
	@FXML private VBox listVBox;
	@FXML private AnchorPane rightAnchor;
	@FXML private VBox statsVbox;
	@FXML private VBox statsItem;
	@FXML private AnchorPane mainAnchor;
	@FXML private GridPane hotBarGridI;
	@FXML private AnchorPane heroAnchor;
	@FXML private Pane background_heroImageV;
	@FXML private ImageView heroImageV;
	@FXML private GridPane inventoryGrid;
	
	//FXML pour la hotbar
	@FXML private ImageView imageHotBar1;
	@FXML private ImageView imageHotBar2;
	@FXML private ImageView imageHotBar3;
	@FXML private ImageView imageHotBar4;
	@FXML private ImageView imageHotBar5;
	@FXML private ImageView imageHotBar6;

	@FXML private TextField hotBarCount1;
	@FXML private TextField hotBarCount2;
	@FXML private TextField hotBarCount3;
	@FXML private TextField hotBarCount4;
	@FXML private TextField hotBarCount5;
	@FXML private TextField hotBarCount6;
	
	@FXML private ImageView imageHotBarI1;
	@FXML private ImageView imageHotBarI2;
	@FXML private ImageView imageHotBarI3;
	@FXML private ImageView imageHotBarI4;
	@FXML private ImageView imageHotBarI5;
	@FXML private ImageView imageHotBarI6;
	
	@FXML private TextField hotBarCountI1;
	@FXML private TextField hotBarCountI2;
	@FXML private TextField hotBarCountI3;
	@FXML private TextField hotBarCountI4;
	@FXML private TextField hotBarCountI5;
	@FXML private TextField hotBarCountI6;
	
	//FXML pour l'inventaire du tabpane inventaire
	@FXML private TextField inventoryCount1;
	@FXML private TextField inventoryCount2;
	@FXML private TextField inventoryCount3;
	@FXML private TextField inventoryCount4;
	@FXML private TextField inventoryCount5;
	@FXML private TextField inventoryCount6;
	@FXML private TextField inventoryCount7;
	@FXML private TextField inventoryCount8;
	@FXML private TextField inventoryCount9;
	@FXML private TextField inventoryCount10;
	@FXML private TextField inventoryCount11;
	@FXML private TextField inventoryCount12;
	@FXML private TextField inventoryCount13;
	@FXML private TextField inventoryCount14;
	@FXML private TextField inventoryCount15;
	@FXML private TextField inventoryCount16;
	@FXML private TextField inventoryCount17;
	@FXML private TextField inventoryCount18;
	@FXML private TextField inventoryCount19;
	@FXML private TextField inventoryCount20;
	@FXML private TextField inventoryCount21;
	@FXML private TextField inventoryCount22;
	@FXML private TextField inventoryCount23;
	@FXML private TextField inventoryCount24;
	@FXML private TextField inventoryCount25;
	@FXML private TextField inventoryCount26;
	@FXML private TextField inventoryCount27;
	@FXML private TextField inventoryCount28;
	@FXML private TextField inventoryCount29;
	@FXML private TextField inventoryCount30;

	@FXML private ImageView inventoryImage1;
	@FXML private ImageView inventoryImage2;
	@FXML private ImageView inventoryImage3;
	@FXML private ImageView inventoryImage4;
	@FXML private ImageView inventoryImage5;
	@FXML private ImageView inventoryImage6;
	@FXML private ImageView inventoryImage7;
	@FXML private ImageView inventoryImage8;
	@FXML private ImageView inventoryImage9;
	@FXML private ImageView inventoryImage10;
	@FXML private ImageView inventoryImage11;
	@FXML private ImageView inventoryImage12;
	@FXML private ImageView inventoryImage13;
	@FXML private ImageView inventoryImage14;
	@FXML private ImageView inventoryImage15;
	@FXML private ImageView inventoryImage16;
	@FXML private ImageView inventoryImage17;
	@FXML private ImageView inventoryImage18;
	@FXML private ImageView inventoryImage19;
	@FXML private ImageView inventoryImage20;
	@FXML private ImageView inventoryImage21;
	@FXML private ImageView inventoryImage22;
	@FXML private ImageView inventoryImage23;
	@FXML private ImageView inventoryImage24;
	@FXML private ImageView inventoryImage25;
	@FXML private ImageView inventoryImage26;
	@FXML private ImageView inventoryImage27;
	@FXML private ImageView inventoryImage28;
	@FXML private ImageView inventoryImage29;
	@FXML private ImageView inventoryImage30;
	
	//saisie de l'item courant
	private String currentItem;

	//méthode pour craft un item en fonction des ressources disponibles dans la hotbar
	@FXML
	void craft(ActionEvent event) {
		countStone = 0;
		countOak = 0;
		caseStone=0;
		caseOak=0;
		
		for (int i = 0; i < countItemInHotBar(); i++) {
			if (hotBarObject[i].getId() == 5) {
				if (hotBar.getCount(i) >= 3) {
					countStone = hotBar.getCount(i);
					caseStone=i;
				}
			} else if (hotBarObject[i].getId() == 11) {
				if (hotBar.getCount(i) >= 2) {
					countOak = hotBar.getCount(i);
					caseOak=i;
				}
			}
		}
		if (countOak >= 2 && countStone >=3) {	
			Image im = new Image("/image/WoodenPickaxe.png");
			hotBar.decrement(caseStone);
			hotBar.decrement(caseStone);
			hotBar.decrement(caseStone);
			hotBar.decrement(caseOak);
			hotBar.decrement(caseOak);

			for(int y = 0; y<inventoryImageItem.size(); y++) {
				if(getBarImageInArrayItem(y).getImage()==null) {
					getBarImageInArrayItem(y).setImage(im);
					y=inventoryImageItem.size();
				}
			}
		}
		setHotBarTest();
	}
	
	public ImageView getBarImageInArrayItem(int i) {
		return inventoryImageItem.get(i);
	}
	
	//compte le nombre d'item
	public int countItemInHotBar() {
		count = 0;
		for (int i = 0; i < hotBarObject.length; i++) {
			if (hotBar.getCount(i) != 0) {
				count++;
			}
		}
		return count;
	}
	
	public void installingCraftList() {
		for (Item itm : inventory.getItemList()) {
			String str = itm.getName();
			// déclaration d'un nouveau TF auquel on attribue le str de la liste
			TextField TF = new TextField(str);
			TF.setId(str);
			// mise en style
			listScroll.setFitToWidth(true);
			listScroll.setStyle("-fx-border-color: black;");
			TF.setEditable(false);
			TF.setStyle("-fx-background-color: black; -fx-text-fill: yellow; -fx-border-color: black;");
			TF.setCursor(Cursor.HAND);
			// mise en action
			TF.setOnMouseClicked(e -> itemSelection(e));
			// ajout du TF à la VBox
			listVBox.getChildren().add(TF);
		}
	}

	//permets de récupérer l'item sélectionné
	private void itemSelection(MouseEvent e) {
		TextField TF = (TextField) e.getSource();
		currentItem = TF.getId();
		resourceField.appendText("Il vous faut 2 de bois et 3 de stone.");
	}
	
	//lance le menu
	@FXML
	void lancerJeu(ActionEvent event) throws FileNotFoundException {
		tabPane.getSelectionModel().select(tabPaneMap);
		tabPaneMenu.setDisable(true);
		tabPaneInventaire.setDisable(true);
		anchorPaneMap.setFocusTraversable(true);
		anchorPaneMap.setTranslateY(-736);
	}

	//lance le menu d'options
	@FXML
	void onActionOptions(ActionEvent event) {
		jouer.setVisible(false);
		Options1.setVisible(false);
		Retour.setVisible(true);
		vBoxOptions.setVisible(true);
	}
	
	//gestion des bordures de map
	public boolean isBorderMapLeftRight() {
		if ((hero.getPosxP().get() <= 544 && anchorPaneMap.getTranslateX() >= 0)
				|| (hero.getPosxP().get() >= 2592 && anchorPaneMap.getTranslateX() <= -2112)) {
			return true;
		}
		return false;
	}

	public boolean isBorderMapUpDown() {
		if ((anchorPaneMap.getTranslateY() <= 0 && hero.getPosyP().get() <= 352)
				|| (anchorPaneMap.getTranslateY() <= -2496 && hero.getPosyP().get() >= 2816)) {
			return true;
		}
		return false;
	}

	public boolean isBorderMapLeftHero() {
		if (hero.getPosxP().get() == 0) {
			return true;
		}
		return false;
	}

	public boolean isBorderMapRightHero() {
		if (hero.getPosxP().get() == 3168) {
			return true;
		}
		return false;
	}

	public boolean isBorderMapUpHero() {
		if (hero.getPosyP().get() == 0) {
			return true;
		}
		return false;
	}

	public boolean isBorderMapDownHero() {
		if (hero.getPosyP().get() == 3136) {
			return true;
		}
		return false;
	}

	//retour à l'écran de jeu
	@FXML
	void onActionRetour(ActionEvent event) {
		jouer.setVisible(true);
		Options1.setVisible(true);
		Retour.setVisible(false);
		vBoxOptions.setVisible(false);
	}

	//quitte le jeu
	@FXML
	void onActionQuitter(ActionEvent event) {
		Platform.exit();
	}

	public int getX() {
		return (int) (hero.getPosxP().get());
	}

	public int getY() {
		return (int) (hero.getPosyP().get());
	}
	
	public boolean testMining() {
		resteXH = (int) hero.getPosxP().get() % 32;
		resteYH = (int) hero.getPosyP().get() % 32;
		heroXH = (int) hero.getPosxP().get() - resteXH;
		heroYH = (int) hero.getPosyP().get() - resteYH;

		if ((posClicX - heroXH == 32 || posClicX - heroXH == -32 || posClicX - heroXH == 0) && (posClicY - heroYH == 64
				|| posClicY - heroYH == -32 || posClicY - heroYH == 32 || posClicY - heroYH == 0)) {
			return true;
		}
		return false;
	}

	//ajout de la hotbar et initialisation de l'inventaire
	public void bindHotBar() {
		hotBarCount = new ArrayList<TextField>();
		hotBarImage = new ArrayList<ImageView>();
		hotBarObject = new Tile[6];

		hotBarCountI = new ArrayList<TextField>();
		hotBarImageI = new ArrayList<ImageView>();
		hotBarObjectI = new Tile[6];

		inventoryCount = new ArrayList<TextField>();
		inventoryImage = new ArrayList<ImageView>();
		inventoryImageItem = new ArrayList<ImageView>();
		inventoryObject = new Tile[30];

		/* beaucoup de redondances et d'ajouts "bruts" car une boucle
		 * car nous n'arrivons pas à initialiser les attributs dans deux boucles for 
		 */
		hotBarCount.add(hotBarCount1);
		hotBarCount.add(hotBarCount2);
		hotBarCount.add(hotBarCount3);
		hotBarCount.add(hotBarCount4);
		hotBarCount.add(hotBarCount5);
		hotBarCount.add(hotBarCount6);
		
		hotBarCountI.add(hotBarCountI1);
		hotBarCountI.add(hotBarCountI2);
		hotBarCountI.add(hotBarCountI3);
		hotBarCountI.add(hotBarCountI4);
		hotBarCountI.add(hotBarCountI5);
		hotBarCountI.add(hotBarCountI6);
		
		hotBarImage.add(imageHotBar1);
		hotBarImage.add(imageHotBar2);
		hotBarImage.add(imageHotBar3);
		hotBarImage.add(imageHotBar4);
		hotBarImage.add(imageHotBar5);
		hotBarImage.add(imageHotBar6);

		hotBarImageI.add(imageHotBarI1);
		hotBarImageI.add(imageHotBarI2);
		hotBarImageI.add(imageHotBarI3);
		hotBarImageI.add(imageHotBarI4);
		hotBarImageI.add(imageHotBarI5);
		hotBarImageI.add(imageHotBarI6);
		
		for (int i = 0; i < 6; i++) {
			hotBarCount.get(i).setText(" ");
			hotBarCountI.get(i).setText(" ");
			
			this.hotBarCount.get(i).textProperty().bind(hotBar.getHotBar()[i].asString());
			this.hotBarCountI.get(i).textProperty().bind(hotBar.getHotBar()[i].asString());
		}

		inventoryCount.add(inventoryCount1);
		inventoryCount.add(inventoryCount2);
		inventoryCount.add(inventoryCount3);
		inventoryCount.add(inventoryCount4);
		inventoryCount.add(inventoryCount5);
		inventoryCount.add(inventoryCount6);
		inventoryCount.add(inventoryCount7);
		inventoryCount.add(inventoryCount8);
		inventoryCount.add(inventoryCount9);
		inventoryCount.add(inventoryCount10);
		inventoryCount.add(inventoryCount11);
		inventoryCount.add(inventoryCount12);
		inventoryCount.add(inventoryCount13);
		inventoryCount.add(inventoryCount14);
		inventoryCount.add(inventoryCount15);
		inventoryCount.add(inventoryCount16);
		inventoryCount.add(inventoryCount17);
		inventoryCount.add(inventoryCount18);
		inventoryCount.add(inventoryCount19);
		inventoryCount.add(inventoryCount20);
		inventoryCount.add(inventoryCount21);
		inventoryCount.add(inventoryCount22);
		inventoryCount.add(inventoryCount23);
		inventoryCount.add(inventoryCount24);
		inventoryCount.add(inventoryCount25);
		inventoryCount.add(inventoryCount26);
		inventoryCount.add(inventoryCount27);
		inventoryCount.add(inventoryCount28);
		inventoryCount.add(inventoryCount29);
		inventoryCount.add(inventoryCount30);
		
		for (int i = 0; i < 30; i++) {
			inventoryCount.get(i).setText(" ");

			this.inventoryCount.get(i).textProperty().bind(inventory.getInventory()[i].asString());
		}
		
		inventoryImage.add(inventoryImage1);
		inventoryImage.add(inventoryImage2);
		inventoryImage.add(inventoryImage3);
		inventoryImage.add(inventoryImage4);
		inventoryImage.add(inventoryImage5);
		inventoryImage.add(inventoryImage6);
		inventoryImage.add(inventoryImage7);
		inventoryImage.add(inventoryImage8);
		inventoryImage.add(inventoryImage9);
		inventoryImage.add(inventoryImage10);
		inventoryImage.add(inventoryImage11);
		inventoryImage.add(inventoryImage12);
		inventoryImage.add(inventoryImage13);
		inventoryImage.add(inventoryImage14);
		inventoryImage.add(inventoryImage15);
		inventoryImage.add(inventoryImage16);
		inventoryImage.add(inventoryImage17);
		inventoryImage.add(inventoryImage18);
		inventoryImage.add(inventoryImage19);
		inventoryImage.add(inventoryImage20);
		inventoryImage.add(inventoryImage21);
		inventoryImage.add(inventoryImage22);
		inventoryImage.add(inventoryImage23);
		inventoryImage.add(inventoryImage24);
		inventoryImage.add(inventoryImage25);
		inventoryImage.add(inventoryImage26);
		inventoryImage.add(inventoryImage27);
		inventoryImage.add(inventoryImage28);
		inventoryImage.add(inventoryImage29);
		inventoryImage.add(inventoryImage30);
	}

	public void setHotBarTest() {
		for (int i = 0; i < hotBarImage.size(); i++) {
			if (hotBar.getCount(i) == 0 && hotBarImage.get(i) != null) {
				getHotBarImageInArray(i).setImage(null);
				getHotBarImageInArrayI(i).setImage(null);
			}
		}
	}

	public boolean hotBarIsFull() {
		for (int i = 0; i < 6; i++) {
			if (hotBar.getCount(i) == 0 && hotBar.getCount(i) != 5) {
				return false;
			}
		}
		return true;
	}

	// gestion du minage avec une méthode miner
	public int setCaseCurrentTake() {
		caseCurrent = 0;

		if (countTestAll > 0 && isFullHotBar == false) {
			for (int i = 0; i < hotBarObject.length; i++) {
				if (hotBar.getCount(caseCurrent) == 0) {
					hotBarObject[caseCurrent] = m.getTile((int) posClicX, (int) posClicY);
					return 1;
				} else if (hotBar.getCount(caseCurrent) == 5
						|| hotBarObject[caseCurrent].getId() != currentTile.getId()) {
					caseCurrent++;
				}
			}
		} else {
			for (int i = 0; i < inventoryObject.length; i++) {
				if (inventory.getCount(caseCurrent) == 0) {
					inventoryObject[caseCurrent] = m.getTile((int) posClicX, (int) posClicY);
					return 1;
				} else if (inventory.getCount(caseCurrent) == 5
						|| inventoryObject[caseCurrent].getId() != currentTile.getId()) {
					caseCurrent++;
				}
			}
		}
		return 0;
	}

	public ImageView getHotBarImageInArray(int i) {
		return hotBarImage.get(i);
	}

	public ImageView getInventoryInArray(int i) {
		return inventoryImage.get(i);
	}

	public ImageView getHotBarImageInArrayI(int i) {
		return hotBarImageI.get(i);
	}

	
	private Timeline gameLoop2;

	//revue de la méthode permettant la gravité
	public void isOnTheGround() {
		gameLoop2 = new Timeline();
		gameLoop2.setCycleCount(Timeline.INDEFINITE);
		movingRightOrLeft=1;
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(50), (event -> {
			resteXH = (int) hero.getPosxP().get() % 32;
			resteYH = (int) hero.getPosyP().get() % 32;
			
			if (m.getTile((int) (getX() - resteXH), (int) (getY() - resteYH) + 64).getTraversable() == true) {
				hero.setPosy(hero.getPosyP().get() + 4 );
				anchorPaneMap.setTranslateY(anchorPaneMap.getTranslateY() - 4);
				resteXH = (int) hero.getPosxP().get() % 32;
				resteYH = (int) hero.getPosyP().get() % 32;
			}
		}));
		gameLoop2.getKeyFrames().add(keyFrame);
	}
	
	private Timeline gameLoop3;
	private int time;
	
	public void jump() {
		time=1;
		gameLoop3 = new Timeline();
		gameLoop3.setCycleCount(Timeline.INDEFINITE);
		movingRightOrLeft=1;

		KeyFrame keyFrame = new KeyFrame(Duration.millis(50), (event -> {
			resteXH = (int) hero.getPosxP().get() % 32;
			resteYH = (int) hero.getPosyP().get() % 32;
			if(time<=4) {
				if (isBorderMapUpHero() == false
						&& m.getTile(getX() - resteXH, getY() - 32 - resteYH).getTraversable() == true
						&& m.getTile(getX() - resteXH, getY() + 64 - resteYH).getTraversable() == false) {
					Image im = new Image("/image/JumpingHero.png");
					heroView.setImage(im);
					if (isBorderMapUpDown() == true && hero.getPosyP().get() - deplacement != 2816) {
						hero.setPosy(hero.getPosyP().get() - 64);
						barLife.setLayoutY(barLife.getLayoutY() - 64);
						hotBarGrid.setLayoutY(hotBarGrid.getLayoutY() - 64);
					} else {
						hero.setPosy(hero.getPosyP().get() - 64);
						anchorPaneMap.setTranslateY(anchorPaneMap.getTranslateY() + 64);
					}
				}
			}
			resteXH = (int) hero.getPosxP().get() % 32;
			resteYH = (int) hero.getPosyP().get() % 32;
			time++;
		}));
		gameLoop3.getKeyFrames().add(keyFrame);
		gameLoop3.play();
	}

	
	private int movingRightOrLeft;
	private Image move = new Image("/image/MovingHero.gif");
	
	//gestion des animations de déplacements
	public void initAnimation() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		movingRightOrLeft=1;

		KeyFrame keyFrame = new KeyFrame(Duration.millis(50), (event -> {
			if (hero.getHp() == 0) {
				gameLoop.stop();
			}
			gameLoop2.play();
			
			tabPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					resteXH = (int) hero.getPosxP().get() % 32;
					resteYH = (int) hero.getPosyP().get() % 32;
					
					switch (event.getCode()) {
						case Z:
							jump();
							break;
	
						case Q:
							if (isBorderMapLeftHero() == false
									&& m.getTile(getX() - resteXH, getY() + 32 - resteYH).getTraversable() == true
									&& m.getTile(getX() - resteXH, getY() - resteYH).getTraversable() == true) {
								if ( m.getTile((int) (getX() - resteXH), (int) (getY() - resteYH) + 64).getTraversable() == true &&
										m.getTile((int) (getX() - resteXH -32), (int) (getY() - resteYH) + 64).getTraversable() == false
										) {
									}
								if (isBorderMapLeftRight() == true && hero.getPosxP().get() - 4 != 2624) {
									heroView.setImage(move);
									heroView.setScaleX(-1);
									
									hero.setPosx(hero.getPosxP().get() - 4);
									hotBarGrid.setLayoutX(hotBarGrid.getLayoutX() + 4);
									barLife.setLayoutX(barLife.getLayoutX() + 4);
								} else {
									heroView.setImage(move);
									heroView.setScaleX(-1);
									anchorPaneMap.setTranslateX(anchorPaneMap.getTranslateX() + 4);
									hero.setPosx(hero.getPosxP().get() - 4);
								}
							}
							movingRightOrLeft=-1;
							break;
							
						case D:
							if (isBorderMapRightHero() == false
									&& m.getTile(getX() + 32 - resteXH, getY() + 32 - resteYH).getTraversable() == true
									&& m.getTile(getX() + 32 - resteXH, getY() - resteYH).getTraversable() == true
							) {
								if (m.getTile((int) (getX() - resteXH +32), (int) (getY() - resteYH) + 64).getTraversable() == false
										&& m.getTile((int) (getX() - resteXH), (int) (getY() - resteYH) + 64).getTraversable() == true) {
								}
								else {
									if (isBorderMapLeftRight() == true && hero.getPosxP().get() + deplacement != 576
											&& temps <= 2) {
										heroView.setScaleX(1);
										heroView.setImage(move);
										hero.setPosx(hero.getPosxP().get() + 4);
										barLife.setLayoutX(barLife.getLayoutX() - 4);
										hotBarGrid.setLayoutX(hotBarGrid.getLayoutX() - 4);
									} else {
										heroView.setImage(move);
										heroView.setScaleX(1);
										heroView.setImage(move);
										anchorPaneMap.setTranslateX(anchorPaneMap.getTranslateX() - 4);
										hero.setPosx(hero.getPosxP().get() + 4);
									}
								}
								movingRightOrLeft=1;
							}
							break;
							
						case E:
							
							tabPaneMenu.setDisable(true);
							tabPaneMap.setDisable(true);
							tabPaneInventaire.setDisable(false);
							tabPane.getSelectionModel().select(tabPaneInventaire);
							break;
							
						case ESCAPE:
							tabPane.getSelectionModel().select(tabPaneMap);
							tabPaneMenu.setDisable(true);
							tabPaneMap.setDisable(false);
							tabPaneInventaire.setDisable(true);
							break;
	
						case NUMPAD1:
							caseCurrent = 0;
							tabPane.setCursor(new ImageCursor(hotBarImage.get(caseCurrent).getImage()));
							break;
							
						case NUMPAD2:
							caseCurrent = 1;
							tabPane.setCursor(new ImageCursor(hotBarImage.get(caseCurrent).getImage()));
							break;
							
						case NUMPAD3:
							caseCurrent = 2;
							tabPane.setCursor(new ImageCursor(hotBarImage.get(caseCurrent).getImage()));
							break;
							
						case NUMPAD4:
							caseCurrent = 3;
							tabPane.setCursor(new ImageCursor(hotBarImage.get(caseCurrent).getImage()));
							break;
							
						case NUMPAD5:
							caseCurrent = 4;
							tabPane.setCursor(new ImageCursor(hotBarImage.get(caseCurrent).getImage()));
							break;
							
						case NUMPAD6:
							caseCurrent = 5;
							tabPane.setCursor(new ImageCursor(hotBarImage.get(caseCurrent).getImage()));
							break;
	
						default:
							break;
						}
					}
			});
			
			tabPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (movingRightOrLeft==1) {
					Image im = new Image("/image/Hero.png");
					heroView.setScaleX(1);
					heroView.setImage(im);
					}
					else {
						Image im = new Image("/image/Hero.png");
						heroView.setScaleX(-1);
						heroView.setImage(im);
					}
				}
			}
			);
		}));
		gameLoop.getKeyFrames().add(keyFrame);
	}
	
	@Override
	public void onChanged(ListChangeListener.Change<? extends Tile> c) {
		while (c.next()) {
			if (c.wasRemoved()) {
				if (isRemove == true) {
					Image im = new Image("/image/Sky.png");
					ImageView imV = new ImageView();
					imV.setImage(im);
					imV.setTranslateX(posClicX);
					imV.setTranslateY(posClicY);
					ImageView i = (ImageView) tilePane.getChildren().get(indexTile);
					tilePane.getChildren().add(imV);
					i.setX(posClicX);
					i.setY(posClicY);
					i.setImage(im);
				} else {
					Image im = new Image("/image/" + hotBarObject[caseCurrent].getName() + ".png");
					ImageView imV = new ImageView();
					imV.setImage(im);
					imV.setTranslateX(posClicX);
					imV.setTranslateY(posClicY);
					ImageView i = (ImageView) tilePane.getChildren().get(indexTile);
					tilePane.getChildren().add(imV);
					i.setX(posClicX);
					i.setY(posClicY);
					i.setImage(im);
				}
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hero = new Hero();
		
		heroView.translateXProperty().bind(hero.getPosxP());
		heroView.translateYProperty().bind(hero.getPosyP());

		barLife.translateXProperty().bind(hero.getPosxP());
		barLife.translateYProperty().bind(hero.getPosyP());
		barLife.setLayoutX(-500);
		barLife.setLayoutY(-340);

		hotBarGrid.translateXProperty().bind(hero.getPosxP());
		hotBarGrid.translateYProperty().bind(hero.getPosyP());
		hotBarGrid.setLayoutX(-250);
		hotBarGrid.setLayoutY(-340);

		initAnimation();
		isOnTheGround();
		gameLoop.play();
		inventory = new Inventory();
		hotBar = new HotBar();
		bindHotBar();
		countTestAll = -1;
		caseCurrent = 0;
		installingCraftList();
		isFullHotBar = false;

		m = null;
		try {
			/* nous avons eu des problèmes avec le chargement des fichiers directement intégrés dans le dossier
			 * nous étions contraints alors de mettre les chemins de chaque fichier/image pour le controler et le fxml
			 */
			m = new map("/home/etudiants/info/mpellan/prive/S2/Projet/Obliss/projetS2---Groupe-7/src/controler/map2.csv");
			m.getMap().addListener(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		tilePane.setPrefWidth((32 * m.getDimensions()[1]));
		tilePane.setPrefHeight((32 * m.getDimensions()[0]));
		for (int x = 0; x < m.getMap().size(); x++) {
			tilePane.getChildren().add(m.getMap().get(x).getImageView());
		}

		tabPane.setTabMinHeight(-1);
		tabPane.setTabMaxHeight(-1);
		barLife.setStyle("-fx-accent: red;");
		barExperience.setStyle("-fx-accent: yellow;");
		barLifeEnemy.setStyle("-fx-accent: red;");

		jouer.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				jouer.setTextFill(Color.GREEN);
				jouer.setPadding(new Insets(10, 10, 10, 10));
			}
		});
		
		jouer.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				jouer.setTextFill(Color.WHITE);
				jouer.setPadding(new Insets(5, 5, 5, 5));
			}
		});

		Options1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Options1.setTextFill(Color.YELLOW);
				Options1.setPadding(new Insets(10, 10, 10, 10));
			}
		});
		
		Options1.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Options1.setTextFill(Color.WHITE);
				Options1.setPadding(new Insets(5, 5, 5, 5));
			}
		});

		Quitter.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Quitter.setTextFill(Color.RED);
				Quitter.setPadding(new Insets(10, 10, 10, 10));
			}
		});
		
		Quitter.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Quitter.setTextFill(Color.WHITE);
				Quitter.setPadding(new Insets(5, 5, 5, 5));
			}
		});

		Retour.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Retour.setTextFill(Color.YELLOW);
				Retour.setPadding(new Insets(10, 10, 10, 10));
			}
		});
		
		Retour.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Retour.setTextFill(Color.WHITE);
				Retour.setPadding(new Insets(5, 5, 5, 5));
			}
		});

		tilePane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				int resteX = (int) (e.getX() % 32);
				int resteY = (int) (e.getY() % 32);

				posClicX = e.getX() - resteX;
				posClicY = e.getY() - resteY;

				currentTile = m.getTile((int) posClicX, (int) posClicY);

				if (testMining() == true) {
					/* ENLEVER BLOC */
					if (currentTile.getTraversable() == false) {
						isFullHotBar = hotBarIsFull();
						setCaseCurrentTake();
						isRemove = true;
						Image im = new Image("/image/" + currentTile.getName() + ".png");
						indexTile = m.getTileInArrayI((int) posClicX, (int) posClicY);
						if (hotBar.getCount(caseCurrent) < 5) {
							if (hotBar.getCount(caseCurrent) == 0) {
								if (isFullHotBar == false) {
									getHotBarImageInArray(caseCurrent).setImage(im);
									getHotBarImageInArrayI(caseCurrent).setImage(im);
									hotBar.increment(caseCurrent);
									hotBarObject[caseCurrent] = m.getTile((int) posClicX, (int) posClicY);
								} else {
									getInventoryInArray(caseCurrent).setImage(im);
									inventory.increment(caseCurrent);
									inventoryObject[caseCurrent] = m.getTile((int) posClicX, (int) posClicY);
								}
								m.getMap().remove(currentTile);
								m.getMap().add(indexTile, new Tile("Sky", 1, true, (int) posClicX, (int) posClicY));
							}
							else {
								if (isFullHotBar == false) {
									hotBar.increment(caseCurrent);
								} else {
									inventory.increment(caseCurrent);
								}
								m.getMap().remove(m.getTileInArrayI((int) posClicX, (int) posClicY));
								m.getMap().add(indexTile, new Tile(currentTile.getName(), currentTile.getId(), true,
										(int) posClicX, (int) posClicY));
							}
						}
					}
					/* RAJOUTER BLOC */
					else {
						if (hotBarObject.length != 0 && hotBar.getCount(caseCurrent) != 0) {
							isRemove = false;
							Image im = new Image("/image/" + hotBarObject[caseCurrent].getName() + ".png");
							indexTile = m.getTileInArrayI((int) posClicX, (int) posClicY);
							m.getMap().remove(m.getTileInArrayI((int) posClicX, (int) posClicY));
							m.getMap().add(indexTile, new Tile(hotBarObject[caseCurrent].getName(),
									hotBarObject[caseCurrent].getId(), false, (int) posClicX, (int) posClicY));
							hotBar.decrement(caseCurrent);
						}
						setHotBarTest();
						isFullHotBar = hotBarIsFull();
					}
					countTestAll++;
				}
			}
		});

	}
}