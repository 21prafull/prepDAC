import java.util.Scanner;

import javax.management.RuntimeErrorException;

class LinkedList{
    static class Node{
        private int data;//contains actual data of type int
        private Node next;//contains reference to the next node

        public Node(int data ){
            this.data = data;
            this.next = null;
        }

    }//end of Node class

    private Node head;//4 bytes
    private int nodesCount;

    public LinkedList(){
        head = null;
        nodesCount=0;
    }

    public boolean isListEmpty( ){
        return ( head == null );
    }

    public int getNodesCount(){
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
            //start traversal of list from first node
            Node trav = head;
            //traverse the list till last node
            while( trav.next != null ){
                trav = trav.next;//move trav towards its next node
            }
            //attach newly created node to the last node
            trav.next = newNode;
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
            //attach newly created node to the head
            head = newNode;
            nodesCount++;
        }
    }

    //counfNodes( ) function takes O(n) time
    public int countNodes( ){
        int count = 0;

        if( !isListEmpty() ){
            //start tarversal of the list from first node
            Node trav = head;
            //traverse the list till last node
            while( trav != null ){
                count++;//increment the counter
                trav = trav.next;//move trav towards its next node
            }
        }
        return count;
    }

    public void addNodeAtSpecificPosition(int pos, int data){
        if( pos == 1 )//if pos is the first position
            addNodeAtFirstPosition(data);
        else if( pos == getNodesCount() + 1 )//if pos is the last position
            addNodeAtLastPosition(data);
        else{//if pos is inbetween position
            //create a newnode
            Node newNode = new Node(data);
            int i=1;
            //start traversal of the list from first node
            Node trav=head;
            //traverse the list till (pos-1)th node
            while( i < pos-1 ){
                i++;
                trav = trav.next;
            }
            //attach cur (pos)th node to the next part of newly created node
            newNode.next = trav.next;
            //attach newly created node to the next part of (pos-1)th node
            trav.next = newNode;
            nodesCount++;
        }
    }

    public void displayLinkedList(){
        if( isListEmpty() )
            throw new RuntimeException("list is empty !!!");
        else{//if list is not empty
            //start traversal from first node
            Node trav = head;
            //System.out.println("no. of nodes in a list are : "+countNodes());
            System.out.println("no. of nodes in a list are : "+getNodesCount());
            System.out.print("list is : head -> ");
            //traverse the list and display data part of each node
            while( trav != null ){
                System.out.printf("%d -> ", trav.data);
                trav = trav.next;//move trav towards its next node
            }
            System.out.println("null");
        }
    }

    public void deleteNodeAtFirstPosition( ){
        //step-1: check list is empty or not
        if( isListEmpty() )
            throw new RuntimeException("list is empty !!!");
        else{//step-2: if list is  not empty 
            //step-3: if list contains only one node
            if( head.next == null ){
                //make head as null => delete the node
                head = null;
                nodesCount--;
            }else{//if list contains more than one node
                //attach cur second node the head
                head  = head.next;
                nodesCount--;
            }
        }
    }

    public void deleteNodeAtLastPosition( ){
        //step-1: check list is empty or not
        if( isListEmpty() )
            throw new RuntimeException("list is empty !!!");
        else{//step-2: if list is  not empty 
            //step-3: if list contains only one node
            if( head.next == null ){
                //make head as null => delete the node
                head = null;
                nodesCount--;
            }else{//if list contains more than one node
                //start traversal from first node
                Node trav = head;
                //traverse the list till second last node
                while( trav.next.next != null ){
                    trav = trav.next;//move trav towards its next node
                }
                //make next part of cur second last node as null
                trav.next = null;
                nodesCount--;
            }
        }
    }

    public void deleteNodeAtSpecificPosition(int pos){
        if( pos == 1  )//if pos is the first position
            deleteNodeAtFirstPosition();
        else if( pos == getNodesCount() )//if pos is the last position
            deleteNodeAtLastPosition();
        else{//if pos is the in between position
            int i=1;
            //start traversal from first node
            Node trav = head;
            //traverse the list till (pos-1)th node
            while( i < pos-1 ){
                i++;
                trav = trav.next;
            }
            //attach (pos+1)th node in to the next part of (pos-1)th node
            trav.next = trav.next.next;
            nodesCount--;
        }
    }

    public void displayLinkedListInReverseOrder(Node trav){
        //base condition
        if( trav == null )
            return;

        displayLinkedListInReverseOrder(trav.next);//modification
        System.out.printf("%4d", trav.data);
    }

    //wrapper function is used to give call rec function
    public void displayLinkedListInReverseOrder( ){
        System.out.println("list in reverse order is : ");
        displayLinkedListInReverseOrder(head);//initiallization
        System.out.println();
    }

    public void reverseList( ){
        //start traversal of the list from first node
        Node t1 = head;
        Node t2 = t1.next;//store ref of cur second into t2 
        t1.next = null;//make cur first node as a last node

        while( t2 != null  ){
            Node t3 = t2.next;//move t3 towards its next node
            t2.next = t1;//reverse link
            t1 = t2;//move t1 towards its next node
            t2 = t3;//move t2 towards its next node
        }
        //attach cur last node to the head, so that cur last node becomes first node
        head = t1;
    }

    /* "searchNode()" function searches a node and on success it return true as an out parameters
    it returns an addr of node which is to be deleted as well as an addr of its prev node and on failure
    it returns false
    */
    public boolean searchNode(int data, Node [] temp){
        temp[ 0 ] = null;//temp[ 0 ] will refer to prev node of node if it is exists
        for( Node trav = head ; trav != null ; trav = trav.next ){
            if( data == trav.data ){
                temp[ 1 ] = trav;
                return true;
            }
            temp[ 0 ] = trav;
        }

        //if key node is not found in a list
        temp[ 0 ] = null;
        return false;
    }

    public boolean searchAndDelete(int data){
        //search a node having data part matches with "data part of a key node"
        Node [] temp = { null, null };
        //temp is an array of references => temp[ 0 ] = null & temp[ 1 ] = null
        
        //if key node is not found then return false
        if( !searchNode(data, temp) )
            return false;

        //if node is found
        Node cur = temp[ 1 ];
        Node prev = temp[ 0 ];

        if( prev == null ){
            System.out.println("node is found at first position => cur.data : "+cur.data);
            //if node which is to be deleted is at first pos -> attach cur second node to the head
            head = cur.next;
        }else{
            System.out.println("prev.data : "+prev.data+"\t cur.data : "+cur.data);
            //attach next node of node which is to be deleted to next part of its prev node
            prev.next = cur.next; 
        }
        
        nodesCount--;
        return true;
    }

}//end of LinkedList class

