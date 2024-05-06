//program struktur data pake ring buffer
#include<stdio.h>
#include<stdlib.h>
#include<string.h> 

struct mahasiswa {
    char nama[100];
    int Nim;
    int UKT;
    int nominal;
};

struct queue {
    int banyakAntri;
    int depan, belakang,count;
    struct mahasiswa** array; // Corrected data type
};

struct queue* ringBuffer(int a) {
    struct queue* qbaru = (struct queue*)malloc(sizeof(struct queue));
    qbaru->banyakAntri = a;
    qbaru->depan = qbaru->belakang = 0;
    qbaru->count = 0;
    qbaru->array = (struct mahasiswa**)malloc(a * sizeof(struct mahasiswa*)); // Allocate memory for array of struct mahasiswa pointers
    return qbaru;
}

void tambahQueue(struct queue* q, struct mahasiswa* mhs) {
    if ((q->belakang + 1) % q->banyakAntri == q->depan) { // Check if queue is full
        printf("queue penuh\n");
        return;
    }
    if (q->depan == -1) { // If the queue is empty
        q->depan = 0; // Set front to 0
    }
    q->array[q->belakang] = mhs;
    q->belakang = (q->belakang + 1) % q->banyakAntri;
    q->count++;
    
}

struct mahasiswa* tambahMhs() {
    struct mahasiswa* mhs = (struct mahasiswa*)malloc(sizeof(struct mahasiswa));
    printf("nama: ");
    scanf("%s", mhs->nama);
    printf("nim: ");
    scanf("%d", &(mhs->Nim));
    printf("ukt: ");
    scanf("%d", &(mhs->UKT));
    printf("nominal : ");
    scanf("%d", &(mhs->nominal));
    return mhs;
}

void prosesBayar(struct queue* q) {
    if (q->depan == -1) {
        printf("queue kosong\n");
        return;
    }
    struct mahasiswa* mhs = q->array[q->depan];
    int kembalian, Nbayar;
    printf("Nama : %s",mhs->nama);
    printf("\n");
    printf("Nominal : %d",mhs->nominal);
    printf("\n");
    printf("Nominal Bayar user : ");
    scanf("%d", &Nbayar);
    kembalian = Nbayar - mhs->nominal;
    printf("kembalian %d", kembalian);
    printf("\n");

    q->depan = (q->depan + 1) % q->banyakAntri; 
    q->count--;

    // If queue becomes empty, reset to -1
    if (q->depan == q->belakang) {
        q->depan = q->belakang = -1;
    }
}

void lihat(struct queue* q) {
    if (q->count == 0)
    {
        printf("queue kosong \n");
    }
    printf("Antrian mahasiswa  :\n");
    int indek=q->depan;
    for (int i = 0; i < q->count; i++)
    {
        struct mahasiswa* mhs = q->array[indek];
        printf("Nama: %s, NIM: %d, UKT: %d, Nominal: %d\n", mhs->nama, mhs->Nim, mhs->UKT, mhs->nominal);
        indek = (indek + 1) % q->banyakAntri; // Circular increment
    }
    
    
}

int main() {
    printf("PROGRAM banyakAntri PEMBAYARAN UKT \n");
    int pilihan, banyakAntriMax;
    printf("input maks banyakAntri :");
    scanf("%d", &banyakAntriMax);
    printf("\n");
    struct queue* q = ringBuffer(banyakAntriMax);

    while (pilihan != 6) {
        printf("Program pembayaran UKT\n");
        printf("menu \n");
        printf("1. Tambah queue\n");
        printf("2. Proses Pembayaran\n");
        printf("3. Lihat queue\n");
        printf("4. Keluar \n");
        printf("masukkan pilihan anda : ");
        scanf("%d", &pilihan);

        switch (pilihan) {
            case 1:
                struct mahasiswa* mhs;
                mhs = tambahMhs();
                tambahQueue(q, mhs);

                break;

            case 2:
                prosesBayar(q);
                break;

            case 3:
                lihat(q);
                break;

            case 4:
                char milih[10];
                printf("\n apakah anda yakin akan keluar(Y/N) :");
                scanf("%s", milih);
                if (strcmp(milih, "Y") == 0) {
                    pilihan = 6; // Change pilihan value to break the loop
                }
                break;

            default:
                break;
        }
    }
    printf("\n suksma \n");
    free(q->array); // Free the dynamically allocated memory
    free(q);

    return 0;
}
