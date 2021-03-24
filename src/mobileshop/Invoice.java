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
public class Invoice {

    private String invoiceMaker;
    private String numberOfItems;
    private String invoiceDetales;
    private double paymentAmount;
    private String paymentMeatod;

    public Invoice(String invoiceMaker, String numberOfItems, String invoiceDetales, double paymentAmount, String paymentMeatod) {
        this.invoiceMaker = invoiceMaker;
        this.numberOfItems = numberOfItems;
        this.invoiceDetales = invoiceDetales;
        this.paymentAmount = paymentAmount;
        this.paymentMeatod = paymentMeatod;
    }

    public String getInvoiceMaker() {
        return invoiceMaker;
    }

    public String getNumberOfItems() {
        return numberOfItems;
    }

    public String getInvoiceDetales() {
        return invoiceDetales;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentMeatod() {
        return paymentMeatod;
    }

}
