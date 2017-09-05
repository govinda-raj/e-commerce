package com.poc.utilities;

/**
 * Created by coviam on 30/07/17.
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.poc.model.Item;
//import com.poc.model.Order;
//import com.zetcode.bean.City;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.coviam.model.*;


public class GeneratePdfReport {

    public static void invoiceReport(Order orderdetails) {

        Document document = new Document();
        //ByteArrayOutputStream out = new ByteArrayOutputStream();
        Double totalAmmount=0.0;


        try {
            OutputStream file = new FileOutputStream(new File("Invoice.pdf"));

            PdfPTable table = new PdfPTable(4);
            //table.setWidthPercentage(60);
            //table.setWidths(new int[]{1, 4, 4});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Product Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Quantity", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Total", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (Item item : orderdetails.getItems()) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(item.getProductName()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(item.getProductQuantity().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(item.getProductPrice().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                Double total=item.getProductPrice()*item.getProductQuantity();
                cell = new PdfPCell(new Phrase(total.toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
                totalAmmount=totalAmmount+total;
            }
            //PdfWriter pdfWriter=new PdfWriter();

            PdfWriter.getInstance(document, file);

            document.open();
            document.add(new Paragraph("Invoice"));
            document.add(new Paragraph(new Date().toString()));
            document.add(new Paragraph(""));

            document.addAuthor("Govinda Raj ");
            document.addCreationDate();
            document.addCreator("Govinda");
            document.addTitle("Sample PDF");
            document.add(new Paragraph(""));



            //Adding Table
            document.add(table);

            Paragraph paragraph1 = new Paragraph("Total payable amount",new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD));
            paragraph1.add(new Paragraph(" "));
            paragraph1.add("Total Amount :" +totalAmmount.toString());
            paragraph1.add(new Paragraph(" "));
            document.add(paragraph1);



            //Create Paragraph
            Paragraph paragraph = new Paragraph("Regards",new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD));

            //New line

            paragraph.add(new Paragraph(" "));
            paragraph.add("Govinda and Malati");
            paragraph.add(new Paragraph(" "));
            paragraph.add("Thank you for shopping with us.");
            paragraph.add(new Paragraph(" "));
            document.add(paragraph);


            //document.addCreationDate();
            document.close();
            file.close();
            System.out.println("File created");


        } catch (Exception ex) {
            System.out.println(ex);
        }

        //return new ByteArrayInputStream(out.toByteArray());
    }

    public static void merchantReport(Integer noOfOrder, Double profit) {

        Document document = new Document();
        //ByteArrayOutputStream out = new ByteArrayOutputStream();
        //Double totalAmmount=0.0;


        try {
            OutputStream file = new FileOutputStream(new File("Merchant.pdf"));
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfWriter.getInstance(document, file);
            document.open();
            document.add(new Paragraph("Merchant Report"));
            document.add(new Paragraph(new Date().toString()));
            document.add(new Paragraph(""));

            Paragraph orderParagraph = new Paragraph("Total Order",new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD));
            orderParagraph.add(new Paragraph(" "));
            orderParagraph.add("Total Order :" +noOfOrder.toString());
            orderParagraph.add(new Paragraph(" "));
            document.add(orderParagraph);

            Paragraph profitParagraph = new Paragraph("Total Profit",new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD));
            profitParagraph.add(new Paragraph(" "));
            profitParagraph.add("Total Profit :" +profit.toString());
            profitParagraph.add(new Paragraph(" "));
            document.add(profitParagraph);

            document.close();
            file.close();

        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        }

    }