package sample;


import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


//import java.awt.ScrollPane;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

import java.awt.*;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;



import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.*;


import javax.swing.table.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.scene.control.ScrollPane;




public class Controller implements Initializable{

    /* sequence : when user enters no of processes setnoofprocesses is called
        and linkedlist is intiallized with processes of attributes equal zero
        then when he enters scheduling type setu is called.When he started filling
        arrival time textfield global counter i is used and every time he presses
        enter arrival time is given to a process in the linked list. When he presses
        simulate Gantt chart and waiting time is calculated.

        If the user didn't enter arrival time at priority scheduling arrival times will
        be assumed as zeros and the same in SJF_non preemptive.

     */

    static LinkedList<process> p = new LinkedList<>();
    static int no_of_processes;
    static process.type u = process.type.None;
    ObservableList<process>o= FXCollections.observableArrayList();
    static int i = 0;
    static boolean flag = true ;
    static int for_burst_time = 0;
    static double round_robin_time = 0;
    static int for_priority = 0;





    /*
    void setNo_of_processes(){
        //clear all old data
        table.getItems().clear();
        no_of_processes=Integer.parseInt(l1.getText());  // Integer.parseint converts string into int.
        System.out.println(no_of_processes);

        /* Intialize all processess */
    //    int z = 0 ;


      //  for_burst_time = 0;
      //  round_robin_time = 0;
      //  for_priority = 0;

      //  if(!p.isEmpty()) {p.clear();}
      //  setP();
    //}



    void clear_all(){
        if(p.size()>0) {
            p.clear();
            table.getItems().clear();
            i = 0;
            no_of_processes = 0;
            for_priority = 0;
            for_burst_time = 0;
        }
    }

    void delete_one(){
        if(p.size()>0) {
            int n = 0;
            p.remove(p.size() - 1);
            i--;
            for_burst_time--;
            for_priority--;
            no_of_processes = i;
            table.getItems().clear();
            draw_table();
        }
    }


    void setRound_robin_time(){

        round_robin_time = 2 ;

        if(!rrt.getText().isEmpty()) {
            round_robin_time = Double.parseDouble(rrt.getText());
        }
                    System.out.println("Round robin time is : "+round_robin_time);
    }

    void setU(){
        // clear waiting time
        wait_time.clear();

        // setting new u
        String j = c1.getValue();
        if(j=="FCFS") u= process.type.fcfs;
        else if(j=="SJF non preemptive") u = process.type.sjf_np;
        else if(j=="SJF preemptive") u = process.type.sjf_p;
        else if(j=="priority non preemptive") u=process.type.pr_np;
        else if(j=="priority preemptive") u = process.type.pr_p;
        else if(j=="Round Robin") u = process.type.rr;
        else u= process.type.None;
        System.out.println(u);
    }

    // Initialize all processes attributes with zeroes

    void setP(){
        int no = 0 ;

        while(no<no_of_processes){

            process p1 = new process();
            p.add(p1);
            p.get(no).order = no ;

            System.out.println("Order is : "+p.get(no).order);
            no++;
        }
        no=0;
    }

    void giving_values(){

        /*
            if(i<no_of_processes)
            {
                if(!t2.getText().isEmpty()) {
                    p.get(i).arrival_time = Integer.parseInt(t2.getText());
                }
                p.get(i).order=i;
                System.out.println(p.get(i).order+"      "+i);
                i++;
                table.getItems().clear();
                draw_table();
            }
           if(i==no_of_processes) {
              /* table.getItems().clear(); draw_table(); */
          //  i=0;
          //}

        if(!t2.getText().isEmpty()) {
            p.get(i).arrival_time = Double.parseDouble(t2.getText());
        }
        p.get(i).order=i;
        System.out.println(p.get(i).order+"      "+i);
        i++;
        no_of_processes=i;
        table.getItems().clear();
        draw_table();

    }

