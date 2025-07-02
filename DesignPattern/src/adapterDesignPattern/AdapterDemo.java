package adapterDesignPattern;

public class AdapterDemo {

    public static void rechargeAndroidPhone(MiceroUsbPort miceroUsbPort){
        miceroUsbPort.useMicroUsb();
        miceroUsbPort.recharge();
    }

    public static void rechrageIPhone(LightningPort lightningPort){
        lightningPort.useLightning();
        lightningPort.recharge();
    }

    public static void main(String[] args) {
        AndroidPhone androidPhone = new AndroidPhone();
        Iphone iphone = new Iphone();

        System.out.println("Recharge Android with MicroUsb");
        rechargeAndroidPhone(androidPhone);

        System.out.println("Recharge Iphone with lightningCable");
        rechrageIPhone(iphone);

        System.out.println("Recharge iphone with microusb");
        rechargeAndroidPhone(new LightningToMiceroUsbAdapter(iphone));
    }
}
