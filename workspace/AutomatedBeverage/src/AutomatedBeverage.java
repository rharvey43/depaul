import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class AutomatedBeverage implements IBeverageObserver
{
	public static void main(String[] args) 
	{
		new AutomatedBeverage().automate();
	}
	
	public void automate()
	{
		BeverageObservable.addBeverageObserver(this, 10);
	}

	@Override
	public void beverage(final ArrayList<BeverageItem> beverage)
	{
		System.out.print("Assembling Beverage-->");//keep this line
		
		IAutomation pipeline = null;
		
		ListIterator<BeverageItem> b = beverage.listIterator(beverage.size());
        while (b.hasPrevious())
        {
        	BeverageItem currentBev = b.previous();
        	if (currentBev.equals(BeverageItem.FLAVOR))
        	{
        		IAutomation flavorMachine = new FlavorMachine();
        		flavorMachine.setOutput(pipeline);
        		pipeline = flavorMachine;
        		
        	}
        	if (currentBev.equals(BeverageItem.ICE))
			{
        		IAutomation iceMachine = new IceMachine();
        		iceMachine.setOutput(pipeline);
        		pipeline = iceMachine;
			}
        	if (currentBev.equals(BeverageItem.SODA))
			{
        		IAutomation sodaMachine = new SodaMachine();
        		sodaMachine.setOutput(pipeline);
        		pipeline = sodaMachine;
			}
        }
        pipeline.processInput();
		pipeline.getOutput();
		System.out.println("-->Beverage Assembled");//keep this line
	}
}
