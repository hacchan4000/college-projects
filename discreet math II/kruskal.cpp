//program minimum spanning tree kruskal
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class DisjointSet {
    vector<int> parent, size;

public:
    DisjointSet(int nodes) {
        parent.resize(nodes + 1);
        size.resize(nodes + 1);
        for (int i = 0; i <= nodes; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int FindPar(int n) {
        if (n == parent[n]) {
            return n;
        } else {
            return parent[n] = FindPar(parent[n]);
        }
    }

    void gabunganBySize(int u, int v) {
        int Pu = FindPar(u);
        int Pv = FindPar(v);

        if (Pu == Pv) {
            return;
        }

        if (size[Pu] < size[Pv]) {
            parent[Pu] = Pv;
            size[Pv] += size[Pu];
        } else {
            parent[Pv] = Pu;
            size[Pu] += size[Pv];
        }
    }
};

class kruskal {
public:
    vector<pair<int, pair<int, int>>> MST(vector<vector<pair<int, int>>>& adj) {
        vector<pair<int, pair<int, int>>> edges;

        for (int i = 0; i < adj.size(); i++) {
            for (auto it : adj[i]) {
                int adjNode = it.first;
                int bbt = it.second;
                int node = i;
                edges.push_back({bbt, {node, adjNode}});
            }
        }
        sort(edges.begin(), edges.end());
        vector<pair<int, pair<int, int>>> mstEdges;
        DisjointSet djs(adj.size());

        for (auto edge : edges) {
            int weight = edge.first;
            int u = edge.second.first;
            int v = edge.second.second;

            if (djs.FindPar(u) != djs.FindPar(v)) {
                mstEdges.push_back({weight, {u, v}});
                djs.gabunganBySize(u, v);
            }
        }
        return mstEdges;
    }
};

int main() {
    int v = 7;
    vector<vector<pair<int, int>>> adj(v);
    adj[0] = {{1, 433}, {2, 473}, {3, 456}};
    adj[1] = {{0, 433}, {2, 447}, {4, 263}};
    adj[2] = {{0, 473}, {1, 447}, {3, 236}, {5, 305}};
    adj[3] = {{0, 456}, {2, 236}, {5, 440}, {6, 454}};
    adj[4] = {{1, 263}, {5, 555}};
    adj[5] = {{2, 305}, {3, 440}, {4, 555}, {6, 753}};
    adj[6] = {{3, 454}, {5, 753}};

    kruskal obj;
    vector<pair<int, pair<int, int>>> mstEdges = obj.MST(adj);

    int totalWeight = 0;
    cout << "Minimum Spanning Tree :" << endl;
    for (auto edge : mstEdges) {
        totalWeight += edge.first;
        cout << edge.second.first << " - " << edge.second.second << " : " << edge.first << endl;
    }
    cout << "total : \n" << totalWeight << endl;

    return 0;
}
