package mx.com.sidlors.itext.barcode;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;


public class CreateWatermarkedPDF
{
		public static void main(String[] args)
		{
				try
				{
						Document document = new Document();
						PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
						
						document.open();
						PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
						
						Barcode128 barcode128 = new Barcode128();
						barcode128.setCode("examples.javacodegeeks.com/author/chandan-singh");
						barcode128.setCodeType(Barcode128.CODE128);
						Image code128Image = barcode128.createImageWithBarcode(pdfContentByte, null, null);
						code128Image.setAbsolutePosition(10, 700);
						code128Image.scalePercent(125);
						document.add(code128Image);

						BarcodeEAN codeEAN = new BarcodeEAN();
						codeEAN.setCodeType(BarcodeEAN.EAN13);
						codeEAN.setCode("1234523453323");
						Image codeEANImage = codeEAN.createImageWithBarcode(pdfContentByte, null, null);
						codeEANImage.setAbsolutePosition(20, 600);
						codeEANImage.scalePercent(125);
						document.add(codeEANImage);

						BarcodeQRCode qrcode = new BarcodeQRCode("examples.javacodegeeks.com/author/chandan-singh", 1, 1, null);
						Image qrcodeImage = qrcode.getImage();
						qrcodeImage.setAbsolutePosition(20, 500);
						qrcodeImage.scalePercent(200);
						document.add(qrcodeImage);

						document.close();
				}
				catch (FileNotFoundException e)
				{
						e.printStackTrace();
				}
				catch (DocumentException e)
				{
						e.printStackTrace();
				}
		}
}
