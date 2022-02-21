package AdjListUnweightedGraph;

/* implementation of unweighted undirected adjList graph */

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


class Graph{
    private ArrayList< ArrayList<Integer> > adjList; 
    private int vertCount;
    private int edgeCount;

    public Graph(int vertCount){
        this.vertCount = vertCount;
        adjList = new ArrayList< ArrayList<Integer> >( vertCount );
        //create vertCount no. of empty ArrayList objects and add them into adjList
        for( int i = 0 ; i < this.vertCount ; i++ ){
            adjList.add(i, new ArrayList<Integer>());
        }
    }

    public void acceptGraph(){
        Scanner sc = new Scanner(System.in);
        System.out.print("enter no. of edges : ");
        this.edgeCount = sc.nextInt();

        for( int i = 0 ; i < this.edgeCount ; i++ ){
            System.out.print("enter an edge : from to to : ");
            int from = sc.nextInt();
            int to = sc.nextInt();

            adjList.get(from).add(to);//we are adding node having data part:"to" for vertex : "from" at index "from"
            adjList.get(to).add(from);//we are adding node having data part:"from" for vertex : "to" at index "to"
        }
    }

    void displayGraph( ){
        System.out.println("****************************************************");
        System.out.println("input graph is : ");
        System.out.println("no. of vertices are : "+vertCount);
        System.out.println("no. of edges are : "+edgeCount);
        for( int i = 0 ; i < adjList.size() ; i++ ){//outer for loop we are using to scan adjList vertically
            System.out.print("adjList[ "+i+" ] => ");
            for( int j = 0 ; j < adjList.get(i).size() ; j++ ){//innner for loop we are using to traverse each array list horizontally
                System.out.print(adjList.get(i).get(j)+" -> ");
            }
            System.out.println("null");
        }
        System.out.println();
        System.out.println("****************************************************");
    }
}

public class AdjListMain {
    public static void main(String[] args) {
        //accept no. of vertices for a graph from user
        Scanner sc = new Scanner(System.in);
        System.out.print("enter no. of vertices : ");
        int vertCount = sc.nextInt();
        Graph g1 = new Graph(vertCount);
        g1.acceptGraph();
        g1.displayGraph();

    }
}
