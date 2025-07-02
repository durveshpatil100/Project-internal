package v1;

import java.util.ArrayList;

public class Customer {

    private int rollNum;
    private String name;
    private String location;
    private long phoneNum;
    private boolean status;


    public Customer(int rollNum, String name, String location, long phoneNum, boolean status) {
        this.rollNum = rollNum;
        this.name = name;
        this.location = location;
        this.phoneNum = phoneNum;
        this.status = status;
    }

    public Customer() {

    }

    @Override
    public String toString() {
        return "Customer{" +
                "rollNum=" + rollNum +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", phoneNum=" + phoneNum +
                ", status=" + status +
                '}';
    }

    public static void main(String[] args) {
       Customer customer = new Customer(11,"abc","pune",4454,true);

        System.out.println(customer);


        int num1 = 500;
        int num2 = 500;
        if(num1 == num2){
            System.out.println("yes");
        }
        else{
            System.out.println("no");
        }
        System.out.println(num1);

        String str= "abc";
         str = "def";

        System.out.println(str);
        

    }
}