public class SingLinLinkedListMain {
    
    public static int menu( ){
        //display menu list
        System.out.println("***** singly linear linked list operations *****");
        System.out.println("0. exit");
        System.out.println("1. add node into the list at last position");
        System.out.println("2. add node into the list at first position");
        System.out.println("3. add node into the list at specific position");
        System.out.println("4. delete node from the list at first position");
        System.out.println("5. delete node from the list at last position");
        System.out.println("6. delete node from the list at specific position");
        System.out.println("7. display the list");
        System.out.println("8. display the list in a reverse order");
        System.out.println("9. reverse list");
        System.out.println("10. search and delete node from the list");
        //accept choice from the user and return it to the calling function
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the choice : ");
        int choice = sc.nextInt();
        return choice;
    }
    public static void main(String [] args ) {
        LinkedList l1 = new LinkedList();
        Scanner sc = new Scanner(System.in);
        int data;
        int pos;

        while( true ){
            int choice = menu();
            switch( choice ){
                case 0: System.exit(0);

                case 1://add node at last position
                    System.out.print("enter the data : ");
                    data = sc.nextInt();
                    l1.addNodeAtLastPosition(data);
                    break;

                case 2: //add node at first position
                    System.out.print("enter the data : ");
                    data = sc.nextInt();
                    l1.addNodeAtFirstPosition(data);
                    break;

                case 3://add node at specific position
                    while( true ){
                        //step-1: accept position from user
                        System.out.print("enter the position : ");
                        pos = sc.nextInt();
            
                        //step-2: validate position
                        if( pos > 0 && pos <= l1.getNodesCount() + 1 )
                            break;
            
                        System.out.println("invalid position !!!");
                    }
                    System.out.print("enter the data : ");
                    data = sc.nextInt();
                    l1.addNodeAtSpecificPosition(pos, data);
                    break;

                case 4://delete node at first position
                    try{
                        l1.deleteNodeAtFirstPosition();
                    }catch( RuntimeException e){
                        System.out.println( e.getMessage() );
                    }
                    break;

                case 5://delete node at last position
                    try{
                        l1.deleteNodeAtLastPosition();
                    }catch( RuntimeException e){
                        System.out.println( e.getMessage() );
                    }
                    break;

                case 6://delete node at specific position
                    while( true ){
                        //step-1: accept position from user
                        System.out.print("enter the position : ");
                        pos = sc.nextInt();
            
                        //step-2: validate position
                        if( pos > 0 && pos <= l1.getNodesCount() )
                            break;
            
                        System.out.println("invalid position !!!");
                    }

                    try{
                        l1.deleteNodeAtSpecificPosition(pos);
                    }catch( RuntimeException e){
                        System.out.println( e.getMessage() );
                    }
                    break;

                case 7://display the list
                    try{
                        l1.displayLinkedList();
                    }catch( RuntimeException e){
                        System.out.println( e.getMessage());
                    }
                    break;

                case 8://display the list in a reverse order
                    try{
                        l1.displayLinkedListInReverseOrder();//wrapper function
                    }catch( RuntimeException e){
                        System.out.println( e.getMessage());
                    }
                    break;

                case 9://reverse the list

                    if( !l1.isListEmpty() )
                        l1.reverseList();

                    break;

                case 10://search and delete node from the list
                    //accept data part of a node which is to be deleted
                    System.out.print("enter the data : ");
                    data = sc.nextInt();

                    if( l1.searchAndDelete(data) )
                        System.out.println("node having data part : "+data+" deleted from the list");
                    else
                        System.out.println("node having data part : "+data+" not found in the list");
                    break;

            }
        }
    }
}
