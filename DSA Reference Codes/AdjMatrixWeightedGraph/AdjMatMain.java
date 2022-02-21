/* implementation of "undirected weighted" graph */

/*
    if vertices are adjacent then in a matrix entry between them = weight
    if vertices are not adjacent then in a matrix entry between them = INF
*/

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
    }    
}
