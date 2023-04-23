package BusinessLogic;

import Model.Server;
import Model.Task;
import View.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class SimulationManager implements Runnable{
    public int timeLimit = 60;
    public int maxProcessingTime = 10;
    public int minProcessingTime = 2;
    public int maxArrivalTime = 10;
    public int minArrivalTime = 1;
    public int numberOfServers;
    public int numberOfClients;
    private Scheduler scheduler;
    private List<Task> generateTask;
    public FileWriter fileOut;
    GUI gui;
    Thread t ;
    int strategy;

    public SimulationManager(){
        gui = new GUI();
        this.t = new Thread(this);
        gui.addStartListener(new StartListener());
        gui.addStart2Listener(new Start2Listener());
    }

    class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(isInteger(gui.getUserInput(gui.getNumOfClientsTxt())) && isInteger(gui.getUserInput(gui.getNumOfServiceTxt())) && isInteger(gui.getUserInput(gui.getMaxArrivalTimeTxt())) && isInteger(gui.getUserInput(gui.getMinServiceTimeTxt())) && isInteger(gui.getUserInput(gui.getMaxArrivalTimeTxt())) && isInteger(gui.getUserInput(gui.getMinServiceTimeTxt()))) {
                setNumberOfClients(Integer.valueOf(gui.getUserInput(gui.getNumOfClientsTxt())));
                setNumberOfServers(Integer.valueOf(gui.getUserInput(gui.getNumOfServiceTxt())));
                setMaxArrivalTime(Integer.valueOf(gui.getUserInput(gui.getMaxArrivalTimeTxt())));
                setMinArrivalTime(Integer.valueOf(gui.getUserInput(gui.getMinArrivalTimeTxt())));
                setMaxProcessingTime(Integer.valueOf(gui.getUserInput(gui.getMaxServiceTimeTxt())));
                setMinProcessingTime(Integer.valueOf(gui.getUserInput(gui.getMinServiceTimeTxt())));
                setTimeLimit(Integer.valueOf(gui.getUserInput(gui.getTimeLimitTxt())));

                scheduler = new Scheduler(numberOfServers, numberOfClients);
                generateNRandomTasks();
                for (int i = 0; i < numberOfClients; i++) {
                    System.out.println(generateTask.get(i).toString());
                }
                t.start();
                strategy = 0;
            } else {
                gui.getResultTxt().setText("Try again!");
            }
        }
    }
    class Start2Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(isInteger(gui.getUserInput(gui.getNumOfClientsTxt())) && isInteger(gui.getUserInput(gui.getNumOfServiceTxt())) && isInteger(gui.getUserInput(gui.getMaxArrivalTimeTxt())) && isInteger(gui.getUserInput(gui.getMinServiceTimeTxt())) && isInteger(gui.getUserInput(gui.getMaxArrivalTimeTxt())) && isInteger(gui.getUserInput(gui.getMinServiceTimeTxt()))) {
                setNumberOfClients(Integer.valueOf(gui.getUserInput(gui.getNumOfClientsTxt())));
                setNumberOfServers(Integer.valueOf(gui.getUserInput(gui.getNumOfServiceTxt())));
                setMaxArrivalTime(Integer.valueOf(gui.getUserInput(gui.getMaxArrivalTimeTxt())));
                setMinArrivalTime(Integer.valueOf(gui.getUserInput(gui.getMinArrivalTimeTxt())));
                setMaxProcessingTime(Integer.valueOf(gui.getUserInput(gui.getMaxServiceTimeTxt())));
                setMinProcessingTime(Integer.valueOf(gui.getUserInput(gui.getMinServiceTimeTxt())));
                setTimeLimit(Integer.valueOf(gui.getUserInput(gui.getTimeLimitTxt())));

                scheduler = new Scheduler(numberOfServers, numberOfClients);
                generateNRandomTasks();
                for (int i = 0; i < numberOfClients; i++) {
                    System.out.println(generateTask.get(i).toString());
                }
                t.start();
                strategy = 1;
            } else {
                gui.getResultTxt().setText("Try again!");
            }
        }
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    private void generateNRandomTasks(){
        int arrivalTime;
        AtomicInteger serviceTime;
        generateTask =  new ArrayList<Task>(numberOfClients);
        for(int i = 0; i < numberOfClients; i++) {
            Random rn = new Random();
            arrivalTime = rn.nextInt((maxArrivalTime - minArrivalTime) + 1) + minArrivalTime;
            Random rn2 =new Random();
            serviceTime = new AtomicInteger(rn2.nextInt((maxProcessingTime - minProcessingTime) + 1) + minProcessingTime);
            Task t = new Task (arrivalTime,  serviceTime);
            this.generateTask.add(t);

        }
        Collections.sort(generateTask, Comparator.comparing(Task::getArrivalTime));
    }
    public float averageTime(List<Server> server){
        int sum = 0;
        for(Server s : server){
            sum = sum + s.getWait();
        }
        float average = (float)sum/(float)numberOfClients;
        return average;
    }

    public float averageServiceTime(List<Task> ta ){
        int sum = 0;
        for(Task t : ta){
            sum = sum + t.getServiceTime().get();
        }
        float average = (float)sum/(float)numberOfClients;
        return average;
    }

    public int getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public void setMaxProcessingTime(int maxProcessingTime) {
        this.maxProcessingTime = maxProcessingTime;
    }

    public int getMinProcessingTime() {
        return minProcessingTime;
    }

    public void setMinProcessingTime(int minProcessingTime) {
        this.minProcessingTime = minProcessingTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getNumberOfServers() {
        return numberOfServers;
    }

    public void setNumberOfServers(int numberOfServers) {
        this.numberOfServers = numberOfServers;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public void createFile(){
        try{
            this.fileOut = new FileWriter("tt.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeFile(){
        try {
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void currentTimeDecr(int currentTime){
        Task task = new Task(0, new AtomicInteger(0));
        int ok = 1;
        while (generateTask.size() > 0 && ok == 1) {
            ok = 0;
            if (currentTime >= generateTask.get(0).getArrivalTime()) {
                task = generateTask.get(0);
                scheduler.changeStrategy(strategy);
                scheduler.dispatchTask(task);
                generateTask.remove(0);
                ok = 1;
            }
        }
    }
     public void printFile(int currentTime){
         try {
             fileOut.write("Time:" + currentTime + "\n" + "Waiting clients:\n");
             for (int j = 0; j < generateTask.size(); j++) {
                 fileOut.write(generateTask.get(j).toString());
             }
             fileOut.write("\n");
             int k = 1;
             for (Server s : scheduler.getServers()) {
                 fileOut.write("Queue " + k + ": " + s.printServer() + "\n");
                 k++;
             }
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
     public void printString(String string){
         try{
             fileOut.write(string);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
     public void sleepTh (){
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }

    public void run () {
        int currentTime = 0, i = 0;
        String result = "";
        createFile();
        printString("Average Service Time " + averageServiceTime(generateTask)+"\n");
        System.out.println("Average Service Time " + averageServiceTime(generateTask));
        int peakHour = 0, max = 0;
        while(currentTime < timeLimit && (generateTask.size() > 0 || scheduler.verifyServers() == false)){
            currentTimeDecr(currentTime);
            result = result + "Time: " + currentTime + "\n" + "WatingClients:\n";
            for (int j = 0; j < generateTask.size(); j++) {
                result = result + generateTask.get(j).toString();
            }
            result += "\n";
            int k1 = 1;
            for (Server s : scheduler.getServers()) {
                result += "Queue " + k1 + ": " + s.printServer() + "\n";
                k1++;
            }
            gui.refreshFrame(result);
            printFile(currentTime);
            sleepTh();
            if (max < scheduler.getSize()){
                max = scheduler.getSize();
                peakHour = currentTime;
            }
            currentTime++;
        }
        printString("Average " + averageTime(scheduler.getServers()) + "Peak Hour: " + peakHour);
        closeFile();
        System.out.println("Average " + averageTime(scheduler.getServers()) + "\n" + "Peak Hour: " + peakHour);
    }

    public static void main(String[] args) {
        SimulationManager gen = new SimulationManager();
    }

}
