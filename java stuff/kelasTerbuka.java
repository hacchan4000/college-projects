import java.util.ArrayList;

import javax.print.DocFlavor.STRING;


//interface

public class kelasTerbuka { //public class jd bsa dipake di package manapun as long di import
    interface Terbang {
        void terbang(); // Abstract method
    }
    
    interface Berenang {
        void berenang(); // Abstract method
    }
    
    class Bebek implements Terbang, Berenang {
        public void terbang() {
            System.out.println("Bebek terbang");
        }
    
        public void berenang() {
            System.out.println("Bebek berenang");
        }
    }
   
    

    public static void main(String[] args) {
        kelasTerbuka outer = new kelasTerbuka();

        Bebek b1 = outer.new Bebek();
        b1.berenang();
        
    }
}