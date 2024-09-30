package rocks.somesandwich.datastructures.dynamicarray;

import java.util.Arrays;
import java.util.Iterator;

public class IntArray implements Iterable<Integer> {

    private static final int DEFAULT_CAP = 1 << 3;

    public int[] arr;
    public int len = 0;
    public int capacity = 0;

    public IntArray() {
        this(DEFAULT_CAP);
    }

    public IntArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }

        this.capacity = capacity;
        arr = new int[capacity];
    }

    public IntArray(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Illegal Array: " + array);
        }

        arr = Arrays.copyOf(array, array.length);
        capacity = len = array.length;
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public int get(int index) {
        return arr[index];
    }

    public void set(int index, int elem) {
        arr[index] = elem;
    }

    public void add(int elem) {
        if (len + 1 > capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2 + 1;
            arr = Arrays.copyOf(arr, capacity);
        }

        arr[len++] = elem;
    }

    public void removeAt(int rm_index) {
        System.arraycopy(arr, rm_index + 1, arr, rm_index, len - rm_index - 1);
        --len;
        --capacity;
    }

    public boolean remove(int elem) {
        for (int i = 0; i < len; ++i) {
            if (arr[i] == elem) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public void reverse() {
        for (int i = 0; i < len / 2; ++i) {
            int tmp = arr[i];
            arr[i] = arr[len - i - 1];
            arr[len - i - 1] = tmp;
        }
    }

    public int binarySearch(int key) {
        int index = Arrays.binarySearch(arr, 0, len, key);
        return index;
    }

    public void sort() {
        Arrays.sort(arr, 0, len);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public Integer next() {
                return arr[index++];
            }
        };
    }

    public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; ++i) sb.append(arr[i] + ", ");
            return sb.append(arr[len - 1] + "]").toString();
        }
    }

    public static void main(String[] args) {

        IntArray ar = new IntArray(50);
        ar.add(3);
        ar.add(7);
        ar.add(6);
        ar.add(-2);

        ar.sort();

        for (int i = 0; i < ar.size(); i++) System.out.println(ar.get(i));

        System.out.println(ar);
    }
}
