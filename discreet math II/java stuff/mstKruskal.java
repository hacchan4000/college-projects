import java.util.*;

class DisjointSet {
    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findPar(int n) {
        if (n == parent.get(n)) {
            return n;
        } else {
            int ulP = findPar(parent.get(n));
            parent.set(n, ulP);
            return parent.get(n);
        }
    }

    public void unionBySize(int u, int v) {
        int Pu = findPar(u);
        int Pv = findPar(v);

        if (Pu == Pv) {
            return;
        }

        if (size.get(Pu) < size.get(Pv)) {
            parent.set(Pu, Pv);
            size.set(Pv, size.get(Pv) + size.get(Pu));
        } else {
            parent.set(Pv, Pu);
            size.set(Pu, size.get(Pu) + size.get(Pv));
        }
    }
}

class Edge implements Comparable<Edge> {
    int dest;
    int bobot;
    int src;

    public Edge(int bobot, int dest, int src) {
        this.src = src;
        this.dest = dest;
        this.bobot = bobot;
    }

    public int compareTo(Edge compareEdge) {
        return this.bobot - compareEdge.bobot;
    }
}

public class mstKruskal {

    static void MST(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {

        List<Edge> edges = new ArrayList<>(); // List untuk menyimpan edges graph
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {

                int node = i;
                int adjNode = adj.get(i).get(j).get(0);
                int bbt = adj.get(i).get(j).get(1);
                Edge temp = new Edge(bbt, adjNode, node);

                edges.add(temp);
            }
        }
        Collections.sort(edges); // Sort edges by weight

        List<Edge> mstEdges = new ArrayList<>(); // List to store MST edges

        DisjointSet ds = new DisjointSet(V);

        // Applying Kruskal's algorithm
        for (Edge edge : edges) {
            int u = edge.src;
            int v = edge.dest;
            

            int Pu = ds.findPar(u);
            int Pv = ds.findPar(v);

            if (Pu != Pv) {
                mstEdges.add(edge);
                ds.unionBySize(Pu, Pv);
            }
        }
        int sum=0;
        System.out.println("MST Kruskal: \n");
        for (Edge edge : mstEdges) {
            System.out.println(edge.src + " -> " + edge.dest + "(" + edge.bobot + ")");
            sum += edge.bobot;
        }

        System.out.println("totalnya : ");
        System.out.println(sum);
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