    void give_burst_time(){

        /*
        if(for_burst_time<no_of_processes)
        {
         if(!t3.getText().isEmpty())   {p.get(for_burst_time).burst_time=Integer.parseInt(t3.getText());}
            p.get(for_burst_time).time_left=p.get(for_burst_time).burst_time;
            for_burst_time++;
            table.getItems().clear();
            draw_table();
        }
        if(for_burst_time==no_of_processes)
        {
            // table.getItems().clear();draw_table();
            for_burst_time=0;
        }
        */
        if(!t3.getText().isEmpty())   {p.get(for_burst_time).burst_time=Double.parseDouble(t3.getText());}
        p.get(for_burst_time).time_left=p.get(for_burst_time).burst_time;
        for_burst_time++;
        table.getItems().clear();
        draw_table();
    }

    void give_priority(){
        /*
        if(for_priority<no_of_processes)
        {
            if(!t4.getText().isEmpty()) {p.get(for_priority).priority = Integer.parseInt(t4.getText());}
            for_priority++;
            table.getItems().clear();
            draw_table();
        }
        if(for_priority==no_of_processes) { for_priority=0;table.getItems().clear();draw_table(); }
        */
        if(!t4.getText().isEmpty()) {p.get(for_priority).priority = Integer.parseInt(t4.getText());}
        for_priority++;
        table.getItems().clear();
        draw_table();
    }





    void draw_table(){

        // Adding all elements of p_linkedlist to observable list



        int r = 0;
        while(r<no_of_processes){
            o.add(p.get(r));r++;
        }
        //Drawing table
        table.setItems(getO());
        col1.setCellValueFactory(new PropertyValueFactory<>("order"));
        col2.setCellValueFactory(new PropertyValueFactory<>("arrival_time"));
        col3.setCellValueFactory(new PropertyValueFactory<>("burst_time"));
        col4.setCellValueFactory(new PropertyValueFactory<>("priority"));
    }


    void add_new()
    {
        p.add(new process());
        System.out.println(t2.getText().isEmpty());
        giving_values();
        give_burst_time();
        give_priority();
    }



    void draw_gantt(double t[] , int pr[], int size , double total){

        System.out.println("After i enterd size is "+size);
        gantt_chart.getChildren().clear();
        time_bar.getChildren().clear();

        //System.out.println("I entered the gantt chart");

        /* Modifing the two arrays first to remove repeated elements */


        Vector<Double>t1=new Vector<>();
        Vector<Integer>pr1 = new Vector<>();

        if(u!= process.type.rr) {

            t1.add(t[0]);
            pr1.add(pr[0]);

            int as = 1;
            int last = pr[0];
            while (as < size - 1) {
                if (pr[as] != last) {
                    t1.add(t[as]);
                    pr1.add(pr[as]);
                    last = pr[as];
                }
                as++;
            }
            t1.add(t[size - 1]);
        }
        else{
            int as = 0 ;
            while (as<size-1){
                pr1.add(pr[as]);
                t1.add(t[as]);
                as++;
            }
            t1.add(t[as]);
        }
        int as=0;

        while( as<pr1.size() ){


            Label l ;

                if(pr1.get(as)!=-1)  l = new Label("P "+pr1.get(as));
                else l=new Label("No process");


                l.setStyle("-fx-border-color: #000000");


                gantt_chart.getChildren().add(l);
                //Separator s = new Separator(Orientation.VERTICAL);
                Double wid = (t1.get(as+1)-t1.get(as))/total; wid*=100;
                int width = wid.intValue();

            if(pr1.get(as)!=-1)l.setPadding(new Insets(0,55+width,0,0));
            else l.setPadding(new Insets(0,15+width,0,0));
                //s.setPadding(new Insets(0,0,0,25+width));
               // s.setStyle("-fx-border-color: #000000");
                //gantt_chart.getChildren().add(s);

                // Adjust timing
                if(as==0){
                    Label time = new Label();
                    time.setText(""+t1.get(0));
                    time.setPadding(new Insets(0,55+width,0,0));
                    //time.setPadding(new Insets(0,35+width,0,0));
                    time_bar.getChildren().add(time);
                }
                Label time2 = new Label();
                time2.setText(""+t1.get(as+1));
                //time2.setPadding(new Insets(0,0,0,25+width));
                //time2.setWrapText(true);
            if(pr1.get(as)!=-1){
                if(t1.get(as+1)<=15)time2.setPadding(new Insets(0,55+width,0,0));
                if(t1.get(as+1)>15&&t1.get(as+1)<100) time2.setPadding(new Insets(0,50+width,0,0));
                if(t1.get(as+1)>=100&&t1.get(as+1)<900) time2.setPadding(new Insets(0,45+width,0,0));
                else time2.setPadding(new Insets(0,34+width,0,0));
            }
            else {
                time2.setPadding(new Insets(0,0,0,62+width));
                //time2.setMaxWidth(55+width);
            }
                time_bar.getChildren().add(time2);


                as++;
        }
    }



