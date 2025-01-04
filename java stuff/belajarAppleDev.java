public class belajarAppleDev {
    class mahasiswa{
        String nama;
        String nim;
        String jurusan;
        int IPK;

        mahasiswa(String inNama,String inNim){
            nama = inNama;
            nim = inNim;
        }
    }
    public static void main(String[] args) {
        mahasiswa mhs1 = new mahasiswa("aditya", "2308561092");
        System.out.println(mhs1.nama);
    }
}
