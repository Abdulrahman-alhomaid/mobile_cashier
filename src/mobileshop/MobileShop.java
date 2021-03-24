/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileshop;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MobileShop {

    public static void main(String[] args) {

        DataBase db = null;
        try {
            db = new DataBase();
            CreatPDF cp = new CreatPDF(db);
        } catch (SQLException ex) {
            Logger.getLogger(MobileShop.class.getName()).log(Level.SEVERE, null, ex);
        }
//        int hash = 7;
//        int strlen = 10;
//        String a = "1111111111";
//        for (int i = 0; i < strlen; i++) {
//            hash = hash * 31 + a.charAt(i);
//        }
        String stringToEncrypt = "ali";
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(stringToEncrypt.getBytes());
            String encryptedString = new String(messageDigest.digest());
            System.out.println(encryptedString);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MobileShop.class.getName()).log(Level.SEVERE, null, ex);
        }

//        String w = "";
//        Object[] options = {"Yes, please",
//            "No way!"};
//
//        String s = (String) JOptionPane.showInputDialog(
//                null,
//                "",
//                "",
//                JOptionPane.ERROR_MESSAGE,
//                null,
//                null,
//                w);
//        System.out.println(s);
//        Product p = new Product("4", "iphon", "red", "1010", 5, 4000);
//        Product p2 = new Product("5", "huwiwe", "red", "2020", 2, 8000);
//        Product p3 = new Product("6", "samsung", "red", "3030", 9, 1500);
//
//        Employee e = new Employee("0002", "a", "11");
//
//        DiscountCode ds3 = new DiscountCode("bl00", 0.1);
//        DiscountCode ds2 = new DiscountCode("as22", 0.05);
//        DiscountCode ds1 = new DiscountCode("wx99", 0.5);
//
//        try {
//
//            db.insertProduct(p);
//            db.insertProduct(p2);
//            db.insertProduct(p3);
//            db.insertEmployee(e);
//            db.insertDiscountCode(ds2);
//            db.insertDiscountCode(ds3);
//            db.insertDiscountCode(ds1);
//
//        } catch (SQLException ex) {
//            System.out.println("1111");
//            System.out.println(ex.getMessage());
//        }
//        try {
//            db.updateToStock(p);
//        } catch (SQLException ex) {
//            System.out.println("can not updated stock");
//        }
//
//        try {
//            Scanner kb = new Scanner(System.in);
//            String code1 = "as22";
//            String code2 = "bl00";
//            String code3 = "wx99";
//            double d1 = db.getDiscount(code1);
//            double d2 = db.getDiscount(code2);
//            double d3 = db.getDiscount(code3);
//            System.out.println(d1 + " " + d2 + " " + d3);
//            System.out.println("enetr the discount fort the ");
//            SoldProduct sp = new SoldProduct(e.getEmployeeName(), e.getEmployeeID(), p.getProductName(), p.getProductID(), d1, p.getProductPrice(), "cash", 1);
//            SoldProduct sp1 = new SoldProduct(e.getEmployeeName(), e.getEmployeeID(), p2.getProductName(), p2.getProductID(), d2, p2.getProductPrice(), "card", 1);
//            SoldProduct sp2 = new SoldProduct(e.getEmployeeName(), e.getEmployeeID(), p3.getProductName(), p3.getProductID(), d3, p3.getProductPrice(), "cash", 1);
//            ArrayList<SoldProduct> l = new ArrayList<>();
//            l.add(sp);
//            l.add(sp1);
//            l.add(sp2);
//            db.soldItem(l);
//
//            System.out.println("SOLD");
//
//        } catch (SQLException ex) {
//            Logger.getLogger(MobileShop.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