    void simulate(){
        if(p.size()<=0) return;
        if(u== process.type.fcfs)
          process.arrange_fcfs(p,no_of_processes,u);

        if (u== process.type.sjf_np) { simulate_sjf_np();return; }

        if(u== process.type.rr)  {simulate_rr();return;}

        if(u== process.type.sjf_p) { simulate_sjf_pr();return; }

        if(u== process.type.pr_np) { simulate_pr_np();return; }

        if(u== process.type.pr_p)  { simulate_pr_pre();return; }



        // Calculating waiting time
        int count = 0 ; double time_now = p.get(0).arrival_time ; double wait_time1 = 0 ;
       process.arrange_fcfs(p,no_of_processes, process.type.fcfs);
       count=0;
       int finished_processes=0;

       Vector<Double> times=new Vector();
        Vector<Integer>processes=new Vector<>();

       int sec = 0 ;


       while (count<no_of_processes)
       {
           if(time_now<p.get(count).arrival_time){
               times.add(time_now);
               processes.add(-1);
               time_now=p.get(count).arrival_time;
               sec++;
           }
           times.add(time_now);
           processes.add(p.get(count).order);
           sec++;
           time_now+=p.get(count).burst_time;
           p.get(count).departure_time=time_now;
           wait_time1+= (p.get(count).departure_time-p.get(count).arrival_time-p.get(count).burst_time);
           count++;
       }
            times.add(time_now);
            sec++;
        int r = 0 ;
        double time[]=new double[times.size()];
        while(r<times.size()){
            time[r]=times.get(r);
            r++;
        }
        r= 0 ; int pro[] = new int[processes.size()];
        while(r<processes.size()){
            pro[r]=processes.get(r);
            r++;
        }
           draw_gantt(time,pro,sec,time_now);
            double waiting2 = wait_time1;
            waiting2 /= no_of_processes;
            wait_time.setText(Double.toString(waiting2));
    }



    void simulate_sjf_np(){

        Vector<Double> times=new Vector<>();
         Vector<Integer>processes=new Vector<>();
        int sec = 0 ;

        process.arrange_fcfs(p,no_of_processes, process.type.fcfs);
        double time_now = p.get(0).arrival_time ;
        double finished_process = 0 ;
        int count = 0 ;
        double arrival_times[]=new double[no_of_processes];
        double wait_time1 = 0 ;
        Vector<process>v=new Vector<>();
        for(int i =0  ; i<no_of_processes ; i++)
        {
            arrival_times[i]=p.get(i).arrival_time;
        }
        while(finished_process<no_of_processes)
        {
            while(count<no_of_processes)
            {
                if(time_now>=arrival_times[count])
                {
                    v.add(p.get(count));
                    v.get(v.size()-1).time_left=p.get(count).burst_time;
                    count++;
                }
                else
                {
                    if(v.isEmpty()) {
                        times.add(time_now);
                        processes.add(-1);
                        sec++;
                        time_now = arrival_times[count];
                    }
                    else break;
                }
            }
              if(!v.isEmpty()) process.arrange_sjfp(v);
            while(!v.isEmpty())
            {
                times.add(time_now);
                processes.add(v.get(0).order);
                sec++;

                time_now+=v.get(0).burst_time;
                wait_time1+=(time_now-v.get(0).arrival_time-v.get(0).burst_time);
                finished_process++;
                v.remove(0);
            }
        }
        times.add(time_now);
        sec++;
        int r = 0 ;
        double time[]=new double[times.size()];
        while(r<times.size()){
            time[r]=times.get(r);
            r++;
        }
        r= 0 ; int pro[] = new int[processes.size()];
        while(r<processes.size()){
            pro[r]=processes.get(r);
            r++;
        }

        draw_gantt(time,pro,sec,time_now);
        // Write waiting time.
        double wait_time2 = wait_time1;
        wait_time2/=no_of_processes;
        wait_time.setText(Double.toString(wait_time2));
    }





