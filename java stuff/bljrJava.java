import java.time.LocalDate;

public class bljrJava {
    public static void main(String[] args) {
       mhs mahasiswa = new mhs();

       mahasiswa.nama = "aditya chandra";
       mahasiswa.ultah = LocalDate.parse("2004-11-20");

       System.out.printf("%s lahir pada %s. umurnya skrg adalah %d tahun",mahasiswa.nama,mahasiswa.ultah.toString(),mahasiswa.umur());
    }
}
