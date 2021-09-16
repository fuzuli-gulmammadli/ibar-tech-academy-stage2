public class Task1 {

    public static void main(String[] args) {
        int[] cars = {1,2,15,3,7};
        System.out.println(getRoofSize(cars,3));
    }

    public static int getRoofSize(int[] cars, int carsToCover) {
        int n = cars.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (cars[j] > cars[j+1])
                {
                    int temp = cars[j];
                    cars[j] = cars[j+1];
                    cars[j+1] = temp;
                }

        int min = Integer.MAX_VALUE;
        for(int i=0; i<n-carsToCover; i++){
            int roofSize = cars[i+carsToCover-1] - cars[i] + 1;
            if(roofSize < min){
                min = roofSize;
            }
        }
        return min;
    }
}
