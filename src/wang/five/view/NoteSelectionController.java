/*
Wang, Ethan
APCS-A
Period 5
Spring Project
*/

package wang.five.view;

//imports necessary classes, including JavaFX objects
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import wang.five.Main;
import wang.five.model.Note;
import wang.five.model.NoteType;
import wang.five.model.Piece;

public class NoteSelectionController {

	//the JavaFX objects that are in the view
	//decorated with @FXML for the loader to recognize

	//reads the Measure selected
	@FXML
	private TextField myTxtFld1;

	//reads the Note selected
	@FXML
	private TextField myTxtFld2;

	//reads the volume entered
	@FXML
	private TextField myTxtFld3;

	//Radio Buttons to select the pitch
	@FXML
	private RadioButton myRdBtnA;

	@FXML
	private RadioButton myRdBtnB;

	@FXML
	private RadioButton myRdBtnC;

	@FXML
	private RadioButton myRdBtnD;

	@FXML
	private RadioButton myRdBtnE;

	@FXML
	private RadioButton myRdBtnF;

	@FXML
	private RadioButton myRdBtnG;

	//Radio Buttons to select any accidentals
	@FXML
	private RadioButton rdBtnNatural;

	@FXML
	private RadioButton rdBtnSharp;

	@FXML
	private RadioButton rdBtnFlat;

	//Radio Buttons to select the NoteType
	@FXML
	private RadioButton rdBtnWN;

	@FXML
	private RadioButton rdBtnHN;

	@FXML
	private RadioButton rdBtnQN;

	@FXML
	private RadioButton rdBtnEN;

	@FXML
	private RadioButton rdBtnSN;

	@FXML
	private RadioButton rdBtnWR;

	@FXML
	private RadioButton rdBtnHR;

	@FXML
	private RadioButton rdBtnQR;

	@FXML
	private RadioButton rdBtnER;

	@FXML
	private RadioButton rdBtnSR;

	//a button to perform the action
	@FXML
	private Button myBtnOK;

	//a button to cancel the action
	@FXML
	private Button myBtnCancel;

	//a label to display error messages
	@FXML
	private Label errorLbl;

	//three ToggleGroups to sort the Radio Buttons
	private ToggleGroup notePitch;
	private ToggleGroup accidentals;
	private ToggleGroup noteType;

	//the root controller for the view (where the call for initialization originated)
	private ProjectOverviewController rootController;

	//the function of the view (changes based upon the root controller call)
	private int function;

	//field that allows controller to connect to the mainApp
	private Main mainApp;


	//empty constructor that is necessary for the loader to run
	public NoteSelectionController() {

	}

	//necessary method for the loader that is decorated with @FXML
	@FXML
	public void initialize() {

		//initializes the ToggleGroup and places the Radio Button in the group
		notePitch = new ToggleGroup();
		myRdBtnA.setToggleGroup(notePitch);
		myRdBtnB.setToggleGroup(notePitch);
		myRdBtnC.setToggleGroup(notePitch);
		myRdBtnD.setToggleGroup(notePitch);
		myRdBtnE.setToggleGroup(notePitch);
		myRdBtnF.setToggleGroup(notePitch);
		myRdBtnG.setToggleGroup(notePitch);

		//initializes the ToggleGroup and places the Radio Button in the group
		accidentals = new ToggleGroup();
		rdBtnNatural.setToggleGroup(accidentals);
		rdBtnSharp.setToggleGroup(accidentals);
		rdBtnFlat.setToggleGroup(accidentals);
		//the default toggle of the group
		accidentals.selectToggle(rdBtnNatural);

		//initializes the ToggleGroup and places the Radio Button in the group
		noteType = new ToggleGroup();
		rdBtnWN.setToggleGroup(noteType);
		rdBtnHN.setToggleGroup(noteType);
		rdBtnQN.setToggleGroup(noteType);
		rdBtnEN.setToggleGroup(noteType);
		rdBtnSN.setToggleGroup(noteType);
		rdBtnWR.setToggleGroup(noteType);
		rdBtnHR.setToggleGroup(noteType);
		rdBtnQR.setToggleGroup(noteType);
		rdBtnER.setToggleGroup(noteType);
		rdBtnSR.setToggleGroup(noteType);

		//initializes the text of the labels
		myTxtFld3.setText("1");
		errorLbl.setText("");

	}

