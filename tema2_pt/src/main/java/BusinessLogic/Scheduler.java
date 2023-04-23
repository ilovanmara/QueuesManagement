package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerserver;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer){
        this.maxNoServers = maxNoServers;
        this.servers = new ArrayList<Server>(maxNoServers);
        this.maxTasksPerserver = maxTasksPerServer;
        for(int i = 0; i < maxNoServers; i++){
            Server server = new Server();
            servers.add(server);
            Thread th = new Thread(server);
            th.start();
        }
    }
    public void changeStrategy(int strategyC){
        if(strategyC == 0)
            strategy = new ConcreteStrategyTime();
        else strategy = new ConcreteStrategyQueue();
    }

    public void dispatchTask(Task t){
        this.strategy.addTask(servers, t, maxTasksPerserver);

    }

    public int getSize(){
        int size = 0;
        for(Server entry : servers){
            size += entry.getTasksSize();
        }
        return size;
    }

    public boolean verifyServers(){
        for(Server entry : servers){
            if(entry.getTasksSize() > 0)
                return false;

        }
        return true;
    }

    public List<Server> getServers(){
        return servers;
    }
    public void printServers(){
        int i = 1;
       // if()
        for(Server entry : servers){
            System.out.println("Queue " + i);
            entry.printServer();
            i++;
        }

    }
}
