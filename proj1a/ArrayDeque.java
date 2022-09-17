public class ArrayDeque<T> {
    private T[] theQueue;
    private int size;
    private int firstPoint;
    //private int lastPoint ; //lastpoint = firstPoint + size -1
    public ArrayDeque() {
        theQueue = (T[]) new Object[8];
        size = 0;
        firstPoint = 0;
    }
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

    public void addFirst(T item) {
        if (size < 8) {
            if (firstPoint == 0) {
                if (theQueue[firstPoint] == null) {
                    theQueue[0] = item;
                    size += 1;
                } else {
                    firstPoint = 7;
                    theQueue[firstPoint] = item;
                    size += 1;
                }
            } else if (firstPoint > 0) {
                firstPoint -= 1;
                theQueue[firstPoint] = item;
                size += 1;
            }
        } else {
            if (firstPoint == 0) {
                T[] a = (T[]) new Object[size + 1];
                System.arraycopy(theQueue, 0, a, 0, size);
                firstPoint = size;
                theQueue = a;
                theQueue[firstPoint] = item;
                size += 1;
            }
            T[] a = (T[]) new Object[size + 1];
            System.arraycopy(theQueue, 0, a, 0, firstPoint);
            System.arraycopy(theQueue, firstPoint, a, firstPoint + 1, size - firstPoint);
            theQueue = a;
            theQueue[firstPoint] = item;
            size += 1;
        }
    }
    public void addLast(T item) {
        if (size < 8) {
            if (firstPoint == 0) {
                if (theQueue[firstPoint] == null) {
                    theQueue[0] = item;
                    size += 1;
                } else {
                    theQueue[size] = item;
                    size += 1;
                }
            } else if (firstPoint > 0) {
                theQueue[firstPoint - 8 + size] = item;
                size += 1;
            }
        } else {
            if (firstPoint == 0) {
                T[] a = (T[]) new Object[size + 1];
                System.arraycopy(theQueue, 0, a, 0, size);
                theQueue = a;
                theQueue[size] = item;
                size += 1;
            } else {
                T[] a = (T[]) new Object[size + 1];
                System.arraycopy(theQueue, 0, a, 0, firstPoint);
                System.arraycopy(theQueue, firstPoint, a, firstPoint + 1, size - firstPoint);
                theQueue = a;
                theQueue[firstPoint] = item;
                firstPoint += 1;
                size += 1;
            }
        }

    }
    public void printDeque() {
        if (firstPoint == 0) {
            if (theQueue[firstPoint] == null) {
                System.out.print(theQueue[firstPoint]);
            } else {
                for (int i = 0; i < size; i++) {
                    System.out.print(theQueue[i] + " ");
                }
            }
        } else {
            if (size > 8) {
                for (int i = firstPoint; i < size; i++) {
                    System.out.print(theQueue[i] + " ");
                }
                for (int i = 0; i < firstPoint; i++) {
                    System.out.print(theQueue[i] + " ");
                }
            } else {
                for (int i = firstPoint; i < 8; i++) {
                    System.out.print(theQueue[i] + " ");
                }
                for (int i = 0; i < firstPoint - 8 + size; i++) {
                    System.out.print(theQueue[i] + " ");
                }
            }
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            if (size < 9) {
                if (firstPoint == 0) {
                    T b = theQueue[firstPoint];
                    int n = firstPoint;
                    for (int i = 0; i < size - 1; i++) {
                        theQueue[n] = theQueue[minusOne(n, 8)];
                        n = minusOne(n, 8);
                    }
                    size -= 1;
                    return b;
                } else {
                    T b = theQueue[firstPoint];
                    size -= 1;
                    firstPoint = minusOne(firstPoint, 8);
                    return b;
                }
            } else {
                T q = theQueue[firstPoint];
                int n = firstPoint;
                int m = firstPoint;
                T[] a = (T[]) new Object[size - 1];
                for (int i = 0; i < size - 1; i++) {
                    m = minusOne(m, size);
                    a[n] = theQueue[m];
                    n = minusOne(n, size - 1);
                }
                theQueue = a;
                size -= 1;
                return q;
            }
        }
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        } else if (size < 9) {
            if ((8 - size == firstPoint) && (firstPoint != 0) ) {
                T b = theQueue[firstPoint + size - 1];
                for (int i = 7; i > firstPoint; i--) {
                    theQueue[i] = theQueue[i - 1];
                }
                firstPoint = minusOne(firstPoint, 8);
                size -= 1;
                return b;
            } else if (firstPoint == 0) {
                T b = theQueue[size - 1];
                size -= 1;
                return b;
            } else  {
                T b = theQueue[firstPoint + size - 9];
                size -= 1;
                return b;
            }
        } else {
            if (firstPoint == 0) {
                T b = theQueue[size - 1];
                T[] a = (T[]) new Object[size - 1];
                System.arraycopy(theQueue, 0, a, 0, size - 1);
                theQueue = a;
                size -= 1;
                return b;
            } else {
                T b = theQueue[firstPoint - 1];
                T[] a = (T[]) new Object[size - 1];
                for (int i = 0; i < firstPoint - 1; i++) {
                    a[i] = theQueue[i];
                }
                for (int i = firstPoint - 1; i < size - 1; i++) {
                    a[i] = theQueue[i + 1];
                }
                theQueue = a;
                firstPoint -= 1;
                size -= 1;
                return b;
            }
        }
    }
    public T get(int index) {
        if (size < 8) {
            if (firstPoint + index < 8) {
                return theQueue[firstPoint + index];
            }
            return theQueue[firstPoint + index - 8];
        } else {
            if (firstPoint + index < size) {
                return theQueue[firstPoint + index];
            }
            return theQueue[firstPoint + index - size];
        }
    }
//    /*--------------Test!!!!!--------------*/
//    public static void main(String[] args) {
//        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
//        System.out.println(ArrayDeque.removeLast());      //==> 5
//    }
}