	//getters and setters for the fields
	public int getFunction() {
		return function;
	}

	public void setFunction(int function) {
		this.function = function;
	}

	public ProjectOverviewController getRootController() {
		return rootController;
	}

	public void setController(ProjectOverviewController controller) {
		this.rootController = controller;
	}

	//method that accepts a main class to link controller with
	public void setMainApp(Main mainApp) {

		this.mainApp = mainApp;

	}

	//method that handles the event set off by the OK Button
	@FXML
	private void handleOK() {

		//removes error comments originally in the label
		errorLbl.setText("");

		try{

			//gets the piece from the main application and finds the selected note
			//calls to helper methods in the class
			Piece piece = mainApp.getProjectList().get(0);
			int noteIndex = getSelectedNote();
			int measureNum = getSelectedMeasureNum();

			//performs the action based upon the function
			switch(function) {

				case 0:

					//adds the note to the piece
					Note note = getSelected();
					piece.getBar(measureNum).addNote(noteIndex, note);

				break;

				case 1:

					//edits the note in the piece
					note = getSelected();
					piece.getBar(measureNum).editNote(noteIndex, note);

				break;

				case 2:

					//deletes the note in the piece
					piece.getBar(measureNum).deleteNote(noteIndex);

				break;

			}

			//call for the measures to be updated
			rootController.setMeasures();

			//closes the window
			mainApp.close(mainApp.getSecondaryStage());

		}

		//helps with debugging
		catch(Exception e) {

			//prints the stack trace of the exception
			e.printStackTrace();

		}

	}

	//method that handles the event set off by the Cancel Button
	@FXML
	private void handleCancel() {

		//closes the window
		mainApp.close(mainApp.getSecondaryStage());

	}

	//helper method that reads the selected Radio Buttons
	private Note getSelected() {

		//defines a Note called note to return
		Note note = new Note();

		//finds the function of the note to find the default values of the note
		switch(function) {

			//add the note
			case 0:

				//creates the new note based on the entered information
				//calls to helper methods that return the information
				NoteType name = getSelectedNoteType();
				String pitch = getSelectedPitch();
				int volume = getSelectedVolume();
				int measureNum = getSelectedMeasureNum();
				note = new Note(name,  pitch,  volume,  measureNum);

			break;

			//edit the note
			case 1:

				//gets the location of the note in the note array
				measureNum = getSelectedMeasureNum();
				int noteIndex = getSelectedNote();

				//if nothing is selected, keep the property of the note the same
				if(getSelectedNoteType() == NoteType.DEFAULT) {

					name = mainApp.getProjectList().get(0).getBar(measureNum).getNote(noteIndex).getName();

				}

				//else change the note property based upon the selected option
				else {

					name = getSelectedNoteType();

				}

				//if nothing is selected, keep the property of the note the same
				if(getSelectedPitch().equals("")) {

					pitch = mainApp.getProjectList().get(0).getBar(measureNum).getNote(noteIndex).getPitch();

				}

				//else change the note property based upon the selected option
				else {

					pitch = getSelectedPitch();

				}

				//if nothing is selected, keep the property of the note the same
				if(getSelectedVolume() == -1) {

					volume = mainApp.getProjectList().get(0).getBar(measureNum).getNote(noteIndex).getVolume();

				}

				//else change the note property based upon the selected option
				else {

					volume = getSelectedVolume();

				}

				//return the new note with the selected values
				note = new Note(name,  pitch,  volume,  measureNum);

			break;

		}

		return note;

	}

