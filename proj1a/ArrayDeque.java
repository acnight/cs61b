public class ArrayDeque<T> {
    private T[] theQueue;
    private int size;
    public ArrayDeque() {
        theQueue = (T[]) new Object[8];
        size = 0;
        ratio = size * 100/(theQueue.length);
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
        T[] a = (T[]) new Object[size * 5];
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
        T[] a = (T[]) new Object[size * 5];
        System.arraycopy(theQueue, 0, a, 0, size);
    }
    public void addLast(T item) {
        if (size == theQueue.length) {
            lastResize();
        }
        theQueue[size] = item;
        size += 1;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++){
            System.out.print(theQueue[i] + " ");
        }
    }
    private void halveSize() {
        T[] a = (T[]) new Object[theQueue.length/2];
        System.arraycopy(theQueue, 0, a, 0, size);
        theQueue = a;
    }
    public T removeFirst() {
        T b = theQueue[0];
        T[] a = (T[]) new Object[theQueue.length];
        System.arraycopy(theQueue, 1, a, 0, size - 1);
        theQueue = a;
        size -= 1;
        if (ratio < 25) {
            halveSize();
        }
        return b;
    }
    public T removeLast() {
        T b = theQueue[size - 1];
        theQueue[size - 1] = null;
        size -= 1;
        if (ratio < 25) {
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
//        ArrayDeque.addLast(0);
//        ArrayDeque.addFirst(3);
//        ArrayDeque.addFirst(4);
//        ArrayDeque.get(2);      //==> 0
//        ArrayDeque.removeLast();      //==> 0
//        ArrayDeque.get(0);      //==> 4
//        ArrayDeque.removeLast();      //==> 3
//        ArrayDeque.addLast(9);
//        ArrayDeque.removeLast();      //==> 9
//        ArrayDeque.addFirst(11);
//        ArrayDeque.removeFirst();     //==> 11
//        System.out.println(ArrayDeque.removeFirst());     //==> 3
//    }
}
