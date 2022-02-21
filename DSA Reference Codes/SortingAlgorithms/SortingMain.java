package SortingAlgorithms;

public class SortingMain {

    public static void displayArrayElements(int [] arr){
        System.out.print("array ele's are : ");
        for( int index = 0 ; index < arr.length ; index++ ){
            System.out.printf("%4d", arr[ index ] );
        }
        System.out.println();
    }
    
    public static void swapArrayElements(int [] arr, int i, int j){
        int temp = arr[ i ];
        arr[ i ] = arr[ j ];
        arr[ j ] = temp;
    }

    public static void selectionSort(int [] arr){
        int iterations=0;
        int comparisons=0;

        for( int sel_pos = 0 ; sel_pos < arr.length-1 ; sel_pos++ ){//outer for loop is for iterations
            iterations++;
            for( int pos = sel_pos+1 ; pos < arr.length ; pos++ ){//inner for loop is for pos
                //if( ele at sel_pos > ele at pos ) => swap them 
                comparisons++;
                if( arr[ sel_pos ] > arr[ pos ] )
                    swapArrayElements(arr, sel_pos, pos);
            }
        }

        System.out.println("no. of iterations are : "+iterations);
        System.out.println("no. of comparisons are : "+comparisons);

    }

    public static void bubbleSort(int [] arr){
        int iterations=0;
        int comparisons=0;

        for( int itr = 0 ; itr < arr.length-1 ; itr++ ){//outer for loop is for iterations
            iterations++;
            for( int pos = 0 ; pos < arr.length-itr-1 ; pos++ ){//inner for loop is for pos
                //if prev pos ele > its next pos ele i.e. if elements are not in order => swap them
                comparisons++;
                if( arr[ pos ] > arr[ pos+1 ] ){
                    swapArrayElements(arr, pos, pos+1);
                }
            }
        }
        System.out.println("no. of iterations are : "+iterations);
        System.out.println("no. of comparisons are : "+comparisons);
    }

    public static void modifiedBubbleSort(int [] arr){
        int iterations=0;
        int comparisons=0;
        boolean flag = true;

        for( int itr = 0 ; itr < arr.length-1 && flag == true ; itr++ ){//outer for loop is for iterations
            flag = false;
            iterations++;
            for( int pos = 0 ; pos < arr.length-itr-1 ; pos++ ){//inner for loop is for pos
                //if prev pos ele > its next pos ele i.e. if elements are not in order => swap them
                comparisons++;
                if( arr[ pos ] > arr[ pos+1 ] ){
                    flag = true;
                    swapArrayElements(arr, pos, pos+1);
                }
            }
        }
        System.out.println("no. of iterations are : "+iterations);
        System.out.println("no. of comparisons are : "+comparisons);
    }

    public static void insertionSort(int [] arr){
        int iterations=0;
        int while_cnt=0;

        for( int i = 1 ; i  < arr.length ; i++ ){
            iterations++;
            int key = arr[ i ];
            int j = i-1;
            
            //if pos is valid then only compare value of key with an ele at that at that pos 
            while( j >= 0 && key < arr[ j ] ){
                arr[ j+1 ] =  arr[ j ];//shift ele towards its right by 1
                j--;//goto prev ele	
                while_cnt++;
            }
            //insert key at its appropriate pos
            arr[ j+1 ] = key;
        }
        System.out.println("no. of iterations are : "+iterations);
        System.out.println("while_cnt : "+while_cnt);
    }

    public static void quickSort(int [] arr, int left, int right){
        //base condition
        if( left >= right )//if either partition contains only 1 ele or partition invalid
            return;

        //if(left < right) => partition is valid and we can apply partitioning on it
        //select leftmost ele as a pivot
        int pivot = arr[ left ];

        System.out.println("pivot = "+pivot);
        
        int i = left;//start scanning partition in a forward dir from left
        int j = right;//start scanning partition in a backward dir from right

        while( i < j ){
            while( i <= right && arr[ i ] <= pivot )
                i++;

            while( arr[ j ] > pivot )
                j--;

            //if i & j have not crossed then swap them
            if( i < j )
                swapArrayElements(arr, i, j);
        }

        //swap jth pos ele with pivot ele
        swapArrayElements(arr, j, left); 

        //apply partitioning on left partition
        quickSort(arr, left, j-1);//modification
        //apply partitioning on right partition
        quickSort(arr, j+1, right);//modification
    }

    public static void merge(int [] arr, int left, int mid, int right){
        //allocate memory dynamically for temp array
        int i = left;//start scanning left subarray from its first ele
        int j = mid+1;//start scanning right subarray from its first ele
        int size = right-left+1;
        int [] temp = new int[ size ];
        int k = 0;

        while( i <= mid && j <= right ){
            /*compare ele's of left subarray with ele's of right subarray sequentially
            & whichever ele is smaller copy it first into temp array */
            if( arr[ i ] < arr[ j ] )
                temp[ k++ ] = arr[ i++ ];
            else
                temp[ k++ ] = arr[ j++ ];
        }

        //copy remaining ele's if any from left subarray into temp array as it is
        while( i <= mid ){
            temp[ k++ ] = arr[ i++ ];
        }

        //copy remaining ele's if any from right subarray into temp array as it is
        while( j <= right ){
            temp[ k++ ] = arr[ j++ ];
        }

        k=0;
        i=left;
        //copy them into an original array
        while( i <= right ){
            arr[ i++ ] = temp[ k++ ];
        }

    }


    public static void mergeSort(int [] arr, int left, int right){
        //base condition
        if( left >= right )//till size of subarray > 1 
            return;

        //if( left < right) => subarray is having size more than 1
        int mid = (left+right)/2;
        mergeSort(arr, left, mid);//divide left subarray further
        mergeSort(arr, mid+1, right);//divide right subarray further
        merge(arr, left, mid, right);//merge 2 already sorted array
    }

    public static void main(String [] args) {
        //int [] arr = { 30, 20, 60, 50, 10, 40 };
        //int [] arr = { 10, 20, 30, 40, 50, 60 };
        int [] arr = { 50, 90, 20, 70, 10, 100, 80, 40, 30, 60 };
        //int [] arr = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
        //int [] arr = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10 };

        System.out.print("bfore sorting => "); displayArrayElements(arr);
        //selectionSort(arr);
        //bubbleSort(arr);
        //modifiedBubbleSort(arr);
        //insertionSort(arr);
        //quickSort(arr, 0, arr.length-1);//initialization
        mergeSort(arr, 0, arr.length-1);//initialization
        System.out.print("after sorting => "); displayArrayElements(arr);
       
    }
}
