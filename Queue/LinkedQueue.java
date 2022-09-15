package Queue;

import Interfaces.QueueADT;
import Exceptions.EmptyCollectionException;

public class LinkedQueue<T> implements QueueADT<T> {

    private LinearNode<T> front;
    private LinearNode<T> rear;
    private int count;

    public LinkedQueue() {
        this.front = this.rear = null;
        this.count = 0;
    }

    @Override
    public void enqueue(T element) {
        LinearNode<T> tmp = new LinearNode<>(element);

        if (!this.isEmpty()) {
            this.rear.setNext(tmp);
        } else {
            this.front = tmp;
        }

        this.rear = tmp;

        this.count++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Array is empty.");
        }

        T tmp = this.front.getElement();
        this.front = this.front.getNext();
        this.count--;

        if (this.isEmpty()) {
            this.rear = null;
        }

        return tmp;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Array is empty.");
        }

        return this.front.getElement();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

}
