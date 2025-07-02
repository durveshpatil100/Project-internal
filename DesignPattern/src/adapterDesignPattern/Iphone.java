package adapterDesignPattern;

public class Iphone implements LightningPort {

    private boolean connector;


    @Override
    public void useLightning() {

        connector = true;
        System.out.println("Lightning connected");

    }

    @Override
    public void recharge() {
        // if(connector) ? System.out.println("Recharge started"); : System.out.println("Connect lightning cable first");

        if (connector) {
            System.out.println("Recharge started");

        } else {
            System.out.println("Connect Lightning first");
        }
    }

}