//program to implement algorithms (iterative & recursive ) to do sum of array elements:

public class ArrayMain {
    
    public static void displayArrayElements(int [] arr){
        System.out.print("array elements are : ");
        for( int index = 0 ; index < arr.length ; index++ ){
            System.out.printf("%4d", arr[ index ]);
        }
        System.out.println();
    }
    
    //iterative approach:
    public static int arraySum(int [] arr){
        int sum = 0;
        
        for( int index = 0 ; index < arr.length ; index++ ){
            sum += arr[ index ];
        }        
        return sum;
    }

    public static int recArraySum(int [] arr, int index){
        //base condition
        if( index == arr.length )
            return 0;

        return ( arr[ index ] + recArraySum( arr, index+1 ) );//modication
        /* for above recArraySum( ) function call => "recursive function call" 
            calling function => recArraySum( )
            called function  => recArraySum( )
        */
        
    }
    public static void main(String [] args ) {
        int [] arr = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
        displayArrayElements(arr);
        
        System.out.println("sum = "+arraySum(arr) );
        
        System.out.println("sum = "+recArraySum(arr, 0) );//initialization of an index
        /* for above recArraySum( ) function call => "first time function call to recusrive function"
            calling function => main( )
            called function  => recArraySum( )
        */
        
    }    
}
