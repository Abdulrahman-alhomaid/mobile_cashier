/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileshop;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class DataBase {

    private String dataBaseName = "newDataBase.db";
    private Connection conn = null;

    DataBase() throws SQLException {
        createNewDatabase();
        System.out.println("done");

    }

    public void insertProduct(Product p) throws SQLException {
        final String INSERT_PRODUCT = "INSERT INTO productsTable(productID, productName,productColor,productType, productQuantity,  productPrice) VALUES(?, ?, ?, ?, ?, ?)";

        if (conn != null) {

            PreparedStatement ps = this.conn.prepareStatement(INSERT_PRODUCT);
            ps.setString(1, p.getProductID());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getProductColor());
            ps.setString(4, p.getProductType());
            ps.setInt(5, p.getProductQuantity());
            ps.setDouble(6, p.getProductPrice());

            int numRowsInserted = ps.executeUpdate();

            System.out.println("inserted" + numRowsInserted);

        }

    }

    public void updateToStock(Product p) throws SQLException {

        String select = "select  productQuantity  FROM productsTable WHERE productID   =  " + p.getProductID() + ";";

        Statement stmt = conn.createStatement();
        int productQuantityInStock = 0;

        ResultSet rs = stmt.executeQuery(select);
        boolean found = false;
        int i = 1;
        while (rs.next()) {
            System.out.println(i++);
            found = true;
            productQuantityInStock = rs.getInt(1);
        }
        if (found) {
            int newQuantity = productQuantityInStock + p.getProductQuantity();

            if (newQuantity > 0 || newQuantity == 0) {

                String sql = "UPDATE productsTable SET productQuantity   = ? "
                        + "WHERE productID  = ?;";
                System.out.println(sql);
                PreparedStatement pstmt2 = conn.prepareStatement(sql);

                pstmt2.setInt(1, newQuantity);
                pstmt2.setString(2, p.getProductID());

                pstmt2.executeUpdate();
                System.out.println("the stock for " + p.getProductName() + " has been updated  from " + p.getProductQuantity() + " to " + newQuantity);
            }
        }

    }

    public void soldItem(ArrayList<SoldProduct> w) throws SQLException {

        final String totdaySalesTable = "INSERT INTO totdaySalesTable "
                + "(salerID , salerName, productName,productID ,discountAmount   ,  salesPriceBeforDiscount,  salesPriceAfterDiscount, paymentMethod , quantity ,salesTime )"
                + "VALUES(?,? ,?,?, ?,?, ?,?, ?,?)";

        LocalDateTime myDateObj = LocalDateTime.now();

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String formattedDate = myDateObj.format(myFormatObj);
        for (SoldProduct s : w) {

            PreparedStatement ps = this.conn.prepareStatement(totdaySalesTable);
            ps.setString(1, s.getSalerID());
            ps.setString(2, s.getSalerName());
            ps.setString(3, s.getProductName());
            ps.setString(4, s.getProductID());
            ps.setString(5, (100 * s.getDiscountAmount()) + "%");
            ps.setDouble(6, s.getSalesPriceBeforDiscount());
            ps.setDouble(7, s.getSalesPriceAfterDiscount());
            ps.setString(8, s.getPaymentMethod());
            ps.setInt(9, s.getSoldQuantity());
            ps.setString(10, formattedDate);

            ps.executeUpdate();

        }
    }

    public String chechUserLogin(String s) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(s);
        String result = "";
        boolean found = false;
        while (rs.next()) {
            found = true;
            result = rs.getString(1);
        }
        if (found) {
            return result;
        }
        return result;
    }

    public ResultSet exSelect(String select) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(select);
        return rs;

    }

    public void checkStock(SoldProduct s) throws SQLException {

        String select = "select  productQuantity  FROM productsTable WHERE productID   =  " + s.getProductID() + ";";

        Statement stmt = conn.createStatement();
        int productQuantityInStock = 0;

        ResultSet rs = stmt.executeQuery(select);
        boolean found = false;
        int i = 1;
        while (rs.next()) {
            System.out.println(i++);
            found = true;
            productQuantityInStock = rs.getInt(1);
        }
        if (found) {
            int newQuantity = productQuantityInStock - s.getSoldQuantity();
        }

    }

    public void insertDiscountCode(DiscountCode d) throws SQLException {

        final String INSERT_DISCOUNT_CODE = "INSERT INTO discountCodesTable (discountId , discountAmount) VALUES(?, ?)";
        if (conn != null) {

            PreparedStatement ps = this.conn.prepareStatement(INSERT_DISCOUNT_CODE);
            ps.setString(1, d.getDiscountId());
            ps.setDouble(2, d.getDiscountAmount());

            int numRowsInserted = ps.executeUpdate();

            System.out.println("inserted" + numRowsInserted);

        }
    }

    public void insertNewInvoice(Invoice in) throws SQLException {

        final String INSERT_NEW_INVOICE = "INSERT INTO invoiceTable (invoiceMaker , invoiceDetales , numberOfItems , paymentAmount , paymentMeatod) VALUES(?, ?,?,?,?)";
        if (conn != null) {

            PreparedStatement ps = this.conn.prepareStatement(INSERT_NEW_INVOICE);
            ps.setString(1, in.getInvoiceMaker());
            ps.setString(2, in.getInvoiceDetales());
            ps.setString(3, in.getNumberOfItems());
            ps.setDouble(4, in.getPaymentAmount());
            ps.setString(5, in.getPaymentMeatod());
            int numRowsInserted = ps.executeUpdate();

            System.out.println("inserted" + numRowsInserted);

        }
    }

    public void insertEmployee(Employee e) throws SQLException {
        final String INSERT_EMPLOYEE = "INSERT INTO employeesTable (employeeID , employeeName , employeePassword ) VALUES(?, ?, ?)";
        if (conn != null) {

            PreparedStatement ps = this.conn.prepareStatement(INSERT_EMPLOYEE);
            ps.setString(1, e.getEmployeeID());
            ps.setString(2, e.getEmployeeName());
            ps.setString(3, e.getEmployeePassword());

            int numRowsInserted = ps.executeUpdate();

            System.out.println("inserted" + numRowsInserted);

        }
    }

    public double getDiscount(String id) throws SQLException {

        String select = "select  discountAmount   FROM discountCodesTable  WHERE discountId    =  '" + id + "';";
        System.out.println(select);
        Statement stmt = conn.createStatement();
        double discountAmount = 0;

        ResultSet rs = stmt.executeQuery(select);
        boolean found = false;

        while (rs.next()) {

            found = true;
            discountAmount = rs.getDouble(1);
        }
        if (found) {

            return discountAmount;
        } else {
            return 0;
        }
    }

    private void creatTables() throws SQLException {

        String employeesTable = "CREATE TABLE IF NOT EXISTS employeesTable (\n"
                + "	employeeID text PRIMARY KEY,\n"
                + "	employeeName text NOT NULL,\n"
                + "	employeePassword text NOT NULL\n"
                + ");";

        String productsTable = "CREATE TABLE IF NOT EXISTS productsTable (\n"
                + "	productID text PRIMARY KEY,\n"
                + "	productName text NOT NULL,\n"
                + "     productColor  text NOT NULL,\n"
                + "     productType  text NOT NULL,\n"
                + "     productQuantity  int NOT NULL,\n"
                + "	productPrice double NOT NULL\n"
                + ");";

        String discountCodesTable = "CREATE TABLE IF NOT EXISTS discountCodesTable (\n"
                + "	discountId text PRIMARY KEY,\n"
                + "	discountAmount double NOT NULL\n"
                + ");";
        String invoiceTable = "CREATE TABLE IF NOT EXISTS invoiceTable (\n"
                + "	invoiceId INTEGER  PRIMARY KEY,\n"
                + "	invoiceMaker text NOT NULL,\n"
                + "	invoiceDetales text NOT NULL,\n"
                + "	numberOfItems text NOT NULL,\n"
                + "	paymentAmount double NOT NULL,\n"
                + "	paymentMeatod text NOT NULL\n"
                + ");";

        String totdaySalesTable = "CREATE TABLE IF NOT EXISTS totdaySalesTable (\n"
                + "	salerID text NOT NULL,\n"
                + "	salerName text NOT NULL,\n"
                + "	productID text NOT NULL,\n"
                + "	productName text NOT NULL,\n"
                + "	discountAmount text , \n"
                + "	salesPriceBeforDiscount double NOT NULL,\n"
                + "	salesPriceAfterDiscount double NOT NULL,\n"
                + "	paymentMethod text NOT NULL,\n"
                + "	quantity int NOT NULL,\n"
                + "	salesTime text NOT NULL,\n"
                + "	FOREIGN KEY(salerID) REFERENCES employeesTable(employeeID),\n"
                + "	FOREIGN KEY(salerName) REFERENCES employeesTable(employeeName),\n"
                + "	FOREIGN KEY(productID) REFERENCES productsTable(productID)\n"
                + "	FOREIGN KEY(productName) REFERENCES productsTable(productName)\n"
                + ");";


        String phoneCare = "CREATE TABLE IF NOT EXISTS phoneCare (\n"
                + "	phoneCareId INTEGER  PRIMARY KEY,\n"
                + "	phoneName text NOT NULL,\n"
                + "	customerName text NOT NULL,\n"
                + "	customerNumber text NOT NULL,\n"
                + "	phoneProblem double NOT NULL,\n"
                + "	paymentMeatod text NOT NULL\n"
                + ");";
        Statement stmt = conn.createStatement();
        
        stmt.execute(employeesTable);
        stmt.execute(productsTable);
        stmt.execute(totdaySalesTable);
        stmt.execute(discountCodesTable);
        stmt.execute(invoiceTable);
        stmt.execute(phoneCare);

    }

    private void createNewDatabase() throws SQLException {

        String url = "jdbc:sqlite:" + dataBaseName;

        this.conn = DriverManager.getConnection(url);
        if (this.conn != null) {
            DatabaseMetaData meta = this.conn.getMetaData();
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("select * from sqlite_master where type='table' ;");

            creatTables();
            int i = 1;

//            while (rs.next()) {
//
//                System.out.println(rs.getString(i++));
//                System.out.println(rs.getString(i++));
//                System.out.println(rs.getString(i++));
//                System.out.println(rs.getString(i++));
//                System.out.println(rs.getString(i++));
//
//                System.out.println("=========================");
//                i = 1;
//
//            }
        }

    }

    public int update(String sql) {
        int found = -1;
        try {
            Statement st = conn.createStatement();
            found = st.executeUpdate(sql);
            System.out.println(found);
            return found;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return found;
    }
}
