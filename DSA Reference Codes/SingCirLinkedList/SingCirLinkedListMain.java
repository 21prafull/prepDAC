/* Program to implement singly circular linked list operations */

class LinkedList{
    static class Node{
        private int data;
        private Node next;

        public Node(int data){
            this.data = data;
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
        if( head == null ){
            head = newNode;
            //attach first node into the next part of newNode which is added at last pos
            newNode.next = head;
            nodesCount++;
        }else{//step-3: if list is not empty
            //start tarversal from first node
            Node trav = head;
            //traverse the list till last node
            while( trav.next != head ){
                trav = trav.next;
            }
            //attach newly created node to the last node
            trav.next = newNode;
            //attach first node into the next part of newNode which is added at last pos
            newNode.next = head;
            nodesCount++;
        }
    }

    public void addNodeAtFirstPosition(int data){
        //step-1: create a newnode
        Node newNode = new Node(data);
        //step-2: if list is empty then attach newly created node to the head
        if( head == null ){
            head = newNode;
            //attach first node into the next part of newNode which is added at last pos
            newNode.next = head;
            nodesCount++;
        }else{//step-3: if list is not empty
            //start tarversal from first node
            Node trav = head;
            //traverse the list till last node
            while( trav.next != head ){
                trav = trav.next;
            }
            //attach cur first node in to the next part of newly created node
            newNode.next = head;
            //attach newly created node to head
            head  = newNode;
            //update next part of last node by an addr newly added node at first pos
            trav.next = head;
            nodesCount++;
        }
    }


    public void displayLinkedList( ){
        //if list is not empty
        if( !isListEmpty() ){
            //start traversal of the list from first node
            Node trav = head;
            System.out.print("list is : ");
            //traverse the list till last node
            do{
                System.out.printf("%4d", trav.data);//visit data part of the cur node
                trav = trav.next;//move trav towards its next node
            }while( trav != head );
            System.out.println();
            System.out.println("no. of nodes in a list are : "+getNodesCount());

        }else
            System.out.println("list is empty !!!");
    }

    public void deleteNodeAtFirstPosition( ){
        //step-1: check list is not empty
        if( !isListEmpty() ){//if list is not empty
            //step-2: if list contains only one node => make head as null
            if( head == head.next ){
                head = null;
                nodesCount--;
            }else{//step-3: if list contains more than one nodes
                //start tarversal of tthe list from the first node
                Node trav = head;
                //traverse the list till last node
                while( trav.next != head )
                    trav = trav.next;
        
                //attach cur second node to the head
                head = head.next;
                //update next part of last node to the cur second node mwhich becomes updated first node
                trav.next = head;
                nodesCount--;
            }	
        }
    }

}//end of LinkedList class

public class SingCirLinkedListMain {
    public static void main(String [] args) {
        //create an empty linked list
        LinkedList l1 = new LinkedList();

        l1.addNodeAtLastPosition(11);
        l1.addNodeAtLastPosition(22);
        l1.addNodeAtLastPosition(33);
        l1.addNodeAtLastPosition(44);
        l1.addNodeAtLastPosition(55);

        l1.displayLinkedList();

        //l1.addNodeAtFirstPosition(99);
        l1.deleteNodeAtFirstPosition();

        l1.displayLinkedList();


    }
}
