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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class Measure {

	//contains the notes in the measure
	private Note[] notes;

	//the amount of space left in the measure
	private DoubleProperty lengthAvailable;
	//the amount of space contained in the measure
	private DoubleProperty lengthContained;
	//the total length of the measure
	private DoubleProperty length;
	//the number of the measure in the piece
	private IntegerProperty measureNum;
	//AnchorPane to display a measure
	private AnchorPane staff;
	//whether the measure is the first in the row
	private BooleanProperty first;

	//default constructor for the class
	public Measure() {

		//default note array in the measure
		this.notes = new Note[0];

		//fields are initialized to default values
		this.lengthAvailable = new SimpleDoubleProperty(0);
		this.lengthContained = new SimpleDoubleProperty(0);
		this.length = new SimpleDoubleProperty(this.lengthContained.get() + this.lengthAvailable.get());
		this.measureNum = new SimpleIntegerProperty(0);
		this.first = new SimpleBooleanProperty(false);

		//creates the AnchorPane to display the measure
		setGraphic();

	}

	//overloaded constructor for the class
	public Measure(double lengthAvailable) {

		//default note array in the measure
		this.notes = new Note[0];

		//fields are initialized to parameters
		this.lengthAvailable = new SimpleDoubleProperty(lengthAvailable);
		this.lengthContained = new SimpleDoubleProperty(0);
		this.length = new SimpleDoubleProperty(this.lengthContained.get() + this.lengthAvailable.get());
		this.measureNum = new SimpleIntegerProperty(0);
		this.first = new SimpleBooleanProperty(false);

		//creates the AnchorPane to display the measure
		setGraphic();

	}

	//overloaded constructor for the class
	public Measure(double lengthAvailable, int measureNum) {

		//default note array in the measure
		this.notes = new Note[0];

		//fields are initialized to parameters
		this.lengthAvailable = new SimpleDoubleProperty(lengthAvailable);
		this.lengthContained = new SimpleDoubleProperty(0);
		this.length = new SimpleDoubleProperty(this.lengthContained.get() + this.lengthAvailable.get());
		this.measureNum = new SimpleIntegerProperty(measureNum);
		this.first = new SimpleBooleanProperty(false);

		//creates the AnchorPane to display the measure
		setGraphic();

	}

	//overloaded constructor for the class
	public Measure(double lengthAvailable, int measureNum, boolean first) {

		//default note array in the measure
		this.notes = new Note[0];

		//fields are initialized to parameters
		this.lengthAvailable = new SimpleDoubleProperty(lengthAvailable);
		this.lengthContained = new SimpleDoubleProperty(0);
		this.length = new SimpleDoubleProperty(this.lengthContained.get() + this.lengthAvailable.get());
		this.measureNum = new SimpleIntegerProperty(measureNum);
		this.first = new SimpleBooleanProperty(first);

		//creates the AnchorPane to display the measure
		setGraphic();

	}

	//overloaded constructor for the class
	public Measure(Note[] notes, double lengthAvailable, double lengthContained, int measureNum, boolean first) {

		//fields are initialized to parameters
		this.notes = notes;
		this.lengthAvailable = new SimpleDoubleProperty(lengthAvailable);
		this.lengthContained = new SimpleDoubleProperty(lengthContained);
		this.length = new SimpleDoubleProperty(this.lengthContained.get() + this.lengthAvailable.get());
		this.measureNum = new SimpleIntegerProperty(measureNum);
		this.first = new SimpleBooleanProperty(first);

		//creates the AnchorPane to display the measure
		setGraphic();

	}

	//getters and setters for the fields
	public Note[] getNotes() {
		return notes;
	}

	public void setNotes(Note[] notes) {
		this.notes = notes;
	}

	public Note getNote(int x) {
		return notes[x];
	}

	public double getLengthAvailable() {
		return lengthAvailable.get();
	}

	public void setLengthAvailable(double lengthAvailable) {
		this.lengthAvailable.set(lengthAvailable);
	}

	public double getLengthContained() {
		return lengthContained.get();
	}

	public void setLengthContained(double lengthContained) {
		this.lengthContained.set(lengthContained);
	}

	public double getLength() {
		return length.get();
	}

	public void setLength(double length) {
		this.length.set(length);
	}

	public int getMeasureNum() {
		return measureNum.get();
	}

	public void setMeasureNum(int measureNum) {
		this.measureNum.set(measureNum);
	}

	public AnchorPane getStaff() {
		return staff;
	}

	public void setStaff(AnchorPane staff) {
		this.staff = staff;
	}

	public boolean getFirst() {
		return first.get();
	}

	public void setFirst(boolean first) {
		this.first.set(first);
		setGraphic();
	}

	//getters for the property values
	public DoubleProperty lengthAvailableProperty() {
		return lengthAvailable;
	}

	public DoubleProperty lengthContainedProperty() {
		return lengthContained;
	}

	public DoubleProperty lengthProperty() {
		return length;
	}

	public IntegerProperty measureNumProperty() {
		return measureNum;
	}

	public BooleanProperty firstProperty() {
		return first;
	}

	//method that adds a note into a given position of the measure
	private void addNote(int index, NoteType name, String pitch, int volume) {

		//creates the note based on parameters
		Note note = new Note(name, pitch, volume, getMeasureNum());

		//if there is room in the measure
		if(lengthAvailable.get() - note.getLength() >= 0){

			//creates a replacement array of size a note bigger
			Note[] newArray = new Note[notes.length + 1];

			//inputs the former notes into new array until the added note
			for(int x = 0; x<index; x++){

				newArray[x] = notes[x];

			}

			//adds the new note
			newArray[index] = note;

			//inputs the remaning notes into the new array
			for(int x = index + 1, stop = notes.length + 1; x<stop; x++){

				newArray[x] = notes[x-1];

			}

			//replaces the old array with the new array
			notes = newArray;

			//calculates the new length
			lengthAvailable.set(lengthAvailable.get() - note.getLength());
			lengthContained.set(lengthContained.get() + note.getLength());

			//changes the display accordingly
			setGraphic();

		}

		//if there is not room in the measure
		else{

			System.out.println("Error: Not Enough Room");

		}

	}

	//calls to the private method for encapsulation
	public void addNote(int index, Note note) {

		addNote(index, note.getName(), note.getPitch(), note.getVolume());

	}

	//method that edits the fields of a note in the array
	private void editNote(NoteType name, String pitch, int volume, int index) {

		//creates a new note that would replace the old one
		notes[index] = new Note(name, pitch, volume, getMeasureNum());

		//calculates the new length
		lengthAvailable.set(lengthAvailable.get() - notes[index].getLength());
		lengthContained.set(lengthContained.get() + notes[index].getLength());

		//changes the display accordingly
		setGraphic();

	}

	//calls to the private method for encapsulation
	public void editNote(int index, Note note) {

		editNote(note.getName(), note.getPitch(), note.getVolume(), index);

	}

	//method that deletes a note in the array
	public void deleteNote(int index) {

		//creates a replacement array of one less note
		Note[] newArray = new Note[notes.length - 1];

		//adds the former notes into the new array up to the deleted note
		for(int x = 0; x<index; x++){

			newArray[x] = notes[x];

		}

		//skips the deleted note and adds the rest of the notes into the new array
		for(int x = index + 1, stop = newArray.length; x<stop; x++){

			newArray[x] = notes[x];

		}

		//calculates the new length
		lengthAvailable.set(lengthAvailable.get() + notes[index].getLength());
		lengthContained.set(lengthContained.get() - notes[index].getLength());

		//replaces the old array with the new one
		notes = newArray;

		//changes the display accordingly
		setGraphic();

	}

	//method that sets up the display for the measure
	private void setGraphic() {

		//if the measure is the first in the piece
		//add extra room for the time signature, key signature, and clef
		if(first.get() == true && measureNum.get() == 0) {

			//calls to setupStaff with extra room
			setupStaff(40*length.get() + 80);

		}

		//if the measure is first in the row
		//add extra room for clef
		else if(first.get() == true){

			//calls to setupStaff with extra room
			setupStaff(40*length.get() + 60);

		}

		//if neither
		else {

			//calls to setupStaff normally
			setupStaff(40*length.get());

		}

	}

	//method that adds the images to the display of the measure
	private void setupStaff(double width) {

		//places the measure on a new AnchorPane to display
		staff = new AnchorPane();
		staff.prefHeight(120);
		staff.prefWidth(width);

		//places horizontal lines into the measure (measure lines)
		for(int x = 0; x<5; x++){

			Line line = new Line();

			//calculates the position of the lines (starting and ending locations)
			line.setStartX(0.0);
			line.setStartY((double) (x+1)*12 + 20);
			line.setEndX(width);
			line.setEndY((double) (x+1)*12 + 20);
			AnchorPane.setTopAnchor(line, (double) (x+1)*12 + 20);

			//adds the line to the AnchorPane
			staff.getChildren().add(line);

		}

		//adds one of the vertical lines (at the end of measures)
		//calculates the starting and ending location
		Line lineOne = new Line();
		lineOne.setStartX(0);
		lineOne.setStartY(33.0);
		lineOne.setEndX(0);
		lineOne.setEndY(80);
		staff.getChildren().add(lineOne);

		//adds the other vertical line (at the end of measure)
		//calculates the starting and ending location
		Line lineTwo = new Line();
		lineTwo.setStartX(width);
		lineTwo.setStartY(33.0);
		lineTwo.setEndX(width);
		lineTwo.setEndY(80);
		staff.getChildren().add(lineTwo);

		//adds the clef, time signature, and key signature
		//initial variable that stores the location of the first note
		double xPosition = 0;

		//adds just the clef
		if(first.get() == true && measureNum.get() != 0){

			//position of first note
			xPosition = 60;

			//calculates position of the clef
			ImageView clef = new ImageView("file:Resources/trebleclef.png");
			AnchorPane.setLeftAnchor(clef, 0.0);
			AnchorPane.setTopAnchor(clef, 20.0);

			//adds the clef to the AnchorPane
			staff.getChildren().add(clef);

		}

		//adds the clef and the time signature
		else if(first.get() == true && measureNum.get() == 0){

			//position of the first note
			xPosition = 80;

			//calculates position of the clef
			ImageView clef = new ImageView("file:Resources/trebleclef.png");
			AnchorPane.setLeftAnchor(clef, 0.0);
			AnchorPane.setTopAnchor(clef, 20.0);

			//adds the clef to the AnchorPane
			staff.getChildren().add(clef);

			//calculates position of the time signature
			ImageView time = new ImageView("file:Resources/commontime.png");
			AnchorPane.setLeftAnchor(time, 50.0);
			AnchorPane.setTopAnchor(time, 40.0);

			//adds the time signature to the AnchorPane
			staff.getChildren().add(time);
		}

		//adds the notes into the measure graphic
		for(int x = 0, stop = getNotes().length; x<stop; x++){

			//gets the display of each note and calculates its position
			AnchorPane pane = notes[x].getPane();
			AnchorPane.setLeftAnchor(pane, xPosition);
			AnchorPane.setTopAnchor(pane, calcPosition(notes[x]));

			//adds the note, and sets the position for the next note
			staff.getChildren().add(pane);
			xPosition += notes[x].getLength() * 40;

		}
	}

	//method that calculates the position of each note in the measure
	private double calcPosition(Note note) {
		int shapeSize = 0;

		//calculates position of note based upon size and pitch
		switch(note.getName()){

		//each case calls the calcNotePosition method to account for sizing differences

			case WHOLENOTE:
				shapeSize = 20;
			return	calcNotePosition(note, shapeSize);

			case HALFNOTE:
				shapeSize = 0;
			return	calcNotePosition(note, shapeSize);

			case QUARTERNOTE:
				shapeSize = 0;
			return	calcNotePosition(note, shapeSize);

			case EIGHTHNOTE:
				shapeSize = 5;
			return	calcNotePosition(note, shapeSize);

			case SIXTEENTHNOTE:
				shapeSize = 6;
			return	calcNotePosition(note, shapeSize);

			case WHOLEREST:

			return 41;

			case HALFREST:

			return 36;

			case QUARTERREST:

			return 35;

			case EIGHTHREST:

			return 42;

			case SIXTEENTHREST:

			return 42;

			case DEFAULT:

			break;

		}

		return 0;

	}

	//calculates position of the note based upon the size of the graphic
	private double calcNotePosition(Note note, int shapeSize) {

		//determines the position based on pitch
		double position = 0;
		String ref = "ABCDEFG";
		String pitch = note.getPitch();
		int octave = note.getOctave();
		position = 31 - ref.indexOf(pitch.charAt(0)) * 6 + (octave - 5) * 42 + shapeSize;
		return position;

	}

}
