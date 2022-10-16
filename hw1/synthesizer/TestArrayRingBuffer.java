package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest1() { //enqueue and peek test
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);

        int expected = arb.peek();
        int output = 1;
        assertEquals(expected, output);
    }

    @Test
    public void someTest2() { //enqueue dequeue and peek test
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.dequeue();
        arb.dequeue();

        int expected = arb.peek();
        int output = 3;
        assertEquals(expected, output);
    }
    @Test
    public void someTest3() { //capacity test
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        int expected = 10;
        int output = arb.capacity();
        assertEquals(expected, output);
    }
    @Test
    public void fillCountTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        int expected = 4;
        int output = arb.fillCount();
        assertEquals(expected, output);
    }

    @Test
    public void backToZeroTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        int expected = 0;
        int fillOutput = arb.fillCount();
        assertEquals(expected, fillOutput);
    }
    @Test
    public void isEmptyTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        boolean expected = true;
        boolean emptyOutput = arb.isEmpty();
        assertEquals(expected, emptyOutput);
    }

    @Test
    public void isFullTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(11);
        int expected = 5;
        int emptyOutput = arb.peek();
        assertEquals(expected, emptyOutput);
    }
    @Test
    public void outBoundTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
//        arb.enqueue(11);
//        arb.enqueue(12);
//        arb.enqueue(13);
        boolean expected = true;
        boolean emptyOutput = arb.isFull();
        assertEquals(expected, emptyOutput);
    }
    @Test
    public void stringTest() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer(10);
        arb.enqueue("hello");
        arb.enqueue("Its");
        arb.enqueue("me");

        String expected = "hello";
        String Output = arb.peek();
        assertEquals(expected, Output);
    }
    @Test
    public void peekTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(6);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(7);
        arb.dequeue();
        arb.enqueue(8);
        arb.dequeue();
        arb.enqueue(9);

        int expected = 9;
        int emptyOutput = arb.peek();
        assertEquals(expected, emptyOutput);
    }
//    @Test
//    public void irterationTest() {
//        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
//        arb.enqueue(1);
//        arb.enqueue(2);
//        arb.enqueue(3);
//        arb.enqueue(4);
//        arb.enqueue(5);
//        arb.enqueue(6);
//        int[] expected ={1,2,3,4,5,6};
//        int i = 0;
//        int[] a = new int[6];
//        for(int x : arb){
//            a[i] = x;
//            i += 1;
//        }
//        assertEquals(expected, a);
//    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);

    }
} 
