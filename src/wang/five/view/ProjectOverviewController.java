/*
Wang, Ethan
APCS-A
Period 5
Spring Project
*/

package wang.five.view;

//imports necessary classes, including JavaFX objects and Java.util objects
import java.util.StringTokenizer;

//imports necessary classes
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import wang.five.Main;
import wang.five.model.Ornament;
import wang.five.model.Piece;

public class ProjectOverviewController {

	//fields of the controller class, the fields are decorated with @FXML to
	//show that they are within the FXML document

	//the ScrollPane to display the view
	@FXML
	private ScrollPane scrollPane;

	//AnchorPane inside the ScrollPane
	@FXML
	private AnchorPane myAnchorPane;

	//MenuItems part of the add option on the MenuBar
	//adds a measure
	@FXML
	private MenuItem addMeasureMI;

	//adds a note
	@FXML
	private MenuItem addNoteMI;

	//adds an ornament
	@FXML
	private MenuItem addOrnamentMI;

	//MenuItems part of the edit option on the MenuBar
	//edits a note
	@FXML
	private MenuItem editNoteMI;

	//edits the ornament
	@FXML
	private MenuItem editOrnamentMI;

	//edits the time signature
	@FXML
	private MenuItem editTimeSigMI;

	//edits the key signature
	@FXML
	private MenuItem editKeySigMI;

	//edits the tempo
	@FXML
	private MenuItem editTempoMI;

	//MenuItems part of the delete option on the MenuBar
	//deletes a measure
	@FXML
	private MenuItem delMeasureMI;

	//deletes a note
	@FXML
	private MenuItem delNoteMI;

	//deletes an ornament
	@FXML
	private MenuItem delOrnamentMI;

	//the MenuBar where the options are located
	@FXML
	private MenuBar myMenuBar;

	@FXML
	private Label errorLbl;

	//a label to display error messages
	@FXML
	private TextField myTxtFld;

	//Boolean that determines if an ornament can be added
	private Boolean addingOrnament;
	//ornament that is added
	private Ornament adding;

	//field that allows controller to connect to the mainApp
	private Main mainApp;


	//empty constructor that is necessary for the loader to run
	public ProjectOverviewController() {

	}

	//necessary method for the loader
	@FXML
	public void initialize() {

		//initializes the text of the label
		errorLbl.setText("");

		//default value is false
		addingOrnament = false;

		//initializes the ornament added
		adding = new Ornament();

	}

	//method that accepts a main class to link controller with
	public void setMainApp(Main mainApp) {

		this.mainApp = mainApp;

	}

	//getters and setters for the fields
	public Ornament getAdding() {
		return adding;
	}

	public void setAdding(Ornament adding) {
		this.adding = adding;
	}

	//method that displays the measures of the piece
	public void setMeasures() {

		//gets the piece from the mainApp
		Piece piece = mainApp.getProjectList().get(0);

		//removes the objects on the AnchorPane to prevent duplicate objects
		myAnchorPane.getChildren().clear();

		//initial values for the row number, the column number, and the spacing needed for graphics
		int row = -1;
		int col = 0;
		int indent = 0;

		//gets every measure in the piece
		for(int x = 0, stop = piece.getBars().length; x<stop; x++) {

			//distributes extra space as needed
			if(piece.getBar(x).getFirst() == true) {

				//changes the value of the row number and column number accordingly
				indent = 0;
				row++;
				col = 0;

				//sets the measure graphic at the calculated position
				AnchorPane graphic = piece.getBar(x).getStaff();
				AnchorPane.setTopAnchor(graphic, (25.0 + 80*row));
				AnchorPane.setLeftAnchor(graphic, 40*piece.getBar(x).getLength() * col + indent);
				myAnchorPane.getChildren().add(graphic);

				//the next measure needs an indent since the first measure was longer
				col++;
				indent = 60;
				if(piece.getBar(x).getMeasureNum() == 0){

					indent = 80;

				}

			}

			//if the measure does not need extra space
			else {

				//the graphic is set in the calculated position
				AnchorPane graphic = piece.getBar(x).getStaff();
				AnchorPane.setTopAnchor(graphic, (25.0 + 80*row));
				AnchorPane.setLeftAnchor(graphic, 40*piece.getBar(x).getLength() * col + indent);
				myAnchorPane.getChildren().add(graphic);
				col++;
			}

		}

		//adds the static items back into the AnchorPane
		AnchorPane.setTopAnchor(myMenuBar, 0.0);
		AnchorPane.setTopAnchor(errorLbl, 2.0);
		AnchorPane.setTopAnchor(myTxtFld, 0.0);
		AnchorPane.setBottomAnchor(myMenuBar, 25.0 + 80*row + 80);
		myAnchorPane.getChildren().addAll(myMenuBar, errorLbl, myTxtFld);

		//makes a call to the helper method
		setOrnaments();

	}

