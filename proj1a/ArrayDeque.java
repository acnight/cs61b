public class ArrayDeque<T> {
    private T[] theQueue;
    private int size;
    public ArrayDeque() {
        theQueue = (T[]) new Object[8];
        size = 0;
        ratio = size * 100 / (theQueue.length);
    }
    private int ratio;

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    public int size() {
        return size;
    }
    private int minusOne(int index, int mode) { //return the before point' number
        if (index == mode - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private void firstResize() {
        T[] a = (T[]) new Object[size * 4];
        System.arraycopy(theQueue, 0, a, 1, size);
        theQueue = a;
    }

    public void addFirst(T item) {
        if (size == theQueue.length) {
            firstResize();
        } else {
            T[] a = (T[]) new Object[theQueue.length];
            System.arraycopy(theQueue, 0, a, 1, size);
            theQueue = a;
        }
        theQueue[0] = item;
        size += 1;
    }
    private void lastResize() {
        T[] a = (T[]) new Object[size * 4];
        System.arraycopy(theQueue, 0, a, 0, size);
        theQueue = a;
    }
    public void addLast(T item) {
        if (size == theQueue.length) {
            lastResize();
        }
        theQueue[size] = item;
        size += 1;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(theQueue[i] + " ");
        }
    }
    private void halveSize() {
        T[] a = (T[]) new Object[theQueue.length / 2];
        System.arraycopy(theQueue, 0, a, 0, size);
        theQueue = a;
    }
    public T removeFirst() {
        T b = theQueue[0];
        T[] a = (T[]) new Object[theQueue.length];
        System.arraycopy(theQueue, 1, a, 0, size - 1);
        theQueue = a;
        size -= 1;
        ratio = size * 100 / (theQueue.length);
        if (ratio < 25 && ratio != 0) {
            halveSize();
        }
        return b;
    }
    public T removeLast() {
        T b = theQueue[size - 1];
        theQueue[size - 1] = null;
        size -= 1;
        ratio = size * 100 / (theQueue.length);
        if (ratio < 25 && ratio != 0) {
            halveSize();
        }
        return b;
    }
    public T get(int index) {
        return theQueue[index];
    }
//    /*--------------Test!!!!!--------------*/
//    public static void main(String[] args) {
//        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
//        ArrayDeque.addFirst(0);
//        ArrayDeque.get(0);      //==> 0
//        ArrayDeque.removeLast();     // ==> 0
//        ArrayDeque.addLast(3);
//        ArrayDeque.removeFirst();     //==> 3
//        ArrayDeque.addLast(5);
//        ArrayDeque.get(0);      //==> 5
//        ArrayDeque.removeFirst();     //==> 5
//        ArrayDeque.addLast(8);
//        ArrayDeque.removeFirst();     //==> 8
//        ArrayDeque.addLast(10);
//    }
}
