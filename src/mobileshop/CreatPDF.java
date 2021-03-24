/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileshop;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

/**
 *
 * @author USER
 */
public class CreatPDF {

    private ArrayList<SoldProduct> spLits;
    private ArrayList<Product> prLits;
    private DataBase db;

    public CreatPDF(DataBase db) {
        spLits = new ArrayList<SoldProduct>();
        prLits = new ArrayList<Product>();
        this.db = db;
    }

    public void printProducts() {

        try {
            String sql = "SELECT * FROM productsTable";
            ResultSet rs = db.exSelect(sql);
            boolean prFlag = false;
            while (rs.next()) {
                prFlag = true;
                //(String productID, String productName,String productColor  ,String productType, int productQuantity, double productPrice) {
                System.out.println(rs.getInt("productQuantity"));
                prLits.add(new Product(rs.getString("productID"), rs.getString("productName"), rs.getString("productColor"), rs.getString("productType"), rs.getInt("productQuantity"), rs.getDouble("productPrice"), 0.0));

            }
            Document document = new Document();
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("products.pdf"));
                document.open();

                PdfPTable table = new PdfPTable(6); // 3 columns.
                table.setWidthPercentage(100); //Width 100%
                table.setSpacingBefore(10f); //Space before table
                table.setSpacingAfter(10f); //Space after table

                //Set Column widths
                float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f};
                table.setWidths(columnWidths);

                PdfPCell prID = new PdfPCell(new Paragraph("prductID"));

                prID.setPaddingLeft(10);
                prID.setHorizontalAlignment(Element.ALIGN_CENTER);
                prID.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell prName = new PdfPCell(new Paragraph("productName"));

                prName.setPaddingLeft(10);
                prName.setHorizontalAlignment(Element.ALIGN_CENTER);
                prName.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell prCol = new PdfPCell(new Paragraph("productColor"));

                prCol.setPaddingLeft(10);
                prCol.setHorizontalAlignment(Element.ALIGN_CENTER);
                prCol.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell prType = new PdfPCell(new Paragraph("produtType"));

                prType.setPaddingLeft(10);
                prType.setHorizontalAlignment(Element.ALIGN_CENTER);
                prType.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell prQu = new PdfPCell(new Paragraph("productQu"));

                prQu.setPaddingLeft(10);
                prQu.setHorizontalAlignment(Element.ALIGN_CENTER);
                prQu.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell prPrice = new PdfPCell(new Paragraph("productPrice"));

                prPrice.setPaddingLeft(10);
                prPrice.setHorizontalAlignment(Element.ALIGN_CENTER);
                prPrice.setVerticalAlignment(Element.ALIGN_MIDDLE);

                table.addCell(prID);
                table.addCell(prName);
                table.addCell(prCol);
                table.addCell(prType);
                table.addCell(prQu);
                table.addCell(prPrice);
                for (Product p : prLits) {

                    PdfPCell cell1 = new PdfPCell(new Paragraph(p.getProductID()));

                    cell1.setPaddingLeft(10);
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell2 = new PdfPCell(new Paragraph(p.getProductName()));

                    cell2.setPaddingLeft(10);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell3 = new PdfPCell(new Paragraph(p.getProductColor()));

                    cell3.setPaddingLeft(10);
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell4 = new PdfPCell(new Paragraph(p.getProductType()));

                    cell1.setPaddingLeft(10);
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell5 = new PdfPCell(new Paragraph(p.getProductQuantity() + ""));

                    cell2.setPaddingLeft(10);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell6 = new PdfPCell(new Paragraph(p.getProductPrice() + ""));

                    cell3.setPaddingLeft(10);
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    table.addCell(cell4);
                    table.addCell(cell5);
                    table.addCell(cell6);

                }

                //To avoid having the cell border and the content overlap, if you are having thick cell borders
                //cell1.setUserBorderPadding(true);
                //cell2.setUserBorderPadding(true);
                //cell3.setUserBorderPadding(true);
                document.add(table);

