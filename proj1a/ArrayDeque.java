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
            return size == 0;
    }

    public int size() {
        return size;
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
        if (size == 0) {
            return null;
        } else {
            T b = theQueue[0];
            T[] a = (T[]) new Object[theQueue.length];
            System.arraycopy(theQueue, 1, a, 0, size - 1);
            theQueue = a;
            size -= 1;
            ratio = size * 100 / (theQueue.length);
            if (size / (double)theQueue.length < 0.25 && ratio >= 16) {
                halveSize();
            }
            return b;
        }
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T b = theQueue[size - 1];
            theQueue[size - 1] = null;
            size -= 1;
            ratio = size * 100 / (theQueue.length);
            if (size / (double)theQueue.length < 0.25 && ratio >= 16) {
                halveSize();
            }
            return b;
        }
    }

    public T get(int index) {
        return theQueue[index];
    }
//    /*--------------Test!!!!!--------------*/
//    public static void main(String[] args) {
//        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
//        ArrayDeque.addFirst(0);
//        ArrayDeque.addLast(8);
//        ArrayDeque.addLast(8);
//        ArrayDeque.addLast(8);
//        ArrayDeque.addLast(8);
//        ArrayDeque.addLast(8);
//        ArrayDeque.addLast(8);
//        ArrayDeque.addLast(8);
//        ArrayDeque.addLast(3);
//        ArrayDeque.removeFirst();
//        ArrayDeque.addLast(5);
//        ArrayDeque.removeFirst();
//        ArrayDeque.removeFirst();
//        ArrayDeque.removeFirst();
//        ArrayDeque.removeFirst();
//    }
}
