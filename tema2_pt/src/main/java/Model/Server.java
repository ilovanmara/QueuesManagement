package Model;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    private int wait = 0;
    public Server(){
        tasks = new LinkedBlockingQueue<Task>();
        waitingPeriod = new AtomicInteger(0);
    }

    public int getTasksSize(){
        return this.tasks.size();
    }
    public void addTask(Task newTask){
        this.tasks.offer(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime().get());
    }

    public void run() {
        while (true) {
            if(tasks.isEmpty()) {

            } else{
                wait = wait + this.getWaitingPeriod().get();
                Task nextTask = tasks.peek();
                try{
                    int i = 1;
                    while(i <= nextTask.getServiceTime().get()) {
                        Thread.sleep(1000);
                        waitingPeriod.decrementAndGet();
                        nextTask.setServiceTime(new AtomicInteger(nextTask.getServiceTime().decrementAndGet()));
                        if (nextTask.getServiceTime().get() == 0) {
                            tasks.remove(nextTask);
                        }
                    }
                }catch(InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public Task[] getTasks(){
        Task[] tasksArr = new Task[this.tasks.size()];
        tasks.toArray(tasksArr);
        return tasksArr;
    }

    public String printServer(){
        if(tasks.size() == 0){
          return "closed";
        }else{
            String aux = "";
            for(Task entry : this.tasks){
                aux = aux + entry.toString() + " ";
            }
            return aux;
        }
    }

}
