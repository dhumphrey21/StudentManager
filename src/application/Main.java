// Name: Dnaiel Humphrey
// Program: Research Project
// Description: This is an enhanced version of Java05. I've added two new JavaFX controls to the form: a group of radio buttons 
// for the student's program of study (focus), and a DatePicker for the enrollment date. Once selected, the values for these controls 
// are added to the student display text area at the bottom of the form.

package application;
	
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;

public class Main extends Application {
	// Declare fields
    private TextField studentIDField;
    private TextField nameField;
    private TextField emailField;
    private TextField phoneField;
    private TextArea studentDisplayArea;
    private ToggleGroup group;
    private String toggleGroupValue;
    private DatePicker datePicker;
	
	@Override
	public void start(Stage primaryStage) {
		// organize the GUI layout
		try {
			primaryStage.setTitle("Student Manager");
			
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.TOP_CENTER);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	        grid.setHgap(10);
	        grid.setVgap(10);
	        
			Scene scene = new Scene(grid);
	        
	        grid.add(new Label("Student ID:"), 0, 0);
	        studentIDField = new TextField();
	        grid.add(studentIDField, 1, 0);

	        grid.add(new Label("Name:"), 0, 1);
	        nameField = new TextField();
	        grid.add(nameField, 1, 1);
	        
	        grid.add(new Label("Email:"), 0, 2);
	        emailField = new TextField();
	        grid.add(emailField, 1, 2);
	        
	        grid.add(new Label("Phone Number:"), 0, 3);
	        phoneField = new TextField();
	        grid.add(phoneField, 1, 3);
	        
	        Button displayButton = new Button("Display");
	        displayButton.setOnAction(event -> displayButtonClicked());
	        
	        HBox buttonBox = new HBox(10);
	        buttonBox.getChildren().add(displayButton);
	        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
	        grid.add(buttonBox, 0, 9, 2, 1);
	        
	        group = new ToggleGroup();
	        
	        grid.add(new Label("Focus:"), 0, 4);
	        
	        RadioButton rb1 = new RadioButton("Applications Development");
	        rb1.setToggleGroup(group);
	        rb1.setSelected(true);
	        
	        RadioButton rb2 = new RadioButton("Cybersecurity");
	        rb2.setToggleGroup(group);
	        
	        RadioButton rb3 = new RadioButton("Network Management");
	        rb3.setToggleGroup(group);
	        
	        RadioButton rb4 = new RadioButton("Computer Support");
	        rb4.setToggleGroup(group);
	        
	        grid.add(rb1, 1, 4);
	        grid.add(rb2, 1, 5);
	        grid.add(rb3, 1, 6);
	        grid.add(rb4, 1, 7);
	        
	        grid.add(new Label("Enrollment Date:"), 0, 8);
	        datePicker = new DatePicker();
	        HBox hbox = new HBox(datePicker);
	        grid.add(hbox, 1, 8);
	        
	       grid.add(new Label("Student Display:"), 0, 10);
	       studentDisplayArea= new TextArea();
	       studentDisplayArea.setEditable(false);
	       grid.add(studentDisplayArea, 1, 10);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Method that validates user input and displays message when display button is clicked
	private void displayButtonClicked()
	{
        Validation v = new Validation();
        String errorMsg = "";
        errorMsg += v.isInteger(studentIDField.getText(), 
                "Student ID");
        errorMsg += v.isPresent(nameField.getText(), 
                "Name");
        errorMsg += v.isPresent(emailField.getText(), 
                "Email");
        errorMsg += v.isPresent(phoneField.getText(), 
                "Phone Number");
        if (datePicker.getValue() == null) {
        	errorMsg += "Enrollment Date is required.";
        }
  
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        toggleGroupValue = selectedRadioButton.getText();
        
        if (errorMsg.isEmpty()) {
            String studentInfo = "Student ID: "+ studentIDField.getText() + "\nName: "+ nameField.getText()
            					 + "\nEmail: "+ emailField.getText() + "\nPhone Number: "+ phoneField.getText() 
            					 + "\nFocus: " + toggleGroupValue + "\nEnrollment Date: " + datePicker.getValue();
            studentDisplayArea.setText(studentInfo);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Data");
            alert.setContentText(errorMsg);
            alert.showAndWait();        
        }
	}
	
	// Main method
	public static void main(String[] args) {
		launch(args);
	}
}
