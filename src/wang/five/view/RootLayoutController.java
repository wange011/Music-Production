/*
Wang, Ethan
APCS-A
Period 5
Spring Project
*/

package wang.five.view;

//imports necessary classes, including JavaFX objects and Java.util objects
import java.io.File;
import java.io.FileNotFoundException;

//imports necessary classes
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import wang.five.Main;
import wang.five.model.Piece;

public class RootLayoutController {

	//fields of the controller class, the fields are decorated with @FXML to
	//show that they are within the FXML document

	//displays the title of the application
	@FXML
	private Label myLabel;

	//creates a new project
	@FXML
	private MenuItem myMI;

	//menu with save and load functions
	@FXML
	private MenuBar myMenuBar;

	//anchorpane display of the nodes
	@FXML
	private AnchorPane myAnchorPane;

	//field that allows controller to connect to the mainApp
	private Main mainApp;


	//empty constructor that is necessary for the loader to run
	public RootLayoutController() {

	}

	//necessary method for the loader
	@FXML
	public void initialize() {

	}

	//method that accepts a main class to link controller with
	public void setMainApp(Main mainApp) {

		this.mainApp = mainApp;

	}

	//when myButton is pressed, creates a new project
	@FXML
	private void handleNew() {

		//clears previous project and removes the file path
		mainApp.getProjectList().clear();

		//creates a new piece and displays the project
		Piece piece = new Piece(4, 4, "C", 120);
		mainApp.showProjectOverview(piece);

	}

	//method that allows a file to be loaded into the project
	@FXML
	private void handleOpen() throws FileNotFoundException {

		//creates a FileChooser to select the file
		FileChooser fileChooser = new FileChooser();

		//allows the user to select a file to open
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        //if a file has been selected
        if(file != null) {

        	//clears previous project and removes the file path
    		mainApp.getProjectList().clear();

    		//creates a default piece
    		Piece piece = new Piece(4, 4, "C", 120);
    		//the piece is rewritten into the piece in the file
    		piece.readFromFile(file);

    		//the program displays the piece
        	mainApp.showProjectOverview(piece);

        	//sets the file path of the program to that file
        	mainApp.setFile(file);

        }

        //used for error messaging
        else {

        	//alerts the user that the file could not be opened
        	Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("Error");
		    alert.setHeaderText("Could not load data");
		    alert.setContentText("No File Selected");

		    alert.showAndWait();

        }

	}

	//method that allows the user to save the file
	@FXML
	private void handleSave() throws FileNotFoundException {

		//if there is a file path to save to
		if (mainApp.getFile() != null) {

			//saves to the file path
			mainApp.getProjectList().get(0).writeAsFile(mainApp.getFile());

		}

		//if there is not
		else {

			//calls to handle a SaveAs
			handleSaveAs();

		}

	}


	//allows the user to save a completely new file
	@FXML
	private void handleSaveAs() throws FileNotFoundException {

		//creates a FileChooser to select the file
		FileChooser fileChooser = new FileChooser();

		//allows the user to select a file to save
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        //if a file is chosen
        if(file != null) {

        	//saves the file
        	mainApp.getProjectList().get(0).writeAsFile(file);

        	//sets the file path to that file
        	mainApp.setFile(file);

        }

      //used for error messaging
        else {

        	//alerts the user that the file could not be opened
        	Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("Error");
		    alert.setHeaderText("Could not save data");
		    alert.setContentText("No File Selected");

		    alert.showAndWait();

        }

	}

	//method that handles the event set off by the close Button
	@FXML
	private void handleClose() {

		//closes the window
		mainApp.close(mainApp.getPrimaryStage());

	}

	//method that handles the event set off by the about Button
	@FXML
	private void handleAbout() {

		//Initializes a surprise as an easter egg
		mainApp.initSurprise();

	}

}
