package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;

public class Main extends Application {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/employment_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "class123";

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();

		root.setPadding(new Insets(20));

		Label heading = new Label("Employment Application");

		heading.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white;");

		HBox header = new HBox(heading);
		header.setAlignment(Pos.CENTER);
		header.setPadding(new Insets(15));
		header.setStyle("-fx-background-color: black;");

		root.setTop(header);

		VBox form = new VBox(12);
		form.setPadding(new Insets(15));

		Label personalHeading = new Label("Personal Information");
		personalHeading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

		GridPane personalGrid = new GridPane();
		personalGrid.setHgap(15);
		personalGrid.setVgap(12);

		TextField fullNameField = new TextField();
		TextField addressField = new TextField();
		TextField contactField = new TextField();
		TextField emailField = new TextField();

		fullNameField.setPromptText("Enter full name");
		addressField.setPromptText("Enter current address");
		contactField.setPromptText("Enter contact number");
		emailField.setPromptText("Enter email address");

		personalGrid.add(new Label("Full Name:"), 0, 0);
		personalGrid.add(fullNameField, 1, 0);

		personalGrid.add(new Label("Current Address:"), 0, 1);
		personalGrid.add(addressField, 1, 1);

		personalGrid.add(new Label("Contact Number:"), 0, 2);
		personalGrid.add(contactField, 1, 2);

		personalGrid.add(new Label("Email Address:"), 0, 3);
		personalGrid.add(emailField, 1, 3);

		personalGrid.getColumnConstraints().add(new ColumnConstraints(150));
		ColumnConstraints personalFields = new ColumnConstraints();
		personalFields.setHgrow(Priority.ALWAYS);
		personalGrid.getColumnConstraints().add(personalFields);

		Label employmentHeading = new Label("Education and Employment Details");
		employmentHeading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

		GridPane employmentGrid = new GridPane();
		employmentGrid.setHgap(15);
		employmentGrid.setVgap(12);

		ComboBox<String> educationBox = new ComboBox<>();
		educationBox.getItems().addAll("High School", "College", "University", "Other");
		educationBox.setPromptText("Select education");

		RadioButton maleButton = new RadioButton("Male");
		RadioButton femaleButton = new RadioButton("Female");
		RadioButton otherButton = new RadioButton("Other");
		RadioButton preferNotButton = new RadioButton("Prefer not to say");

		ToggleGroup genderGroup = new ToggleGroup();

		maleButton.setToggleGroup(genderGroup);
		femaleButton.setToggleGroup(genderGroup);
		otherButton.setToggleGroup(genderGroup);
		preferNotButton.setToggleGroup(genderGroup);

		HBox genderBox = new HBox(12, maleButton, femaleButton, otherButton, preferNotButton);

		genderBox.setAlignment(Pos.CENTER_LEFT);

		DatePicker availableDatePicker = new DatePicker();
		availableDatePicker.setPromptText("Select date");

		TextField positionField = new TextField();
		positionField.setPromptText("Enter desired position");

		TextField salaryField = new TextField();
		salaryField.setPromptText("Enter desired salary");

		employmentGrid.add(new Label("Education:"), 0, 0);
		employmentGrid.add(educationBox, 1, 0);

		employmentGrid.add(new Label("Gender:"), 0, 1);
		employmentGrid.add(genderBox, 1, 1);

		employmentGrid.add(new Label("Date Available:"), 0, 2);
		employmentGrid.add(availableDatePicker, 1, 2);

		employmentGrid.add(new Label("Desired Position:"), 0, 3);
		employmentGrid.add(positionField, 1, 3);

		employmentGrid.add(new Label("Desired Salary:"), 0, 4);
		employmentGrid.add(salaryField, 1, 4);

		employmentGrid.getColumnConstraints().add(new ColumnConstraints(150));

		ColumnConstraints employmentFields = new ColumnConstraints();
		employmentFields.setHgrow(Priority.ALWAYS);
		employmentGrid.getColumnConstraints().add(employmentFields);

		form.getChildren().addAll(personalHeading, personalGrid, employmentHeading, employmentGrid);

		Label authorizationHeading = new Label("Authorization and Additional Questions");
		authorizationHeading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

		RadioButton authorizedYes = new RadioButton("Yes");
		RadioButton authorizedNo = new RadioButton("No");

