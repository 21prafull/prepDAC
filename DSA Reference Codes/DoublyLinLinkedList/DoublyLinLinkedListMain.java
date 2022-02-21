import java.util.Scanner;

/* Program to implement doubly linear linked list operations */

class LinkedList{
    static class Node{
        private Node prev;
        private int data;
        private Node next;

        public Node(int data){
            this.data = data;
            this.prev = null;
            this.next = null;
        }

    }//end of Node class

    private Node head;
    private int nodesCount;
    
    public LinkedList(){
        head = null;
        nodesCount=0;
    }

    public boolean isListEmpty( ){
        return ( head == null );
    }

    public int getNodesCount( ){
        return ( this.nodesCount );
    }

    public void addNodeAtLastPosition(int data){
        //step-1: create a newnode
        Node newNode = new Node(data);
    
        //step-2: if list is empty then attach newly created node to the head
        if( isListEmpty() ){
            head = newNode;
            nodesCount++;
        }else{//step-3: if list is not empty
            //start traversal from first node
            Node trav = head;
            //tarverse the list till last node
            while( trav.next != null ){
                trav = trav.next;
            }
            //attach newly created node to the last node
            trav.next = newNode;
            //attach cur last node into the prev part of newly created node
            newNode.prev = trav;
            nodesCount++; 
        }
    }

    public void addNodeAtFirstPosition(int data){
        //step-1: create a newnode
        Node newNode = new Node(data);
    
        //step-2: if list is empty then attach newly created node to the head
        if( isListEmpty() ){
            head = newNode;
            nodesCount++;
        }else{//step-3: if list is not empty
            //attach cur first node into the next part of newly created node
            newNode.next = head;
            //attach newly created node into the prev part of cur first node
            head.prev = newNode;
            //attach newly created node to the head
            head = newNode;
            nodesCount++;
        }
    }

    public void displayLinkedList( ){
        //if list is not empty
        if( !isListEmpty()){
            //start traversal of the list from first node
            Node trav = head;
            Node temp = null;
            System.out.println("no. of nodes in a list are : "+getNodesCount() );
            System.out.print("list in a forward dir is  : ");
            //traverse the list till last node
            while( trav != null ){
                temp = trav;
                System.out.printf("%4d", trav.data);//visit data part of each node
                trav = trav.next;//move trav towards it next node
            }
            System.out.println();

            //start traversal of the list from last node
            trav = temp; 
            System.out.print("list in a backward dir is : ");
            //traverse the list till last node
            while( trav != null ){
                System.out.printf("%4d", trav.data);//visit data part of each node
                trav = trav.prev;//move trav towards it prev node
            }
            System.out.println();

        }else
            System.out.println("list is empty !!!");
    }

    public void addNodeAtSpecificPosition(int pos, int data){
        if( pos == 1 )//if pos is the first position
            addNodeAtFirstPosition(data);
        else if ( pos == getNodesCount() + 1 )//if pos is the last position
            addNodeAtLastPosition(data);
        else{//if pos is in between position
            //create a newnode
            Node newNode = new Node(data);
            int i=1;
            //start traversal of the list from first node
            Node trav = head;
            //traverse the list till (pos-1)th node
            while( i < pos-1 ){
                i++;
                trav = trav.next;//move trav towards its next node
            }
            //attach cur (pos-1)th node into the prev part of newly created node
            newNode.prev = trav;
            //attach cur (pos)th node into the next part of newly created node
            newNode.next = trav.next;
            //attach newly created node into the prev part of cur (pos)th node
            trav.next.prev = newNode;
            //attach newly created node into the next part of (pos-1)th node
            trav.next = newNode;
            nodesCount++;
        }
    }

    public void deleteNodeAtFirstPosition( ){
        //step-1: check list is not empty
        if( !isListEmpty() ){//if list is not empty
            //step-2: if list contains only one node
            if( head.next == null ){
                //make head as null
                head = null;
                nodesCount--;
            }else{//step-3: if list contains more than one nodes
                //attach cur second node to the head
                head = head.next;
                //make prev part of cur second ndoe as null which is modified first node
                head.prev = null;
                nodesCount--;
            }
        }else
            System.out.println("list is empty !!!");
    }

    public void deleteNodeAtLastPosition( ){
        //step-1: check list is not empty
        if( !isListEmpty() ){//if list is not empty
            //step-2: if list contains only one node
            if( head.next == null ){
                //make head as null
                head = null;
                nodesCount--;
            }else{//step-3: if list contains more than one nodes
                //start traversal from first node
                Node trav = head;
                //traverse the list till second last node
                while( trav.next.next != null )
                    trav = trav.next;
                
                //make next part of cur second last node as null
                trav.next = null;
                nodesCount--;
            }
        }else
            System.out.println("list is empty !!!");
    }

    public void deleteNodeAtSpecificPosition(int pos){
        if( pos == 1 )//if pos the first psoition
            deleteNodeAtFirstPosition();
        else if( pos == getNodesCount() )//if pos is the last position
            deleteNodeAtLastPosition();
        else{//if pos is in between position
            int i = 1;
            //start travesal of the list from first node
            Node trav = head;
            //traverse the list till (pos-1)th node
            while( i < pos-1 ){
                i++;
                trav = trav.next;
            }
            //attach cur (pos-1)th node into the prev part of cur (pos+1)th node
            trav.next.next.prev = trav;
            //attach cur (pos+1)th node into the next part of cur (pos-1)th node
            trav.next = trav.next.next;
            nodesCount--;
        } 
    }

}//end of LinkedList class

public class DoublyLinLinkedListMain {
    public static void main(String[] args) {
        //create an empty list
        LinkedList l1 = new LinkedList();
        
        l1.addNodeAtLastPosition(10);
        l1.addNodeAtLastPosition(20);
        l1.addNodeAtLastPosition(30);
        l1.addNodeAtLastPosition(40);
        l1.addNodeAtLastPosition(50);
        l1.addNodeAtLastPosition(60);
        l1.addNodeAtLastPosition(70);
        l1.addNodeAtLastPosition(80);
        l1.addNodeAtLastPosition(90);


        l1.displayLinkedList();

        
        //l1.addNodeAtFirstPosition(5);
        Scanner sc = new Scanner(System.in);
        int pos;

        while( true ){
            //step-1: accept position from the user
            System.out.print("enter the pos : ");
            pos = sc.nextInt();
            //step-2: validate position
            if( pos > 0 && pos <= l1.getNodesCount() )
                break;
                
            System.out.println("invalid poistion !!!");
        }

        l1.deleteNodeAtSpecificPosition(pos);

        //l1.addNodeAtSpecificPosition(pos, 99);
        

        //l1.deleteNodeAtFirstPosition();
        //l1.deleteNodeAtLastPosition();

        l1.displayLinkedList();
        
    }
}
