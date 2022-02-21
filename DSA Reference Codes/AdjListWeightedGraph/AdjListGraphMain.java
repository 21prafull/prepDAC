import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

class Graph{

    static class Edge{
        private int u;
        private int v;
        private int w;
    
        public Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    
    private ArrayList< LinkedList<Edge> > adjList;
    private int vertCount;
    private int edgeCount;

    public Graph(int vertCount){
        this.vertCount = vertCount;
        adjList = new ArrayList<LinkedList<Edge>>();
        for( int i = 0 ; i < vertCount ; i++ ){
            LinkedList<Edge> list = new LinkedList<Edge>();
            adjList.add(i, list);
        }
    }

    public void acceptGraph(){
        Scanner sc = new Scanner(System.in);
        System.out.print("enter no. of edges : ");
        edgeCount = sc.nextInt();
        
        for( int i = 0 ; i < edgeCount ; i++ ){
            System.out.print("enter an edge : from to to & weight : ");
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            
            Edge ed1 = new Edge(from, to, weight);
            adjList.get(from).add(ed1);

            Edge ed2 = new Edge(to, from, weight);
            adjList.get(to).add(ed2);
        }
    }

    public void displayGraph(){
        System.out.println("input graph is : ");
        System.out.println("no. of vertices are : "+vertCount);
        System.out.println("no. of edges are : "+edgeCount);
        for( int i = 0 ; i < vertCount; i++ ){
            LinkedList<Edge> list = adjList.get(i);
            System.out.print("list [ "+ i+" ] : ");
            for( int j = 0 ; j < list.size() ; j++ ){
                System.out.printf("| %d -> %d : %d | => ", list.get(j).u, list.get(j).v, list.get(j).w);
            }
            System.out.println(" null ");
        }
    }
} 


public class AdjListGraphMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter no. of vertices : ");
        int vertCount = sc.nextInt();

        Graph g1 = new Graph(vertCount);
        g1.acceptGraph();
        g1.displayGraph();        

    }
}
