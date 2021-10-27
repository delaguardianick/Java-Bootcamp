public class AssignSummativeSums {
    
    // Loop through and add each elem to count
    public static int sumArray(int[] arr){
        int total = 0;

        for (int elem : arr){
            total += elem;
        }
        return total;
    }
    
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 90, -33, -55, 67, -16, 28, -55, 15};
        int[] arr2 = new int[]{ 999, -60, -77, 14, 160, 301};
        int[] arr3 = new int[]{ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 
            140, 150, 160, 170, 180, 190, 200, -99 };

        System.out.printf("#1 Array Sum: %d \n", sumArray(arr1));
        System.out.printf("#2 Array Sum: %d \n", sumArray(arr2));
        System.out.printf("#3 Array Sum: %d", sumArray(arr3));
    }
}
