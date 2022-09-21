package Sub;

public class Sub {
    public static int[] getSlice(int[] array, int startIndex, int endIndex) {   
        // Get the slice of the Array   
        int[] slicedArray = new int[(endIndex - startIndex) - 1];   
        //copying array elements from the original array to the newly created sliced array  
        for (int i = 0; i < slicedArray.length; i++)   
        {   
            slicedArray[i] = array[startIndex + i];   
        }   
        //returns the slice of an array  
        return slicedArray;   
    }   
}