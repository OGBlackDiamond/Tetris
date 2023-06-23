package TetJava.Sub;

/** A class that contains methods to support the main program */
public class Sub {

    /** 
     * Gets a slice of an array and returns it

     * @param array - the array to be sliced
     * @param startIndex - the start point of the slice
     * @param endIndex - the end point fo the slice

     * @return slicedArray - the new array
     */
    public static int[] getSlice(int[] array, int startIndex, int endIndex) {
        // get the slice of the Array
        int[] slicedArray = new int[(endIndex - startIndex) - 1];
        // copying array elements from the original array to the newly created sliced array
        for (int i = 0; i < slicedArray.length; i++)
        {
            slicedArray[i] = array[startIndex + i];
        }
        // returns the slice of an array
        return slicedArray;
    }
}