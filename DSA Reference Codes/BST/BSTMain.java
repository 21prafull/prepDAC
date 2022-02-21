import javax.lang.model.util.ElementScanner6;
import javax.swing.text.AbstractDocument.LeafElement;

import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

class BST{

    static class Node{
        private int data;
        private Node left;
        private Node right;
        private boolean visited;

        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
            this.visited = false;//initially node is not visited
        }

    }//end of Node class

    private Node root;//it is a reference which contains an addr/ref of root node if bst is not empty

    public BST( ){
        root = null;//intially bst is empty
    }

    public boolean isBSTEmpty( ){
        return ( root == null );
    }

    /* we can add as many as we want no. of nodes into the BST in O(log n) time,
       as to find/search position of newly created node takes O(log n) time.
    */

    public void addNode(int data){
        //step-1: create a newNode
        Node newNode = new Node(data);
        //step-2: if BST is empty then attach newly created node to the root
        if( isBSTEmpty() ){
            root = newNode;
        }else{//step-3: if BST is not empty
            //find an approripriate position of a newly created node in a BST and attach it by traversing BST
            //start traversal of BST from root node
            Node trav = root;

            while( true ){
                if( data < trav.data ){//node will be a part of left subtree of cur node
                    if( trav.left == null ){//if left subtree of cur node is empty
                        //attach newly created node as a left child of cur node
                        trav.left = newNode;
                        break;
                    }else{//if cur node is having left subtree => goto its left subtree
                        trav = trav.left;
                    }
                }else{//if( data >= trav.data )//node will be a part of right subtree of cur node
                    if( trav.right == null ){//if right subtree of cur node is empty
                        //attach newly created node as a right child of cur node
                        trav.right = newNode;
                        break;
                    }else{//if cur node is having right subtree => goto its right subtree
                        trav = trav.right;
                    }
                }
            }
        }//end of else
    }//end of addNode() method

    public void recAddNode(Node trav, int data){
        //base condition
        if( trav == null )
            return;

        if( data < trav.data ){//=> node will be a part of left subtree of cur node
            if( trav.left == null ){//if left subtree of cur node is empty
                //create a newnode and attach it as left child of cur node
                trav.left = new Node(data);
            }else{//if cur node is having left subtree => goto its left subtree
                recAddNode(trav.left, data);
            }
        }else{//if( data >= trav.data ) => node will be a part of right subtree of cur node
            if( trav.right == null ){//if left right subtree of cur node is empty
                //create a newnode and attach it as right child of cur node
                trav.right = new Node(data);
            }else{//if cur node is having right subtree => goto its right subtree
                recAddNode(trav.right, data);
            }
        }
    }

    public void recAddNode(int data){
        //if BST is empty then a create a newnode and attach it to the root
        if( isBSTEmpty() ){
            root = new Node(data); 
        }else{//if BST is not empty
            recAddNode(root, data);//initialization
        }
    }

    public void preOrder(Node trav){
        //base condition
        if( trav == null )
            return;

        //VLR
        System.out.printf("%4d", trav.data);//visit data of cur node
        preOrder(trav.left);//goto visit its left subtree
        preOrder(trav.right);//goto visit its right subtree
    }

    public void preOrder(){
        if( !isBSTEmpty() ){
            System.out.print("preorder traversal is : ");;
            preOrder(root);//initialization
            System.out.println();
        }else
            System.out.println("bst is empty !!!");
    }

    public void inOrder(Node trav){
        //base condition
        if( trav == null )
            return;

        //LVR
        inOrder(trav.left);//goto visit its left subtree
        System.out.printf("%4d", trav.data);//visit data of cur node
        inOrder(trav.right);//goto visit its right subtree
    }

    public void inOrder(){
        if( !isBSTEmpty() ){
            System.out.print("inorder traversal is  : ");;
            inOrder(root);//initialization
            System.out.println();
        }else
            System.out.println("bst is empty !!!");
    }

    public void postOrder(Node trav){
        //base condition
        if( trav == null )
            return;

        //LRV
        postOrder(trav.left);//goto visit its left subtree
        postOrder(trav.right);//goto visit its right subtree
        System.out.printf("%4d", trav.data);//visit data of cur node
    }

    public void postOrder(){
        if( !isBSTEmpty() ){
            System.out.print("postorder traversal is: ");;
            postOrder(root);//initialization
            System.out.println();
        }else
            System.out.println("bst is empty !!!");
    }

    public void nonRecPreOrder( ){
        //if BST is not empty
        if( !isBSTEmpty() ){
            Stack<Node> s = new Stack<Node>();
            //start traversal from root node
            Node trav = root;
            System.out.print("preorder traversal is : ");
            while( trav != null || !s.empty( ) ){//outer while loop
                
                while( trav != null ){//inner while loop
                    //visit cur node
                    System.out.printf("%4d", trav.data);
                    
                    //push right child cur node onto the stack if it is having
                    if( trav.right != null )
                        s.push(trav.right);

                    //goto its left subtree
                    trav = trav.left;
                }//end of inner while loop

                //pop element from the stack if stack is not empty
                if( !s.empty() ){
                    trav = s.pop();
                }

            }//end of outer while loop
            System.out.println();
        }else
            System.out.println("bst is empty !!!");
    }

    public void nonRecInOrder( ){
        //if BST is not empty
        if( !isBSTEmpty() ){
            Stack<Node> s = new Stack<Node>();
            //start traversal from root node
            Node trav = root;
            System.out.print("inorder traversal is  : ");
            while( trav != null || !s.empty( ) ){//outer while loop
                
                while( trav != null ){//inner while loop
                    //push cur node onto the stack
                    s.push(trav);

                    //goto its left subtree
                    trav = trav.left;
                }//end of inner while loop

                //if stack is not empty
                if( !s.empty() ){
                    //pop element from the stack 
                    trav = s.pop();
                    //visit cur node
                    System.out.printf("%4d", trav.data);
                    //goto its right subtree
                    trav = trav.right;
                }

            }//end of outer while loop
            System.out.println();
        }else
            System.out.println("bst is empty !!!");
    }

    public void nonRecPostOrder( ){
        //if BST is not empty
        if( !isBSTEmpty() ){
            Stack<Node> s = new Stack<Node>();
            //start traversal from root node
            Node trav = root;
            System.out.print("postorder traversal is: ");
            while( trav != null || !s.empty( ) ){//outer while loop
                
                while( trav != null ){//inner while loop
                    //push cur node onto the stack
                    s.push(trav);

                    //goto its left subtree
                    trav = trav.left;
                }//end of inner while loop

                //if stack is not empty
                if( !s.empty() ){
                    //pop element from the stack 
                    trav = s.pop();

                    //if cur node is having right subtree && its right child is not visited 
                    if( trav.right != null && trav.right.visited == false ){
                        //push cur node onto the stack
                        s.push(trav);
                        //goto its right subtree
                        trav = trav.right;
                    }else{//if right subtree of cur node is empty or already visited
                        //visit cur node & mark it as visted
                        System.out.printf("%4d", trav.data);
                        trav.visited = true;
                        trav = null;//so that we can go for popping next node from the stack
                    }
                }    
            }//end of outer while loop
            System.out.println();
        }else
            System.out.println("bst is empty !!!");
    }

    public void dfsTraversal( ){
        if( !isBSTEmpty() ){
            Stack<Node> s = new Stack<Node>();
            //step-1: push root node onto the stack
            s.push(root);
            System.out.print("dfs traversal is : ");
            while( !s.empty( ) ){
                //step-2: pop the node from the stack and visit it
                Node trav = s.pop();
                System.out.printf("%4d", trav.data);

                //step-3: if cur node is having right child push it onto the stack
                if( trav.right != null )
                    s.push(trav.right);

                //step-4: if cur node is having left child push it onto the stack
                if( trav.left != null )
                    s.push(trav.left);
            }//step-5: repeat step-2, step-3 & step-4 till stack is not empty
            System.out.println();
        }else
            System.out.println("bst is empty !!!");
    }

    public void bfsTraversal( ){
        if( !isBSTEmpty() ){
            Queue<Node> q = new LinkedList<Node>();
            //step-1: push root node into the queue
            q.offer(root);//enqueue
            System.out.print("bfs traversal is : ");
            while( !q.isEmpty() ){
                //step-2: pop the node from the queue and visit it
                Node trav = q.poll();//dequeue
                System.out.printf("%4d", trav.data);

                //step-3: if cur node is having left child push it into the queue
                if( trav.left != null )
                    q.offer(trav.left);

                //step-4: if cur node is having right child push it into the queue
                if( trav.right != null )
                    q.offer(trav.right);

                }//step-5: repeat step-2, step-3 & step-4 till queue is not empty
            System.out.println();
        }else
            System.out.println("bst is empty !!!");
    }

    public boolean searchNode(int data, Node [] arr){
        //arr[ 1 ] => ref of node which is to be deleted
        //arr[ 0 ] => ref of parent node of node which is to be deleted
        Node trav = root;
        arr[ 0 ] = null;

        while( trav != null ){
            if( data == trav.data ){
                arr[ 1 ] = trav;
                return true;
            }

            arr[ 0 ] = trav;

            if( data < trav.data )
                trav = trav.left;//goto search key into the left subtree
            else
                trav = trav.right;//goto search key into the left subtree
        }

        //if node is not found
        arr[ 0 ] = null;
        return false;

    }

    public boolean deleteNode(int data){
        Node [] arr = { null, null };
        //search a node having data part matches with "data"
        if( !searchNode(data, arr) )//if node is not found
            return false;

        //if node is found
        Node temp = arr[ 1 ];
        Node parent = arr[ 0 ];

        if( parent == null )
            System.out.println("node is found at root position => temp.data : "+temp.data);
        else
            System.out.println("parent.data : "+parent.data+"\ttemp.data : "+temp.data);

        //delete node
        //case-1: node which we want to delete is having left subtree as well as right subtree
        if( temp.left != null && temp.right != null ){
            //get an addr of an inorder succ of node which we want to delete
            Node succ = temp.right;
            parent = temp;
            while( succ.left != null ){
                parent = succ;
                succ = succ.left;
            }
            //replace data part of temp by data part of succ
            temp.data = succ.data;
            //assign succ as a temp which is to be deleted now
            temp = succ;
        }

        //case-2:
        if( temp.left == null ){//if node which is to be deleted is having empty left subtree
            if( temp == root )//if node that we want to delete is a root node
                root = temp.right;//its right right child becomes root
            else if( temp == parent.left )//if node that we want to delete is a left child of its parent
                parent.left = temp.right;
            else//node that we want to delete is a right child of its parent
                parent.right = temp.right;
        }else{//case-3: if node which is to be deleted is having empty right subtree : if( temp.right == null )
            if( temp == root )//if node that we want to delete is a root node
                root = temp.left;//its right right child becomes root
            else if( temp == parent.left )//if node that we want to delete is a left child of its parent
                parent.left = temp.left;
            else//node that we want to delete is a right child of its parent
                parent.right = temp.left;
        }

        return true;
    }

    public int max( int n1, int n2){
        return ( ( n1 > n2 ) ? n1 : n2 );
    }

    public int height(Node trav){
        //base condition
        if( trav == null )
            return -1;

        return ( max( height(trav.left), height(trav.right) ) + 1 );
    }

    //wrapper function
    public int height( ){
        if( isBSTEmpty() )
            return -1;
        else
            return ( height(root) );//initialization        
    }

    public Node leftRotation(Node axis, Node parent){
        if( axis == null || axis.right == null )
            return null;
        
        Node newaxis = axis.right;
        axis.right = newaxis.left;
        newaxis.left = axis;

        if( axis == root )//if axis is root
            root = newaxis;
        else if( axis == parent.left )//if axis is left child of its parent
            parent.left = newaxis;
        else////if axis is right child of its parent
            parent.right = newaxis;
        
        return newaxis;
    }

    public Node rightRotation(Node axis, Node parent){
        if( axis == null || axis.left == null )
            return null;
        
        Node newaxis = axis.left;
        axis.left = newaxis.right;
        newaxis.right = axis;

        if( axis == root )//if axis is root
            root = newaxis;
        else if( axis == parent.left )//if axis is left child of its parent
            parent.left = newaxis;
        else//if axis is right child of its parent
            parent.right = newaxis;
        
        return newaxis;
    }

    public void balance(Node trav, Node parent){
        //base condition
        if( trav == null )
            return;
        //calculate balance factor of cur node
        int bf = height(trav.left) - height(trav.right);

        //if node is imbalanced
        while( bf < -1 || bf > +1 ){
            if( bf < -1 ){//if node is left imbalanced => apply left rotation
                trav = leftRotation(trav, parent);
                bf += 2;//increment balance factor of a node by 2
            }else{//if( bf > +1 ) => if node is right imbalanced => apply right rotation
                trav = rightRotation(trav, parent);
                bf -= 2;////decrement balance factor of a node by 2
            }
        }
        
        //control comes here only if cur node is balanced i.e. if bf is either -1 OR 0 Or +1.
        balance(trav.left, trav);//balance left subtree of cur node
        balance(trav.right, trav);//balance right subtree of cur node

    }

    //warpper function
    public void balance( ){
        if( !isBSTEmpty( ) )
            balance(root, null);//initialization
    }
}

