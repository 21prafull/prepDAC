package SearchingAlgorithms;
/* Program to implement searching algorithms */

import java.util.Scanner;

public class SearchingMain {
    
    public static int comparisons;

    public static void displayArrayElements(int [] arr){
        System.out.print("entered array elements are : ");
        for( int index = 0 ; index < arr.length ; index++ ){
            System.out.printf("%4d", arr[ index ]);
        }
        System.out.println();
    }

    public static boolean linearSearch(int [] arr, int key){
        comparisons=0;
        for( int index = 0 ; index < arr.length ; index++ ){
            comparisons++;
            if( key == arr[ index ] )//if key mathces with array element
                return true;
        }
        //if key do not mathces with any of array element
        return false;
    }

    public static boolean recLinearSearch(int [] arr, int key, int index){
        //base condition
        if( index == arr.length )//if key is not matches with any of array elements -> return false
            return false;

        comparisons++;
        if( key == arr[ index ] )//if key is matches with any of array element -> return true
            return true;
        else
            return ( recLinearSearch(arr, key, index+1) );//modification
    }

    public static boolean binarySearch(int [] arr, int key){
        int left=0;
        int right=arr.length-1;

        while( left <= right ){
            //step-2: calculate mid position
            int mid = (left + right)/2;
            
            comparisons++;
            //step-3: compare the value of key with ele at mid poistion
            if( key == arr[ mid ] )//if key matches with an ele at mid pos => return true
                return true;

            //if key do not matches with an ele at mid pos => goto the next iteration
            if( key < arr[ mid ] )//=> goto search key into the left subarray
                right=mid-1;
            else//if( key > arr[ mid ] ) => goto search key into the right subarray
                left=mid+1;
        }//step-4: repeat step-2 & step-3 till either key is not found or max till subarray is valid

        //if key is not found in an array at any position
        return false;

    }

    public static boolean recBinarySearch(int [] arr, int key, int left, int right){
        //base condition
        if( left > right )//if key do not matches with any of array ele at mid position
            return false;

        //calculate mid position
        int mid = (left+right)/2;
        //compare value of key with an ele at mid position
        comparisons++;
        if( key == arr[ mid ] )//if value of the key matches with an ele at mid pos
            return true;

        //if value of the key do not matches with an ele at mid pos
        if( key < arr[ mid ] )//=> goto search key into the left subarray
            return ( recBinarySearch(arr, key, left, mid-1) );//modification
        else//=> goto search key into the right subarray
            return ( recBinarySearch(arr, key, mid+1, right) );
    }

    public static void main(String [] args) {
        int [] arr = { 10, 20, 30, 40, 50, 50, 60, 70, 80, 90, 100 };
        displayArrayElements(arr);

        Scanner sc = new Scanner(System.in);
        //step-1: accept key from user
        System.out.print("enter the value of key : ");
        int key = sc.nextInt();
        
        /*
        //non-recursive approach:
        //step-2:
        if( linearSearch(arr, key) )
            System.out.println(key+" is found in an array, no. of comparisons are : "+comparisons);
        else
            System.out.println(key+" is not found in an array, no. of comparisons are : "+comparisons);
        */

        /*
        //recursive approach:
        //step-2:
        if( recLinearSearch(arr, key, 0) )//initialization
            System.out.println(key+" is found in an array, no. of comparisons are : "+comparisons);
        else
            System.out.println(key+" is not found in an array, no. of comparisons are : "+comparisons);
        */

        /*
        //non-recursive approach:
        if( binarySearch(arr, key) )
            System.out.println(key+" is found in an array, no. of comparisons are : "+comparisons);
        else
            System.out.println(key+" is not found in an array, no. of comparisons are : "+comparisons);
        */

        //recursive approach:
        if( recBinarySearch(arr, key, 0, arr.length-1) )//initialization
            System.out.println(key+" is found in an array, no. of comparisons are : "+comparisons);
        else
            System.out.println(key+" is not found in an array, no. of comparisons are : "+comparisons);

    }   
}
