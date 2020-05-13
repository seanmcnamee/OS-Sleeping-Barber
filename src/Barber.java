

public class Barber implements Runnable {
    public enum State {
        running, terminated;
    }

    private State state;

    private BarberShop barberShop;
    private final double serveChance = .000001;
    private double waitTime = 0;

    public Barber(BarberShop barberShop) {
        this.barberShop = barberShop;
    }

    @Override
    public void run() {
        this.state = State.running;
        //this.barberShop.printCustomers();
        

        //Try for customer
        Customer toServe = this.barberShop.takeCustomer();
        while (toServe!=null) {
            toServe.serve(); //Start serving customer 
            this.waitTime = System.currentTimeMillis();

            //Wait until the serveChance is met
            while (!(Math.random() < this.serveChance)) {
            }
            System.out.println((System.currentTimeMillis() - waitTime));

            toServe.leave(); //Customer leaves

            //Try for next customer
            toServe = this.barberShop.takeCustomer();
        }

        this.state = State.terminated;
    }

    public boolean isTerminated() {
        return (this.state == State.terminated);
    }

}
