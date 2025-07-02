package adapterDesignPattern;

public class AndroidPhone implements MiceroUsbPort{
    private boolean connector;


    @Override
    public void useMicroUsb() {

        connector = true;
        System.out.println("MicroUsb connected");

    }

    @Override
    public void recharge() {
        if (connector) {
            System.out.println("Recharge started");

        } else {
            System.out.println("Connect MicroUsb first");
        }


    }



}
