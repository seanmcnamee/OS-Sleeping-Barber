import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BarberShop {
    private int maxChairs;
    private Queue<Customer> inShop;
    private ArrayList<Customer> everyCustomer;
    private Barber barber;

    //Initially, all the seats are open
    public BarberShop(int maxChairs) {
        this.maxChairs = maxChairs;
        this.everyCustomer = new ArrayList<Customer> ();
        this.inShop = new LinkedList<Customer> ();
        this.barber = new Barber(this);
        wakeUpBarber();
    }

    public synchronized boolean lockSeat(Customer customer) {
        if (inShop.size() < maxChairs) {
            this.printCustomers();
            this.inShop.add(customer);
            return true;
        }
        return false;
    }

    public synchronized Customer takeCustomer() {
        if (inShop.size() > 0) {
            return this.inShop.poll();
        }
        return null;
    }

    public boolean hasAsleepBarber() {
        return this.barber.isTerminated();
    }

    public void wakeUpBarber() {
        new Thread(this.barber).start();
    }

    public void createCustomer() {
        Customer newCust = new Customer(this);
        newCust.start();
        everyCustomer.add(newCust);
    }

    public void removeCustomer(Customer c) {
        everyCustomer.remove(c);
    }

    /**
     * print all the customers that have ever been here along with their current status
     */
    public void printCustomers() {
        System.out.print("Customers: ");
        for (int i = 0; i < everyCustomer.size(); i++) {
            System.out.print(everyCustomer.get(i).getCustomerState() + " ");
        }
        System.out.println("\n");
    }

}