package Model;

import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean toString;
    private int arrivalTime;
    private AtomicInteger serviceTime;
    private int id;
    private static int makeId = 1;

    public Task(int arrivalTime, AtomicInteger serviceTime){
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.id = this.makeId;
        makeId++;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public AtomicInteger getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(AtomicInteger serviceTime) {
        this.serviceTime = serviceTime;
    }
    
    public String toString(){
        return "(" + id + ", " + arrivalTime + ", " + serviceTime + ")";
    }


}
