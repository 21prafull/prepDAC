import java.util.Scanner;

/* Program to implement Circular Queue by using an array => Static Circular Queue */

class Queue{
    private int [] arr;
    private int rear;
    private int front;

    public Queue( ){
        arr = new int[ 5 ];
        rear = -1;
        front = -1;
    }

    public Queue(int size){
        arr = new int[ size ];
        rear = -1;
        front = -1;
    }

    public boolean isQueueFull( ){
        return ( front == (rear+1) % arr.length );
    }

    public boolean isQueueEmpty( ){
        return ( rear == -1 && front == rear );
    }

    public void enqueue(int element){
        //step-2: increment the value of rear by 1.
        rear = (rear+1)%arr.length;

        //step-3: insert an element into the queue at rear end
        arr[ rear ] = element;
        /* step-4: if( front == -1 )
		    front = 0;  */
        if( front == -1 )
            front = 0;
    }

    public int getElementAtFront(){
        //return the value of an element which is at front end
        return ( arr[ front ] );
    }

    public void dequeue(){
        if( front == rear )//if we are deleting last ele then reinitialize queue
            front = rear  = -1;
        else
        /* step-2: increment the value of front by 1.
        [ by means of incrementing the value of front by 1 we are achieving deletion of an element from the queue ]. */
            front = (front+1)%arr.length;
    }
}


public class CirQueueMain {
    
    public static int menu( ){
        System.out.println("****** circular queue ******");
        System.out.println("0. exit");
        System.out.println("1. enqueue");
        System.out.println("2. dequeue");

        Scanner sc = new Scanner(System.in);
        System.out.print("enter the choice : ");
        int choice = sc.nextInt();
        return choice;
    }
    
    public static void main(String[] args) {
        Queue q = new Queue();
        while( true ){
            int choice = menu();
            switch( choice ){
                case 0: System.exit(0);

                case 1: //insert element into the queue
                    //step-1: check queue is not full
                    if( !q.isQueueFull( ) ){//if queue is not full then only we can insert an element into it from rear end).
                        Scanner sc = new Scanner(System.in);
                        System.out.print("enter an element: ");
                        int element = sc.nextInt();
                        q.enqueue(element);
                        System.out.println(element+" inserted into the queue ....");
                    }else{
                        System.out.println("queue is full !!!");
                    }
                    break;

                case 2://delete element from the queue
                    //step-1: check queue is not empty
                    if( !q.isQueueEmpty() ){//if queue is not empty then only we can delete element from it from front end).
                        int element = q.getElementAtFront();
                        q.dequeue();
                        System.out.println("deleted element is :"+element);
                    }else{
                        System.out.println("queue is empty !!!");
                }
                break;
            }

        }        
    }    
}
