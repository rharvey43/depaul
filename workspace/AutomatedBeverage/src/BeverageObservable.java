import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BeverageObservable implements Runnable
{
	private static BeverageObservable beverageObservable = new BeverageObservable();
	
	private boolean inBeverage = false;
	private ScheduledThreadPoolExecutor scheduler = null;

	private IBeverageObserver iBeverageObserver = null;
	
	private int beveragesToMake = 0;
	
	private BeverageObservable()
	{
	}

	private static BeverageObservable getBeverageObservable()
	{
		return beverageObservable;
	}
	
	public static void addBeverageObserver(final IBeverageObserver iiBeverageObserver, int nBeveragesToMake)
	{
		BeverageObservable beverageObservable = getBeverageObservable();
		if (beverageObservable != null)
			beverageObservable.add(iiBeverageObserver, nBeveragesToMake);
	}
	
	private synchronized void add(final IBeverageObserver iiBeverageObserver, int nBeveragesToMake)
	{
		iBeverageObserver = iiBeverageObserver;
		
		beveragesToMake = nBeveragesToMake;

		if (!getIsInBeverage())
			startBeverage();
	}
	
	public static void removeBeverageObserver(final IBeverageObserver iiBeverageObserver)
	{
		BeverageObservable beverageObservable = getBeverageObservable();
		if (beverageObservable != null)
			beverageObservable.remove(iiBeverageObserver);
	}

	private synchronized void remove(final IBeverageObserver iiBeverageObserver)
	{
		if (iBeverageObserver == iiBeverageObserver)
		{
			iBeverageObserver = null;
	
			stopBeverage();
		}
	}
	
	private synchronized void startBeverage()
	{
		setIsInBeverage(true);
		
		if (scheduler == null)
		{
			AutomatedBeverageObservableFactory automatedBeverageObservableFactory = new AutomatedBeverageObservableFactory("AutomatedBeverage Observable");
			
			scheduler = new ScheduledThreadPoolExecutor(1, automatedBeverageObservableFactory);
			if (scheduler != null)
				scheduler.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);//1 beverage every second
		}
	}

	private synchronized void stopBeverage()
	{
		setIsInBeverage(false);
		
		if (scheduler != null)
		{
			scheduler.shutdown();
			
			scheduler = null;
		}
	}
	
	public static final boolean isInBeverage()
	{
		BeverageObservable beverageObservable = getBeverageObservable();
		return (beverageObservable != null ? beverageObservable.getIsInBeverage() : false);
	}

	public final synchronized boolean getIsInBeverage()
	{
		return inBeverage;
	}
	
	private final synchronized void setIsInBeverage(final boolean bIsInBeverage)
	{
		inBeverage = bIsInBeverage;
	}
	
	public synchronized void run()
	{
		if (--beveragesToMake < 0)
		{
			stopBeverage();
		}
		else
		{
			if ((iBeverageObserver != null) && (isInBeverage()))
			{
				iBeverageObserver.beverage(BeverageFactory.assembleBeverage());
			}
		}
	}	
}
