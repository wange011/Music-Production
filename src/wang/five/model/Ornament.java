/*
Wang, Ethan
APCS-A
Period 5
Spring Project
*/

package wang.five.model;

//all the necessary imports, including JavaFX properties
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

//ornaments will be sorted, so they must implement Comparable
public class Ornament implements Comparable<Ornament> {

	//the location of the ornament in the piece
	private DoubleProperty xLocation;
	private DoubleProperty yLocation;
	//the type of ornament added (ex. Trill)
	private OrnamentType type;

	//an AnchorPane to display the ornament
	private AnchorPane display;

	//default constructor for the class
	public Ornament() {

		//fields are initialized to default values
		this.xLocation = new SimpleDoubleProperty(-1);
		this.yLocation = new SimpleDoubleProperty(-1);
		type = OrnamentType.DEFAULT;

		//creates the AnchorPane to display the ornament
		setGraphic();

	}

	//overloaded constructor for the class
	public Ornament(OrnamentType type) {

		//fields are initialized to parameters
		this.xLocation = new SimpleDoubleProperty(-1);
		this.yLocation = new SimpleDoubleProperty(-1);
		this.type = type;

		//creates the AnchorPane to display the ornament
		setGraphic();

	}

	//overloaded constructor for the class
	public Ornament(double xLocation, double yLocation, OrnamentType type) {

		//fields are initialized to parameters
		this.xLocation = new SimpleDoubleProperty(xLocation);
		this.yLocation = new SimpleDoubleProperty(yLocation);
		this.type = type;

		//creates the AnchorPane to display the ornament
		setGraphic();

	}

	//getters and setters for the fields
	public double getxLocation() {
		return xLocation.get();
	}

	public void setxLocation(double xLocation) {
		this.xLocation.set(xLocation);
	}

	public double getyLocation() {
		return yLocation.get();
	}

	public void setyLocation(double yLocation) {
		this.yLocation.set(yLocation);
	}

	public OrnamentType getType() {
		return type;
	}

	public void setType(OrnamentType type) {
		this.type = type;
		setGraphic();
	}

	public AnchorPane getDisplay() {
		return display;
	}

	public void setDisplay(AnchorPane display) {
		this.display = display;
	}

	public DoubleProperty xLocationProperty() {
		return xLocation;
	}

	public DoubleProperty yLocationProperty() {
		return yLocation;
	}

	//method creates the display for each ornament
	private void setGraphic() {

		//switches to display an image based upon the type of ornament
		switch(type) {

		//each case finds the image and instantiates the AnchorPane

		case TRILL:
			ImageView imageView = new ImageView("file:Resources/trill.png");
			display = new AnchorPane(imageView);
		break;

		case CRES:
			imageView = new ImageView("file:Resources/cres.png");
			display = new AnchorPane(imageView);
		break;

		case DIM:
			imageView = new ImageView("file:Resources/dim.png");
			display = new AnchorPane(imageView);
		break;

		case RITARD:
			imageView = new ImageView("file:Resources/ritard.png");
			display = new AnchorPane(imageView);
		break;

		case FERMATA:
			imageView = new ImageView("file:Resources/fermata.png");
			display = new AnchorPane(imageView);
		break;

		case PIANO:
			imageView = new ImageView("file:Resources/piano.png");
			display = new AnchorPane(imageView);
		break;

		case MP:
			imageView = new ImageView("file:Resources/mp.png");
			display = new AnchorPane(imageView);
		break;

		case MF:
			imageView = new ImageView("file:Resources/mf.png");
			display = new AnchorPane(imageView);
		break;

		case FORTE:
			imageView = new ImageView("file:Resources/forte.png");
			display = new AnchorPane(imageView);
		break;

		case PP:
			imageView = new ImageView("file:Resources/pp.png");
			display = new AnchorPane(imageView);
		break;

		case FF:
			imageView = new ImageView("file:Resources/ff.png");
			display = new AnchorPane(imageView);
		break;

		case COMMENT:

		break;

		case DEFAULT:

		break;

		//default case for debugging
		default:
			System.out.println("Error");
		break;

		}

	}

	//method that is part of Comparable
	//implemented to allow ornaments to be compared by xLocation
	public int compareTo(Ornament ornament) {

		return((int) xLocation.get() - (int) ornament.xLocation.get());

	}


}
