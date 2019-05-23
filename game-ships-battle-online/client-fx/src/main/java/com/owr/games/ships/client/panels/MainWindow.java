package com.owr.games.ships.client.panels;

import org.springframework.stereotype.Component;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

@Component
public class MainWindow {

//	@Autowired
//	private MainToolbar mainToolbar;
//
//	@Autowired
//	private Nav nav;

	private BorderPane mainPane;
	private Pane infoBarPane;
	private AnchorPane infoBarTextWraper;
	private Text infoBarText;
	private VBox errMsgContainer;

	public MainWindow() {

		/*
		 * Text
		 */
		infoBarText = new Text("");
		infoBarText.setLayoutX(15);
		infoBarText.setLayoutY(15);
		infoBarText.setFill(Color.GREY);

		/*
		 * Border pane
		 */
		infoBarPane = new Pane(infoBarText);
		infoBarPane.setMaxHeight(20);
		infoBarPane.setMinHeight(20);
		infoBarPane.setStyle("-fx-border-radius:3; -fx-border-color:lightgrey;");

		/*
		 * Wrapper
		 */
		infoBarTextWraper = new AnchorPane(infoBarPane);
		AnchorPane.setTopAnchor(infoBarPane, 2d);
		AnchorPane.setLeftAnchor(infoBarPane, 2d);
		AnchorPane.setRightAnchor(infoBarPane, 2d);
		AnchorPane.setBottomAnchor(infoBarPane, 2d);

		errMsgContainer = new VBox();
		// errMsgContainer.setMaxWidth(200);

	}

	public void init(BorderPane pMainPane) {

		this.mainPane = pMainPane;
		mainPane.setPrefSize(1280, 720);

		// Top main menu
		//mainPane.setTop(mainToolbar.getNode());

		// Infobar

		clearMessages();
		mainPane.setBottom(errMsgContainer);

		// mainPane.setRight(errMsgContainer);

		//setMainMenuVisible(false);

	}

	public void changeAppStatus(String infoBarText) {
		this.infoBarText.setText(infoBarText);
	}

	public void disableUI() {
		changeAppStatus("Waiting for server. Please wait ... ");
		mainPane.setDisable(true);
	}

	

	

	

	public Node getFormContainer() {
		return mainPane.getCenter();
	}

	

	
	public void clearMessages() {
		errMsgContainer.getChildren().clear();
		errMsgContainer.getChildren().add(infoBarTextWraper);
	}


}