		ToggleGroup authorizedGroup = new ToggleGroup();
		authorizedYes.setToggleGroup(authorizedGroup);
		authorizedNo.setToggleGroup(authorizedGroup);

		HBox authorizedBox = new HBox(15, new Label("Are you legally authorized to work?"), authorizedYes,
				authorizedNo);

		RadioButton relativesYes = new RadioButton("Yes");
		RadioButton relativesNo = new RadioButton("No");

		ToggleGroup relativesGroup = new ToggleGroup();
		relativesYes.setToggleGroup(relativesGroup);
		relativesNo.setToggleGroup(relativesGroup);

		HBox relativesBox = new HBox(15, new Label("Do you have relatives working for this company?"), relativesYes,
				relativesNo);

		Label explanationLabel = new Label("If yes, please explain:");
		TextArea explanationField = new TextArea();
		explanationField.setPromptText("Enter explanation here");
		explanationField.setPrefRowCount(3);
		explanationField.setWrapText(true);

		form.getChildren().addAll(authorizationHeading, authorizedBox, relativesBox, explanationLabel,
				explanationField);

		Label signatureHeading = new Label("Application Signature");
		signatureHeading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

		TextField signatureField = new TextField();
		signatureField.setPromptText("Enter your full name as your signature");

		form.getChildren().addAll(signatureHeading, signatureField);

		Button submitButton = new Button("Submit Application");

		submitButton.setStyle(
				"-fx-background-color: black; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 25 10 25;");

		submitButton.setOnAction(e -> {

			if (fullNameField.getText().isEmpty() || addressField.getText().isEmpty()
					|| contactField.getText().isEmpty() || emailField.getText().isEmpty()
					|| educationBox.getValue() == null || genderGroup.getSelectedToggle() == null
					|| availableDatePicker.getValue() == null || positionField.getText().isEmpty()
					|| salaryField.getText().isEmpty() || authorizedGroup.getSelectedToggle() == null
					|| relativesGroup.getSelectedToggle() == null || signatureField.getText().isEmpty()) {

				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Missing Information");
				alert.setHeaderText(null);
				alert.setContentText("Please complete all required fields.");
				alert.showAndWait();

				return;
			}

			try {
				BigDecimal salary = new BigDecimal(salaryField.getText());

				String sql = "INSERT INTO applications "
						+ "(full_name, current_address, contact_number, email_address, "
						+ "education, gender, date_available, desired_position, "
						+ "desired_salary, legally_authorized, relatives_working, "
						+ "explanation, applicant_signature) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

						PreparedStatement statement = connection.prepareStatement(sql)) {

					statement.setString(1, fullNameField.getText());
					statement.setString(2, addressField.getText());
					statement.setString(3, contactField.getText());
					statement.setString(4, emailField.getText());
					statement.setString(5, educationBox.getValue());

					RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
					statement.setString(6, selectedGender.getText());

					statement.setDate(7, java.sql.Date.valueOf(availableDatePicker.getValue()));

					statement.setString(8, positionField.getText());
					statement.setBigDecimal(9, salary);

					RadioButton authorizedAnswer = (RadioButton) authorizedGroup.getSelectedToggle();
					statement.setString(10, authorizedAnswer.getText());

					RadioButton relativesAnswer = (RadioButton) relativesGroup.getSelectedToggle();
					statement.setString(11, relativesAnswer.getText());

					statement.setString(12, explanationField.getText());
					statement.setString(13, signatureField.getText());

					statement.executeUpdate();

					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Application Submitted");
					alert.setHeaderText(null);
					alert.setContentText("Your application was submitted successfully!");
					alert.showAndWait();
				}
			}

			catch (NumberFormatException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Invalid Salary");
				alert.setHeaderText(null);
				alert.setContentText("Please enter a valid salary.");
				alert.showAndWait();
			}

			catch (SQLException ex) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Database Error");
				alert.setHeaderText(null);
				alert.setContentText("Could not submit application: " + ex.getMessage());
				alert.showAndWait();
			}

		});

		form.getChildren().add(submitButton);

		root.setCenter(form);

		Scene scene = new Scene(root, 800, 865);

		primaryStage.setTitle("Employment Application");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}