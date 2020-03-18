import java.util.Random;

public class MultiplicationThread implements Runnable {

	private Matrix operand1;
	private Matrix operand2;
	private Matrix result;
	
	//Constructor
	public MultiplicationThread(Matrix A, Matrix B)
	{	
		operand1 = new Matrix(A.rows,A.cols);
		operand2 = new Matrix(B.rows,B.cols);
		result = new Matrix(A.rows,B.cols);
		
		for(int i = 0; i < operand1.rows; ++i)
			for(int j = 0; j < operand1.cols; j++)
				operand1.numbers[i][j] = A.numbers[i][j];
		
		for(int i = 0; i < operand2.rows; ++i)
			for(int j = 0; j < operand2.cols; j++)
				operand2.numbers[i][j] = B.numbers[i][j];
	}
	
	@Override
	public void run()
	{
		if(Thread.currentThread().getName().equals("1"))
		{
    		int x;
    		for(int i = 0; i < (operand1.rows+1)/2; ++i)
    			for(int j = 0; j < operand2.cols; ++j)
    			{
    				x = 0;
    				for(int k = 0; k < operand2.rows; ++k)
    					x += operand1.numbers[i][k] * operand2.numbers[k][j];
    				result.numbers[i][j] = x;
    			}
		}
		else
		{
    		int x;
    		for(int i = 0; i < operand1.rows/2; ++i)
    			for(int j = 0; j < operand2.cols; ++j)
    			{
    				x = 0;
    				for(int k = 0; k < operand2.rows; ++k)
    					x += operand1.numbers[i+((operand1.rows+1)/2)][k] * operand2.numbers[k][j];
    				result.numbers[i+((operand1.rows+1)/2)][j] = x;
    			}
		}
	}

	public static void main(String[] args) throws Exception
	{
		Matrix m1 = new Matrix(3,4);
		Matrix m2 = new Matrix(4,2);
		int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12};
		
		m1.setNumbers(arr);
    	m2.setNumbers(arr);
    	
    	System.out.println("m1");
    	m1.print();
    	System.out.println("");
    	System.out.println("m2");
    	m2.print();
    	System.out.println("");
    	
    	//------------------------------------------------------//
    	
    	MultiplicationThread mt1 = new MultiplicationThread(m1,m2);
    	MultiplicationThread mt2 = new MultiplicationThread(m1,m2);
    	
    	Thread thread1 = new Thread(mt1);
    	Thread thread2 = new Thread(mt2);
    	thread1.setName("1");
    	thread2.setName("2");
    	
    	long time = System.nanoTime();
    	thread1.start();
    	thread2.start();
    	
    	thread1.join();
    	thread2.join();
    	time = System.nanoTime() - time ;
    	System.out.println("The time needed for multiplying m1 and m2 using the two threads= "+time+" ns");
    	System.out.println("");

    	System.out.println("result in thread1");
    	mt1.result.print();
    	System.out.println("");
    	System.out.println("result in thread2");
    	mt2.result.print();
    	System.out.println("");
    	
    	//------------------------------------------------------//
    	
    	Matrix m4 = new Matrix(500,500);
    	Matrix m5 = new Matrix(500,500);
  
    	int[] arr2 = new int[250000];
    	Random rd = new Random();
    	for(int i = 0; i < 250000; ++i)
    		arr2[i] = rd.nextInt();
    	
    	m4.setNumbers(arr2);
    	m5.setNumbers(arr2);
    	
    	Thread thread4 = new Thread(new MultiplicationThread(m4,m5));
    	Thread thread5 = new Thread(new MultiplicationThread(m4,m5));
    	thread4.setName("1");
    	thread5.setName("2");
    	
    	time = System.nanoTime();
    	thread4.start();
    	thread5.start();
    	
    	thread4.join();
    	thread5.join();
    	time = System.nanoTime() - time ;
    	System.out.println("The time needed for multiplying m1 and m2 using the two threads= "+time+" ns");
	}
}
