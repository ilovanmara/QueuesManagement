package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t, int maxTasksPerServer) {
        Server s = null;
        int pos = 0;
        int i = 0;
        int min = servers.get(0).getTasksSize();
        for(Server entry : servers){
            if(entry.getTasksSize() < min ){
                min = entry.getTasksSize();
                pos = i;
            }
            i++;
        }
        s = servers.get(pos);
        s.addTask(t);
    }
}
