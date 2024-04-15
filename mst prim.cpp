//program minimum spanning tree prim
#include<iostream>
#include<vector>
#include<unordered_set>
#include<queue>

using namespace std;

struct Edge
{
    int bobot;
    int node;
    int parent;

    Edge(int b ,int n, int p):bobot(b),node(n),parent(p){};

    bool operator>(const Edge& other) const {
        return bobot > other.bobot;
    }
};

void printMST(const vector<Edge>& MstEdges){
    int sum = 0;
    cout << "Edge pembentuk MST : ";
    for (const Edge& edge : MstEdges)
    {
        cout << edge.parent << " -> " << edge.node << "(bobotnya : " << edge.bobot << ")" << endl;
        sum+=edge.bobot;
    }
    cout << "total bobot MST : " << sum << endl;
}

vector<int> prim(int v,vector<vector<int>> adj[]){
    priority_queue<Edge, vector<Edge>, greater<Edge>> pq;//priority queue ato min heap untuk nyimpen bbt n edge
    vector<int> visited(v,0);//array visited

    pq.push(Edge(0, 0, -1));// inisialisasi
    vector<Edge> MstEdges;

    while (!pq.empty())// iterasi "tree"
    {
        Edge e = pq.top();
        pq.pop();
        int node = e.node;
        int weight = e.bobot;
        int ortu = e.parent;

        if (visited[node] == 1) continue;
        {
            visited[node] = 1;
            if (ortu!= -1)
            {
                MstEdges.push_back(Edge(weight, node, ortu));
            }
            

            for (auto it: adj[node])
            {
                int adjNode = it[0];
                int edW = it[1];
                if (!visited[adjNode])
                {
                    pq.push(Edge(edW,adjNode,node));// perjelas
                }
                
            }  
        } 
    }
    printMST(MstEdges);
}

int main() {
    int v = 7;
    vector<vector<int>> adj[v];
    adj[0] = {{1, 433}, {2, 473}, {3, 456}};
    adj[1] = {{0, 433}, {2, 447}, {4, 263}};
    adj[2] = {{0, 473}, {1, 447}, {3, 236}, {5, 305}};
    adj[3] = {{0, 456}, {2, 236}, {5, 440}, {6, 454}};
    adj[4] = {{1, 263}, {5, 555}};
    adj[5] = {{2, 305}, {3, 440}, {4, 555}, {6, 753}};
    adj[6] = {{3, 454}, {5, 753}};

    prim(v, adj);
    

    return 0;
}
