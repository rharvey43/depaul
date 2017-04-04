package model;

import java.util.List;
import java.util.ArrayList;

/**
 * A road holds cars.
 */
public class Road {
	private String roadType = "Road";
	Road() { } // Created only by this package

	private List<Car> cars = new ArrayList<Car>();

	public void accept(Car d) {
		if (d == null) { throw new IllegalArgumentException(); }
		cars.add(d);
	}
	public List<Car> getCars() {
		return cars;
	}
	public void remove(Car d)
	{
		cars.remove(d);
	}
	public String getRoadType()
	{
		return roadType;
	}
	
}
