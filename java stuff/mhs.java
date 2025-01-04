import java.time.LocalDate;
import java.time.Period;

public class mhs {
    public String nama;
    public LocalDate ultah;
    
    public int umur() {
        Period umur = Period.between(this.ultah, LocalDate.now());
        return umur.getYears();
    }
}
