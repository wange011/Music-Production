/*
Wang, Ethan
APCS-A
Period 5
Spring Project
*/

package wang.five.model;

//all the necessary imports, including JavaFX objects and properties
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Piece {

	//contains the ornaments in the piece
	private Ornament[] ornaments;
	//contains the measures in the piece
	private Measure[] bars;
	//the top value for time signature
	private IntegerProperty topTimeSig;
	//the bottom value for time signature
	private IntegerProperty botTimeSig;
	//the key signature
	private StringProperty keySig;
	//the tempo
	private DoubleProperty tempo;

	//default constructor for the class
	public Piece() {

		//default ornament and measure arrays
		ornaments = new Ornament[0];
		bars = new Measure[0];

		//fields are initialized to default values
		topTimeSig = new SimpleIntegerProperty(0);
		botTimeSig = new SimpleIntegerProperty(0);
		keySig = new SimpleStringProperty("");
		tempo = new SimpleDoubleProperty(0);

	}

	//overloaded constructor for the class
	public Piece(int topTimeSig, int botTimeSig, String keySig, double tempo) {

		//fields are initialized to parameters
		this.topTimeSig = new SimpleIntegerProperty(topTimeSig);
		this.botTimeSig = new SimpleIntegerProperty(botTimeSig);
		this.keySig = new SimpleStringProperty(keySig);
		this.tempo = new SimpleDoubleProperty(tempo);

		//measure array is initialized as a default
		bars = new Measure[30];

		for(int x = 0; x<30; x++){

			//creates a default measure in each position of the array
			bars[x] = new Measure(topTimeSig/botTimeSig*4, x);

		}

		//calls to the method that checks the position of each measure
		checkFirst();

		//default array of ornaments
		ornaments = new Ornament[0];

	}

	//getters and setters for the fields
	public Ornament[] getOrnaments() {
		return ornaments;
	}

	public void setOrnaments(Ornament[] ornaments) {
		this.ornaments = ornaments;
	}

	public Ornament getOrnament(int index) {
		return ornaments[index];
	}

	public Measure[] getBars() {
		return bars;
	}

	public Measure getBar(int index) {
		return bars[index];
	}

	public void setBars(Measure[] bars) {
		this.bars = bars;
	}

	public int getTopTimeSig() {
		return topTimeSig.get();
	}

	public void setTopTimeSig(int topTimeSig) {
		this.topTimeSig.set(topTimeSig);
	}

	public int getBotTimeSig() {
		return botTimeSig.get();
	}

	public void setBotTimeSig(int botTimeSig) {
		this.botTimeSig.set(botTimeSig);
	}

	public String getKeySig() {
		return keySig.get();
	}

	public void setKeySig(String keySig) {
		this.keySig.set(keySig);
	}

	public double getTempo() {
		return tempo.get();
	}

	public void setTempo(double tempo) {
		this.tempo.set(tempo);
	}

	public IntegerProperty topTimeSigProperty() {
		return topTimeSig;
	}

	public IntegerProperty botTimeSigProperty() {
		return botTimeSig;
	}

	public StringProperty keySigProperty() {
		return keySig;
	}

	public DoubleProperty tempoProperty() {
		return tempo;
	}

	//method that adds measures into the piece
	public void addMeasure(int index) {

		//creates the added measure
		Measure measure = new Measure(topTimeSig.get()/botTimeSig.get()*4, index);

		//creates a replacement array that is one measure larger
		Measure[] newArray = new Measure[bars.length + 1];

		//fills the new array with the elements of the old array up until the new measure
		for(int x = 0; x<index; x++){

			newArray[x] = bars[x];

		}

		//places the new measure in the array
		newArray[index] = measure;

		//places the remaining elements into the new array
		for(int x = index + 1, stop = bars.length + 1; x<stop; x++){

			newArray[x] = bars[x-1];

		}

		//replaces the old array with the new array
		bars = newArray;

		//rechecks the position of each measure
		checkFirst();

	}

	//method that deletes a measure in the piece
	public void deleteMeasure(int index) {

		//creates a replacement array of one less measure
		Measure[] newArray = new Measure[bars.length - 1];

		//fills the new array with the elements of the old array up until the deleted measure
		for(int x = 0; x<index; x++){

			newArray[x] = bars[x];

		}

		//places the remaining elements into the new array
		for(int x = index + 1, stop = newArray.length + 1; x<stop; x++){

			newArray[x - 1] = bars[x];

		}

		//replaces the old array with the new array
		bars = newArray;

		//rechecks the position of each measure
		checkFirst();

	}

	//method that adds an ornament to the piece
	//asks for the fields of the ornaments as parameters
	private void addOrnament(int index, double xLocation, double yLocation, OrnamentType type){

		//creates the added ornament
		Ornament ornament = new Ornament(xLocation, yLocation, type);

		//creates a replacement array that is one ornament larger
		Ornament[] newArray = new Ornament[ornaments.length + 1];

		//fills the new array with the elements of the old array up until the added ornament
		for(int x = 0; x<index; x++){

			newArray[x] = ornaments[x];

		}

		//places the new ornament into the array
		newArray[index] = ornament;

		//places the remaining elements into the new array
		for(int x = index + 1, stop = ornaments.length + 1; x<stop; x++){

			newArray[x] = ornaments[x-1];

		}

		//replaces the old array with the new array
		ornaments = newArray;

		//sorts the ornaments by their xLocation
		sortOrnaments();

	}

	//method that calls to the private method for encapsulation
	public void addOrnament(Ornament ornament) {

		//add this ornament to the end of the array
		addOrnament(ornaments.length, ornament.getxLocation(), ornament.getyLocation(), ornament.getType());

	}

	//method that deletes an ornament in the piece
	//asks for the index of the ornament that is deleted
	public void deleteOrnament(int index) {

		//creates a replacement array that is one ornament smaller
		Ornament[] newArray = new Ornament[ornaments.length - 1];

		//fills the new array with the elements of the old array up until the deleted ornament
		for(int x = 0; x<index; x++){

			newArray[x] = ornaments[x];

		}

		//places the remaining elements into the new array
		for(int x = index + 1, stop = ornaments.length; x<stop; x++){

			newArray[x-1] = ornaments[x];

		}

		//replaces the old array with the new array
		ornaments = newArray;

		//sorts the ornaments by their xLocation
		sortOrnaments();

	}

	//method that checks the position of the measure
	//to determine if it needs a time signature, key signature, or clef
	private void checkFirst() {

		//the first bar is the first measure
		bars[0].setFirst(true);

		//specifies the position within the row that each measure is in
		int rowNum = 0;

		//determines if each measure is the first in the row
		for(int x = 1, stop = bars.length; x<stop; x++) {

			//total length of the measure
			double length = getBar(x).getLengthAvailable() + getBar(x).getLengthContained();

			//the property is default false
			boolean first = false;

			//increment here since the position of the first measure is known
			rowNum ++;

			//check to see if the measure line has to be placed on a new row
			if(40* length * (rowNum-1) + 80 >= 400){

				//if it does, set the property to true
				first = true;
				//the measure then becomes the first measure in the row
				rowNum = 0;

			}

			//set the field of the measure to the property
			bars[x].setFirst(first);

		}

	}

	//helper method that sorts the ornament array
	private void sortOrnaments() {

		Arrays.sort(ornaments);

	}

	//method that writes the information of the piece into a text file
	//asks for the file as the parameter
	public void writeAsFile(File file) throws FileNotFoundException {

		//removes previous information stored in the file for a new save
		file = new File(file.toURI());

		//creates a PrintWriter to print onto the file
		PrintWriter pw = new PrintWriter(file);

		//prints the fields of the piece
		pw.println("Piece");
		pw.println(topTimeSig.get());
		pw.println(botTimeSig.get());
		pw.println(keySig.get());
		pw.println(tempo.get());
		pw.println(bars.length);

		//prints the fields of each measure in the piece
		for(int x = 0, stop = bars.length; x<stop; x++){

			Measure m = bars[x];
			pw.println(m.getLengthAvailable());
			pw.println(bars[x].getLengthContained());
			pw.println(m.getMeasureNum());
			pw.println(m.getFirst());
			pw.println(m.getNotes().length);

			//prints the fields of each note in the measure
			for(int i = 0, end = m.getNotes().length; i<end; i++){

				Note n = m.getNote(i);
				pw.println(n.getLength());
				pw.println(n.getPitch());
				pw.println(n.getVolume());
				pw.println(n.getName());
				pw.println(n.getIsDotted());
				pw.println(n.getIsSlured());
				pw.println(n.getOctave());

			}

		}

		pw.println(ornaments.length);

		//prints the fields of each ornament in the piece
		for(int j = 0, stop = ornaments.length; j<stop; j++) {

			Ornament o = ornaments[j];
			pw.println(o.getxLocation());
			pw.println(o.getyLocation());
			pw.println(o.getType());

		}

		//closes the PrintWriter
		pw.close();

	}

	//method that reads the save file from the previous method
	//asks for the file as a parameter
	public void readFromFile(File file) throws FileNotFoundException {

		//creates a scanner to read the save file
		Scanner sc = new Scanner(file);
		sc.next();

		//reads the fields of the piece
		int topTimeSig = Integer.parseInt(sc.next());
		int botTimeSig = Integer.parseInt(sc.next());
		String keySig = sc.next();
		double tempo = Double.parseDouble(sc.next());
		int numOfMeasures = Integer.parseInt(sc.next());

		Measure[] bars = new Measure[numOfMeasures];

		//reads the fields of each measure in the piece
		for(int x = 0; x<numOfMeasures; x++) {

			double lengthAvailable = Double.parseDouble(sc.next());
			double lengthContained = Double.parseDouble(sc.next());
			int measureNum = Integer.parseInt(sc.next());
			boolean first = Boolean.parseBoolean(sc.next());
			int numOfNotes = Integer.parseInt(sc.next());

			Note[] notes = new Note[numOfNotes];

			//reads the fields of each note in the measure
			for(int i = 0; i<numOfNotes; i++) {

				double length = Double.parseDouble(sc.next());
				String pitch = sc.next();
				int volume = Integer.parseInt(sc.next());
				NoteType name = NoteType.valueOf(sc.next());
				boolean isDotted = Boolean.parseBoolean(sc.next());
				boolean isSlured = Boolean.parseBoolean(sc.next());
				int octave = Integer.parseInt(sc.next());

				//creates the note array
				notes[i] = new Note(length, name, pitch, volume, isDotted, isSlured, measureNum, octave);

			}

			//creates the measure array
			bars[x] = new Measure(notes, lengthAvailable, lengthContained, measureNum, first);

		}

		int numOfOrnaments = Integer.parseInt(sc.next());

		Ornament[] ornaments = new Ornament[numOfOrnaments];

		//reads the fields of each ornament in the piece
		for(int j = 0; j<numOfOrnaments; j++) {

			double xLocation = Double.parseDouble(sc.next());
			double yLocation = Double.parseDouble(sc.next());
			OrnamentType type = OrnamentType.valueOf(sc.next());
			ornaments[j] = new Ornament(xLocation, yLocation, type);

		}

		//sets the values of the piece to the read information
		this.topTimeSig = new SimpleIntegerProperty(topTimeSig);
		this.botTimeSig = new SimpleIntegerProperty(botTimeSig);
		this.keySig = new SimpleStringProperty(keySig);
		this.tempo = new SimpleDoubleProperty(tempo);
		this.bars = bars;
		this.ornaments = ornaments;

		//closes the scanner
		sc.close();

	}

}
