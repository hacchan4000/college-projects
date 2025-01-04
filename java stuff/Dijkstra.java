import java.util.*;

class Edge {
    int node;
    int bobot;


    public Edge(int bobot, int node) {
        this.node = node;
        this.bobot = bobot;
     
    }
}

public class Dijkstra {
    static void printDistances(int[] jarak) {
        System.out.println("jarak terpendek dari source menggunakan Dijkstra:");
        for (int i = 0; i < jarak.length; i++) {
            System.out.println("Node " + i + " : " + jarak[i]);
        }
    }

    static void minHeap(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int Source) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.bobot - y.bobot);//priority queue utk simpan edge n bbt
        int[] jarak = new int[V]; // jarak array
        for(int i = 0;i<V;i++){ // inisialisasi jarak semua sebagai int besar
            jarak[i] = (int)(1e9);
        }
        jarak[Source] = 0;

        pq.add(new Edge(0, Source)); // initialization

        while (!pq.isEmpty()) { //sama seperti BFS
            Edge top = pq.poll();
            int node = top.node;
            int dist = top.bobot;

            for(ArrayList<Integer> edge : adj.get(node)){
                int adjNode = edge.get(0);
                int edgeWeight = edge.get(1);

                if (dist + edgeWeight < jarak[adjNode]) {
                    jarak[adjNode] = dist + edgeWeight;
                    pq.add(new Edge(jarak[adjNode], adjNode));
                }
            }
        }
      printDistances(jarak);
    }

    public static void main(String[] args) {
        int V = 6;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());

        adj.get(0).add(new ArrayList<>(List.of(1, 40)));
        adj.get(0).add(new ArrayList<>(List.of(2, 40)));
        adj.get(1).add(new ArrayList<>(List.of(2, 20)));
        adj.get(2).add(new ArrayList<>(List.of(3, 30)));
        adj.get(2).add(new ArrayList<>(List.of(4, 10)));
        adj.get(2).add(new ArrayList<>(List.of(5, 60)));
        adj.get(3).add(new ArrayList<>(List.of(5, 20)));
        adj.get(4).add(new ArrayList<>(List.of(5, 30)));


        minHeap(V, adj,0);
    }
}
