public class floyd {
    public void warshal(int [][] adjM){
        int pnjg = adjM.length;
        for (int i = 0; i < pnjg; i++) {
            for (int j = 0; j < pnjg; j++) {
                if (adjM[i][j]  == -1) {
                    adjM[i][j] = (int)(1e9);
                }
                if (i == j) {
                    adjM[i][j] = 0;
                }
            }
        }

        for (int k = 0; k < pnjg; k++) {
            for (int l = 0; l < pnjg; l++) {
                for (int m = 0; m < pnjg; m++) {
                    adjM[l][m] = Math.min(adjM[l][m], adjM[l][k] + adjM[k][m]);
                }
            }
        }

       for (int f = 0; f < pnjg; f++) {
        for (int s = 0; s < pnjg; s++) {
            if (adjM[f][s] == (int)(1e9)) {
                adjM[f][s] = -1;
            }
        }
        
       } 

    }
    public static void main(String[] args) {
        //adjacency matrix
        int[][] adjM = {
            {0, 2, -1, -1},
            {1, 0, 3, -1},
            {-1, -1, 0, -1},
            {3, 5, -1, 0}
        };
        floyd fw = new floyd();
        fw.warshal(adjM);

        System.out.println(" matrix:");
        for (int i = 0; i < adjM.length; i++) {
            for (int j = 0; j < adjM[i].length; j++) {
                if (adjM[i][j] == -1) {
                    System.out.print("INF ");
                } else {
                    System.out.print(adjM[i][j] + " ");
                }
            }
            System.out.println();
        }
        
    }
}
