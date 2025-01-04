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