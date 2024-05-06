#include <stdio.h>
#include <stdlib.h>

typedef struct mahasiswa {
    char nama[20];
    char nim[20];
    int nilaiUts;
    int nilaiUas;
    float avg;
    
}mahasiswa;
int main() {
    // Write C code here
    int banyakmhs;
    printf("ketik jumlah mahasiswa :");
    scanf("%d",&banyakmhs);
    
    struct mahasiswa kelasA[30];
    int avg;
    for(int i =0;i<banyakmhs;i++){
        printf("masukkan nama :\n");
        scanf("%s",kelasA[i].nama);
        printf("masukkan nilai UTS :\n");
        scanf("%d",&kelasA[i].nilaiUts);
        printf("masukkan nilai UAS:\n");
        scanf("%d",&kelasA[i].nilaiUas);
        
        printf("rata-rata:\n");
        float rata2 = (kelasA[i].nilaiUas + kelasA[i].nilaiUts)/2;
        printf("%f",rata2);
        printf("\n");
        printf("total :");
        int totalnya =kelasA[i].nilaiUas + kelasA[i].nilaiUts;
        printf("%d",totalnya);
        printf("\n");
        
        if(rata2<=60){
            printf("Tidak lulus\n");
        }else {
            printf("lulus");
        }
        
    }
}