import java.util.Random;

public class Matrix
{
    public int [][] numbers;
    public int rows; 
    public int cols; 
    
    static final class MultiplicationException extends Exception
    {
    	// This serial is added by default to solve warning because of static final
		private static final long serialVersionUID = 1L;
		
		public String errorMessage;
    	
    	public MultiplicationException(String msg) {	errorMessage = msg;	}
    	
    	public void message() { System.out.println(errorMessage);	}
    }
    
    //Constructor
    public Matrix(int M, int N)
    {	rows = M;	cols = N;	numbers = new int [M][N];	}

    public boolean setNumbers(int[] arr)
    {
    	if(arr.length < cols * rows)	return false;
    	
        int k = 0;
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                numbers[i][j] = arr[k++];
        return true;
    }

    public void print()
    {
        for(int i = 0; i < rows; ++i)
        {
            for(int j = 0; j < cols; ++j)
                System.out.print(numbers[i][j]+" ");
            System.out.println();
        }
    }
    
    public Matrix multiply(Matrix B) throws MultiplicationException
    {
    	if(cols != B.rows)
    	{
    		String msg = "Exception occured while trying to multiply two matrices of dimensions ";
        	msg+="("+rows+","+cols+")"+" and "+"("+B.rows+","+B.cols+")";
    		throw new  MultiplicationException(msg);
    	}
    	
    	Matrix M = new Matrix(rows,B.cols);
    	int x;
    	for(int i = 0; i < M.rows; ++i)
    		for(int j = 0; j < M.cols; ++j)
    		{
    			x = 0;
    			
    			for(int k = 0; k < B.rows; ++k)	x += numbers[i][k] * B.numbers[k][j];
    			
    			M.numbers[i][j] = x;
    		}
    	return M;
    }
    
    public static void main(String [] args)
    {
    	Matrix m1 = new Matrix(3,4);
    	Matrix m2 = new Matrix(4,2);
    	Matrix m3 = new Matrix(2,5);
    	int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12};
    	
    	m1.setNumbers(arr);
    	m2.setNumbers(arr);
    	m3.setNumbers(arr);
    	
    	System.out.println("m1");
    	m1.print();
    	System.out.println("");
    	System.out.println("m2");
    	m2.print();
    	System.out.println("");
    	System.out.println("m3");
    	m3.print();
    	System.out.println("");
    	
    	//------------------------------------------------------//
    	
    	long time = System.nanoTime();
    	System.out.println("m1 * m2");
    	try {	(m1.multiply(m2)).print();	}
    	catch(MultiplicationException e) {}
    	time = System.nanoTime() - time;
    	System.out.println("The time needed for multipling m1 and m2 is " + time +" ns");
    	System.out.println("");
    	
    	System.out.println("m1 * m3");
    	try {	(m1.multiply(m3)).print();	}
    	catch(MultiplicationException e) {	e.message();}
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
    	
    	
    	time = System.nanoTime();
    	try {	(m4.multiply(m5)).print();	}
    	catch(MultiplicationException e) {}
    	time = System.nanoTime() - time;
    	System.out.println("The time needed for multipling m4 and m5 is " + time +" ns");
    	
    }
}