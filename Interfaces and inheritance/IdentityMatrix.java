package com.company;

public class IdentityMatrix extends Matrix
{
    public IdentityMatrix(int N)
    {
        super(N, N);
    }

    public boolean SetNumbers(int[] arr, int size)
    {
    	//Check square size
    	if(size != this.N || size != this.M)	return false;
    	// Check all diagonals are equal to 1
    	for(int i=0;i<size;i++)	if(arr[i]!=1) return false; 
        
        for(int i = 0; i < M; ++i)	
        	for(int j = 0; j < N; ++j)
        		if(i == j)	this.arr2D[i][j] = 1;
        		else		this.arr2D[i][j] = 0;
        
        return true;
    }
    public void Transpose(){}
}