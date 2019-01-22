package Image_Dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;


public class CompressImageDao {
	public String compress_image(String image_path, double c_factor, String dest_folder) throws IOException {
		float compression_factor = (float)c_factor;
		ImageIO.scanForPlugins();
			
		File input_image = new File(image_path);
		BufferedImage image = ImageIO.read(input_image);
		System.out.println("\nInside Compression Method!\n");
		String compressedImagePath = dest_folder+File.separator+"compressed_"+input_image.getName();
	    File compressedImageFile = new File(compressedImagePath);
	    System.out.println("Compressed File Name:"+compressedImageFile.getName());

        OutputStream os =new FileOutputStream(compressedImageFile);

	    Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
	    ImageWriter writer = (ImageWriter) writers.next();

	    ImageOutputStream ios = ImageIO.createImageOutputStream(os);

	    writer.setOutput(ios);

	    ImageWriteParam param = writer.getDefaultWriteParam();
	      
	    if (param.canWriteCompressed()) { 
	    	    // NOTE: Any method named [set|get]Compression.* throws UnsupportedOperationException if false
	 	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	   	    param.setCompressionQuality(compression_factor);
	   	} 

	    writer.write(null, new IIOImage(image, null, null), param);
	      
	    os.close();
	    ios.close();
	      
	    writer.dispose();
	    System.out.println("File compressed inside Compression Method\n");

	    return compressedImageFile.getName();
	   }
}
