/* 
    # dijsktra's algorithm: to find shortest distance of all vertices from given source vertex
    - this algo is also used to find shortest distance of all vertices from all vertices
    
    # prim's algo to find minimum spanning tree of a given graph (spanning tree of a given graph having
    minimum weight ).
    
    ===============================================================================================*/

import java.util.Scanner;

class Graph{
    private int [][] mat;
    private int vertCount;
    private int edgeCount;
    public static final int INF=9999;


    public Graph(int vertCount){
        this.vertCount = vertCount;
        //allocate memory dyanamically for 2-d array of size V*V, V = no. of vertices
        mat = new int[ this.vertCount ][ this.vertCount ];
        for( int i = 0 ; i < this.vertCount ; i++ ){
            for( int j = 0 ; j < this.vertCount ; j++ ){
                mat[ i ][ j ] = INF;
            }
        }
    }

    public void acceptGraph( ){
        Scanner sc = new Scanner(System.in);
        System.out.print("enter no. of edges : ");
        this.edgeCount = sc.nextInt();

        //accept edgeCount no. of pairs of vertices i.e. edges from user
        for( int i = 0 ; i < this.edgeCount ; i++ ){
            System.out.print("enter an edge : from to to & weight : ");
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            mat[ from ][ to ] = weight;
            mat[ to ][ from ] = weight;//this line will be in comment for di-graph
        }
    }

    public void displayGraph( ){
        System.out.println("=============================================");
        System.out.println("input graph is : ");
        System.out.println("no. of vertices are : "+this.vertCount);
        System.out.println("no. of edges are: "+this.edgeCount);
        for( int i = 0 ; i < this.vertCount ; i++ ){
            for( int j = 0 ; j < this.vertCount ; j++ ){
                if( mat[ i ][ j ] != INF )
                    System.out.printf("%4d", mat[ i ][ j ]);
                else
                    System.out.printf("%4s", "#");
            }
            System.out.println();
        }
        System.out.println("=============================================");
    }

    /* this function returns such a vertex which is having min key value and not yet added into
    the MST set of vertices */
    public int getMinKeyVertex(int [] dist, boolean [] marked){
        int minIndex = 0;
        int minDistance = INF;
        for( int i = 0 ; i < vertCount ; i++ ){
            if( marked[ i ] == false && dist[ i ] < minDistance ){
                minIndex = i;
                minDistance = dist[ i ];
            }
        }
        return minIndex;
    }

    public void dijsktra(int source){
        int [] dist = new int[ vertCount ];
        boolean [] marked = new boolean[ vertCount ];
        int mstVertexCount=0;

        //intially distance of all vertices from given source vertex is INF & all vertices are unmarked
        for( int v = 0 ; v < vertCount ; v++ ){
            dist[ v ] = INF;
            marked[ v ] = false;
        }

        //distance of source vertex from itself is 0
        dist[ source ] = 0;
        System.out.print("MST Set of Vertices is : { ");
        while( mstVertexCount < vertCount ){
            //get minKeyVertex and add it into MST set of vertices i.e. mark it
            int minKeyVertex = getMinKeyVertex(dist, marked);
            marked[ minKeyVertex ] = true;
            mstVertexCount++;

            System.out.printf("%4d", minKeyVertex);

            //update all its adjacent but unmarked vertices to the min distance
            for( int v = 0 ; v < vertCount ; v++ ){
                if( mat[ minKeyVertex ][ v ] != INF && marked[ v ] == false &&
                    dist[ v ] > dist[ minKeyVertex] + mat[ minKeyVertex ][ v ] ){
                        dist[ v ] = dist[ minKeyVertex] + mat[ minKeyVertex ][ v ];
                  }
            }
        }//end of while loop
        System.out.println(" } ");

        System.out.println("distance of all vertices from given source vertex is : ");
        for( int v = 0 ; v < vertCount ; v++ ){
            System.out.printf("distance[ %d ] from %d is : %d\n", v, source, dist[ v ] );
        }
    }

    void prims(int root){
        boolean [] marked = new boolean[ vertCount ];
        int [] vertKey = new int[ vertCount ];
        int mst_vert_cnt=0;
        int [] parent = new int[ vertCount ]; 
        int weightMST = 0;

        //initially key value of all vertices is INF and parent of each vertex is -1 
        //all vertices are unmarked i.e. not yet added into the MST set of vertices
        for( int v = 0 ; v < vertCount ; v++ ){
            vertKey[ v ] = INF;
            marked[ v ] = false;
            parent[ v ] = -1;
        }

        //key value of root vertext is 0 & parent of root vertex is root itself
        vertKey[ root ] = 0;
        parent[ root ] = root;

        System.out.print("MST SET OF VERTICES: { ");
        while( mst_vert_cnt < vertCount ){
            int min_key_vert = getMinKeyVertex(vertKey, marked);
            System.out.print(min_key_vert+" ");
            //mark "min_key_vert" as added into the MST set of vertices and increment the counter
            marked[ min_key_vert ] = true;
            mst_vert_cnt++;


            //update key values of all its adjancent but unmarked vertices to the min weigh
            for( int v = 0 ; v < vertCount ; v++ ){
                if( mat[ min_key_vert ][ v] != INF && marked[v] == false &&
                    mat[min_key_vert][v] < vertKey[v] ) {
                    vertKey[ v ] = mat[ min_key_vert ][ v ] ;
                    parent[ v ] = min_key_vert;
                }
            }
        }//end of while loop
        System.out.println(" } ");
        System.out.println("edge set of MST is : ");
        for( int i = 0 ; i < vertCount ; i++ ){
            if( i != root ){
                System.out.println(i +" -> "+ parent[i] + " : " + mat[ i ][ parent[i] ]);
                weightMST += mat[ i ][ parent[i] ];
            } 
        }

        System.out.println("Weight of MST is : "+weightMST);
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

        //accept source vertex from user
        System.out.print("enter source vertex : ");
        int source = sc.nextInt();
        System.out.println("output of dijsktra's is : ");
        g1.dijsktra(source);

        //accept root vertex from user
        System.out.print("enter root vertex : ");
        int root = sc.nextInt();
        System.out.println("output of prim's is : ");
        g1.prims(root);

    }    
}
