import java.util.ArrayList;
import java.util.Collections;

public class BeverageFactory
{
	private final static ArrayList<BeverageItem> beverageItems = new ArrayList<BeverageItem>(3);
	
	static
	{
		beverageItems.add(BeverageItem.SODA);
		beverageItems.add(BeverageItem.ICE);
		beverageItems.add(BeverageItem.FLAVOR);
	}
	
	public static ArrayList<BeverageItem> assembleBeverage()//NOT THREAD SAFE - create local arrays each time
	{
		Collections.shuffle(beverageItems);
		
		return new ArrayList<BeverageItem>(beverageItems.subList(0, (int)(Math.ceil(Math.random() * 3.0d))));
	}
}
      