    void simulate_sjf_pr(){

        Vector<Double> time = new Vector();
        Vector<Integer> pr = new Vector<>();

        process.arrange_fcfs(p,no_of_processes, process.type.fcfs);
        double arrival_times[]=new double[no_of_processes];
        int count = 0 ;
        while(count<no_of_processes){
            arrival_times[count]=p.get(count).arrival_time;
            count++;
        }
        count=0;

        int sec = 0 ;

        double wait_time1 = 0 ;
        Vector<process> arrived_processes = new Vector<>();
        int finished_processes = 0 ;
        double time_now = 0 ;
        while(finished_processes<no_of_processes)
        {
            while(count<no_of_processes)  // While loop to add all elements arriving at time 0 for example.
            {
                if(time_now>=arrival_times[count])
                {
                    arrived_processes.add(p.get(count));
                    //System.out.println("Process is added");
                    arrived_processes.get(arrived_processes.size()-1).time_left=p.get(count).burst_time;
                    count++;
                }
                else
                {
                    if(arrived_processes.isEmpty()){
                        time.add(time_now);
                        pr.add(-1);
                        sec++;
                        time_now = arrival_times[count]; //Change this to the next time instead
                    }
                    else break;
                }
            }
            // Arrange arrived processes according to time left .

                process.arrange_sjfp(arrived_processes);

                while (finished_processes<no_of_processes)
                {
                    time.add(time_now);
                    pr.add(arrived_processes.get(0).order);
                    sec++;

                    if(count<no_of_processes&&( (time_now+arrived_processes.get(0).time_left)>=arrival_times[count]))
                    {
                        arrived_processes.get(0).time_left -= (arrival_times[count]-time_now);
                        time_now = arrival_times[count];
                        if(arrived_processes.get(0).time_left==0)
                        {
                            arrived_processes.remove(0);
                            wait_time1+= (time_now-arrived_processes.get(0).arrival_time-
                                    arrived_processes.get(0).burst_time);
                            finished_processes++;
                        }
                        break;
                    }

                    //arrived_processes.get(0).time_left--
                        else
                        {
                            time_now+=arrived_processes.get(0).time_left;
                            wait_time1+=(time_now-arrived_processes.get(0).arrival_time-
                                    arrived_processes.get(0).burst_time);
                            arrived_processes.remove(0);
                            finished_processes++;
                        }
                        if( (count<no_of_processes&&time_now>=arrival_times[count]) || arrived_processes.isEmpty())
                            break;
                }
        }
        time.add(time_now);
        sec++;
        double wait_time2 = wait_time1;
        wait_time2/=no_of_processes;
        wait_time.setText(Double.toString(wait_time2));
        double t[]=new double[time.size()];
        int r = 0 ; while(r<time.size()) {
            t[r]=time.get(r);
            r++;
        }
        r=0;
        int pro[]=new int[pr.size()];
        while(r<pr.size()){
            pro[r]=pr.get(r);
            r++;
        }
        System.out.println("About to call gantt");
        draw_gantt(t,pro,sec,time_now);

        System.out.println("time finished at : "+time_now);
    }




