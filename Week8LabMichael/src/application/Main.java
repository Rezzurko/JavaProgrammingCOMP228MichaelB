package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class Main extends Application {
	
	// declaring here so all methods can access
	TextField studentIDField;
	TextField fullNameField;
	TextField emailField;
	TextField programField;
	Label statusLabel;
	
	@Override
	public void start(Stage primaryStage) {

		// create the menu
		Label menuTitle = new Label("MENU");
		menuTitle.setStyle("-fx-font-weight: bold;");
		
		// create the application menu
		Label titleLabel = new Label("STUDENT REGISTRATION SYSTEM");
		titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		// create hbox at top
		HBox appTitle = new HBox(titleLabel);
		appTitle.setPadding(new Insets(15));
		appTitle.setAlignment(Pos.CENTER);
		appTitle.setStyle("-fx-background-color: #dbeafe;");		
		
		
		titleLabel.setMaxWidth(Double.MAX_VALUE);
		appTitle.setMaxWidth(Double.MAX_VALUE);
		
		// create buttons
		Button dashboardButton = new Button("Dashboard");
		Button registrationButton = new Button("Registration");
		Button coursesButton = new Button("Courses");
		Button helpButton = new Button("Help");
		
		// make button array for better placements
		Button [] buttons = {dashboardButton, registrationButton, coursesButton, helpButton};
		
		// for loop for better placements
		for (int i = 0 ; i < buttons.length ; i++) {
			buttons[i].setPrefWidth(130);
			buttons[i].setPrefHeight(40);
			buttons[i].setMaxWidth(Double.MAX_VALUE);
			buttons[i].setAlignment(Pos.CENTER);
			buttons[i].setPadding(new Insets(10));
		}
		
		// arrange buttons vertically
		VBox navigation = new VBox(12, menuTitle, dashboardButton, 
				registrationButton, coursesButton, helpButton);
		navigation.setPadding(new Insets(15));
		navigation.setPrefWidth(160);
		navigation.setStyle("-fx-background-color: #f1f3f5;");
		
		// create text fields
		studentIDField = new TextField();
		fullNameField = new TextField();
		emailField = new TextField();
		programField = new TextField();
		
		// ghost text prompts
		studentIDField.setPromptText("Enter Student ID");
		fullNameField.setPromptText("Enter Full Name");
		emailField.setPromptText("Enter Email");
		programField.setPromptText("Enter Program");

		// create grid pane
		GridPane formGrid = new GridPane();

		// set horizontal and vertical spacing
		formGrid.setHgap(10);
		formGrid.setVgap(15);
		formGrid.setPadding(new Insets(20));

		// add student id label, name label, email label, and program label
		formGrid.add(new Label("Student ID:"), 0, 0);
		formGrid.add(studentIDField, 1, 0);
		
		formGrid.add(new Label("Full Name:"), 0, 1);
		formGrid.add(fullNameField, 1, 1);
		
		formGrid.add(new Label("Email:"), 0, 2);
		formGrid.add(emailField, 1, 2);
		
		formGrid.add(new Label("Program:"), 0, 3);
		formGrid.add(programField, 1, 3);
		
		// set preferred width
		studentIDField.setPrefWidth(250);
		fullNameField.setPrefWidth(250);
		emailField.setPrefWidth(250);
		programField.setPrefWidth(250);

		// create register and clear buttons
		Button registerButton = new Button("Register");
		Button clearButton = new Button("Clear");

		// create hbox to arrange everything horizontally
		HBox actionButtons = new HBox(15, registerButton, clearButton);
		actionButtons.setAlignment(Pos.CENTER);

		// create button to call registerStudent when clicked
		registerButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        registerStudent();
		    }
		});

		// create button to call clearFields when clicked
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        clearFields();
		    }
		});
		
		// create a label to display registration messages
		statusLabel = new Label();
		statusLabel.setStyle("-fx-font-size: 14px;");

		// create the registration form label 
		Label formTitle = new Label("Student Registration Form");
		formTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
		formTitle.setMaxWidth(Double.MAX_VALUE);
		formTitle.setAlignment(Pos.CENTER_LEFT);
		formTitle.setPadding(new Insets(0, 0, 0, 20));
		
		// create vbox to arrange vertically
		VBox centerSide = new VBox(10, formTitle, formGrid, actionButtons, statusLabel);
		centerSide.setAlignment(Pos.CENTER);
		centerSide.setPadding(new Insets(10));
		
		// create border pane as the root for the layout
		BorderPane root = new BorderPane();
		root.setTop(appTitle);
		root.setLeft(navigation);
		root.setCenter(centerSide);
		root.setStyle("-fx-background-color: white;");
		
		// create scene
		Scene scene = new Scene(root, 700, 500);
		primaryStage.setTitle("Student Registration System"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Displays the stage

		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void registerStudent() {

	    String studentID = studentIDField.getText().trim();
	    String fullName = fullNameField.getText().trim();
	    String email = emailField.getText().trim();
	    String program = programField.getText().trim();

	    if (studentID.isEmpty() ||
	        fullName.isEmpty() ||
	        email.isEmpty() ||
	        program.isEmpty()) {

	        statusLabel.setText("Error: Please fill in all required fields.");
	        statusLabel.setStyle("-fx-text-fill: red;");

	    } else {

	        statusLabel.setText("Registration successful! Welcome, " + fullName + ".");
	        statusLabel.setStyle("-fx-text-fill: green;");
	    }
	}
	
	private void clearFields() {

	    studentIDField.clear();
	    fullNameField.clear();
	    emailField.clear();
	    programField.clear();

	    statusLabel.setText("");
	}
	
}
