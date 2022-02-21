/* "undirected unweighted graph" */


import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;


class Graph{
    private int [][] mat;
    private int vertCount;
    private int edgeCount;

    public Graph(int vertCount){
        this.vertCount = vertCount;
        //allocate memory dyanamically for 2-d array of size V*V, V = no. of vertices
        mat = new int[ this.vertCount ][ this.vertCount ];
        for( int i = 0 ; i < this.vertCount ; i++ ){
            for( int j = 0 ; j < this.vertCount ; j++ ){
                mat[ i ][ j ] = 0;
            }
        }
    }

    public void acceptGraph( ){
        Scanner sc = new Scanner(System.in);
        System.out.print("enter no. of edges : ");
        this.edgeCount = sc.nextInt();

        //accept edgeCount no. of pairs of vertices i.e. edges from user
        for( int i = 0 ; i < this.edgeCount ; i++ ){
            System.out.print("enter an edge : from to to : ");
            int from = sc.nextInt();
            int to = sc.nextInt();
            mat[ from ][ to ] = 1;
            mat[ to ][ from ] = 1;//this line will be in comment for di-graph
        }
    }

    public void displayGraph( ){
        System.out.println("=============================================");
        System.out.println("input graph is : ");
        System.out.println("no. of vertices are : "+this.vertCount);
        System.out.println("no. of edges are: "+this.edgeCount);
        for( int i = 0 ; i < this.vertCount ; i++ ){
            for( int j = 0 ; j < this.vertCount ; j++ ){
                System.out.printf("%4d", mat[ i ][ j ]);
            }
            System.out.println();
        }
        System.out.println("=============================================");
    }

    public void dfsTraversal(int start){
        Stack<Integer> s = new Stack<Integer>();
        boolean [] marked = new boolean[ vertCount ];
        //initially all vertices are unmarked
        for( int i = 0 ; i < vertCount ; i++ ){
            marked[ i ] = false;
        }

        //step-1: push start vertex onto the stack and mark it
        s.push(start);
        marked[ start ] = true;
        System.out.print("dfs traversal is : ");
        while( !s.empty() ){
            //step-2: pop vertex from the stack and visit it
            int trav = s.pop();
            System.out.printf("%4d", trav);

            //step-3: push all its adjacent but unmarked vertices onto the stack and mark them
            for( int v = 0 ; v < vertCount ; v++ ){
                if( mat[ trav ][ v ] != 0 && marked[ v ] == false ){
                    s.push(v);
                    marked[ v ] = true;
                }
            }
        }//step-4: repeat step-2 & step-3 till stack not becomes empty 
        System.out.println();
    }

    public void bfsTraversal(int start){
        Queue<Integer> q = new LinkedList<Integer>();
        boolean [] marked = new boolean[ vertCount ];
        //initially all vertices are unmarked
        for( int i = 0 ; i < vertCount ; i++ ){
            marked[ i ] = false;
        }

        //step-1: push start vertex into the queue and mark it
        q.offer(start);
        marked[ start ] = true;
        System.out.print("bfs traversal is : ");
        while( !q.isEmpty() ){
            //step-2: pop vertex from the queue and visit it
            int trav = q.poll();
            System.out.printf("%4d", trav);

            //step-3: push all its adjacent but unmarked vertices into the queue and mark them
            for( int v = 0 ; v < vertCount ; v++ ){
                if( mat[ trav ][ v ] != 0 && marked[ v ] == false ){
                    q.offer(v);
                    marked[ v ] = true;
                }
            }
        }//step-4: repeat step-2 & step-3 till queue not becomes empty 
        System.out.println();
    }

    public void dfsSpanningTree(int root){
        Stack<Integer> s = new Stack<Integer>();
        boolean [] marked = new boolean[ vertCount ];
        //initially all vertices are unmarked
        for( int i = 0 ; i < vertCount ; i++ ){
            marked[ i ] = false;
        }

        //step-1: push root vertex onto the stack and mark it
        s.push(root);
        marked[ root ] = true;
        System.out.print("dfs spanning tree is : ");
        while( !s.empty() ){
            //step-2: pop vertex from the stack and visit it
            int trav = s.pop();
            System.out.printf("%4d", trav);

            //step-3: push all its adjacent but unmarked vertices onto the stack and mark them
            for( int v = 0 ; v < vertCount ; v++ ){
                if( mat[ trav ][ v ] != 0 && marked[ v ] == false ){
                    s.push(v);
                    marked[ v ] = true;
                }
            }
        }//step-4: repeat step-2 & step-3 till stack not becomes empty 
        System.out.println();
    }

    public void bfsSpanningTree(int root){
        Queue<Integer> q = new LinkedList<Integer>();
        boolean [] marked = new boolean[ vertCount ];
        //initially all vertices are unmarked
        for( int i = 0 ; i < vertCount ; i++ ){
            marked[ i ] = false;
        }

        //step-1: push root vertex into the queue and mark it
        q.offer(root);
        marked[ root ] = true;
        System.out.print("bfs spanning tree is : ");
        while( !q.isEmpty() ){
            //step-2: pop vertex from the queue and visit it
            int trav = q.poll();
            System.out.printf("%4d", trav);

            //step-3: push all its adjacent but unmarked vertices into the queue and mark them
            for( int v = 0 ; v < vertCount ; v++ ){
                if( mat[ trav ][ v ] != 0 && marked[ v ] == false ){
                    q.offer(v);
                    marked[ v ] = true;
                }
            }
        }//step-4: repeat step-2 & step-3 till queue not becomes empty 
        System.out.println();
    }

    public boolean isConnected(int source){
        Stack<Integer> s = new Stack<Integer>();
        boolean [] marked = new boolean[ vertCount ];
        int connectedVertexCount=0;

        //initially all vertices are unmarked
        for( int i = 0 ; i < vertCount ; i++ ){
            marked[ i ] = false;
        }

        //step-1: push source vertex onto the stack and mark it
        s.push(source);
        marked[ source ] = true;
        while( !s.empty() ){
            //step-2: pop vertex from the stack
            int trav = s.pop();
            
            //step-3: push all its adjacent but unmarked vertices onto the stack and mark them
            for( int v = 0 ; v < vertCount ; v++ ){
                if( mat[ trav ][ v ] != 0 && marked[ v ] == false ){
                    s.push(v);
                    marked[ v ] = true;
                    connectedVertexCount++;

                    if( connectedVertexCount == vertCount-1 )
                        return true;
                }
            }
        }//step-4: repeat step-2 & step-3 till stack not becomes empty 

        return ( ( connectedVertexCount == vertCount-1 ) ? true : false );
    }

}

public class AdjMatMain {
    public static void main(String[] args) {
        //accept no. of vertices in a graph from user
        Scanner sc = new Scanner(System.in);
        System.out.print("enter no. of vertices : ");
        int vertCount = sc.nextInt();

        Graph g1 = new Graph(vertCount);
        g1.acceptGraph();
        g1.displayGraph();

        
        //accept starting vertex from user
        System.out.print("enter start vertex : ");
        int start = sc.nextInt();

        g1.dfsTraversal(start);
        g1.bfsTraversal(start);

        //accept starting root from user
        System.out.print("enter root vertex : ");
        int root = sc.nextInt();
        g1.dfsSpanningTree(root);
        g1.bfsSpanningTree(root);

        //accept source vertex from user
        System.out.print("enter source vertex : ");
        int source = sc.nextInt();

        if( g1.isConnected(source) )
            System.out.println("g1 is connected");
        else
            System.out.println("g1 is not connected");

    }    
}
