package sliding_window;

public class MinimumSizeSubArray {

    public static void main(String[] args) {
        int arr[] = {2,3,1,2,4,3};
        int s = 7;

        smallestSubarrayWithGivenSum(arr,s);
    }

    public static int smallestSubarrayWithGivenSum(int[] arr, int s) {
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;
        int windowStart = 0;

        // First, we will add-up elements from the beginning of the array
        // until their sum becomes greater than or equal to S.
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // Add the next element
            windowSum += arr[windowEnd];

            // Shrink the window as small as possible until windowSum is smaller than s
            while (windowSum >= s) {
                // Check if the current window length is the smallest so far, and if so, remember its length.
                minLength = Math.min(minLength, windowEnd - windowStart + 1);

                // Subtract the first element of the window from the running sum to shrink the sliding window.
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }

        // If minLength is still Integer.MAX_VALUE, it means no valid subarray was found
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
