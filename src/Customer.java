public class Customer extends Thread {
    public enum State {
        wandering, entering, waiting, beingServiced, left;
    } 

    private State currentState;
    private BarberShop barberShop;

    public Customer(BarberShop barberShop) {
        currentState = State.wandering;
        this.barberShop = barberShop;
    }

    @Override
    public void run() {

        if (this.currentState == State.wandering) {
            enterStore();
        }
        if (this.currentState == State.waiting) {
            wakeUpBarberOrKeepWaiting();
        }
    }

    private void enterStore() {
        this.currentState = State.entering;

        //Try to grab a seat in the barbershop, otherwise, die.
        if (barberShop.lockSeat(this)) {
            this.currentState = State.waiting;
        }   else {
            //Remove from ArrayList
            this.currentState = State.wandering;
            this.barberShop.removeCustomer(this);
        }
    }

    private void wakeUpBarberOrKeepWaiting() {
        if (barberShop.hasAsleepBarber()) {
            barberShop.wakeUpBarber();
        }
    }

    public void serve() {
        this.currentState = State.beingServiced;
    }

    public void leave() {
        this.currentState = State.left;
    }

    public String getCustomerState() {
        return currentState.toString();
    }
    
}