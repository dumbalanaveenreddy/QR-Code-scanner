package com.qr2.in;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;

public class IDCradsCreation1 {

	public IDCradsCreation1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws Exception {
		
		String file = "C:/Users/THOUGHTWAVE/Downloads/addingTableToPDF.pdf";

		String test = "Diwali fest event";
		
		String name = "NaveenReddy";
		String mobile = "+91-8688922702";
		String image = "C:\\Users\\THOUGHTWAVE\\Downloads\\FireShot\\image21.png";
		String logo = "C:\\Users\\THOUGHTWAVE\\Downloads\\FireShot\\swarm.png";
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));

		// Creating a Document object
		Document doc = new Document(pdfDoc, new PageSize(PageSize.A4).rotate());
		int commonIntWidth = 150;
		float[] columnWidths = { commonIntWidth, commonIntWidth, commonIntWidth, commonIntWidth, commonIntWidth };
		// Creating a table
		Table table = new Table(columnWidths);

		float height = 260;

		Image joinedImage = new Image(ImageDataFactory.create(image));
		Image logoImage = new Image(ImageDataFactory.create(logo));

		for (int i = 0; i < 23; i++) {

			try {
				BufferedImage img2 = ImageIO.read(new File("C:/Users/THOUGHTWAVE/Downloads/Images/image21.png"));
				BufferedImage img1 = ImageIO.read(new File("C:/Users/THOUGHTWAVE/Downloads/Images/swarm.png"));
				BufferedImage img3 = ImageIO.read(new File("C:/Users/THOUGHTWAVE/Downloads/Images/analysis.png"));
				BufferedImage joinedImg = joinBufferedImage(img1, img2,img3);
				boolean success = ImageIO.write(joinedImg, "png",
						new File("C:/Users/THOUGHTWAVE/Downloads/" + "joined.png"));
				if (success)
					joinedImage = new Image(ImageDataFactory.create("C:/Users/THOUGHTWAVE/Downloads/" + "joined.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			table.addCell(new Cell().add(joinedImage.setAutoScale(true)).add(test).add(name).add(mobile).add(logoImage)
					.setHeight(height).setBackgroundColor(com.itextpdf.kernel.color.Color.LIGHT_GRAY));

		}

		doc.add(table);

		// Closing the document
		doc.close();
		System.out.println("Table created successfully..");
	}

	/**
	 * join two BufferedImage you can add a orientation parameter to control
	 * direction you can use a array to join more BufferedImage
	 * @param img3 
	 */

	public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2, BufferedImage img3) {

		// do some calculate first
		int offset = 5;
		int wid = img1.getWidth() + img2.getWidth() + offset;
		int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
		// create a new buffer and draw two image into the new image
		BufferedImage newImage = new BufferedImage(wid, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = newImage.createGraphics();
		Color oldColor = g2.getColor();
		// fill background
		g2.setPaint(Color.lightGray);
		g2.fillRect(0, 0, wid, height);
		// draw image
		g2.setColor(oldColor);
		g2.drawImage(img1, null, 0, 0);
		g2.drawImage(img2, null, img1.getWidth() + offset, 0);
		g2.drawImage(img3, null, img1.getWidth() + offset, 0);
		g2.dispose();
		return newImage;
	}

}