    void simulate_pr_np()
    {
        double time_now = 0 ;
        Vector<process>v=new Vector<>();
        // Arrange them according to arrival.
        process.arrange_fcfs(p,no_of_processes, process.type.fcfs);

        /*
        System.out.println(p.get(0).burst_time);
        System.out.println(p.get(1).burst_time);
        System.out.println(p.get(2).burst_time);
        System.out.println(p.get(3).burst_time);
        */

        Vector<Double> times = new Vector<>();
        Vector<Integer>pr = new Vector<>();
        int sec= 0 ;

        int count = 0 ;
        double wait_time1 = 0 ;
        int processes_finished = 0 ;
        while(processes_finished<no_of_processes)
        {
            while(count<no_of_processes)
            {
                if(time_now>=p.get(count).arrival_time)
                {
                    v.add(p.get(count));
                    count++;
                }
                else
                {
                    if(v.isEmpty()) {
                        times.add(time_now);
                        pr.add(-1);
                        sec++;
                        time_now = p.get(count).arrival_time;
                    }
                    // Here we will set it to the next time to fit with double values.
                    else break;
                }
            }

            if(!v.isEmpty())
            {
                process.arrange_priority(v);
                /*
                System.out.println(v.get(0).burst_time);
                System.out.println(v.get(1).burst_time);
                System.out.println(v.get(2).burst_time);
                System.out.println(v.get(3).burst_time);
                System.out.println(v.get(4).burst_time);
                */
                while (!v.isEmpty())
                {
                    times.add(time_now);
                    pr.add(v.get(0).order);
                    sec++;

                    time_now += v.get(0).burst_time;
                    wait_time1 = wait_time1 + (time_now - v.get(0).arrival_time-v.get(0).burst_time);
                    // Draw process in excuetion.
                    processes_finished++;
                    v.remove(0);
                }
            }
        }
        times.add(time_now);
        sec++;

        double t []=new double[times.size()];
        int r = 0 ; while(r<times.size()) {
        t[r]=times.get(r);
        r++;
    }
        r=0;
        int pro[]=new int[pr.size()];
        while(r<pr.size()){
            pro[r]=pr.get(r);
            r++;
        }


        draw_gantt(t,pro,sec,time_now);
        double wait_time2 = wait_time1;
        wait_time2/=no_of_processes;
        wait_time.setText(Double.toString(wait_time2));
        System.out.println(wait_time2);
        System.out.println("Priority scheduling ended in : "+time_now);
    }


