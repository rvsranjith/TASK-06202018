package app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Rectangle;

public class View extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("JavaFX Word Counter");
		
		//Instantiate layout nodes for root:
		//---------------------------------------------------
		VBox root = new VBox();
		Label header = new Label("JavaFX Word Counter");
		Label subheader = new Label("Developed by TALKER");
		Rectangle headerDiv = new Rectangle(555,1,Color.BLACK);
		HBox controlArea = new HBox();
		
		root.getChildren().addAll(header,subheader,headerDiv,controlArea);
		
		//Instantiate layout nodes for HBox controlArea:
		//The control area represents everything beneath the title
		//and subtitle.
		//---------------------------------------------------
		//Left side:
		VBox leftRoot = new VBox();
		HBox leftRootControlBox = new HBox(50); // <- contains button, label
		Label leftRootHeader = new Label("Input");
		Button btn = new Button("Analyze Text");
		TextArea userInputTextArea = new TextArea();
		
		userInputTextArea.setMinWidth(153);
		userInputTextArea.setMaxWidth(155);
		
		leftRootControlBox.getChildren().addAll(leftRootHeader,btn);
		leftRoot.getChildren().addAll(leftRootControlBox,userInputTextArea);
		
		//Divider:
		Rectangle controlAreaDiv = new Rectangle(1,435,Color.GAINSBORO);
		
		//Right side:
		VBox rightRoot = new VBox();
		//
		Label results = new Label("Results:");
		Rectangle rightRootDiv = new Rectangle (150,1,Color.BLACK);
		Label totalWordsLabel = new Label("");
		Label uniqueWordsLabel = new Label("");
		Label totalCharsLabel = new Label("");
		//
		Label wordFrequencyLabel = new Label("Word Frequency:");
		Rectangle rightRootDiv2 = new Rectangle(150,1,Color.BLACK);
		ScrollPane scrollpane = new ScrollPane();
		VBox scrollpaneOutputNode = new VBox(2);
		
		scrollpane.setContent(scrollpaneOutputNode);
		scrollpane.setMaxHeight(250);
		scrollpane.setFitToWidth(true);
		
		rightRoot.getChildren().addAll(results,rightRootDiv,totalWordsLabel,uniqueWordsLabel,totalCharsLabel,wordFrequencyLabel,rightRootDiv2,scrollpane);
		
		//Add the left side, divider, and right side to the target HBox controlArea:
		controlArea.getChildren().addAll(leftRoot,controlAreaDiv,rightRoot);

		//Add style classes for CSS and make basic adjustments:
		//---------------------------------------------------
		root.getStyleClass().add("root");
		header.getStyleClass().add("header");
		subheader.getStyleClass().add("subheader");
		headerDiv.getStyleClass().add("headerDiv");
		controlArea.getStyleClass().add("controlArea");
		leftRoot.getStyleClass().add("leftRoot");
		leftRoot.setMaxWidth(340);
		leftRoot.setMinWidth(338);
		leftRootHeader.getStyleClass().add("leftRootHeader");
		userInputTextArea.getStyleClass().add("userInputTextArea");
		userInputTextArea.setMinWidth(310);
		userInputTextArea.setMinHeight(400);
		controlAreaDiv.getStyleClass().add("controlAreaDiv");
		rightRoot.getStyleClass().add("rightRoot");
		results.getStyleClass().add("results");
		rightRootDiv.getStyleClass().add("rightRootDiv");
		totalWordsLabel.getStyleClass().add("totalWordsLabel");
		uniqueWordsLabel.getStyleClass().add("uniqueWordsLabel");
		totalCharsLabel.getStyleClass().add("totalCharsLabel");
		wordFrequencyLabel.getStyleClass().add("wordFrequencyLabel");
		rightRootDiv2.getStyleClass().add("rightRootDiv2");
		scrollpane.getStyleClass().add("scrollpane");
		scrollpane.setMinWidth(212);
		scrollpane.setMaxWidth(215);
		scrollpaneOutputNode.getStyleClass().add("scrollpaneOutputNode");
		btn.getStyleClass().add("btn");
		btn.setMaxSize(100, 50);
		
		//Create scene and connect CSS style sheet:
		//---------------------------------------------------
		Scene scene = new Scene(root, 600, 570);
		root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
		
		//Add event handler:
		//---------------------------------------------------
		btn.setOnAction(e -> {
			ApplicationMethods.analyzeText(userInputTextArea, totalWordsLabel, uniqueWordsLabel, totalCharsLabel, scrollpaneOutputNode);
		});
		
		//Set scene and show() stage:
		//---------------------------------------------------
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	//Default Java Main entry point:
	//---------------------------------------------------------
	public static void main(String[] args){launch(args);}

}