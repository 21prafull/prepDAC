/* Program to implement doubly circular linked list operations */

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
            //attach first node into next part last node
            newNode.next = head;
            //attach last node into the prev part of first node
            newNode.prev = newNode;
            nodesCount++;
        }else{//step-3: if list is not empty
            //attach first node into the next part of newly created node
            newNode.next = head;
            //attach cur last ndoe into the prev part of newly created node
            newNode.prev = head.prev;
            //attach newly created node into the next part of cur last node
            head.prev.next = newNode;
            //attach newly created node into the prev part of first node
            head.prev = newNode;
            nodesCount++;
        }
    }

    public void addNodeAtFirstPosition(int data){
        //step-1: create a newnode
        Node newNode = new Node(data);
        //step-2: if list is empty then attach newly created node to the head
        if( isListEmpty() ){
            head = newNode;
            //attach first node into next part last node
            newNode.next = head;
            //attach last node into the prev part of first node
            newNode.prev = newNode;
            nodesCount++;
        }else{//step-3: if list is not empty
            //attach cur first node into the next part of newly created node
            newNode.next = head;
            //attach last node into the prev part of newly created node
            newNode.prev = head.prev;
            //attach newly created node into the next part of last node
            head.prev.next = newNode;
            //attach newly created node into the prev part of cur first node
            head.prev = newNode;
            //attach newly created node to the head
            head = newNode;
            nodesCount++;
        }
    }

    public void displayLinkedList( ){
        //if list is not empty
        if( !isListEmpty() ){
            //start traversal of the list from first node
            Node trav = head;
            System.out.println("no. of nodes in a list are : "+getNodesCount() );
            System.out.print("list in a forward dir is  : ");
            //traverse the list till last node
            do{
                System.out.printf("%4d", trav.data);//visit data part of each node
                trav = trav.next;//move trav towards it next node
            }while( trav != head );
            System.out.println();

            //start traversal of the list from last node
            trav = head.prev; 
            System.out.print("list in a backward dir is : ");
            //traverse the list till last node
            do{
                System.out.printf("%4d", trav.data);//visit data part of each node
                trav = trav.prev;//move trav towards it prev node
            }while( trav != head.prev );
            System.out.println();

        }else
            System.out.println("list is empty !!!");
    }

    public void deleteNodeAtLastPosition(){
        //step-1: check list is not empty
        if( !isListEmpty() ){//if list is not empty
            //step-2: if list contains only one node
            if( head == head.next ){
                //make head as null
                head = null;
                nodesCount--;
            }else{//step-3: if list contains more than one nodes
                //attach cur first node into the prev part of cur second last node
                head.prev.prev.next = head;
                //attach cur second last node into the prev part of first node
                head.prev = head.prev.prev;
                nodesCount--;
            }
        }
    }
}

public class DoublyCirLinkedListMain {
    
    public static void main( String [] args ){
        LinkedList l1 = new LinkedList();
        
        l1.addNodeAtLastPosition(11);
        l1.addNodeAtLastPosition(22);
        l1.addNodeAtLastPosition(33);
        l1.addNodeAtLastPosition(44);
        l1.addNodeAtLastPosition(55);
        l1.addNodeAtLastPosition(66);

        l1.displayLinkedList();

        //l1.addNodeAtFirstPosition(5);
        l1.deleteNodeAtLastPosition();

        l1.displayLinkedList();


    }    
}
