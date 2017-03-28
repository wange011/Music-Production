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
import javafx.scene.control.ToggleGroup;
import wang.five.Main;
import wang.five.model.Ornament;
import wang.five.model.OrnamentType;


public class OrnamentSelectionController {


	//the JavaFX objects that are in the view
	//decorated with @FXML for the loader to recognize

	//a button to perform the action
	@FXML
	private Button myBtnOK;

	//a button to cancel the action
	@FXML
	private Button myBtnCancel;

	//a label to display error messages
	@FXML
	private Label errorLbl;

	//Radio Buttons to select the OrnamentType
	//TRILL, CRES, DIM, RITARD, FERMATA, PIANO, MP, MF, FORTE, PP, FF, COMMENT
	@FXML
	private RadioButton rdBtnTrill;

	@FXML
	private RadioButton rdBtnCres;

	@FXML
	private RadioButton rdBtnDim;

	@FXML
	private RadioButton rdBtnRitard;

	@FXML
	private RadioButton rdBtnFermata;

	@FXML
	private RadioButton rdBtnPiano;

	@FXML
	private RadioButton rdBtnMP;

	@FXML
	private RadioButton rdBtnMF;

	@FXML
	private RadioButton rdBtnForte;

	@FXML
	private RadioButton rdBtnPP;

	@FXML
	private RadioButton rdBtnFF;

	@FXML
	private RadioButton rdBtnComment;

	//a ToggleGroup to group the Radio Buttons
	private ToggleGroup ornaments;

	//the root controller for the view (where the call for initialization originated)
	private ProjectOverviewController rootController;

	//field that allows controller to connect to the mainApp
	private Main mainApp;


	//empty constructor that is necessary for the loader to run
	public OrnamentSelectionController() {

	}

	//necessary method for the loader
	@FXML
	public void initialize() {

		//initializes the ToggleGroup and places the Radio Button in the group
		ornaments = new ToggleGroup();
		rdBtnTrill.setToggleGroup(ornaments);
		rdBtnCres.setToggleGroup(ornaments);
		rdBtnDim.setToggleGroup(ornaments);
		rdBtnRitard.setToggleGroup(ornaments);
		rdBtnFermata.setToggleGroup(ornaments);
		rdBtnPiano.setToggleGroup(ornaments);
		rdBtnMP.setToggleGroup(ornaments);
		rdBtnMF.setToggleGroup(ornaments);
		rdBtnForte.setToggleGroup(ornaments);
		rdBtnPP.setToggleGroup(ornaments);
		rdBtnFF.setToggleGroup(ornaments);
		rdBtnComment.setToggleGroup(ornaments);

		//initializes the text of the label
		errorLbl.setText("");

	}

	//getters and setters for the fields
	public ProjectOverviewController getRootController() {
		return rootController;
	}

	public void setRootController(ProjectOverviewController controller) {
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

			//calls to helper methods in the class to determine the selected ornament
			Ornament ornament = getSelected();

			//if an ornament was selected
			if(ornament.getType() != OrnamentType.DEFAULT) {

				//allows the root controller to handle the adding of the ornament
				rootController.setAdding(ornament);

			}

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

	//helper method that returns the Ornament selected
	private Ornament getSelected() {

		try {

			//defines the Ornament ornament to return
			Ornament ornament = new Ornament();

			//gets the property OrnamentType based on the Radio Button selected
			switch(((RadioButton) ornaments.getSelectedToggle()).getText()) {

				//each case sets the type of the ornament to the proper value

				case "Trill":
					ornament.setType(OrnamentType.TRILL);
				break;

				case "Crescendo":
					ornament.setType(OrnamentType.CRES);
				break;

				case "Diminuendo":
					ornament.setType(OrnamentType.DIM);
				break;

				case "Ritardando":
					ornament.setType(OrnamentType.RITARD);
				break;

				case "Fermata":
					ornament.setType(OrnamentType.FERMATA);
				break;

				case "Piano":
					ornament.setType(OrnamentType.PIANO);
				break;

				case "Mezzo Piano":
					ornament.setType(OrnamentType.MP);
				break;

				case "Mezzo Forte":
					ornament.setType(OrnamentType.MF);
				break;

				case "Forte":
					ornament.setType(OrnamentType.FORTE);
				break;

				case "Pianissimo":
					ornament.setType(OrnamentType.PP);
				break;

				case "Fortissimo":
					ornament.setType(OrnamentType.FF);
				break;

				case "Comment":
					ornament.setType(OrnamentType.COMMENT);
				break;

			}

			return ornament;

		}

		//used for debugging or error messaging
		catch(Exception e) {

			//tells the user what the error is
			errorLbl.setText("Error: No Ornament Selected");

			//if there is an error, returns null
			return null;

		}

	}

}
