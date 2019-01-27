package com.vcs.colors.game.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public abstract class GameWindow {

	protected Label lblStatus;
	protected Canvas pnValid;
	protected Canvas pnNotValid;
	protected Canvas pnInput;
	protected ProgressBar prgBar;

	public GameWindow(Pane mainPane) {

		createForm(mainPane);

	}

	protected abstract void resetGamePlay();

	protected abstract void onMouseClicked(MouseEvent event);

	protected abstract void onMouseScrool(ScrollEvent event);

	protected abstract int calcPlayerInputWeight();

	private void createForm(Pane mainPane) {

		mainPane.setPrefSize(600, 300);

		// Valid not valid ...
		addStaticLabels(mainPane);
		lblStatus = creteStatusLabel();

		pnValid = createResultPane(14);
		pnNotValid = createResultPane(439);
		pnInput = addPlayerInputPane();

		prgBar = createProgressBar();

		ToolBar toolBar = addToolBar(mainPane);

		mainPane.getChildren().add(pnValid);
		mainPane.getChildren().add(pnNotValid);
		mainPane.getChildren().add(pnInput);
		mainPane.getChildren().add(toolBar);
		mainPane.getChildren().add(lblStatus);

	}

	private ToolBar addToolBar(Pane mainPane) {

		Button btn = new Button("Reset");
		btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				resetGamePlay();

			}
		});

		ToolBar t = new ToolBar(btn, prgBar, new Label("Progress: "));
		t.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		t.setPrefSize(600, 40);

		return t;
	}

	private ProgressBar createProgressBar() {
		ProgressBar p = new ProgressBar();
		p.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		p.setPrefSize(453, 20);
		p.setProgress(0);
		return p;
	}

	private Canvas addPlayerInputPane() {
		Canvas p = new Canvas();
		p.setLayoutX(180);
		p.setLayoutY(165);
		// p.resize(259, 45);
		p.setWidth(calcPlayerInputWeight());
		p.setHeight(52);

		p.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				onMouseClicked(event);

			}

		});

		p.setOnScroll(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				onMouseScrool(event);
			}
		});
		return p;
	}

	private Canvas createResultPane(int xLayout) {
		Canvas p = new Canvas();
		p.setLayoutX(xLayout);
		p.setLayoutY(77);
		// p.resize(148, 200);
		p.setWidth(148);
		p.setHeight(200);
		return p;
	}

	private void addStaticLabels(Pane mainPane) {

		Label lblValid = new Label("Valid");
		lblValid.setLayoutX(72);
		lblValid.setLayoutY(51);

		Label lblNotValid = new Label("Not valid");
		lblNotValid.setLayoutX(484);
		lblNotValid.setLayoutY(51);

		Label lblText = new Label(
				"Use mouse scrool to change color, first button for input lenght and the second for check the result.");
		lblText.setAlignment(Pos.TOP_LEFT);
		lblText.setLayoutX(171);
		lblText.setLayoutY(97);
		lblText.setPrefSize(259, 57);
		lblText.setTextAlignment(TextAlignment.CENTER);
		lblText.setWrapText(true);

		mainPane.getChildren().add(lblValid);
		mainPane.getChildren().add(lblNotValid);
		mainPane.getChildren().add(lblText);

	}

	private Label creteStatusLabel() {

		Label l = new Label();
		l.setAlignment(Pos.CENTER);
		l.setLayoutX(248);
		l.setLayoutY(236);
		l.setPrefSize(107, 16);

		return l;
	}

}