	//helper method that displays the ornaments in the piece
	public void setOrnaments() {

		//gets the piece from the mainApp
		Piece piece = mainApp.getProjectList().get(0);

		//for every ornament in the peice
		for(int x = 0, stop = piece.getOrnaments().length; x<stop; x++) {

			//calculates the position of the ornament and inputs it into the Pane
			AnchorPane graphic = piece.getOrnament(x).getDisplay();
			double xLocation = piece.getOrnament(x).getxLocation();
			double yLocation = piece.getOrnament(x).getyLocation();
			AnchorPane.setLeftAnchor(graphic, xLocation - 20);
			AnchorPane.setTopAnchor(graphic, yLocation - 70);
			myAnchorPane.getChildren().add(graphic);

		}

	}

	//handles the event caused by the add note option
	@FXML
	private void handleAddNote() {

		//Initializes the NoteSelection with this as the root controller and add as the function
		mainApp.initNoteSelection(0, this);

	}

	//handles the event caused by the edit note option
	@FXML
	private void handleEditNote() {

		//Initializes the NoteSelection with this as the root controller and edit as the function
		mainApp.initNoteSelection(1, this);

	}

	//handles the event caused by the delete note option
	@FXML
	private void handleDeleteNote() {

		//Initializes the NoteSelection with this as the root controller and delete as the function
		mainApp.initNoteSelection(2, this);

	}

	//handles the event caused by the add measure option
	@FXML
	private void handleAddMeasure() {

		try {

			//removes error comments originally in the label
			errorLbl.setText("");

			//gets the piece from the mainApp
			Piece piece = mainApp.getProjectList().get(0);
			int measureNum = 0;

			//if no measure is selected
			if(myTxtFld.textProperty().get().equals("")) {

				//places the measure at the end
				measureNum = piece.getBars().length;

			}

			else {

				//gets the selected measure
				measureNum = Integer.parseInt(myTxtFld.textProperty().get().toString()) - 1;

			}

			//adds a new measure at the index
			piece.addMeasure(measureNum);

			//updates the measures
			setMeasures();

			//display the action to the user
			errorLbl.setText("Added Measure " + (measureNum + 1));
			myTxtFld.textProperty().set("");


		}

		//used for debugging or error messaging
		catch (Exception e) {

			//tells the user what the error is
			e.printStackTrace();
			errorLbl.setText("Type in Measure Number");

		}

	}

	//handles the event caused by the delete measure option
	@FXML
	private void handleDeleteMeasure() {

		try {

			//removes error comments originally in the label
			errorLbl.setText("");
			//gets the piece from the mainApp
			Piece piece = mainApp.getProjectList().get(0);

			//gets the measure selected to delete
			int measureNum = Integer.parseInt(myTxtFld.textProperty().get().toString()) - 1;
			//deletes the selected measure
			piece.deleteMeasure(measureNum);

			//updates the measures
			setMeasures();

			//displays to the user the performed action
			errorLbl.setText("Deleted Measure " + (measureNum + 1));
			myTxtFld.textProperty().set("");


		}

		//used for debugging or error messaging
		catch (Exception e) {

			//tells the user what the error is
			e.printStackTrace();
			errorLbl.setText("Type in Measure Number");

		}

	}

	//handles the event caused by the edit time signature option
	@FXML
	private void handleEditTimeSig() {

		try {

			//removes error comments originally in the label
			errorLbl.setText("");
			//gets the piece from the mainApp
			Piece piece = mainApp.getProjectList().get(0);

			//creates a StringTokenizer that reads the top and bottom of the time signature
			StringTokenizer st = new StringTokenizer(myTxtFld.textProperty().get().toString());
			//inputs the top and bottom of the time signature into the piece
			int topTimeSig = Integer.parseInt(st.nextToken());
			int botTimeSig = Integer.parseInt(st.nextToken());
			piece.setTopTimeSig(topTimeSig);
			piece.setBotTimeSig(botTimeSig);

			//updates the measures
			setMeasures();

			//displays to the user the performed action
			errorLbl.setText("Edited Time Signature To: " + topTimeSig + " " + botTimeSig);
			myTxtFld.textProperty().set("");

		}

		//used for debugging or error messaging
		catch (Exception e) {

			//tells the user what the error is
			e.printStackTrace();
			errorLbl.setText("Type in Time Signature in form: Top Bottom");

		}

	}

