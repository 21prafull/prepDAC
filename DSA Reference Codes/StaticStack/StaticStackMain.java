import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

/* Program to implement Static Stack */

class Stack{
    private int [] arr;
    private int top;

    Stack( ){
        arr = new int[ 5 ];
        top = -1;
    }

    Stack(int size){
        arr = new int[ size ];
        top = -1;
    }

    public boolean isStackFull( ){
        return ( top == arr.length-1 );
    }

    public boolean isStackEmpty( ){
        return ( top == -1 );
    }

    public void pushElement(int element){
        //step-2: increment the value of top by 1.
        top++;
        //step-3: push/add element onto stack at top position
        arr[ top ] = element;
    }

    public int peekElement(){
        /* step-2: get the value of an element which is is at top end
        [ without incrementing/decrementing top ]. */
        return ( arr[ top ] );

    }

    public void popElement( ){
        /* step-2: decrement the value of top by 1.
        (by means of decrementing the value of top by 1 we are achieving deletion of an element from  the stack). */
        top--;
    }
}


public class StaticStackMain {
    public static int menu(){
        
        System.out.println("****** static stack ******");
        System.out.println("0. exit");
        System.out.println("1. push element");
        System.out.println("2. pop element");
        System.out.println("3. peek element");
    
        System.out.print("enter the choice : ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
    
        return choice;
    }

    public static void main( String [] args ){
        Stack s1 = new Stack();
        int element;

        while( true ){
            int choice = menu();
            switch( choice ){
                case 0: System.exit(0);
                
                case 1://push element
                    //step-1: check stack is not full 
                    //if stack is not full then only we can push element onto it.
                    if( !s1.isStackFull() ){
                        System.out.print("enter an element : ");
                        Scanner sc = new Scanner(System.in);
                        element = sc.nextInt();
                        s1.pushElement(element);
                        System.out.println(element+ " inserted/pushed onto the stack....");
                    }else
                        System.out.println("stack overflow !!!");
                    break;
                
                case 2://pop element
                    //step-1: check stack is not empty 
                    //if stack is not empty then only we can pop element from it.
                    if( !s1.isStackEmpty() ){
                        element = s1.peekElement();
                        s1.popElement();
                        System.out.println(element+ " popped from the the stack....");
                    }else
                        System.out.println("stack underflow !!!");

                    break;
                
                case 3://peek element
                    //step-1: check stack is not empty 
                    //if stack is not empty then only we can peek element from it.
                    if( !s1.isStackEmpty() ){
                        element = s1.peekElement();
                        System.out.println("topmost ele is : "+element);
                    }else
                        System.out.println("stack underflow !!!");
                    break;
            }

        }
    }    
}