public class BSTMain {
    public static void main(String[] args) {
        //create an empty BST        
        BST t1 = new BST();
        //Binary Search Tree => Input Order : 50 20 90 85 10 45 30 100 15 75 95 120 5 50
        /*
        t1.recAddNode(50);
        t1.recAddNode(20);
        t1.recAddNode(90);
        t1.recAddNode(85);
        t1.recAddNode(10);
        t1.recAddNode(45);
        t1.recAddNode(30);
        t1.recAddNode(100);
        t1.recAddNode(15);
        t1.recAddNode(75);
        t1.recAddNode(95);
        t1.recAddNode(120);
        t1.recAddNode(5);
        t1.recAddNode(50);
        */
        /*
        t1.addNode(50);
        t1.addNode(20);
        t1.addNode(90);
        t1.addNode(85);
        t1.addNode(10);
        t1.addNode(45);
        t1.addNode(30);
        t1.addNode(100);
        t1.addNode(15);
        t1.addNode(75);
        t1.addNode(95);
        t1.addNode(120);
        t1.addNode(5);
        t1.addNode(50);
        */

        t1.addNode(10);
        t1.addNode(20);
        t1.addNode(30);
        t1.addNode(40);
        t1.addNode(50);
        t1.addNode(60);
        //t1.addNode(70);


        t1.preOrder();
        //t1.nonRecPreOrder();
        t1.inOrder();
        //t1.nonRecInOrder();
        t1.postOrder();
        //t1.nonRecPostOrder();        
        t1.dfsTraversal();
        t1.bfsTraversal();
        System.out.println("height of t1 is : "+t1.height( ));
        
        System.out.println("=======================================================================");
        /*
        //accept data part of node which is to delete
        System.out.print("enter data part of a node which is to delete : ");
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();

        if( !t1.isBSTEmpty() ){
            if( t1.deleteNode(data) )
                System.out.println("node having data part : "+data+" is found in a bst and deleted");
            else
                System.out.println("node having data part : "+data+" is not found in a bst");
        }
        */

        t1.balance();

        t1.preOrder();
        t1.inOrder();
        t1.postOrder();
        t1.dfsTraversal();
        t1.bfsTraversal();
        System.out.println("height of t1 is : "+t1.height( ));

    }
}
