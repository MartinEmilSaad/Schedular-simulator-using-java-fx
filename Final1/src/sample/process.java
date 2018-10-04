package sample;


import java.util.LinkedList;
import java.util.Vector;

public class process {
    double arrival_time ;
    double burst_time;
    double departure_time;
    double time_left;
    double waiting_time ;
    int order ;
    int priority ;

    public double getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }



    enum type {None ,fcfs , sjf_np , rr , sjf_p , pr_np , pr_p };


    /*** Constructor for temp process ***/

    process(process g){
        waiting_time=g.waiting_time;
        burst_time=g.burst_time;
        arrival_time=g.arrival_time;
        departure_time=g.departure_time;
        time_left=g.time_left;
        order=g.order;
    }


    /*** Constructor for intiallizing linkedlist  ***/

    process(){
        this.arrival_time=0; this.departure_time=0;
        this.waiting_time=0; this.time_left=0;
        this.burst_time=0 ;  this.order=-1;
        this.priority=0;
    }


    /***    Constructor for fcfs    ***/
    public process(double arrival_time , double burst_time) {
        this.arrival_time = arrival_time;
        this.burst_time=burst_time;
    }


    public double getArrival_time() {
        return arrival_time;
    }

    public double getBurst_time() {
        return burst_time;
    }

    public double getDeparture_time() {
        return departure_time;
    }

    public double getTime_left() {
        return time_left;
    }

    public double getWaiting_time() {
        return waiting_time;
    }

    public double getOrder() {
        return order;
    }

    /*** Constructor for shortest job first non-preemptive ***/
    public process(int burst_time){
        this.burst_time=burst_time;
    }

    /*** arrange list according to arrival time. no is the number of processes ***/

    static void arrange_fcfs(LinkedList<process> p , int no , type u )
    {
        for(int i = 0 ; i<no ; i++)
        {
            for(int j = i+1 ; j<no ; j++)
            {
                if ((p.get(i).arrival_time > p.get(j).arrival_time && u == type.fcfs) ||
                        (p.get(i).burst_time > p.get(j).burst_time && u == type.sjf_np) )
                {
                    process temp = new process();

                    temp.arrival_time=p.get(i).arrival_time;
                    temp.burst_time=p.get(i).burst_time;
                    temp.waiting_time=p.get(i).waiting_time;
                    temp.departure_time=p.get(i).departure_time;
                    temp.time_left=p.get(i).time_left;
                    temp.order=p.get(i).order;
                    temp.priority=p.get(i).priority;


                    //System.out.println("Swap will take place");

                    // setting p[i] parameters
                    p.get(i).arrival_time = p.get(j).arrival_time;
                    p.get(i).burst_time = p.get(j).burst_time;
                    p.get(i).departure_time = p.get(j).departure_time;
                    p.get(i).waiting_time = p.get(j).waiting_time;
                    p.get(i).order = p.get(j).order;
                    p.get(i).time_left = p.get(j).time_left;
                    p.get(i).priority = p.get(j).priority;

                    // Setting p[j] parameters
                    p.get(j).arrival_time = temp.arrival_time;
                    p.get(j).burst_time = temp.burst_time;
                    p.get(j).departure_time = temp.departure_time;
                    p.get(j).waiting_time = temp.waiting_time;
                    p.get(j).order = temp.order;
                    p.get(j).time_left = temp.time_left;
                    p.get(j).priority = temp.priority;
                }
            }
        }
    }

    static void arrange_sjfp (Vector<process> p )
    {
        for (int i = 0 ; i<p.size() ; i++)
        {
            for (int j = i+1 ; j < p.size() ; j++ )
            {
                if( (p.get(i).time_left>p.get(j).time_left) && (p.get(j).time_left>0) )
                {
                    process temp = new process();

                    temp.arrival_time=p.get(i).arrival_time;
                    temp.burst_time=p.get(i).burst_time;
                    temp.waiting_time=p.get(i).waiting_time;
                    temp.departure_time=p.get(i).departure_time;
                    temp.time_left=p.get(i).time_left;
                    temp.order=p.get(i).order;
                    temp.priority=p.get(i).priority;



                   // System.out.println("Swap will take place");

                    // setting p[i] parameters
                    p.get(i).arrival_time=p.get(j).arrival_time;
                    p.get(i).burst_time=p.get(j).burst_time;
                    p.get(i).departure_time=p.get(j).departure_time;
                    p.get(i).waiting_time=p.get(j).waiting_time;
                    p.get(i).order=p.get(j).order;
                    p.get(i).time_left=p.get(j).time_left;

                    // Setting p[j] parameters
                    p.get(j).arrival_time=temp.arrival_time;
                    p.get(j).burst_time=temp.burst_time;
                    p.get(j).departure_time=temp.departure_time;
                    p.get(j).waiting_time=temp.waiting_time;
                    p.get(j).order=temp.order;
                    p.get(j).time_left=temp.time_left;
                }
            }
        }
    }

    static void arrange_priority(Vector<process>v)
    {
        int count1 = 0 ;
        for (int i = 0 ; i<v.size();i++)
        {
            for (int j = i+1 ; j<v.size() ; j++)
            {
                if(v.get(i).priority>v.get(j).priority)
                {
                    count1 ++;
                    process temp = new process();

                    temp.arrival_time=v.get(i).arrival_time;
                    temp.burst_time=v.get(i).burst_time;
                    temp.waiting_time=v.get(i).waiting_time;
                    temp.departure_time=v.get(i).departure_time;
                    temp.time_left=v.get(i).time_left;
                    temp.order=v.get(i).order;
                    temp.priority=v.get(i).priority;


                    //System.out.println(v.get(i).priority);

                  //  System.out.println("the 2 priorities are : "+ v.get(i).priority +" and  "
                    //+v.get(j).priority);

                        //System.out.println("Swap will take place  " +  i   +  "        " +  j);

                    // setting p[i] parameters
                    v.get(i).arrival_time=v.get(j).arrival_time;
                    v.get(i).burst_time=v.get(j).burst_time;
                    v.get(i).departure_time=v.get(j).departure_time;
                    v.get(i).waiting_time=v.get(j).waiting_time;
                    v.get(i).order=v.get(j).order;
                    v.get(i).time_left=v.get(j).time_left;
                    v.get(i).priority=v.get(j).priority;

                    // Setting p[j] parameters
                    v.get(j).arrival_time=temp.arrival_time;
                    v.get(j).burst_time=temp.burst_time;
                    v.get(j).departure_time=temp.departure_time;
                    v.get(j).waiting_time=temp.waiting_time;
                    v.get(j).order=temp.order;
                    v.get(j).time_left=temp.time_left;
                    v.get(j).priority=temp.priority;

                }
            }
        }
    }

}
