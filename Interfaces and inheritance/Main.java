package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix m1 = new Matrix(4,5);
        Matrix m2 = new Matrix(4,5);
        int []arr1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        if(m1.SetNumbers(arr1, 20)){
            m1.Print();
        }
        else {
            System.out.println("Can't set numers in m1 array size is not acceptable");
        }
        if(m2.SetNumbers(arr1, 20)){
            m2.Print();
        }
        else {
            System.out.println("Can't set numers in m2 array size is not acceptable");
        }
        Matrix m3 = (Matrix)m1.Add(m2);
        m3.Print();
        m3.Transpose();
        m3.Print();

        int []arr2 = {1,1,1};
        IdentityMatrix im1 = new IdentityMatrix(3);
        if(im1.SetNumbers(arr2, 3))
        	im1.Print();
        else
            System.out.println("Can't set numers in im1 array size is not acceptable");
        im1.Transpose();
        im1.Print();
    }
}
