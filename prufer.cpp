//pengkodean tree
#include<iostream>
#include<vector>
#include<unordered_set>
#include<queue>


using namespace std;

vector<int> TreeToPrufer(int nodes,vector<pair<int,int> > &edges){
    unordered_set<int> tetangga[nodes+1];
    for (int i = 0; i < edges.size(); i++)
    {
        pair<int,int> edge = edges[i];
        int u = edges[i].first;
        int v = edges[i].second;
        tetangga[u].insert(v);
        tetangga[v].insert(u);
    }
    priority_queue<int> daun;
    for (int j = 0; j <= nodes; j++)
    {
        if (tetangga[j].size() == 1)
        {
            daun.push(-j);
        }
    }
    vector<int> prufer;
    int banyak = nodes-2;
    while (banyak--)
    {
        int leaf=-daun.top(); daun.pop();
        int tetanggaDaun = *(tetangga[leaf].begin());
        prufer.push_back(tetanggaDaun);
        tetangga[tetanggaDaun].erase(leaf);

        if (tetangga[tetanggaDaun].size() == 1)
        {
            daun.push(-tetanggaDaun);
        }  
    }
    return prufer;
    
}

void createTree(){
    int banyakVertex, banyakEdge;
    cout << "Masukkan banyak vertex: ";
    cin >> banyakVertex;
    cout << "Masukkan banyak edges: ";
    cin >> banyakEdge;
    //tree menggunakan adjacency set
    vector<pair<int, int> > edgeList;// array dinamis yg bsa resize sendiri klo ditambah elemen
    cout << "masukan vertex src dan dest tiap edge :" << endl;
    for (int i = 0; i < banyakEdge; i++)
    {
        int src,dest;
        cin >> src >> dest;
        edgeList.push_back({src,dest});
    }


    vector<int> pruferSeq = TreeToPrufer(banyakVertex, edgeList);
    cout << "Prufer Sequence: ";
    for (int i = 0; i < pruferSeq.size(); ++i) {
        cout << pruferSeq[i] << " ";
    }
    cout << endl;
}

vector<pair<int, int> > PruferToTree() {
    int pnjgLabel;
    cout << "Enter Prüfer sequence's length: ";
    cin >> pnjgLabel;
    int label[pnjgLabel];

    cout << "Enter each element of the Prüfer sequence: \n";
    for (int i = 0; i < pnjgLabel; i++) {
        cin >> label[i];
    }

    int banyakAsli = pnjgLabel + 2;
    int Vset[banyakAsli];
    for (int j = 0; j < banyakAsli; j++) {
        Vset[j] = 0;
    }

    for (int k = 0; k < pnjgLabel; k++) {
        Vset[label[k]] += 1;
    }

    vector<pair<int, int> > edges;

    for (int i = 0; i < pnjgLabel; i++) {
        int j = 0;
        for (; j < banyakAsli; j++) {
            if (Vset[j] == 0) {
                Vset[j] = -1;
                edges.push_back({j, label[i]});
                Vset[label[i]]--;
                break;
            }
        }
    }

    int x = 0;
    for (int i = 0; i < banyakAsli; i++) {
        if (Vset[i] == 0 && x == 0) {
            edges.push_back({i, -1});
            x++;
        } else if (Vset[i] == 0 && x == 1) {
            edges.back().second = i;
        }
    }

    return edges;
}

int main() {
    int pilihan = 0;
    while (pilihan!=3)
        {
            cout << "1. convert tree to label\n";
            cout << "2. convert label to tree\n";
            cout << "3. exit\n";
            cin >> pilihan;

            switch (pilihan)
            {
            case 1:
                createTree();
                break;
            case 2:
                PruferToTree();
                break;
            case 3:
                break;
            default:
                break;
            }
        }
    
   
    

    return 0;
}
