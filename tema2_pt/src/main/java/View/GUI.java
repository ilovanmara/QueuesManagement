package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    Border border = BorderFactory.createLineBorder(Color.white);
    private JPanel panel = new JPanel();
    private JLabel title;
    private JLabel timeLimit = new JLabel("Simulation time:");
    private JLabel numerOfClientsLbl = new JLabel("Number of clients:");
    private JLabel numberOfServersLbl = new JLabel("Number of servers");
    private JLabel minArrivalTimeLbl = new JLabel("Min arrival time:");
    private JLabel maxArrivalTimeLbl = new JLabel("Max arrival time:");
    private JLabel minServiceTimeLbl = new JLabel("Min service time");
    private JLabel maxServiceTimeLbl = new JLabel("Max service time:");
    private JTextField numOfClientsTxt = new JTextField();
    private JTextField numOfServiceTxt = new JTextField();
    private JTextField minArrivalTimeTxt = new JTextField();
    private JTextField maxArrivalTimeTxt = new JTextField();
    private JTextField minServiceTimeTxt = new JTextField();
    private JTextField maxServiceTimeTxt = new JTextField();
    private JTextField timeLimitTxt = new JTextField();
    private JButton startBtn = new JButton("Time");
    private JButton start2Btn = new JButton("Queue");
    private JTextArea resultTxt;
    private JScrollPane scroll;

    public GUI(){
        this.setName("Queues management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(680, 700);
        this.setLocationRelativeTo(null);

        title = new JLabel("Queues management");
        title.setBounds(160, 10, 400, 50);
        title.setFont(new Font("Georgia",Font.BOLD,30));
        panel.setLayout(null);
        panel.setBackground(new Color(202, 208, 173));
        panel.add(title);
        panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        insertPanel();
        addBtn();
        createResultText();

        this.add(panel, BorderLayout.CENTER);
        this.setContentPane(panel);
        this.setVisible(true);
    }

    public void insertPanel(){
        numerOfClientsLbl.setBounds(70, 80, 200, 25);
        numerOfClientsLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        numberOfServersLbl.setBounds(70, 110, 200, 25);
        numberOfServersLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        maxArrivalTimeLbl.setBounds(70, 140, 200, 25);
        maxArrivalTimeLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        minArrivalTimeLbl.setBounds(70, 170, 200, 25);
        minArrivalTimeLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        maxServiceTimeLbl.setBounds(70, 200, 200, 25);
        maxServiceTimeLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        minServiceTimeLbl.setBounds(70, 230, 200, 25);
        minServiceTimeLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        timeLimit.setBounds(70, 260, 200, 25);
        timeLimit.setFont(new Font("Century",Font.CENTER_BASELINE,16));

        numOfClientsTxt = new JTextField(10);
        numOfClientsTxt.setBounds(270, 80, 300, 25);
        numOfServiceTxt = new JTextField(10);
        numOfServiceTxt.setBounds(270, 110, 300, 25);
        maxArrivalTimeTxt = new JTextField(10);
        maxArrivalTimeTxt.setBounds(270, 140, 300, 25);
        minArrivalTimeTxt = new JTextField(10);
        minArrivalTimeTxt.setBounds(270, 170, 300, 25);
        maxServiceTimeTxt = new JTextField(10);
        maxServiceTimeTxt.setBounds(270, 200, 300, 25);
        minServiceTimeTxt = new JTextField(10);
        minServiceTimeTxt.setBounds(270, 230, 300, 25);
        timeLimitTxt = new JTextField(10);
        timeLimitTxt.setBounds(270, 260, 300, 25);

        panel.add(numberOfServersLbl);
        panel.add(numerOfClientsLbl);
        panel.add(numOfClientsTxt);
        panel.add(numOfServiceTxt);
        panel.add(maxArrivalTimeTxt);
        panel.add(minArrivalTimeTxt);
        panel.add(maxArrivalTimeLbl);
        panel.add(minArrivalTimeLbl);
        panel.add(maxServiceTimeLbl);
        panel.add(minServiceTimeLbl);
        panel.add(maxServiceTimeTxt);
        panel.add(minServiceTimeTxt);
        panel.add(timeLimit);
        panel.add(timeLimitTxt);
    }

    public JTextField getTimeLimitTxt() {
        return timeLimitTxt;
    }

    public JLabel getTimeLimit() {
        return timeLimit;
    }

    public void addBtn(){
        panel.add(startBtn);
        createButton(startBtn);
        startBtn.setBounds(200, 295, 120, 40);
        panel.add(start2Btn);
        createButton(start2Btn);
        start2Btn.setBounds(350, 295, 120, 40);
    }

    private void createButton(JButton button){
        Color buttonColor = new Color(97, 107, 69);
        button.setFont(new Font("Century",Font.CENTER_BASELINE,20));
        button.setForeground(Color.white);
        button.setBackground(buttonColor);
        button.setBorder(border);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    private void createResultText(){
        resultTxt = new JTextArea(100, 58);
        resultTxt.setBounds(40, 340, 700, 400);
        resultTxt.setEditable(false);
        scroll = new JScrollPane(resultTxt);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(40, 350, 600, 300);
        panel.add(scroll);
    }

    public void setResultTxt(JTextArea resultTxt) {
        this.resultTxt = resultTxt;
    }

    public JTextField getNumOfClientsTxt() {
        return numOfClientsTxt;
    }

    public JTextField getNumOfServiceTxt() {
        return numOfServiceTxt;
    }

    public JTextField getMinArrivalTimeTxt() {
        return minArrivalTimeTxt;
    }

    public JTextField getMaxArrivalTimeTxt() {
        return maxArrivalTimeTxt;
    }

    public JTextField getMinServiceTimeTxt() {
        return minServiceTimeTxt;
    }

    public JTextField getMaxServiceTimeTxt() {
        return maxServiceTimeTxt;
    }

    public JButton getStartBtn() {
        return startBtn;
    }

    public JTextArea getResultTxt() {
        return resultTxt;
    }

    public void addStartListener(ActionListener e){
        startBtn.addActionListener(e);
    }
    public void addStart2Listener(ActionListener e){
        start2Btn.addActionListener(e);
    }
    public String getUserInput(JTextField text){
        return text.getText();
    }
    public void refreshFrame(String string){
        resultTxt.setText(string);
    }

}
