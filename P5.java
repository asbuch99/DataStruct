/*
Anvay Buch
Grocery Store Simulation
Did a tutoring session with Eric Grandizio for help understanding how to start off
*/

import java.util.Random;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class P5 {

    public static Random random = new Random();

    static class Customer implements Comparable<Customer> {
        int num; // number of the customer.
        public int arrivalTime; // time customer arrives at check-out line (in seconds since 7:00 AM).
        public int taskTime; // number of seconds it will take to serve the customer.
        public int serviceTime; // time (in seconds since 7:00 AM) that customer was served.
        public int waitTime; // variable to keep track of avg wait time

        public Customer() {
        }

        public Customer(int num, int arrivalTime, int taskTime) {
            this.num = num;
            this.arrivalTime = arrivalTime;
            this.taskTime = taskTime;
            serviceTime = -1; // customer not served yet.
        }

        public String toString() {
            return "(" + num + ":" + arrivalTime + "," + taskTime + "," + serviceTime + ","
                    + (serviceTime - arrivalTime) + ")";
        }

        public int compareTo(Customer a) {
            return arrivalTime - a.arrivalTime;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setServiceTime(int serviceTime) {
            this.serviceTime = serviceTime;
        }

        public int endTime() {
            return serviceTime + taskTime;
        }
    }

    static class Cashier {
        int num; // number of the cashier.
        public Customer customer; // customer that the cashier is waiting on.
        public int idleTime; // time that the cashier becomes idle.

        public Cashier() {
        }

        public Cashier(int num, Customer customer, int idleTime) {
            this.num = num;
            this.customer = customer;
            this.idleTime = idleTime;
        }

        public Cashier(int num) {
            this(num, null, 0);
        }

        public String toString() {
            return "(" + num + ": " + customer + "," + idleTime + ")";
        }

        public void setIdleTime(int idleTime) {
            this.idleTime = idleTime;
        }

        public static double avgIdleTime(Cashier[] totalcash) {
            double avgidle = 0;
            for (int i = 0; i < totalcash.length; i++) {
                avgidle += totalcash[i].idleTime;
            }
            avgidle /= totalcash.length;
            return avgidle;
        }
    }

    public static Customer[] genCustomers(int N) {
        Customer[] customers = new Customer[N];
        for (int i = 0; i < N; i++) {
            int arrivalTime = (int) (Math.max(Math.min((random.nextGaussian() + 3.25) / 7 * 54000, 54000 - 1), 0));
            int taskTime = (int) (Math.max((random.nextGaussian() + 3.25) * 60, 60));
            customers[i] = new Customer(0, arrivalTime, taskTime);
        }
        Arrays.sort(customers);
        for (int i = 0; i < N; i++)
            customers[i].setNum(i);
        return customers;
    }

    public static Cashier[] genCashiers(int x) { // randomize number generated
        Cashier[] cash = new Cashier[x];
        for (int i = 0; i < x; i++) {
            cash[i] = new Cashier(i);
        }
        return cash;
    }

    public static void main(String[] args) {

        Customer[] cust = genCustomers(1000);
        Cashier[] cashier = genCashiers(6);
        int time = 0;
        double totaltasktime = 0;
        double avgwait = 0;
        Queue<Customer> chkoutline = new LinkedList<>() {

        };

        for (time = 0; time < 54001 || !chkoutline.isEmpty(); time++) {
            for (int i = 0; i < 1000; i++) {
                if (cust[i].arrivalTime == time) {
                    chkoutline.add(cust[i]);
                    for (int y = 0; y < cashier.length; y++) {
                        if (cashier[y].customer == null && !chkoutline.isEmpty()) {
                            Customer tempcustomer = chkoutline.remove();
                            totaltasktime = totaltasktime + tempcustomer.taskTime;
                            for (int sertime = 0; sertime <= tempcustomer.taskTime; sertime++) {
                                cashier[y].customer = tempcustomer;
                            }
                            cashier[y].customer = null;

                        } else if (chkoutline.isEmpty()) {
                            cashier[y].idleTime++;
                        } else if (cashier[y].customer != null) {
                            for (int x = 0; x < chkoutline.size(); x++) {
                                avgwait++;
                            }
                        }
                    }
                }
            }

        }
        System.out.println("Number of cashiers = " + cashier.length);
        System.out.println("Number of customers = 1000");
        System.out.println("Average wait time = " + avgwait);
        System.out.println("Average idle time = " + Cashier.avgIdleTime(cashier) + " seconds");
        System.out.println("Total task time = " + totaltasktime + " seconds");
        System.out.println("Last customer was finished being served at time:");

    }

}
