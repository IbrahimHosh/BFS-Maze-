// Q2Gen.java
// Simple QGen interface implemented using an array of generic objects
// Revised 11/2020

public class Q2Gen <T> implements QGen <T> {
    // constructors
    public Q2Gen() {
        q = (T[]) new Object[3];  // generic array allocation not allowed
    }

    public Q2Gen(int initLength) {
        if (initLength < 0)
            throw new IllegalArgumentException("capacity requested is negative");
        q = (T[]) new Object[initLength];
    }

    // selectors
    public void add(T o) {
        if (size == q.length) {  // allocate bigger array
            Object[] newq = new Object[2 * q.length + 1];
            if (front <= rear)  // queue has not wrapped,
                                // so make simple copy to new space
                System.arraycopy(q, front, newq, 0, size);
            else if (front > rear) {  // queue has wrapped,
                                      // so copy in two chunks
                System.arraycopy(q, front, newq, 0, q.length - front);
                System.arraycopy(q, 0, newq, q.length - front, rear + 1);
                front = 0;
                rear = size - 1;
            }
            q = (T[]) newq;
        }  // allocate bigger array
        rear = (rear + 1) % q.length;
        q[rear] = o;
        size++;
        //        System.out.println("Front is: " + front + ";  Rear is: " + rear +
        //                           ";  Size is: " + size);
    }

    public T remove() {
        if (size == 0)
            throw new RuntimeException("removing from an empty queue");

        T answer = q[front];
        front = (front + 1) % q.length;
        size--;
        return answer;
    }

    public int length() {
        return size;
    }

    private T[] q;
    private int size;
    private int front;
    private int rear = -1;
}  // Q2Gen class

