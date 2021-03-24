/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileshop;

/**
 *
 * @author USER
 */
public class PhoneCare {
    
    private String phoneCareNumber;
    private String phoneName;
    private String customerName;
    private String customerNumber;
    private String phoneProblem;
    private double carePrice;

    public PhoneCare( String phoneName,  String phoneProblem ,String customerName, String customerNumber , double carePrice) {
        this.phoneCareNumber = phoneCareNumber;
        this.phoneName = phoneName;
        this.customerName = customerName;
        this.customerNumber = customerNumber;
        this.phoneProblem = phoneProblem;
        this.carePrice = carePrice;
    }

    public String getPhoneCareNumber() {
        return phoneCareNumber;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getPhoneProblem() {
        return phoneProblem;
    }
     public double getCarePrice() {
        return carePrice;
    }
    
}
