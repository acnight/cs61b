public class LinkedListDeque<T> {
    private int size;
    private IntNode sentinel;
    public class IntNode {
        private IntNode prev;
        private T item;
        private IntNode next;
        public IntNode(IntNode s, T f, IntNode r) {
            prev = s;
            item = f;
            next = r;
        }
    }
    private IntNode theQueue;
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel.prev;
        theQueue = sentinel;
        this.size = 0;
    }
//    public LinkedListDeque(T x) {
//        sentinel = new IntNode(null, x, null);
//        theQueue = new IntNode(null, x, null);
//        sentinel.next = theQueue;
//        sentinel.prev = theQueue;
//        theQueue.prev = sentinel;
//        theQueue.next = sentinel;
//
//        this.size = 1;
//    }
    public void addFirst(T item) {
//        if(this.isEmpty() == true){
//            IntNode a = new IntNode(null, item, null);
//            sentinel.next = a;
//            a.prev = sentinel;
//            sentinel.prev = a;
//            a.next = sentinel;
//            theQueue = a;
//            size += 1;
//        }else {
        IntNode a = new IntNode(null, item, null);
        sentinel.next = a;
        theQueue.prev = a;
        a.prev = sentinel;
        a.next = theQueue;

        theQueue = a;
        this.size += 1;
//        }
    }
    public int size() {
        return size;
    }
    public void addLast(T item) {
        if (isEmpty()) {
            addFirst(item);
        } else {
            IntNode a = new IntNode(null, item, null);
            IntNode b = sentinel.prev;  //b points to the last IntNode
            b.next = a;
            a.prev = b;
            sentinel.prev = a;
            a.next = sentinel;
            theQueue = sentinel.next;
            size += 1;
        }
    }
    public boolean isEmpty() {
        if (this.size > 0) {
            return false;
        }
        return true;
    }
    public void printDeque() {
        int i = 0;
        IntNode c = theQueue;
        while (i < size) {
            System.out.print(c.item + " ");
            c = c.next;
            i += 1;
        }
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T d = theQueue.item;
        theQueue = theQueue.next;
        theQueue.prev = sentinel;
        sentinel.next = theQueue;
        size -= 1;
        return d;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            return removeFirst();
        }
        T d = sentinel.prev.item;
        IntNode a = sentinel.prev.prev;
        a.next = sentinel;
        sentinel.prev = a;
        size -= 1;
        return d;
    }
    public T get(int index) {
        if (size <= index) {
            return null;
        }
        IntNode a = theQueue;
        int i = 0;
        while (i < index) {
            a = a.next;
            i += 1;
        }
        return a.item;
    }
    public T getRecursive(int index) {
        IntNode a = theQueue;
        if (size <= index) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }
    private T getRecursive(int index, IntNode a) {
        if (index == 0) {
            return a.item;
        }
        return getRecursive(index - 1, a.next);
    }
    /*--------------------Here is the Test!!!!! ----------------------*/
//    public static void main(String[] args) {
//        LinkedListDeque<Integer> m = new LinkedListDeque<>();
//    }
}