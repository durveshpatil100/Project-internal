package sliding_window;

public class MaxSumOFSubArray {

    private static double maxSumSubarray(int arr[], int k){

        double maxSum =0;

        for (int i=0; i<k; i++){
           maxSum += arr[i];
        }

        double window = maxSum;

        for(int i =k; i< arr.length; i++){
            window += arr[i] -arr[i-k];

            maxSum = Math.max(maxSum, window);
        }

        return maxSum;
    }
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Maximum sum of subarray of size " + k + " is " + maxSumSubarray(arr, k)); // Output: 9
    }


}

