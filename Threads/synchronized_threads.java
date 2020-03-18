class Threadsrequirement {
	 public static void main(String... args) throws InterruptedException {
	 	BookStock b = new BookStock (10);
				
		StoreBranch sb = new StoreBranch(b);
		Thread supplier = new Thread(new Supplier(b));
		Thread gizaBranch = new Thread(sb);
		Thread cairoBranch = new Thread(sb);
		Thread alexBranch = new Thread(sb);

		supplier.start();
		gizaBranch.start();
		cairoBranch.start();
		alexBranch.start();

		supplier.join();
		gizaBranch.join();
		cairoBranch.join();
		alexBranch.join();
    }
}

class BookStock {
	private int bookCount;
	private final int maxCount;
	
	public BookStock  (int max) 	{	this.maxCount = max;	}
	public void produce() 		{	bookCount++;		}
	public void consume () 		{	bookCount--;		}
	public int getCount () 		{	return bookCount;	}
	public final int getMaxCount() 	{	return maxCount; 	}
}


class Supplier implements Runnable {
	private BookStock b;

	public Supplier (BookStock b) 	{	this.b = b;		}

	public void run () 		{	doWork();		}
	
	public void doWork () 
	{
		while (true) 
		{
			synchronized (this.b)
			{
				if(b.getCount()<b.getMaxCount())
				{
					b.produce();
					System.out.println (Thread.currentThread().getName() + " provided a book, total " +b.getCount());
				}
				b.notify();
			}
			try {	Thread.sleep (200);	} 
			catch (InterruptedException e) { System.out.println (Thread.currentThread().getName() + "is awaken");	}
        	}
	}

}

class StoreBranch implements Runnable {
	private BookStock b;

	public StoreBranch (BookStock b) {	this.b = b;		}

	public void run () 		{	doWork();		}
	
	public void doWork () 
	{
		while (true) 
		{
			synchronized (this.b)
			{
				if(b.getCount()>0)
				{
					b.consume();
					System.out.println (Thread.currentThread().getName() + " sold a book, total " +b.getCount());
				}				
				b.notify();
			}			
			try {	Thread.sleep (2000);	} 
			catch (InterruptedException e) { System.out.println (Thread.currentThread().getName() + "is awaken");	}
        	}
	}
}
