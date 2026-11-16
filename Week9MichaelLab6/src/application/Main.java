package application;

import javafx.application.Application;
import java.time.LocalDate;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main extends Application {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "class123";
	private final TextField nameField = new TextField();
	private final TextField ageField = new TextField();
	private final ComboBox<String> genderBox = new ComboBox<>();
	private final TextField idField = new TextField();
	private final TextField phoneField = new TextField();
	private final TextField diagnosisField = new TextField();
	private final DatePicker datePicker = new DatePicker();

	private final TextField searchField = new TextField();
	private final TableView<Patient> patientTable = new TableView<>();
	private final ObservableList<Patient> patientList = FXCollections.observableArrayList();

	private final Label statusLabel = new Label("Enter patient details.");

	@Override

	public void start(Stage stage) {

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(20));

		Label heading = new Label("Hospital Management System");
		heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

		Label subtitle = new Label("Patient Records Management");

		VBox header = new VBox(5, heading, subtitle);
		header.setPadding(new Insets(0, 0, 20, 0));
		root.setTop(header);

		GridPane form = new GridPane();
		form.setHgap(12);
		form.setVgap(14);
		form.setPadding(new Insets(20));

		idField.setPromptText("Auto-generated");
		idField.setEditable(false);

		nameField.setPromptText("Full name");
		ageField.setPromptText("Age (0-125)");
		phoneField.setPromptText("Phone number");
		diagnosisField.setPromptText("Patient diagnosis");
		datePicker.setPromptText("Admission date");

		genderBox.getItems().addAll("Female", "Male", "Non-binary", "Prefer not to say");
		genderBox.setPromptText("Select gender");

		addRow(form, 0, "Patient ID:", idField);
		addRow(form, 1, "Full Name:", nameField);
		addRow(form, 2, "Age:", ageField);
		addRow(form, 3, "Gender:", genderBox);
		addRow(form, 4, "Phone:", phoneField);
		addRow(form, 5, "Diagnosis:", diagnosisField);
		addRow(form, 6, "Admission Date:", datePicker);

		ColumnConstraints labels = new ColumnConstraints(130);
		ColumnConstraints fields = new ColumnConstraints();
		fields.setHgrow(Priority.ALWAYS);
		form.getColumnConstraints().addAll(labels, fields);

		Button addButton = new Button("Add Patient");
		Button updateButton = new Button("Update");
		Button deleteButton = new Button("Delete");
		Button clearButton = new Button("Clear");

		addButton.setOnAction(e -> addPatient());
		updateButton.setOnAction(e -> setStatus("Update selected.", false));
		deleteButton.setOnAction(e -> setStatus("Delete selected.", false));
		clearButton.setOnAction(e -> clearForm());

		HBox buttons = new HBox(10, addButton, updateButton, deleteButton, clearButton);
		buttons.setAlignment(Pos.CENTER);

		VBox leftPanel = new VBox(12, form, buttons);
		leftPanel.setPrefWidth(480);

		searchField.setPromptText("Search by name or phone");

		Button searchButton = new Button("Search");
		searchButton.setOnAction(e -> searchPatients());

		Button showAllButton = new Button("Show All");
		showAllButton.setOnAction(e -> showAllPatients());

		HBox searchBar = new HBox(10, searchField, searchButton, showAllButton);
		searchBar.setAlignment(Pos.CENTER_LEFT);

		TableColumn<Patient, Integer> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));

		TableColumn<Patient, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Patient, Integer> ageColumn = new TableColumn<>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Patient, String> genderColumn = new TableColumn<>("Gender");
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

		TableColumn<Patient, String> phoneColumn = new TableColumn<>("Phone");
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

		TableColumn<Patient, String> diagnosisColumn = new TableColumn<>("Diagnosis");
		diagnosisColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));

		TableColumn<Patient, LocalDate> dateColumn = new TableColumn<>("Admission Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("admissionDate"));

		patientTable.getColumns().add(idColumn);
		patientTable.getColumns().add(nameColumn);
		patientTable.getColumns().add(ageColumn);
		patientTable.getColumns().add(genderColumn);
		patientTable.getColumns().add(phoneColumn);
		patientTable.getColumns().add(diagnosisColumn);
		patientTable.getColumns().add(dateColumn);
		patientTable.setItems(patientList);
		patientTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedPatient) -> {
			if (selectedPatient != null) {
				idField.setText(String.valueOf(selectedPatient.getPatientId()));
				nameField.setText(selectedPatient.getName());
				ageField.setText(String.valueOf(selectedPatient.getAge()));
				genderBox.setValue(selectedPatient.getGender());
				phoneField.setText(selectedPatient.getPhone());
				diagnosisField.setText(selectedPatient.getDiagnosis());
				datePicker.setValue(selectedPatient.getAdmissionDate());
			}
		});
		patientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

		VBox rightPanel = new VBox(12, searchBar, patientTable);

		rightPanel.setPrefWidth(650);
		patientTable.setPrefHeight(450);

		HBox.setHgrow(rightPanel, Priority.ALWAYS);
		VBox.setVgrow(patientTable, Priority.ALWAYS);

		HBox centerContent = new HBox(20, leftPanel, rightPanel);
		centerContent.setAlignment(Pos.TOP_LEFT);	

		root.setCenter(centerContent);

		root.setBottom(statusLabel);

		stage.setTitle("Hospital Management System");
		stage.setScene(new Scene(root, 1200, 650));
		stage.show();
	}

	private void addRow(GridPane grid, int row, String text, Control control) {

		Label label = new Label(text);
		label.setStyle("-fx-font-weight: bold;");

		control.setMaxWidth(Double.MAX_VALUE);
		grid.add(label, 0, row);
		grid.add(control, 1, row);

	}

	private void addPatient() {
		String name = nameField.getText().trim();
		String ageValue = ageField.getText().trim();
		String gender = genderBox.getValue();
		String phone = phoneField.getText().trim();
		String diagnosis = diagnosisField.getText().trim();
		LocalDate admissionDate = datePicker.getValue();

		if (name.isEmpty() || ageValue.isEmpty() || gender == null || phone.isEmpty() || diagnosis.isEmpty()
				|| admissionDate == null) {
			setStatus("Please complete all patient fields.", true);
			return;
		}

		int age;

		try {
			age = Integer.parseInt(ageValue);
		} catch (NumberFormatException nfe) {
			setStatus("Age must be a whole number.", true);
			return;
		}

		if (age < 0 || age > 125) {
			setStatus("Age must be between 0 and 125.", true);
			return;
		}

		if (admissionDate.isAfter(LocalDate.now())) {
			setStatus("Admission date cannot be in the future.", true);
			return;
		}

		String sql = "INSERT INTO patients " + "(name, age, gender, phone, diagnosis, admission_date) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, name);
			statement.setInt(2, age);
			statement.setString(3, gender);
			statement.setString(4, phone);
			statement.setString(5, diagnosis);
			statement.setDate(6, java.sql.Date.valueOf(admissionDate));

			int count = statement.executeUpdate();

			if (count == 1) {
				setStatus("Patient added successfully!", false);
				clearFields();
				showAllPatients();
			} else {
				setStatus("Patient could not be added.", true);
			}

		} catch (SQLException sqlex) {
			setStatus("Database error: " + sqlex.getMessage(), true);
			sqlex.printStackTrace();
		}
	}

	private void showAllPatients() {
		String sql = "SELECT * FROM patients ORDER BY patient_id";

		patientList.clear();

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				int patientId = result.getInt("patient_id");
				String name = result.getString("name");
				int age = result.getInt("age");
				String gender = result.getString("gender");
				String phone = result.getString("phone");
				String diagnosis = result.getString("diagnosis");
				LocalDate admissionDate = result.getDate("admission_date").toLocalDate();

				Patient patient = new Patient(patientId, name, age, gender, phone, diagnosis, admissionDate);

				patientList.add(patient);
			}

			setStatus("Loaded " + patientList.size() + " patient record(s).", false);

		} catch (SQLException sqlex) {
			setStatus("Database error: " + sqlex.getMessage(), true);
			sqlex.printStackTrace();
		}
	}

	private void searchPatients() {
		String searchValue = searchField.getText().trim();

		if (searchValue.isEmpty()) {
			showAllPatients();
			return;
		}

		String sql = "SELECT * FROM patients " + "WHERE name LIKE ? OR phone LIKE ? " + "ORDER BY patient_id";

		patientList.clear();

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, "%" + searchValue + "%");
			statement.setString(2, "%" + searchValue + "%");

			try (ResultSet result = statement.executeQuery()) {

				while (result.next()) {
					int patientId = result.getInt("patient_id");
					String name = result.getString("name");
					int age = result.getInt("age");
					String gender = result.getString("gender");
					String phone = result.getString("phone");
					String diagnosis = result.getString("diagnosis");

					LocalDate admissionDate = result.getDate("admission_date").toLocalDate();

					Patient patient = new Patient(patientId, name, age, gender, phone, diagnosis, admissionDate);

					patientList.add(patient);
				}
			}

			if (patientList.isEmpty()) {
				setStatus("No matching patients found.", false);
			} else {
				setStatus("Found " + patientList.size() + " patient record(s).", false);
			}

		} catch (SQLException sqlex) {
			setStatus("Database error: " + sqlex.getMessage(), true);
			sqlex.printStackTrace();
		}
	}

	private void clearForm() {

		clearFields();
		setStatus("Form cleared.", false);

	}

	private void clearFields() {
		idField.clear();
		nameField.clear();
		ageField.clear();
		genderBox.getSelectionModel().clearSelection();
		phoneField.clear();
		diagnosisField.clear();
		datePicker.setValue(null);
		searchField.clear();
		patientTable.getSelectionModel().clearSelection();
	}

	private void setStatus(String message, boolean error) {

		statusLabel.setText(message);
		statusLabel.setStyle(error ? "-fx-text-fill: #b00020;" : "-fx-text-fill: #167545;");

	}

	public static void main(String[] args) {

		launch(args);

	}

}
