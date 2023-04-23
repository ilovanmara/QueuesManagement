package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{

    public void addTask(List<Server> servers, Task t, int max) {

        int pos = 0;
        int i = 0;
        int minWaitingTime = servers.get(0).getWaitingPeriod().get();
        for(Server entry : servers){
            if(entry.getWaitingPeriod().get() < minWaitingTime && entry.getTasksSize() < max){
                minWaitingTime = entry.getTasksSize();
                pos = i;
            }
            i++;
        }
        servers.get(pos).addTask(t);
    }

}
