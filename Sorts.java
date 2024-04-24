
//nuevo
import java.util.Arrays;

public class Sorts {
    public static <T extends Comparable<? super T>> void heapSort(T[] array) {
        int length = array.length;

        //change the array format to heap
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(array, length, i);
        }

        //sort the heap (previous array)
        for (int j = length - 1; j >= 0; j--) {
            T temp = array[ 0 ];
            array[ 0 ] = array[ j ];
            array[ j ] = temp;
            heapify(array, j, 0);
        }
    }

    private static <T extends Comparable<? super T>> void heapify(T[] array, int heapSize, int root) {
        int bigElement = root;
        int leftElement = 2 * root + 1;
        int rightElement = 2 * root + 2;
        // If left child is larger than root
        if (leftElement < heapSize && array[ leftElement ].compareTo(array[ bigElement ]) > 0) {
            bigElement = leftElement;
        }
        //If right child is larger than the current value of bigElement
        if (rightElement < heapSize && array[ rightElement ].compareTo(array[ bigElement ]) > 0) {
            bigElement = rightElement;
        }

        // If the biggestElement is not root, change/swap it
        if (bigElement != root) {
            T element = array[ root ];
            array[ root ] = array[bigElement];
            array[bigElement] = element;
            heapify(array, heapSize, bigElement);
        }
    }
}