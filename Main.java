package knn;
//@author Rudy Rachman

//Program ini belum ada input NILAI K dan NILAI DATA TEST

//sumber data dari Breast Cancer Wisconsin (Original) Data Set
//https://archive.ics.uci.edu/ml/datasets/Breast+Cancer+Wisconsin+%28Original%29
//menggunakan rumus manhattan distance (SUM)|Xik - Xjk|
public class Main {
    public static int k=15;
    //data ganjil cocok dengan k yang nilai nya genap,
    //sedangkan data genap cocok dengan nilai k yang ganjil.
    public static int attribute=9;//attribute di dataset
    
    public static int manhattanDistance(int x1, int x2){
        int MD=x1-x2;
        if(MD<0) return MD*=-1;
        return MD;
    }
    public static void sorting(int[][] data, int[] hasil, int[] klas){
        int temp[]=new int[attribute];
        int bntr,sementara;
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data.length;j++){
                if(hasil[i]<hasil[j]){
                    for(int k=0;k<temp.length;k++) temp[k]=data[i][k];
                    sementara=hasil[i];
                    bntr=klas[i];
                    
                    for(int k=0;k<temp.length;k++) data[i][k]=data[j][k];
                    hasil[i]=hasil[j];
                    klas[i]=klas[j];
                    
                    for(int k=0;k<temp.length;k++) data[j][k]=temp[k];
                    hasil[j]=sementara;
                    klas[j]=bntr;
                }
            }
        }
    }
    
    public static int jawabanKlas(int[] klas){
        int hasil;
        int baik=0,jelek=0;
        for(int i=0;i<k;i++){
            if(klas[i]==2) baik++;
            else jelek++;
        }
        if(baik>jelek) hasil=2;
        else hasil=4;
        return hasil;
    }
    
    public static void main(String args[]){
        DataSet data = new DataSet();//manggil kelas dataset (Dataset buat dan cari sendiri..)
        
        int x1[][]=data.dataX1();
        int klas[]=data.klasifikasi();
        int xk[]={1,2,3,4,5,6,7,8,9};//kunci
        int kerasu;
        String kualitas;
        int hasilDistance[][] = new int[x1.length][attribute];
        int hasil[]=new int[x1.length];
        int sum=0;
        
        for(int i=0;i<x1.length;i++){
            for(int j=0;j<attribute;j++){
                hasilDistance[i][j]=manhattanDistance(x1[i][j],xk[j]);
            }
        }
        int index=0;
        for(int i[]:hasilDistance){
            for(int j:i) sum+=j;
            hasil[index]=sum;
            sum=0;
            index++;
        }
        index=0;
        for(int i[]:x1){
            for(int j:i) System.out.print(j+" \t");
            System.out.print(" \t"+hasil[index]+" \t "+klas[index]+'\n');
            index++;
        }
        sorting(x1,hasil,klas);
        System.out.println("\n\nAfter Sorting");
        index=0;
        for(int i[]:x1){
            for(int j:i) System.out.print(j+" \t");
            System.out.print(" \t"+hasil[index]+" \t "+klas[index]+'\n');
            index++;
        }
        kerasu=jawabanKlas(klas);
        if (kerasu==2) kualitas="Benign";
        else kualitas="Malignant";
        index=0;
        System.out.println("\n\nData kunci:");
        for(int i:xk){
            if(index==xk.length-1) System.out.print("dan "+i);
            else System.out.print(i+", ");
            index++;
        }
        System.out.println("\nKlasifikasi nya adalah "+kualitas);
    }
}
