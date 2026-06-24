import java.util.*;

public class FoodChainCO5 {

    // Heap Sort
    static void heapify(int arr[], int n, int i) {

        int largest = i;
        int l = 2*i+1;
        int r = 2*i+2;

        if(l<n && arr[l]>arr[largest])
            largest=l;

        if(r<n && arr[r]>arr[largest])
            largest=r;

        if(largest!=i){
            int temp=arr[i];
            arr[i]=arr[largest];
            arr[largest]=temp;

            heapify(arr,n,largest);
        }
    }

    static void heapSort(int arr[]) {

        int n=arr.length;

        for(int i=n/2-1;i>=0;i--)
            heapify(arr,n,i);

        for(int i=n-1;i>0;i--){

            int temp=arr[0];
            arr[0]=arr[i];
            arr[i]=temp;

            heapify(arr,i,0);
        }
    }

    // Counting Sort
    static void countingSort(int arr[]) {

        int max = Arrays.stream(arr).max().getAsInt();

        int count[] = new int[max+1];

        for(int num:arr)
            count[num]++;

        int index=0;

        for(int i=0;i<count.length;i++){
            while(count[i]-- >0){
                arr[index++]=i;
            }
        }
    }

    // Radix Sort
    static void countSort(int arr[], int exp){

        int n=arr.length;
        int output[]=new int[n];
        int count[]=new int[10];

        for(int i=0;i<n;i++)
            count[(arr[i]/exp)%10]++;

        for(int i=1;i<10;i++)
            count[i]+=count[i-1];

        for(int i=n-1;i>=0;i--){
            output[count[(arr[i]/exp)%10]-1]=arr[i];
            count[(arr[i]/exp)%10]--;
        }

        System.arraycopy(output,0,arr,0,n);
    }

    static void radixSort(int arr[]){

        int max=Arrays.stream(arr).max().getAsInt();

        for(int exp=1;max/exp>0;exp*=10)
            countSort(arr,exp);
    }

    public static void main(String[] args) {

        int priorities[] = {9,5,7,2,8,3};

        heapSort(priorities);

        System.out.println("Heap Sort:");
        System.out.println(Arrays.toString(priorities));

        int ratings[] = {5,3,4,5,2,1,5,4};

        countingSort(ratings);

        System.out.println("\nCounting Sort:");
        System.out.println(Arrays.toString(ratings));

        int orderIds[] = {10234,56890,23145,99876,12345};

        radixSort(orderIds);

        System.out.println("\nRadix Sort:");
        System.out.println(Arrays.toString(orderIds));
    }
}