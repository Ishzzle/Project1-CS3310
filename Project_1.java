package project_1;

import java.util.Random;

/**
 * 		**For sanity check**
 * 
 * 		int one [][] = 	{{2,0,-1,6},
						{3,7,8,0},
						{-5,1,6,-2},
						{8,0,2,7}};
		
		int two [][] = {{0,1,6,3},
				        {-2,8,7,1},
				        {2,0,-1,0},
				        {9,1,6,-2}};
 * @author ishan
 *
 */

public class Project_1 {
	
	//Helper method to add two arrays
	public static int[][] add(int x[][],int y[][]){
		
		int n = x.length;
		int[][] addOutput = new int[n][n];
		
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++) 
			{
				addOutput[i][j] = x[i][j] + y[i][j];
			}
		}
		return addOutput;
	}
	
	public static int[][] sub(int x[][],int y[][]){
		
		int n = x.length;
		int[][] subOutput = new int[n][n];
		
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++) 
			{
				subOutput[i][j] = x[i][j] - y[i][j];
			}
		}
		return subOutput;
	}
	
	//Helper Method to combine 4 arrays
	public static int[][] combine(int cc1[][],int cc2[][],int cc3[][],int cc4[][]){
	    
		int n = cc1.length;
		//Since we know that the parameter arrays are n/2, we have to make the return array 2*n
	    int cc[][] = new int[2*n][2*n];
	    
	    for (int i = 0; i < 2*n; i++)
	    	{
	    		for (int j = 0; j < 2*n; j++)
	    		{
	    			if (i<n) //First half
	    			{
	    				if (j<n) //First Quadrant
	    				{
	    					cc[i][j] = cc1[i][j];
	    				}
	    				else //Third Quadrant
	    				{	
	    					cc[i][j] = cc2[i][j-n];
	    				}
	    			}
	    			else //Second half
	    			{
	    				if (j<n) //Second Quadrant
	    				{
	    					cc[i][j] = cc3[i-n][j];
	    				}
	    				else //Fourth Quadrant
	    				{
	    					cc[i][j] = cc4[i-n][j-n];
	    				}
	    			}
	    		}
	    	}
	    return cc;
	}
	
	//For classical matrix multiplication
	public static int[][] classical(int x[][], int y[][]){
		
		//Finding length for the new output matrix
		int n = x.length;
		int output [][] = new int [n][n];
		
		//base case
		if (n==1) {
			output[0][0]= x[0][0]*y[0][0];
		}
		else {
		
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n ; j++)
			{
				for(int k=0; k<n; k++)
				{
					output[i][j] += x[i][k]*y[k][j];
				}
			}
		}
	}
		return output;
	}
	
	//For naive divide and conquer matrix multiplication
	public static int[][] dc(int x[][], int y[][]) {
		
			int n = x.length;
			int output [][] = new int [n][n];
			
			//base case
			if (n==1) {
				output[0][0]= x[0][0]*y[0][0];
			}
			else
				{
					int a[][] = new int[n/2][n/2];
			        int b[][] = new int[n/2][n/2];
			        int c[][] = new int[n/2][n/2];
			        int d[][] = new int[n/2][n/2];
			        int e[][] = new int[n/2][n/2];
				    int f[][] = new int[n/2][n/2];
				    int g[][] = new int[n/2][n/2];
				    int h[][] = new int[n/2][n/2];
				    
				    for (int i = 0; i < n/2; i++)
				    {
				    	for (int j = 0; j < n/2; j++)
				    	{
				               a[i][j] = x[i][j];
				               b[i][j] = x[i][j+n/2];
				               c[i][j] = x[i+n/2][j];
				               d[i][j] = x[i+n/2][j+n/2];
				               e[i][j] = y[i][j];
				               f[i][j] = y[i][j+n/2];
				               g[i][j] = y[i+n/2][j];
				               h[i][j] = y[i+n/2][j+n/2];
				        }
				    }
			    
			    int ae[][] = new int[n/2][n/2];
			        ae = dc(a, e);
			    int bg[][] = new int[n/2][n/2];
			        bg = dc(b, g);
			    int cc1[][] = new int[n/2][n/2];
			        cc1 = add(ae,bg);
			    int af[][] = new int[n/2][n/2];
			        af = dc(a, f);
		        int bh[][] = new int[n/2][n/2];
			        bh = dc(b, h);
		        int cc2[][] = new int[n/2][n/2];
			        cc2 = add(af,bh);
		        int ce[][] = new int[n/2][n/2];
			        ce = dc(c, e);
			    int dg[][] = new int[n/2][n/2];
			        dg = dc(d, g);
		        int cc3[][] = new int[n/2][n/2];
			        cc3 = add(ce,dg);
		        int cf[][] = new int[n/2][n/2];
			        cf = dc(c, f);
		        int dh[][] = new int[n/2][n/2];
			        dh = dc(d, h);
		        int cc4[][] = new int[n/2][n/2];
			        cc4 = add(cf,dh);
			     
			    output = combine(cc1, cc2, cc3, cc4);	
			}
				return output;
		}
	
	public static int[][] strassen(int x[][], int y[][]){
		
		int n = x.length;
		int output [][] = new int [n][n];
		
		if (n==1) {
			output[0][0]= x[0][0]*y[0][0];
		}
		else {
		    
		    int a11[][] = new int[n/2][n/2];
	        int a12[][] = new int[n/2][n/2];
	        int a21[][] = new int[n/2][n/2];
	        int a22[][] = new int[n/2][n/2];
	        int b11[][] = new int[n/2][n/2];
		    int b12[][] = new int[n/2][n/2];
		    int b21[][] = new int[n/2][n/2];
		    int b22[][] = new int[n/2][n/2];
		    
		    for (int i = 0; i < n/2; i++)
		    {
		    	for (int j = 0; j < n/2; j++)
		    	{
		               a11[i][j] = x[i][j];
		               a12[i][j] = x[i][j+n/2];
		               a21[i][j] = x[i+n/2][j];
		               a22[i][j] = x[i+n/2][j+n/2];
		               b11[i][j] = y[i][j];
		               b12[i][j] = y[i][j+n/2];
		               b21[i][j] = y[i+n/2][j];
		               b22[i][j] = y[i+n/2][j+n/2];
		        }
		    }
		    int p1[][] = new int[n/2][n/2];
	        	p1 = strassen(add(a11,a22), add(b11,b22));
	        int p2[][] = new int[n/2][n/2];
	        	p2 = strassen(add(a21,a22), b11);
	        int p3[][] = new int[n/2][n/2];
	        	p3 = strassen(a11,sub(b12,b22));
	        int p4[][] = new int[n/2][n/2];
	        	p4 = strassen(a22, sub(b21,b11));
	        int p5[][] = new int[n/2][n/2];
	        	p5 = strassen(add(a11,a12),b22);
	        int p6[][] = new int[n/2][n/2];
	        	p6 = strassen(sub(a21,a11),add(b11,b12));
	        int p7[][] = new int[n/2][n/2];
	        	p7 = strassen(sub(a12,a22), add(b21,b22));
	        int c11[][] = new int[n/2][n/2];
	        	c11 = add(sub(add(p1,p4),p5),p7);
	        int c12[][] = new int[n/2][n/2];
	        	c12 = add(p3,p5);
	        int c21[][] = new int[n/2][n/2];
	        	c21 = add(p2, p4);
	        int c22[][] = new int[n/2][n/2];
	        	c22 = add(sub(add(p1,p3),p2),p6);
	     
	        output = combine(c11, c12, c21, c22);
		    
		}
		return output;
	}
	
	public static int[][] create(int n){
		
		Random random = new Random();
		int[][] output = new int[n][n];
		for(int i = 0; i<n; i++) 
		{
			for(int j = 0; j<n;j++) 
			{
				output[i][j] = random.nextInt();
			}
		}
		return output;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int one [][] = 	{{2,0,-1,6},
				{3,7,8,0},
				{-5,1,6,-2},
				{8,0,2,7}};

		int two [][] = {{0,1,6,3},
		        {-2,8,7,1},
		        {2,0,-1,0},
		        {9,1,6,-2}};
		
		
		//Classical
		long startTime = System.nanoTime();
		int[][] answer = classical(one,two);
		long elapsedTime = System.nanoTime() - startTime;
		System.out.print("**The time to execute the classical matrix multiplication is "+elapsedTime+" nanoseconds**\n");
		System.out.print("The answer for the classical matrix multiplication is:");
		int size = answer.length;
		System.out.println("");
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size ; j++)
			{
				System.out.print(answer[i][j]+" ");
			}
			System.out.println("");
		}	
		
		//Divide and Conquer
		long dcStartTime = System.nanoTime();
		int[][] dcAnswer = dc(one,two);
		long dcElapsedTime = System.nanoTime() - dcStartTime;
		System.out.print("**The time to execute the divide and conquer matrix multiplication is "+dcElapsedTime+" nanoseconds**\n");
		System.out.print("The answer for the divide and conquer matrix multiplication is:");
		int dcSize = dcAnswer.length;
		System.out.println("");
		for(int i=0; i<dcSize; i++)
		{
			for(int j=0; j<dcSize ; j++)
			{
				System.out.print(dcAnswer[i][j]+" ");
			}
			System.out.println("");
		}
		
		//Strassen's Algorithm
		long strassenStartTime= System.nanoTime();
		int[][] strassenAnswer = strassen(one,two);
		long strassenElapsedTime = System.nanoTime() - strassenStartTime;
		System.out.print("**The time to execute the strassen matrix multiplication is "+strassenElapsedTime+" nanoseconds**\n");
		System.out.print("The answer for the strassen matrix multiplication is:");
		int strassenSize = strassenAnswer.length;
		System.out.println("");
		for(int i=0; i<strassenSize; i++)
		{
			for(int j=0; j<strassenSize ; j++)
			{
				System.out.print(strassenAnswer[i][j]+" ");
			}
			System.out.println("");
		}
	}
}
