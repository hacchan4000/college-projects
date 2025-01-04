import java.util.*;

public class BFS {

    class Node {
        int dest;
        Node next;

        public Node(int dest) {
            this.dest = dest;
            this.next = null;
        }
    }

    class AdjList {
        Node head;
    }

    class Graph {
        int vertex; // Banyak vertex adj list
        AdjList[] array;

        public Graph(int V) {
            this.vertex = V;
            this.array = new AdjList[V];

            for (int i = 0; i < V; i++) {
                array[i] = new AdjList();
                array[i].head = null;
            }
        }

        public void addEdge(int v1, int v2) {
            Node newNode = new Node(v2);
            newNode.next = array[v1].head;
            array[v1].head = newNode;

            newNode = new Node(v1);
            newNode.next = array[v2].head;
            array[v2].head = newNode;
        }

        public void BFS(int startPoint) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[vertex];

            visited[startPoint] = true;
            queue.add(startPoint);

            while (!queue.isEmpty()) {
                int current = queue.poll();
                System.out.println("\nAdjacency list of vertex " + current + " :");
                System.out.print("head");

                Node adjList = array[current].head;
                while (adjList != null) {
                    System.out.print(" -> " + adjList.dest);
                    adjList = adjList.next;
                }

                adjList = array[current].head;
                while (adjList != null) {
                    int adjVertex = adjList.dest;
                    if (!visited[adjVertex]) {
                        visited[adjVertex] = true;
                        queue.add(adjVertex);
                    }
                    adjList = adjList.next;
                }
            }
        }
    }

    public static void main(String[] args) {
        BFS bfs = new BFS(); // Create an instance of BFS to access the Graph class

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan banyak vertex: ");
        int banyakVertex = scanner.nextInt();

        System.out.print("Masukkan banyak edge: ");
        int banyakEdge = scanner.nextInt();

        Graph graph = bfs.new Graph(banyakVertex); // Use bfs.new to create an instance of the inner class

        for (int i = 0; i < banyakEdge; i++) {
            System.out.print("Masukkan edge (v1 v2): ");
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            graph.addEdge(v1, v2);
        }

        System.out.print("Masukkan starting node: ");
        int start = scanner.nextInt();

        graph.BFS(start);

        scanner.close();
    }
}
