import java.util.*;

class Edge {
    int node;
    int bobot;
    int parent;

    public Edge(int bobot, int node, int parent) {
        this.node = node;
        this.bobot = bobot;
        this.parent = parent;
    }
}

public class mstPrim {
    static void printMST(ArrayList<Edge> MstEdges) {
        int sum = 0;
        System.out.println("MST prim : \n");
        for (Edge edge : MstEdges) {
            System.out.println(edge.parent + " -> " + edge.node + " (bobot: " + edge.bobot + ")");
            sum += edge.bobot;
        }
        System.out.println("Total sum: \n" + sum);
    }

    static void MST(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.bobot - y.bobot);//priority queue utk simpan edge n bbt
        int[] vis = new int[V];// visited array
        ArrayList<Edge> mstEdges = new ArrayList<>();// array nyimpen edge pembuat mst

        pq.add(new Edge(0, 0, -1)); // initialization

        while (!pq.isEmpty()) { 
            Edge top = pq.poll();
            int node = top.node;
            int bbt = top.bobot;
            int ortu = top.parent;

            if (vis[node] == 1) continue;
            {
                if (ortu != -1) {
                    mstEdges.add(new Edge(bbt, node, ortu));
                }
                vis[node] = 1;
                for (ArrayList<Integer> edge : adj.get(node)) {
                    int adjNode = edge.get(0);
                    int edgeWeight = edge.get(1);
    
                    if (vis[adjNode] == 0) {
                        pq.add(new Edge(edgeWeight, adjNode, node));
                    }
                }
            }
        }
        printMST(mstEdges);
    }

    public static void main(String[] args) {
        int V = 7;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>());

        adj.get(0).add(new ArrayList<>(List.of(1, 433)));
        adj.get(0).add(new ArrayList<>(List.of(2, 473)));
        adj.get(0).add(new ArrayList<>(List.of(3, 456)));
        adj.get(1).add(new ArrayList<>(List.of(2, 447)));
        adj.get(1).add(new ArrayList<>(List.of(4, 263)));
        adj.get(2).add(new ArrayList<>(List.of(3, 236)));
        adj.get(2).add(new ArrayList<>(List.of(5, 305)));
        adj.get(3).add(new ArrayList<>(List.of(5, 440)));
        adj.get(3).add(new ArrayList<>(List.of(6, 454)));
        adj.get(4).add(new ArrayList<>(List.of(5, 555)));
        adj.get(5).add(new ArrayList<>(List.of(6, 753)));



        MST(V, adj);
    }
}
