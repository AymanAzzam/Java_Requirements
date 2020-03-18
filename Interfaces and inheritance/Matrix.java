package com.company;

public class Matrix implements Addable
{
    public int [][] arr2D;
    public int M; // rows
    public int N; // cols

    public Matrix(int M, int N)
    {
        this.M = M;
        this.N = N;
        this.arr2D = new int [M][N];
    }

    public boolean SetNumbers(int[] arr, int size)
    {
        if(size == this.N * this.M)
        {
            int k = 0;
            for(int i = 0; i < M; ++i)
            {
                for(int j = 0; j < N; ++j)
                {
                    this.arr2D[i][j] = arr[k];
                    k++;
                }
            }
            return true;
        }
        return false;
    }

    public Object Add(Object m)
    {
        if ((((Matrix) m).M != this.M || ((Matrix) m).N != this.N))
            throw new AssertionError(" Trying adding two matrices with two different dimensions");
        Matrix res = new Matrix(this.M, this.N);
        for(int i = 0; i < M; ++i)
        {
            for(int j = 0; j < N; ++j)
            {
                res.arr2D[i][j] = this.arr2D[i][j] + ((Matrix)m).arr2D[i][j];
            }
        }
        return res;
    }

    public void Print()
    {
        for(int i = 0; i < M; ++i)
        {
            for(int j = 0; j < N; ++j)
            {
                System.out.print(this.arr2D[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void Transpose()
    {
        Matrix newMat = new Matrix(N, M);
        for(int i = 0; i < M; ++i)
        {
            for(int j = 0; j < N; ++j)
            {
                newMat.arr2D[j][i] = this.arr2D[i][j];
            }
        }
        this.arr2D = newMat.arr2D;
        this.M = newMat.M;
        this.N = newMat.N;
    }

}