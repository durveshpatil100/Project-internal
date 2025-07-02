package adapterDesignPattern;

public class LightningToMiceroUsbAdapter implements MiceroUsbPort{

    private final LightningPort lightningPort;

    public LightningToMiceroUsbAdapter(LightningPort lightningPort) {
        this.lightningPort = lightningPort;
    }



    @Override
    public void useMicroUsb() {

        System.out.println("Microusb connected");
        lightningPort.useLightning();
    }

    @Override
    public void recharge() {

        lightningPort.recharge();

    }
}