                document.close();
                writer.close();
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("products.pdf");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException ex) {

            Logger.getLogger(CreatPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pritnTotdaySales() {

        try {
            String sql = "SELECT * FROM totdaySalesTable ";
            ResultSet rs = db.exSelect(sql);
            boolean slPrFlag = false;
            while (rs.next()) {
                slPrFlag = true;
                String ds = rs.getString("discountAmount");
                double dsInDouble = Double.parseDouble(ds.replace('%', ' '));

                spLits.add(new SoldProduct(rs.getString("salerName"), rs.getString("salerID"), rs.getString("productName"), rs.getString("productID"), dsInDouble, rs.getDouble("salesPriceBeforDiscount"), rs.getDouble("salesPriceAfterDiscount"), rs.getString("paymentMethod"), rs.getInt("quantity"), rs.getString("salesTime")));
            }
            Document document = new Document();
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("today.pdf"));
                document.open();

                PdfPTable table = new PdfPTable(9); // 3 columns.
                table.setWidthPercentage(100); //Width 100%
                table.setSpacingBefore(10f); //Space before table
                table.setSpacingAfter(10f); //Space after table

                //Set Column widths
                float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
                table.setWidths(columnWidths);

                PdfPCell saID = new PdfPCell(new Paragraph("salerID"));

                saID.setPaddingLeft(10);
                saID.setHorizontalAlignment(Element.ALIGN_CENTER);
                saID.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell saName = new PdfPCell(new Paragraph("salerName"));

                saName.setPaddingLeft(10);
                saName.setHorizontalAlignment(Element.ALIGN_CENTER);
                saName.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell prID = new PdfPCell(new Paragraph("productID"));

                prID.setPaddingLeft(10);
                prID.setHorizontalAlignment(Element.ALIGN_CENTER);
                prID.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell prName = new PdfPCell(new Paragraph("produtName"));

                prName.setPaddingLeft(10);
                prName.setHorizontalAlignment(Element.ALIGN_CENTER);
                prName.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell disAmount = new PdfPCell(new Paragraph("discoundAmount"));

                disAmount.setPaddingLeft(10);
                disAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
                disAmount.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell priceBeforDis = new PdfPCell(new Paragraph("priceBeforDis"));

                priceBeforDis.setPaddingLeft(10);
                priceBeforDis.setHorizontalAlignment(Element.ALIGN_CENTER);
                priceBeforDis.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell priceAfterDis = new PdfPCell(new Paragraph("priceAfterDis"));

                priceAfterDis.setPaddingLeft(10);
                priceAfterDis.setHorizontalAlignment(Element.ALIGN_CENTER);
                priceAfterDis.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell pametMrthod = new PdfPCell(new Paragraph("pametMrthod"));

                pametMrthod.setPaddingLeft(10);
                pametMrthod.setHorizontalAlignment(Element.ALIGN_CENTER);
                pametMrthod.setVerticalAlignment(Element.ALIGN_MIDDLE);
                PdfPCell qu = new PdfPCell(new Paragraph("qu"));

                qu.setPaddingLeft(10);
                qu.setHorizontalAlignment(Element.ALIGN_CENTER);
                qu.setVerticalAlignment(Element.ALIGN_MIDDLE);

                table.addCell(saID);
                table.addCell(saName);
                table.addCell(prID);
                table.addCell(prName);

                table.addCell(disAmount);
                table.addCell(priceBeforDis);
                table.addCell(priceAfterDis);
                table.addCell(pametMrthod);
                table.addCell(qu);
                double total = 0.0;
                int totalQu = 0;
                for (SoldProduct p : spLits) {
                    total += p.getSalesPriceAfterDiscount(new String("a"));
                    totalQu += p.getSoldQuantity();
                    PdfPCell cell1 = new PdfPCell(new Paragraph(p.getSalerID()));

                    cell1.setPaddingLeft(10);
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell2 = new PdfPCell(new Paragraph(p.getSalerName()));

                    cell2.setPaddingLeft(10);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell3 = new PdfPCell(new Paragraph(p.getProductID()));

                    cell3.setPaddingLeft(10);
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell4 = new PdfPCell(new Paragraph(p.getProductName()));

                    cell1.setPaddingLeft(10);
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell5 = new PdfPCell(new Paragraph(p.getDiscountAmount() + "%"));

                    cell2.setPaddingLeft(10);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell6 = new PdfPCell(new Paragraph(p.getSalesPriceBeforDiscount() + ""));

                    cell3.setPaddingLeft(10);
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell7 = new PdfPCell(new Paragraph(p.getSalesPriceAfterDiscount(new String("a")) + ""));

                    cell7.setPaddingLeft(10);
                    cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell8 = new PdfPCell(new Paragraph(p.getPaymentMethod()));

                    cell8.setPaddingLeft(10);
                    cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell cell9 = new PdfPCell(new Paragraph(p.getSoldQuantity() + ""));

                    cell9.setPaddingLeft(10);
                    cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    table.addCell(cell4);
                    table.addCell(cell5);
                    table.addCell(cell6);
                    table.addCell(cell7);
                    table.addCell(cell8);
                    table.addCell(cell9);

                }

                //To avoid having the cell border and the content overlap, if you are having thick cell borders
                //cell1.setUserBorderPadding(true);
                //cell2.setUserBorderPadding(true);
                //cell3.setUserBorderPadding(true);
                PdfPCell cell10 = new PdfPCell(new Paragraph(""));
                PdfPCell cell11 = new PdfPCell(new Paragraph(""));
                PdfPCell cell12 = new PdfPCell(new Paragraph(""));
                PdfPCell cell13 = new PdfPCell(new Paragraph(""));
                PdfPCell cell14 = new PdfPCell(new Paragraph(""));
                PdfPCell cell15 = new PdfPCell(new Paragraph(""));
                PdfPCell cell16 = new PdfPCell(new Paragraph(total + ""));
                PdfPCell cell17 = new PdfPCell(new Paragraph(""));
                PdfPCell cell18 = new PdfPCell(new Paragraph(totalQu + ""));
                table.addCell(cell10);
                table.addCell(cell11);
                table.addCell(cell12);
                table.addCell(cell13);
                table.addCell(cell14);
                table.addCell(cell15);
                table.addCell(cell16);
                table.addCell(cell17);
                table.addCell(cell18);

                document.add(table);

                String numberOfEmpl = "SELECT COUNT(*) FROM employeesTable;";

                ResultSet st = db.exSelect(numberOfEmpl);
                int a = st.getInt(1);

               // PdfPTable pdfArrau []  = new PdfPTable;//table1 = new PdfPTable(9); 
//                PdfPTable[] intArray = new PdfPTable[a]; 
//                for (int i = 0; i < a; i++) {
//                      PdfPTable table1 = getTable();
//                      document.add(table1);
//                }

                document.close();
                writer.close();
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("today.pdf");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {

                    }

                }

            } catch (DocumentException ex) {
                Logger.getLogger(CreatPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CreatPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {

            Logger.getLogger(CreatPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private PdfPTable getTable() {
      
        try {
            PdfPTable table = new PdfPTable(9); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table
            
            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);
            
            PdfPCell saID = new PdfPCell(new Paragraph("salerID"));
            
            saID.setPaddingLeft(10);
            saID.setHorizontalAlignment(Element.ALIGN_CENTER);
            saID.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell saName = new PdfPCell(new Paragraph("salerName"));
            
            saName.setPaddingLeft(10);
            saName.setHorizontalAlignment(Element.ALIGN_CENTER);
            saName.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell prID = new PdfPCell(new Paragraph("productID"));
            
            prID.setPaddingLeft(10);
            prID.setHorizontalAlignment(Element.ALIGN_CENTER);
            prID.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell prName = new PdfPCell(new Paragraph("produtName"));
            
            prName.setPaddingLeft(10);
            prName.setHorizontalAlignment(Element.ALIGN_CENTER);
            prName.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell disAmount = new PdfPCell(new Paragraph("discoundAmount"));
            
            disAmount.setPaddingLeft(10);
            disAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            disAmount.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell priceBeforDis = new PdfPCell(new Paragraph("priceBeforDis"));
            
            priceBeforDis.setPaddingLeft(10);
            priceBeforDis.setHorizontalAlignment(Element.ALIGN_CENTER);
            priceBeforDis.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell priceAfterDis = new PdfPCell(new Paragraph("priceAfterDis"));
            
            priceAfterDis.setPaddingLeft(10);
            priceAfterDis.setHorizontalAlignment(Element.ALIGN_CENTER);
            priceAfterDis.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell pametMrthod = new PdfPCell(new Paragraph("pametMrthod"));
            
            pametMrthod.setPaddingLeft(10);
            pametMrthod.setHorizontalAlignment(Element.ALIGN_CENTER);
            pametMrthod.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell qu = new PdfPCell(new Paragraph("qu"));
            
            qu.setPaddingLeft(10);
            qu.setHorizontalAlignment(Element.ALIGN_CENTER);
            qu.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            table.addCell(saID);
            table.addCell(saName);
            table.addCell(prID);
            table.addCell(prName);
            
            table.addCell(disAmount);
            table.addCell(priceBeforDis);
            table.addCell(priceAfterDis);
            table.addCell(pametMrthod);
            table.addCell(qu);
            double total = 0.0;
            int totalQu = 0;
            for (SoldProduct p : spLits) {
                total += p.getSalesPriceAfterDiscount(new String("a"));
                totalQu += p.getSoldQuantity();
                PdfPCell cell1 = new PdfPCell(new Paragraph(p.getSalerID()));
                
                cell1.setPaddingLeft(10);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                
                PdfPCell cell2 = new PdfPCell(new Paragraph(p.getSalerName()));
                
                cell2.setPaddingLeft(10);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                
                PdfPCell cell3 = new PdfPCell(new Paragraph(p.getProductID()));
                
                cell3.setPaddingLeft(10);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                
                PdfPCell cell4 = new PdfPCell(new Paragraph(p.getProductName()));
                
                cell1.setPaddingLeft(10);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                
                PdfPCell cell5 = new PdfPCell(new Paragraph(p.getDiscountAmount() + "%"));
                
                cell2.setPaddingLeft(10);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                
                PdfPCell cell6 = new PdfPCell(new Paragraph(p.getSalesPriceBeforDiscount() + ""));
                
                cell3.setPaddingLeft(10);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                
                PdfPCell cell7 = new PdfPCell(new Paragraph(p.getSalesPriceAfterDiscount(new String("a")) + ""));
                
                cell7.setPaddingLeft(10);
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
                
                PdfPCell cell8 = new PdfPCell(new Paragraph(p.getPaymentMethod()));
                
                cell8.setPaddingLeft(10);
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
                
                PdfPCell cell9 = new PdfPCell(new Paragraph(p.getSoldQuantity() + ""));
                
                cell9.setPaddingLeft(10);
                cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
                
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
                table.addCell(cell8);
                table.addCell(cell9);
                
            }
            
            //To avoid having the cell border and the content overlap, if you are having thick cell borders
            //cell1.setUserBorderPadding(true);
            //cell2.setUserBorderPadding(true);
            //cell3.setUserBorderPadding(true);
            PdfPCell cell10 = new PdfPCell(new Paragraph(""));
            PdfPCell cell11 = new PdfPCell(new Paragraph(""));
            PdfPCell cell12 = new PdfPCell(new Paragraph(""));
            PdfPCell cell13 = new PdfPCell(new Paragraph(""));
            PdfPCell cell14 = new PdfPCell(new Paragraph(""));
            PdfPCell cell15 = new PdfPCell(new Paragraph(""));
            PdfPCell cell16 = new PdfPCell(new Paragraph(total + ""));
            PdfPCell cell17 = new PdfPCell(new Paragraph(""));
            PdfPCell cell18 = new PdfPCell(new Paragraph(totalQu + ""));
            table.addCell(cell10);
            table.addCell(cell11);
            table.addCell(cell12);
            table.addCell(cell13);
            table.addCell(cell14);
            table.addCell(cell15);
            table.addCell(cell16);
            table.addCell(cell17);
            table.addCell(cell18);
            
            return  table;
        } catch (DocumentException ex) {
            Logger.getLogger(CreatPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

          return null;
    }

    public void printInvoice(Invoice in) {

        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AddTableExample.pdf"));
            document.open();

            PdfPTable table = new PdfPTable(2); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

//            Table table = new Table(UnitValue.createPointArray(new float[]{60f, 180f, 50f, 80f, 110f}));
//
//            // headers
//            table.addCell(new Paragraph("S.N.O.").setBold());
//            table.addCell(new Paragraph("PARTICULARS").setBold());
//            table.addCell(new Paragraph("QTY").setBold());
//            table.addCell(new Paragraph("RATE").setBold());
//            table.addCell(new Paragraph("AMOUNT IN RS.").setBold());
//
//            // items
//            for (Article a : articleList) {
//                table.addCell(new Paragraph(a.SNO + ""));
//                table.addCell(new Paragraph(a.description));
//                table.addCell(new Paragraph(a.quantity + ""));
//                table.addCell(new Paragraph(a.unitPrice + ""));
//                table.addCell(new Paragraph((a.quantity * a.unitPrice) + ""));
//            }
//
//            layoutDocument.add(table);
            //Set Column widths
            float[] columnWidths = {1f, 1f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
            cell1.setBorderColor(BaseColor.BLUE);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
            cell2.setBorderColor(BaseColor.GREEN);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
            cell3.setBorderColor(BaseColor.RED);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            //To avoid having the cell border and the content overlap, if you are having thick cell borders
            //cell1.setUserBorderPadding(true);
            //cell2.setUserBorderPadding(true);
            //cell3.setUserBorderPadding(true);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);

            document.add(table);

            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printInvoice(String invoice) throws IOException {
        try {
            PrintService mPrinter = null;
            Boolean bFoundPrinter = false;

            PrintService[] printServices = PrinterJob.lookupPrintServices();

            for (PrintService printService : printServices) {
                String sPrinterName = printService.getName();
                if (sPrinterName.equals("Black Cobra")) {
                    mPrinter = printService;
                    bFoundPrinter = true;
                }
            }
            String testData = invoice + "\f";
            InputStream is = new ByteArrayInputStream(testData.getBytes());
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            System.out.println(service);

            DocPrintJob job = service.createPrintJob();
            Doc doc = new SimpleDoc(is, flavor, null);

            PrintJobWatcher pjDone = new PrintJobWatcher(job);

            job.print(doc, null);

            pjDone.waitForDone();

            is.close();
        } catch (PrintException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  

    static class PrintJobWatcher {

        boolean done = false;

        PrintJobWatcher(DocPrintJob job) {
            // Add a listener to the print job
            job.addPrintJobListener(new PrintJobAdapter() {
                public void printJobCanceled(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobCompleted(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobFailed(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobNoMoreEvents(PrintJobEvent pje) {
                    allDone();
                }

                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }

        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
