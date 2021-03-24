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
public class DiscountCode {

    private String discountId;
    private double discountAmount;

    public DiscountCode(String discountId, double discountAmount) {
        this.discountId = discountId;
        this.discountAmount = discountAmount;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountId() {
        return discountId;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

}
