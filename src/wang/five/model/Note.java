/*
Wang, Ethan
APCS-A
Period 5
Spring Project
*/

package wang.five.model;

//all the necessary imports, includes properties and JavaFX objects
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Note {

	//length of the note
	private DoubleProperty length;
	//pitch of the note
	private StringProperty pitch;
	//volume of the note
	private IntegerProperty volume;
	//name of the note (ex. QuarterNote)
	private NoteType name;
	//if the note is dotted or not
	private BooleanProperty isDotted;
	//if the note is slured or not
	private BooleanProperty isSlured;
	//what measure the note is in
	private IntegerProperty measureNum;
	//what octave the pitch is
	private IntegerProperty octave;
	//AnchorPane to display each note
	private AnchorPane pane;

	//default constructor for the class
	public Note() {

		//fields are initialized to default values
		this.length = new SimpleDoubleProperty(0);
		this.name = NoteType.DEFAULT;
		this.pitch = new SimpleStringProperty("");
		this.volume = new SimpleIntegerProperty(0);
		this.isDotted = new SimpleBooleanProperty(false);
		this.isSlured = new SimpleBooleanProperty(false);
		this.measureNum = new SimpleIntegerProperty(0);
		this.octave = new SimpleIntegerProperty(5);

		//creates the AnchorPane to display the note
		setGraphic();

	}

	//overloaded constructor for the class
	public Note(NoteType name, String pitch, int volume, int measureNum) {

		//fields are initialized to parameters
		this.length = new SimpleDoubleProperty(0);
		this.name = name;
		this.pitch = new SimpleStringProperty(pitch);
		this.volume = new SimpleIntegerProperty(volume);
		this.isDotted = new SimpleBooleanProperty(false);
		this.isSlured = new SimpleBooleanProperty(false);
		this.measureNum = new SimpleIntegerProperty(measureNum);
		this.octave = new SimpleIntegerProperty(5);

		//creates the AnchorPane to display the note
		setGraphic();

	}

	//overloaded constructor for the class
	public Note(NoteType name, String pitch, int volume, boolean isDotted, boolean isSlured, int measureNum) {

		//fields are initialized to parameters
		this.length = new SimpleDoubleProperty(0);
		this.name = name;
		this.pitch = new SimpleStringProperty(pitch);
		this.volume = new SimpleIntegerProperty(volume);
		this.isDotted = new SimpleBooleanProperty(isDotted);
		this.isSlured = new SimpleBooleanProperty(isSlured);
		this.measureNum = new SimpleIntegerProperty(measureNum);
		this.octave = new SimpleIntegerProperty(5);

		//creates the AnchorPane to display the note
		setGraphic();

	}

	//overloaded constructor for the class
	public Note(double length, NoteType name, String pitch, int volume, boolean isDotted, boolean isSlured, int measureNum, int octave) {

		//fields are initialized to parameters
		this.length = new SimpleDoubleProperty(length);
		this.name = name;
		this.pitch = new SimpleStringProperty(pitch);
		this.volume = new SimpleIntegerProperty(volume);
		this.isDotted = new SimpleBooleanProperty(isDotted);
		this.isSlured = new SimpleBooleanProperty(isSlured);
		this.measureNum = new SimpleIntegerProperty(measureNum);
		this.octave = new SimpleIntegerProperty(octave);

		//creates the AnchorPane to display the note
		setGraphic();

	}


	//getters and setters for the fields
	public double getLength() {
		return length.get();
	}

	public void setLength(double length) {
		this.length.set(length);
	}

	public String getPitch() {
		return pitch.get();
	}

	public void setPitch(String pitch) {
		this.pitch.set(pitch);
	}

	public int getVolume() {
		return volume.get();
	}

	public void setVolume(int volume) {
		this.volume.set(volume);
	}

	public NoteType getName() {
		return name;
	}

	public void setName(NoteType name) {
		this.name = name;
	}

	public boolean getIsDotted() {
		return isDotted.get();
	}

	public void setIsDotted(boolean isDotted) {
		this.isDotted.set(isDotted);
	}

	public boolean getIsSlured() {
		return isSlured.get();
	}

	public void setIsSlured(boolean isSlured) {
		this.isSlured.set(isSlured);
	}

	public int getMeasureNum() {
		return measureNum.get();
	}

	public void setMeasureNum(int measureNum) {
		this.measureNum.set(measureNum);
	}

	public int getOctave() {
		return octave.get();
	}

	public void setOctave(int octave) {
		this.octave.set(octave);
	}

	public AnchorPane getPane() {
		return pane;
	}

	public void setPane(AnchorPane pane) {
		this.pane = pane;
	}

	//getters for the property values
	public DoubleProperty noteLengthProperty() {
		return length;
	}

	public StringProperty notePitchProperty() {
		return pitch;
	}

	public IntegerProperty noteVolumeProperty() {
		return volume;
	}

	public BooleanProperty isDottedProperty() {
		return isDotted;
	}

	public BooleanProperty isSluredProperty() {
		return isSlured;
	}

	public IntegerProperty measureNumProperty() {
		return measureNum;
	}

	public IntegerProperty octaveProperty() {
		return octave;
	}


	//sets the shape of the note to display based on the type
	private void setGraphic() {

		//finds the proper image
		switch(this.name){

		//each case changes the length of the note according to the type
		//and instantiates the AnchorPane for the display

		case WHOLENOTE:

			this.length.set(4);
			calcLength();
			ImageView imageView = new ImageView("file:Resources/wholenote.png");
			pane = new AnchorPane(imageView);
			pane.prefWidth(26);
			pane.prefHeight(22);

		break;

		case HALFNOTE:

			this.length.set(2);
			calcLength();
			imageView = new ImageView("file:Resources/halfnote.png");
			pane = new AnchorPane(imageView);
			pane.prefWidth(21);
			pane.prefHeight(40);

		break;

		case QUARTERNOTE:

			this.length.set(1);
			calcLength();
			imageView = new ImageView("file:Resources/quarternote.png");
			pane = new AnchorPane(imageView);
			pane.prefWidth(21);
			pane.prefHeight(40);

		break;

		case EIGHTHNOTE:

			this.length.set(.5);
			calcLength();
			imageView = new ImageView("file:Resources/eighthnote.png");
			pane = new AnchorPane(imageView);
			pane.prefWidth(30);
			pane.prefHeight(30);

		break;

		case SIXTEENTHNOTE:

			this.length.set(.25);
			calcLength();
			imageView = new ImageView("file:Resources/sixteenthnote.png");
			pane = new AnchorPane(imageView);
			pane.prefWidth(23);
			pane.prefHeight(30);

		break;

		case WHOLEREST:

			this.length.set(4);
			calcLength();
			imageView = new ImageView("file:Resources/wholerest.png");
			pane = new AnchorPane(imageView);
			pane.prefWidth(53);
			pane.prefHeight(25);
			this.volume.set(0);

		break;

		case HALFREST:

			this.length.set(2);
			calcLength();
			imageView = new ImageView("file:Resources/halfrest.png");
			pane = new AnchorPane(imageView);
			pane.prefWidth(53);
			pane.prefHeight(25);
			this.volume.set(0);

		break;

		case QUARTERREST:

			this.length.set(1);
			calcLength();
			imageView = new ImageView("file:Resources/quarterrest.png");
			pane = new AnchorPane(imageView);
			pane.prefWidth(16);
			pane.prefHeight(40);
			this.volume.set(0);

		break;

		case EIGHTHREST:

			this.length.set(.5);
			calcLength();
			imageView = new ImageView("file:Resources/eighthrest.png");
			pane = new AnchorPane(imageView);
			pane.prefWidth(12);
			pane.prefHeight(22);
			this.volume.set(0);

		break;

		case SIXTEENTHREST:

			this.length.set(.25);
			calcLength();
			imageView = new ImageView("file:Resources/sixteenthrest.png");
			pane = new AnchorPane(imageView);
			pane.prefWidth(11);
			pane.prefHeight(22);
			this.volume.set(0);

		break;

		case DEFAULT:

			this.length.set(0);
			calcLength();
			this.volume.set(0);

		break;

		//default case that should not occur
		//helps with debugging
		default:

			System.out.println("Error");

		break;

		}

	}

	//calculates the length of the note (based upon if dotted or not)
	private void calcLength() {

		if(getIsDotted()) {

			//if it is dotted the length is increased by 50%
			this.length.set(getLength() * 1.5);

		}

	}

}
