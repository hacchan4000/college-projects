//program graph dengan representasi adjacency list menggunakan linked list
#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

struct node
{
    int dest;
    struct node* next;
};

//mulai g paham
struct node* addNewNode(int data){
    struct node* newNode =(struct node*)malloc(sizeof(struct node));
    newNode->dest=data;
    newNode->next=NULL;
    return newNode;
};

struct adjList
{
    struct node* head;
};

struct Graph
{
    int vertex; //banyak vertex adj list
    struct adjList* array;
};


// Function to create a graph with V vertices
struct Graph* createGraph(int V) {
    struct Graph* graphku = (struct Graph*)malloc(sizeof(struct Graph));
    graphku->vertex = V;

    // Create an array of adjacency lists. Size of array will be V
    graphku->array = (struct adjList*)malloc(V * sizeof(struct adjList));

    // Initialize each adjacency list as empty by making head as NULL
    for (int i = 0; i < V; ++i)
        graphku->array[i].head = NULL;

    return graphku;
}


void addEdge(struct Graph* graph,int v1,int v2){
    struct node* NodeBaru =addNewNode(v2);
    NodeBaru->next=graph->array[v1].head;
    graph->array[v1].head=NodeBaru;

    NodeBaru=addNewNode(v1);
    NodeBaru->next=graph->array[v2].head;
    graph->array[v2].head=NodeBaru;
};

void BFS(struct Graph* graph, int StartPoint) {
    int* queue = (int*)malloc(graph->vertex * sizeof(int));
    int depan = 0, belakang = 0;

    bool* visited = (bool*)malloc(graph->vertex * sizeof(bool));
    for (int i = 0; i < graph->vertex; i++)
        visited[i] = false;

    visited[StartPoint] = true;
    queue[belakang++] = StartPoint;

    while (depan != belakang) {
        int current = queue[depan++];
        int derajat=0;
        printf("\n Adjacency list of vertex %d :\n head", current);
        printf("\n");

        struct node* pAdj = graph->array[current].head;
        while (pAdj) {
            printf(" -> %d", pAdj->dest);
            pAdj = pAdj->next;
        }

        pAdj = graph->array[current].head;
        while (pAdj) {
            int adjvertex = pAdj->dest;
            if (!visited[adjvertex]) {
                visited[adjvertex] = true;
                queue[belakang++] = adjvertex;
            }
            pAdj = pAdj->next;
            
        }
    }

    free(visited);
    free(queue);
};

int main(){

    int banyakVertex,banyakEdge,v1,v2;
    int start;
  
    
    printf("masukkan banyak vertex : ");
    scanf("%d",&banyakVertex);
    printf("masukkan banyak edge : ");
    scanf("%d",&banyakEdge);


    

    struct Graph* graph = createGraph(banyakVertex);

    for (int i = 0; i < banyakEdge; i++)
    {
        scanf("%d %d",&v1,&v2);
        addEdge(graph,v1,v2);
        printf("\n");
    }
    printf("masukkan starting node : ");
    scanf("%d",&start);

    BFS(graph,start);
    
    return 0;
}