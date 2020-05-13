
public class App {
    public static BarberShop barberShop;
    public static double newCustomerChance = .00001;

    public static void main(String[] args) {
        barberShop = new BarberShop(5);
        loop();
    }

    public static void loop() {
        while (true) {
            createCustomer();
        }
    }

    public static boolean createCustomer() {
        if (Math.random() < newCustomerChance) {
            barberShop.createCustomer();
            return true;
        }
        return false;
    }
}