	//handles the event caused by the edit key signature option
	@FXML
	private void handleEditKeySig() {

		try {

			//removes error comments originally in the label
			errorLbl.setText("");
			//gets the piece from the mainApp
			Piece piece = mainApp.getProjectList().get(0);

			//gets the value of the key signature selected and sets it to the piece
			String keySig = myTxtFld.textProperty().get().toString();
			piece.setKeySig(keySig);

			//updates the measures
			setMeasures();

			//displays to the user the performed action
			errorLbl.setText("Edited Key Signature To: " + keySig);
			myTxtFld.textProperty().set("");


		}

		//used for debugging or error messaging
		catch (Exception e) {

			//tells the user what the error is
			e.printStackTrace();
			errorLbl.setText("Type in the Key Signature");

		}

	}

	//handles the event caused by the edit tempo option
	@FXML
	private void handleEditTempo() {

		try {

			//removes error comments originally in the label
			errorLbl.setText("");
			//gets the piece from the mainApp
			Piece piece = mainApp.getProjectList().get(0);

			//gets the value of the tempo selected and sets it to the piece
			int tempo = Integer.parseInt(myTxtFld.textProperty().get().toString());
			piece.setTempo(tempo);

			//updates the measures
			setMeasures();

			//displays to the user the performed action
			errorLbl.setText("Edited Tempo To: " + tempo);
			myTxtFld.textProperty().set("");


		}

		//used for debugging or error messaging
		catch (Exception e) {

			//tells the user what the error is
			e.printStackTrace();
			errorLbl.setText("Type in the Tempo");

		}

	}

	//handles the event caused by the add ornament option
	@FXML
	private void handleAddOrnament() {

		try {

			//allows an ornament to be added
			addingOrnament = true;

			//initializes the OrnamentSelection with this controller as the root controller
			mainApp.initOrnamentSelection(this);

			//tells the user that an ornament can be added
			errorLbl.setText("Click to Add");

		}

		//helps with debugging
		catch(Exception e) {

			//prints the stack trace of the exception caused
			e.printStackTrace();

		}

	}

	//handles the event caused by the edit ornament option
	@FXML
	private void handleEditOrnament() {

		try {

			//removes error comments originally in the label
			errorLbl.setText("");
			//gets the piece from the mainApp
			Piece piece = mainApp.getProjectList().get(0);

			//gets the ornament that is edited
			int index = Integer.parseInt(myTxtFld.textProperty().get().toString()) - 1;
			//deletes the ornament to add a new one
			piece.deleteOrnament(index);

			//allows an ornament to be added
			addingOrnament = true;

			//initializes the OrnamentSelection with this controller as the root controller
			mainApp.initOrnamentSelection(this);

			//tells the user that an ornament can be added
			errorLbl.setText("Click New Position");

		}

		//used for debugging or error messaging
		catch(Exception e) {

			//tells the user what the error is
			errorLbl.setText("Error: Input Ornament Number (Counting by Position)");
			e.printStackTrace();

		}

	}

	//handles the event caused by the delete ornament option
	@FXML
	private void handleDeleteOrnament() {

		try {

			//removes error comments originally in the label
			errorLbl.setText("");
			//gets the piece from the mainApp
			Piece piece = mainApp.getProjectList().get(0);

			//gets the value of the ornament that is deleted
			int index = Integer.parseInt(myTxtFld.textProperty().get().toString()) - 1;
			//deletes the ornament
			piece.deleteOrnament(index);

			//updates the measures
			setMeasures();

		}

		//used for debugging or error messaging
		catch(Exception e) {

			//tells the user what the error is
			errorLbl.setText("Error: Incorrect Ornament Number");
			e.printStackTrace();

		}

	}

	//handles the event caused by a MouseEvent (click on the AnchorPane)
	@FXML
    private void inputOrnament(MouseEvent event) {

		try {

			//removes error comments originally in the label
			errorLbl.setText("");
			//gets the piece from the mainApp
			Piece piece = mainApp.getProjectList().get(0);

			//if an ornament can be added
			if(addingOrnament == true) {

				//gets the x and y location according to the mouse position
				double xLocation = event.getSceneX();
				double yLocation = event.getSceneY();
				//sets those value to the ornament
				adding.setxLocation(xLocation);
				adding.setyLocation(yLocation);
				//adds the ornament
				piece.addOrnament(adding);
				//the ornament is no longer being added
				addingOrnament = false;

			}

			//updates the measures
			setMeasures();

		}

		//helps with debugging
        catch(Exception e) {

        	//prints the stack trace of the exception caused
        	e.printStackTrace();

        }

	}

}