    void simulate_pr_pre()
    {

        Vector<Double> time = new Vector();
        Vector<Integer> pr = new Vector<>();
        int sec = 0 ;

        double time_now = 0 ;  int count = 0 ;
        double wait_time1 = 0 ;  int count2 = 0 ;
        int finished_processes = 0 ;
        Vector<process>arrived_processes=new Vector<>();
        process.arrange_fcfs(p,no_of_processes, process.type.fcfs);


        double arrival_times[]=new double[no_of_processes];
        while(count<no_of_processes)
        {
            arrival_times[count]=p.get(count).arrival_time;
            //System.out.println(p.get(count).burst_time);
            count++;
        }



        time_now = arrival_times[0];

        count=0;



        while(finished_processes<no_of_processes)
        {
            while (count<no_of_processes)
            {
                if(time_now>=arrival_times[count])
                {
                    //process temp = new process(p.get(count));
                    arrived_processes.add(p.get(count));
                    arrived_processes.get(arrived_processes.size()-1).time_left=p.get(count).burst_time;

                    //arrived_processes.get(arrived_processes.size()-1).priority=p.get(count).priority;
                    //System.out.println(temp.priority);
                    //System.out.println(p.get(arrived_processes.size()-1).priority);
                    count++;
                }
                else
                {
                    if(arrived_processes.isEmpty()) {
                        time.add(time_now);
                        pr.add(-1);
                        sec++;
                        time_now = arrival_times[count];  // Here we'll set time_now to the next time instead
                    }
                    else break;
                }
            }
             // Arrange arrived processes by priority



            process.arrange_priority(arrived_processes);

            //System.out.println(arrived_processes.get(0).priority);
            //System.out.println(arrived_processes.get(1).priority);
            //System.out.println(arrived_processes.get(2).priority);
            //System.out.println(arrived_processes.get(3).priority);


                while (finished_processes<no_of_processes)
                {
                    time.add(time_now);
                    pr.add(arrived_processes.get(0).order);
                    sec++;

                    if (count < no_of_processes && ((time_now + arrived_processes.get(0).time_left) >= arrival_times[count])) {
                        arrived_processes.get(0).time_left -= (arrival_times[count] - time_now);
                        time_now = arrival_times[count];
                        if (arrived_processes.get(0).time_left == 0) {
                            arrived_processes.remove(0);
                            wait_time1 += (time_now - arrived_processes.get(0).arrival_time -
                                    arrived_processes.get(0).burst_time);
                            finished_processes++;
                        }
                        break;
                    }

                    //arrived_processes.get(0).time_left--
                    else {
                        time_now += arrived_processes.get(0).time_left;
                        wait_time1 += (time_now - arrived_processes.get(0).arrival_time -
                                arrived_processes.get(0).burst_time);
                        arrived_processes.remove(0);
                        finished_processes++;
                    }
                    if ((count < no_of_processes && time_now >= arrival_times[count]) || arrived_processes.isEmpty())
                        break;

                }
        }

        // Write the waiting time in wait_time TextField


        time.add(time_now);
        sec++;

        double t[]=new double[time.size()];
        int r = 0 ;
        while(r<time.size())
        {
        t[r]=time.get(r);
        r++;
        }
        r=0;
        int pro[]=new int[pr.size()];
        while(r<pr.size()){
            pro[r]=pr.get(r);
            r++;
        }
        System.out.println("About to call gantt");
        draw_gantt(t,pro,sec,time_now);



        double wait_time2 = wait_time1;
        wait_time2/=no_of_processes;
        wait_time.setText(Double.toString(wait_time2));

    }





    void simulate_rr(){


        setRound_robin_time();

        Vector<Double> time = new Vector();
        Vector<Integer> pr = new Vector<>();
        int sec = 0 ;


        if(!rrt.getText().isEmpty()&&Double.parseDouble(rrt.getText())<=0)
        {
            System.out.println("Enter round robin time greater than zero");
            return;
        }

        // Sort all arrival times by order of arrival.
        process.arrange_fcfs(p,no_of_processes, process.type.fcfs);

        // Placing all arrival times in an array.
        double arrival_times[] = new double[no_of_processes];
        int count= 0 ;
        while(count<no_of_processes){
            arrival_times[count]=p.get(count).arrival_time;
            p.get(count).time_left = p.get(count).burst_time;
            System.out.println(count);
            count++;
        }
        Vector<process>v=new Vector<>();
        count=0; int finished_processes = 0 ; double wait_time1 = 0 ;
        double time_now = 0 ;
        int count2 = 0 ;

        time_now = arrival_times[0];

        while(finished_processes<no_of_processes)
        {
            while(count<no_of_processes)
            {
                if(time_now>=arrival_times[count])
                {
                    if(!v.isEmpty()) {v.add(0,p.get(count));}
                    else {v.add(p.get(count));}
                    System.out.println(v.get(v.size()-1).time_left);
                    count++;
                }
                else
                {
                    if(!v.isEmpty()) break;
                    else{
                        time.add(time_now);
                        pr.add(-1);
                        sec++;
                        time_now=arrival_times[count];
                    }
                }
            }

            // Loop on the given interval of processes until the arrival of the next one.

            if(!v.isEmpty())
            {
                boolean is_removed = false;
                count2 = 0 ;  // Every time we add a new process we loop from the first one.
                while(true)
                {
                    time.add(time_now);
                    pr.add(v.get(count2).order);
                    sec++;

                       if(v.get(count2).time_left>=round_robin_time)
                       {
                           v.get(count2).time_left -= round_robin_time;
                           time_now += round_robin_time;

                           if (v.get(count2).time_left == 0)
                           {
                               wait_time1 += (time_now-v.get(count2).arrival_time-v.get(count2).burst_time);
                               //System.out.println("One has finished : "+ time_now +" and with burst time = "+
                               //v.get(count2).burst_time);
                               finished_processes++;
                               is_removed=true;
                               v.remove(count2);
                           }
                           else{
                               is_removed=false;
                               if(v.size()>1) {
                                   Vector<process> v2 = new Vector<>();
                                   // Push element in the back of the queue
                                   int z = 1;
                                   process temp = v.get(0);
                                   while (z < v.size()) {
                                       v2.add(v.get(z));
                                       z++;
                                   }
                                   v2.add(temp);
                                   v = v2;
                                   int k = 0;
                                   while (k < v.size()) {
                                       System.out.println("p  " + v.get(k).order);
                                       k++;
                                   }
                                   System.out.println("End");
                               }
                           }
                       }

                    else
                    {
                        time_now+=v.get(count2).time_left;
                        wait_time1 += (time_now-v.get(count2).arrival_time-v.get(count2).burst_time);
                        //System.out.println("One has finished "+ time_now +"and with burst time = "+
                          //    v.get(count2).burst_time);
                        v.remove(count2);
                        is_removed = true;
                        finished_processes++;
                    }

                     //count2++;
                    if(count2>=v.size()) count2=0;
                    if( (count<no_of_processes && time_now>=arrival_times[count]) || v.isEmpty()
                            || finished_processes==no_of_processes ) break;
                }
            }
        }
        time.add(time_now);
        sec++;
        double t[]=new double[time.size()];
        int r = 0 ;
        while(r<time.size())
        {
            t[r]=time.get(r);
            r++;
        }
        r=0;
        int pro[]=new int[pr.size()];
        while(r<pr.size()){
            pro[r]=pr.get(r);
            r++;
        }
        draw_gantt(t,pro,sec,time_now);

        System.out.println("Finished at "+ time_now);
        double wait_time2 = wait_time1;
        wait_time2/=no_of_processes;
        wait_time.setText(Double.toString(wait_time2));
    }