	//helper method that returns the Pitch that is entered
	private String getSelectedPitch() {

		try {

			//finds the text of the Radio Button selected
			String pitch = ((RadioButton) notePitch.getSelectedToggle()).getText();

			//switches on the accidentals ToggleGroup to find whether an accidental
			//needs to be concatenated
			switch(((RadioButton) accidentals.getSelectedToggle()).getText()) {

				case "Natural":
				break;

				case "Sharp":
					pitch += "#";
				break;

				case "Flat":
					pitch += "b";
				break;

			}

			//returns the string
			return pitch;

		}

		//used for debugging or error messaging
		catch(Exception e) {

			//tells the user what the error is
			errorLbl.setText(errorLbl.getText() + "Error: No Pitch Selected 	");

		}

		//if there is an error, returns an empty string
		return "";

	}

	//helper method that returns the value of the NoteType selected
	private NoteType getSelectedNoteType() {

		try {

			//defines the NoteType type
			NoteType type = NoteType.DEFAULT;

			//switches on the text of the Radio Button selected to determine the correct
			//NoteType
			switch(((RadioButton) noteType.getSelectedToggle()).getText()) {

				//each case sets the value of type to the corresponding NoteType

				case "Whole Note":
					type = NoteType.WHOLENOTE;
				break;

				case "Half Note":
					type = NoteType.HALFNOTE;
				break;

				case "Quarter Note":
					type = NoteType.QUARTERNOTE;
				break;

				case "Eighth Note":
					type = NoteType.EIGHTHNOTE;
				break;

				case "Sixteenth Note":
					type = NoteType.SIXTEENTHNOTE;
				break;

				case "Whole Rest":
					type = NoteType.WHOLEREST;
				break;

				case "Half Rest":
					type = NoteType.HALFREST;
				break;

				case "Quarter Rest":
					type = NoteType.QUARTERREST;
				break;

				case "Eighth Rest":
					type = NoteType.EIGHTHREST;
				break;

				case "Sixteenth Rest":
					type = NoteType.SIXTEENTHREST;
				break;

			}

			return type;

		}

		//used for debugging and error messaging
		catch(Exception e) {

			//tells the user what the error was
			errorLbl.setText(errorLbl.getText() + "Error: No Note Type Selected  	");

		}

		//if there was an error, returns the DEFAULT NoteType
		return NoteType.DEFAULT;

	}

	//helper method that returns the value of the volume selected
	private int getSelectedVolume() {

		try {

			//gets the value entered in the TextField
			int volume = Integer.parseInt(myTxtFld3.textProperty().get().toString());

			return volume;

		}

		//used for debugging and error messaging
		catch(Exception e) {

			//tells the user what the error was
			errorLbl.setText(errorLbl.getText() + "Error: No Volume Entered 	");

		}

		//if there was an error, returns -1 (a flag value)
		return -1;

	}

	//helper method that returns the value of the Measure entered
	private int getSelectedMeasureNum() {

		try {

			//gets the value entered in the TextField
			int measureNum = Integer.parseInt(myTxtFld1.textProperty().get().toString()) - 1;

			return measureNum;

		}

		//used for debugging and error messaging
		catch(Exception e) {

			//tells the user what the error was
			errorLbl.setText(errorLbl.getText() + "Error: No Measure Number Entered 	");

		}

		//if there was an error, returns -1 (a flag value)
		return -1;

	}

	//helper method that returns the value of the Note selected
	private int getSelectedNote() {

		try {

			//gets the value entered in the TextField
			int noteIndex = Integer.parseInt(myTxtFld2.textProperty().get().toString()) - 1;

			return noteIndex;

		}

		//used for debugging and error messaging
		catch(Exception e) {

			//tells the user what the error was
			errorLbl.setText(errorLbl.getText() + "Error: No Note Selected		");

		}

		//if there was an error, returns -1 (a flag value)
		return -1;

	}

}
