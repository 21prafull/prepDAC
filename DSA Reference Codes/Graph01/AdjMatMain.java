import java.util.Scanner;

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