    @FXML private BorderPane main_border ;
   // @FXML private TextField l1 ;  // TextField of number of processes.
    @FXML private ComboBox<String> c1 ;
    @FXML private TextField t2 ;  // TextField of arrival_time.
    @FXML private TextField t3 ;  // TextField of burst_time.
    @FXML private TextField t4 ;  // TextField of priority.
    @FXML private TableView<process>table ;
    @FXML private TableColumn<process,Integer> col1;
    @FXML private TableColumn<process,Integer> col2;
    @FXML private TableColumn<process,Integer> col3;
    @FXML private TableColumn<process,Integer> col4;
    @FXML private Button main_button;
    @FXML private TextField wait_time ;
    @FXML private TextField rrt ;
    @FXML private HBox gantt_chart ;
    @FXML private HBox time_bar ;
    @FXML private Button add_process;
    @FXML private Button clear;
    @FXML private Button delete;
    @FXML private ScrollPane sc;



   public ObservableList<process> getO(){
        return o;
    }


   public void getnoo(){
        System.out.println("hello");
    }
    public void gett(String j ){
        System.out.println(j);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       // main_border.setStyle("-fx-background:#9370DB");



        sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        add_process.setOnAction(e->add_new());
        t3.setOnAction(e->give_burst_time());
        t4.setOnAction(e->give_priority());
        main_button.setOnAction(e->simulate());
      //l1.setOnAction(e->setNo_of_processes());
     // l1.setStyle("-fx-background-color:#FF1493");

        delete.setOnAction(e->delete_one());
        clear.setOnAction(e->clear_all());

       c1.getItems().addAll("FCFS","SJF non preemptive","SJF preemptive"
       ,"priority non preemptive","priority preemptive","Round Robin");
        c1.setOnAction(e->setU()); // Handling combobox action.
        t2.setOnAction(e->giving_values()); //Handling arrival_time textfield.
        //rrt.setOnAction(e->setRound_robin_time()); //handling assigning round robin time.
    }
}
