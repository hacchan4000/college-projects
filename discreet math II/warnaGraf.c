////program pewarnaan graph final
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

struct node {
    int val;
    int derajat;
    struct node* link;
};

struct AdjList {
    struct node* head;
};

struct Graph {
    int vertex;
    struct AdjList *array;
};

struct Graph* createGraph(int V) {
    struct Graph* graphBaru = (struct Graph*)malloc(sizeof(struct Graph));
    graphBaru->vertex = V;
    graphBaru->array = (struct AdjList*)malloc(V * sizeof(struct AdjList));
    for (int i = 0; i < V; i++) {
        graphBaru->array[i].head = NULL;
    }
    return graphBaru;
}

struct node* addNewNode(int data) {
    struct node* NodeBaru = (struct node*)malloc(sizeof(struct node));
    NodeBaru->val = data;
    NodeBaru->link = NULL;
    NodeBaru->derajat = 0;
    return NodeBaru;
}

void addEdge(struct Graph* graph, int src, int dest) {
    struct node* newNode = addNewNode(dest);
    newNode->link = graph->array[src].head;
    graph->array[src].head = newNode;

    newNode = addNewNode(src);
    newNode->link = graph->array[dest].head;
    graph->array[dest].head = newNode;
}

void derajatNode(struct Graph* graph, int banyakdrjt, int nodeVal) {
    bool found = false;
    for (int i = 0; i < graph->vertex; i++) {
        struct node* head = graph->array[i].head;
        while (head != NULL) {
            if (head->val == nodeVal) {
                head->derajat = banyakdrjt;
                found = true;
                break;
            }
            head = head->link;
        }
        if (found) break;
    }
    if (!found) {
        printf("Node val %d not found.\n", nodeVal);
    }
}

void BFS(struct Graph* graph, int StartPoint) {
    int* queue = (int*)malloc(graph->vertex * sizeof(int));
    int depan = 0, belakang = 0;
    int count = 0; // Initialize count

    bool* visited = (bool*)malloc(graph->vertex * sizeof(bool));
    for (int i = 0; i < graph->vertex; i++)
        visited[i] = false;

    visited[StartPoint] = true;
    queue[belakang++] = StartPoint;

    while (depan != belakang) {
        int current = queue[depan++];

        printf("\n Adjacency list of vertex %d :\n head", current);
        printf("\n");

        struct node* pAdj = graph->array[current].head;
        while (pAdj) {
            printf(" -> %d", pAdj->val);
            pAdj = pAdj->link;
            count++;
        }
        printf("\n derajatnya  : %d", count);
        derajatNode(graph, count, current);
        count = 0;

        pAdj = graph->array[current].head;
        while (pAdj) {
            int adjvertex = pAdj->val;
            if (!visited[adjvertex]) {
                visited[adjvertex] = true;
                queue[belakang++] = adjvertex;
            }
            pAdj = pAdj->link;
        }
    }
    free(visited);
    free(queue);
}

// Function buat sortir nodes pake bubble sort dr terbesar ke terkecil
void sortNodes(struct node **nodes, int size) {
    for (int i = 0; i < size - 1; i++) {
        for (int j = 0; j < size - i - 1; j++) {
            if (nodes[j]->derajat < nodes[j + 1]->derajat) {
                // Swap nodes
                struct node *temp = nodes[j];
                nodes[j] = nodes[j + 1];
                nodes[j + 1] = temp;
            }
        }
    }
}

// Function to print the nodes in sorted order
int* printSortedNodes(struct node **nodes, int size) {
    printf("\n Node dari derajat terbesar ke terkecil\n");
    int *listSortedVal=(int *)malloc(size * sizeof(int));

    for (int i = 0; i < size; i++) {
        printf("%d ", nodes[i]->val);
        listSortedVal[i]=nodes[i]->val;
    }
    printf("\n");
    return listSortedVal;
}

struct Graph* GraphColour(struct Graph* graph, int* listSortedVal) {
    int panjang = graph->vertex; // Number of vertices in the graph

    //Array to store the color assigned to each vertex
    int* vertexColors = (int*)malloc(panjang * sizeof(int));
    memset(vertexColors, 0, panjang * sizeof(int));

    for (int i = 0; i < panjang; i++) {
        int currentVertex = listSortedVal[i];
        struct node* currentNode = graph->array[currentVertex].head;

        bool usedColors[panjang];
        memset(usedColors, false, panjang * sizeof(bool));

        // Check colors of neighboring vertices
        while (currentNode != NULL) {
            int neighborVal = currentNode->val;
            if (vertexColors[neighborVal] != 0) {
                usedColors[vertexColors[neighborVal] - 1] = true;
            }
            currentNode = currentNode->link;
        }

        // Assign the smallest unused color to the current vertex
        int color = 1;
        while (usedColors[color - 1]) {
            color++;
        }
        vertexColors[currentVertex] = color;
    }

    // Print chromatic value for each vertex
    printf("Chromatic value for each vertex:\n");
    for (int i = 0; i < panjang; i++) {
        printf("Vertex %d: %d\n", i, vertexColors[i]);
    }

    free(vertexColors); // Free dynamically allocated memory

    // Return the colored graph (no modification needed)
    return graph;
}

int main() {
    int banyakVertex, banyakEdge, v1, v2;

    printf("Masukkan banyak vertex : ");
    scanf("%d", &banyakVertex);
    printf("Masukkan banyak edge : ");
    scanf("%d", &banyakEdge);

    struct Graph* graph = createGraph(banyakVertex);

    for (int i = 0; i < banyakEdge; i++) {
        scanf("%d %d", &v1, &v2);
        addEdge(graph, v1, v2);
        printf("\n");
    }
    BFS(graph, 0);

    // Create an array to store all nodes
    struct node** allNodes = malloc(banyakVertex * sizeof(struct node *));
    int count = 0;
    bool check[banyakVertex];
    for (int j = 0; j < banyakVertex; j++) {
        check[j] = false;
    }

    for (int i = 0; i < banyakVertex; i++) {
        struct node* temp = graph->array[i].head;

        while (temp != NULL) {
            int val = temp->val;

            if (check[val] == false) {
                allNodes[count++] = temp;
                check[val] = true;
            }
            temp = temp->link;
        }
    }

   
    sortNodes(allNodes, banyakVertex);
    printSortedNodes(allNodes, banyakVertex);

    int *sortedVals = printSortedNodes(allNodes, banyakVertex);

    // Perform graph coloring and print chromatic values
    graph = GraphColour(graph, sortedVals);

    // Free allocated memory
    free(allNodes);

    return 0;
}
