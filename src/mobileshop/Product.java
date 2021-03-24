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
public class Product {

    private String productID;
    private String productType;
    private String productName;
    private String productColor ;
    private double discoundAmount;
    private int productQuantity;
    private double productPrice;

    public Product(String productID, String productName,String productColor  ,String productType, int productQuantity, double productPrice , double discoundAmount) {

        this.productID = productID;
        this.productName = productName;
        this.productType = productType;
        this.productColor  = productColor  ;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.discoundAmount = discoundAmount;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public double getDiscoundAmount() {
        return discoundAmount;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public double getProductPrice() {
        return productPrice ;
    }

    public String getProductColor() {
        return productColor;
    }

    @Override
    public String toString() {
        return  "productID=" + productID + ", productType=" + productType + ", productName=" + productName + ", productColor=" + productColor + ", productQuantity=" + productQuantity + ", productPrice=" + productPrice;
    }
    
    
    public String addToinvoic() {
        return " " + productName + ", " + productColor + ", " + productQuantity + ", " + productPrice +" rs" + ", " + discoundAmount*100 +"% ";
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

}
