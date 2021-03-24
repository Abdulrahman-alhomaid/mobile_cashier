/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileshop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author USER
 */
public class SoldProduct {

    private String salerName;
    private String salerID;
    private String productID;
    private String productName;
    private int soldQuantity;
    private double discountAmount;
    private double salesPriceBeforDiscount;
    private double salesPriceAfterDiscount;
    private String paymentMethod;
    private String salesTime;

    public SoldProduct(String salerName, String salerID,String productName ,String productID, double discountAmount, double salesPriceBeforDiscount, String paymentMethod, int soldQuantity) {

        LocalDateTime myDateObj = LocalDateTime.now();

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String formattedDate = myDateObj.format(myFormatObj);
        //System.out.println("After formatting: " + formattedDate);
        this.productName = productName;
        this.soldQuantity = soldQuantity;
        this.salerID = salerID;
        this.salerName = salerName;
        this.productID = productID;
        this.discountAmount = discountAmount;
        this.salesPriceBeforDiscount = salesPriceBeforDiscount;
        
        this.paymentMethod = paymentMethod;
        this.salesTime = formattedDate;

    }
    public SoldProduct(String salerName, String salerID,String productName ,String productID, double discountAmount, double salesPriceBeforDiscount,double salesPriceAfterDiscount, String paymentMethod, int soldQuantity , String salesTime) {

      
        this.productName = productName;
        this.soldQuantity = soldQuantity;
        this.salerID = salerID;
        this.salerName = salerName;
        this.productID = productID;
        this.discountAmount = discountAmount;
        this.salesPriceBeforDiscount = salesPriceBeforDiscount;
        this.salesPriceAfterDiscount = salesPriceAfterDiscount;

        this.paymentMethod = paymentMethod;
        this.salesTime = salesTime;

    }

    public String getProductName() {
        return productName;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public String getSalerID() {
        return salerID;
    }

    public String getSalerName() {
        return salerName;
    }

    public String getProductID() {
        return productID;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getSalesPriceBeforDiscount() {
        return salesPriceBeforDiscount;
    }



    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getSalesTime() {
        return salesTime;
    }

    public double getSalesPriceAfterDiscount() {

        return this.salesPriceAfterDiscount = this.salesPriceBeforDiscount - (this.salesPriceBeforDiscount * discountAmount);

    }
   public double getSalesPriceAfterDiscount(String w) {

        return this.salesPriceAfterDiscount ;

    }
}
