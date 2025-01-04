import java.util.Scanner;
//program penentu mahhasiswa lulus atau tidak
public class tgsPBO1 {
    public class Mahasiswa {
        String nama, nim;
        int nilaiIMK, nilaiBasdat, nilaiDAA, nilaiRPL, nilaiPBO, nilaiKDJK, nilaiTBO;
        double avg;

        
        public double itungavg() {
            int total = nilaiIMK + nilaiBasdat + nilaiDAA + nilaiRPL + nilaiPBO + nilaiKDJK + nilaiTBO;
            avg = total / 7;
            return avg;
        }

       
        void penentu(double avg) {
            if (avg >= 80 && avg <= 100) {
                System.out.println("Lulus dengan predikat: Sangat Baik");
            } else if (avg >= 70 && avg < 80) {
                System.out.println("Lulus dengan predikat: Baik");
            } else if (avg >= 60 && avg < 70) {
                System.out.println("Lulus dengan predikat: Cukup");
            } else if (avg >= 50 && avg < 60) {
                System.out.println("Lulus dengan predikat: Kurang");
            } else {
                System.out.println("Tidak Lulus");
            }
        }
    }

    public static void main(String[] args) {
        tgsPBO1 outer = new tgsPBO1();
        Mahasiswa m1 = outer.new Mahasiswa();

        Scanner Myscan = new Scanner(System.in);
        String ulangi = "y";

        while (ulangi.equals("y")) {
           
            System.out.printf("Masukkan nama: ");
            m1.nama = Myscan.nextLine();
            System.out.printf("Masukkan nim: ");
            m1.nim = Myscan.nextLine();
            System.out.printf("Masukkan nilai Interaksi Manusia Komputer: ");
            m1.nilaiIMK = Myscan.nextInt();
            System.out.printf("Masukkan nilai Basis Data: ");
            m1.nilaiBasdat = Myscan.nextInt();
            System.out.printf("Masukkan nilai DAA: ");
            m1.nilaiDAA = Myscan.nextInt();
            System.out.printf("Masukkan nilai RPL: ");
            m1.nilaiRPL = Myscan.nextInt();
            System.out.printf("Masukkan nilai PBO: ");
            m1.nilaiPBO = Myscan.nextInt();
            System.out.printf("Masukkan nilai KDJK: ");
            m1.nilaiKDJK = Myscan.nextInt();
            System.out.printf("Masukkan nilai TBO: ");
            m1.nilaiTBO = Myscan.nextInt();

           
            Myscan.nextLine();

        
            double myavg = m1.itungavg();
            System.out.println("rata-rata nilai kamu: " + myavg);

            
            m1.penentu(myavg);

            
            System.out.printf("Input nilai lagi? (y/n): ");
            ulangi = Myscan.nextLine();
        }

        Myscan.close(); 
    }
}
