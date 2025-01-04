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

public class belmanford {
    static int[] relax(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int Source) {
        int[] jarak = new int[V]; // jarak array
        for (int i= 0; i<V ; i++) { // inisialisasi semua jarak dari source sbg infinity
            jarak[i] =(int)(1e8); 
        } 
        jarak[Source] = 0;

        // Relax edges 
        for (int j = 0; j < V - 1; j++) { //iterasi seluruh adj list
            for (int u = 0; u < V; u++) {
                for (ArrayList<Integer> edge : adj.get(u)) {
                    int v = edge.get(0);
                    int bbt = edge.get(1);

                    if (jarak[u] != 1e8 && jarak[u] + bbt < jarak[v]) { 
                        jarak[v] = jarak[u] + bbt;
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (int u = 0; u < V; u++) {
            for (ArrayList<Integer> edge : adj.get(u)) {
                int v = edge.get(0);
                int bbt = edge.get(1);
                if (jarak[u] != 1e8 && jarak[u] + bbt < jarak[v]) {
                    // Negative cycle detected
                    return new int[]{-1};
                }
            }
        }

        return jarak;
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
        adj.add(new ArrayList<>());

        adj.get(0).add(new ArrayList<>(List.of(1, 50)));
        adj.get(1).add(new ArrayList<>(List.of(2, -20)));
        adj.get(1).add(new ArrayList<>(List.of(5, -30)));
        adj.get(2).add(new ArrayList<>(List.of(4, 30)));
        adj.get(3).add(new ArrayList<>(List.of(2, 60)));
        adj.get(3).add(new ArrayList<>(List.of(4, -20)));
        adj.get(5).add(new ArrayList<>(List.of(3, 10)));

        int[] hasil = relax(V, adj, 0);

        if (hasil.length == 1 && hasil[0] == -1) {
            System.out.println("ada siklus negatif");
        }else{
            System.out.println("Jarak terpendeknya pake belmanford ");
            for (int i = 0; i < hasil.length; i++) {
                System.out.println("Node " + i + " : " + hasil[i]);
            }
        }
    }
    
}
