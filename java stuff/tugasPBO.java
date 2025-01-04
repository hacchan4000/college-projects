import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge{ //class ato objek bernama Edge ini tu kaya template dlm sebuah edge ada ap aj
    int node; // atribut" dr objek Edge
    int bobot;

    public Edge(int bobot,int node){
        this.node = node;
        this.bobot = bobot;
    }
}

public class tugasPBO {

    static void printJarak(int[] jarak) {
        System.out.println("jarak terpendek dari source menggunakan Dijkstra:");
        for (int i = 0; i < jarak.length; i++) {
            System.out.println("Node " + i + " : " + jarak[i]);
        }
    }

    static void MinHeap(int vertex,ArrayList<ArrayList<ArrayList<Integer>>> adj,int asal){ //function minimum heap utk jalanin algo dijkstra
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.bobot - y.bobot); // priority queue tu pokonya struktur data queue yg paling kecil selalu diatas ato pertama
        int[] jarak = new int[vertex]; // array jarak buat nyimpen jarak dr semua vertex dr vertex src 

        for (int i = 0; i < vertex; i++) {// inisialisasi semwa nilai di array jarak jd infinity ato int yg besar sbg tanda node ke i blm dicapai
            jarak[i] = (int)(1e9);
        }

        jarak[asal] = 0;
        pq.add(new Edge(0, asal));// inisialisasi pq

        while (!pq.isEmpty()) { // selama pq ga kosong loop bkl trs jalan
            Edge top = pq.poll();// ngeluarin elemen paling atas / yg paling kecil
            int node = top.node;
            int dist = top.bobot;
            for (ArrayList<Integer> edge : adj.get(node)) {
                int adjNode = edge.get(0);
                int edgeWeight = edge.get(1);
                if (dist + edgeWeight < jarak[adjNode]) {
                    jarak[adjNode] =dist + edgeWeight;
                    pq.add(new Edge(jarak[adjNode], adjNode));
                }
            }
        }
        printJarak(jarak);
    }


    @SuppressWarnings("resource")
    public static void main(String[] args) { //main function
        System.out.println("PROGRAM SHORTEST PATH METODE DIJKSTRA\n");

        System.out.println("Masukkan banyak vertex dalam graph");
        Scanner MyScanner = new Scanner(System.in); // input user banyak vertex yg membangun sebuah graph
        int vertex = MyScanner.nextInt();

        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>(); // array untuk nyimpen seluruh edge 

        for (int i = 0; i < vertex; i++) {
            adj.add(new ArrayList<>());// buat bikin array lg dlm array adj mcm ky matriks, banya sub array nya sesuai sm vertex
        }

        while (true) { //loop buat nambahin bobot sm nentuin vertex dest n src dr sebuah edge
            //user bakal input satu persatu sesuai dgn graph yg ingin dibangun
            System.out.println("Masukkan Vertex Source :");
            int source = MyScanner.nextInt();
            System.out.println("Masukkan Vertex Destination :");
            int dest = MyScanner.nextInt();
            System.out.println("Masukkan Bobot Edge :");
            int bobot = MyScanner.nextInt();
 
            adj.get(source).add(new ArrayList<>(List.of(dest, bobot)));// masukkan nilai yg diinput ke dlm array adj

            System.out.println("tambah edge lagi ?(y/n) :");
            String pilih = MyScanner.next();

            if (!pilih.equals("y")) { // kalo user input string bukan y maka loop slese
                break;
            }
        }
        
        MinHeap(vertex,adj,0);// panggil function minimum heap
    }
    
}