package Business;

import java.sql.Timestamp;

public class Queue {
    double queue[] = new double[100];
    int size;
    int front;
    int rear;

    public void enQueue(Timestamp time,double data){
        queue[rear] = data;
        rear = (rear+1)%100;
        size++;
    }

    public double deQueue(){
        double data = queue[front];
        front = (front+1)%100;
        size--;

        return data;
    }
}
