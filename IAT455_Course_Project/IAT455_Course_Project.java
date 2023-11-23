/*File Exercise1.java

 IAT455 - Course project 

 **********************************************************/
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;

class IAT455_Course_Project extends Frame { 
	
	//images set up 
	BufferedImage imageOne;
	BufferedImage imageTwo;
	BufferedImage imageThree;
	BufferedImage imageFour;
	BufferedImage imageFive;
	BufferedImage imageSix;
	BufferedImage outputImage; // use later for middle edited image 
	
	
	//depth images
	BufferedImage imageOneDepth;
	BufferedImage imageTwoDepth;
	BufferedImage imageThreeDepth;
	BufferedImage imageFourDepth;
	BufferedImage imageFiveDepth;
	BufferedImage imageSixDepth;

	int width; // width of the image
	int height; // height of the image
	
	public IAT455_Course_Project() {
		// constructor
		// Get an image from the specified file in the current directory on the
		// local hard disk.
		try {
			//load original images 
			imageOne = ImageIO.read(new File("mushrooms.jpg"));
			imageTwo = ImageIO.read(new File("lake.jpg"));
			imageThree = ImageIO.read(new File("boatimage.jpg"));
			imageFour = ImageIO.read(new File("beachimage.jpg"));
			imageFive = ImageIO.read(new File("treeimage.jpg"));
			imageSix = ImageIO.read(new File("fallpark.jpg"));
		
			//load corresponding depth images
			imageOneDepth = ImageIO.read(new File("mushroomsdepth.png"));
			imageTwoDepth = ImageIO.read(new File("lake_depth.png"));
			imageThreeDepth = ImageIO.read(new File("boatimage_depth.png"));
			imageFourDepth = ImageIO.read(new File("beachimage_depth.png"));
			imageFiveDepth = ImageIO.read(new File("treeimage_depth.png"));
			imageSixDepth = ImageIO.read(new File("fallpark_depth.png"));
			
			outputImage = ImageIO.read(new File("mushrooms.jpg")); // start with first image off default?? 

		} catch (Exception e) {
			System.out.println("Cannot load the provided image");
		}
		
		this.setTitle("IAT 455 Course Project");
		this.setVisible(true);
		
		width = imageOne.getWidth();
		height = imageOne.getHeight();
		addMouseListener(new MouseClickedListener());

		
		//imageOneDepth = createDepthMap(imageOne);
		//outputImage = createDepthMap(imageOne);
		
		checkRGB(imageOne);
		
		//Anonymous inner-class listener to terminate program
		this.addWindowListener(
				new WindowAdapter(){//anonymous class definition
					public void windowClosing(WindowEvent e){
						System.exit(0);//terminate the program
					}//end windowClosing()
				}//end WindowAdapter
				);//end addWindowListener
	}// end constructor
	
	
class MouseClickedListener extends MouseAdapter{
	
	@Override
	public void mouseClicked(MouseEvent e) {
			System.out.println("mouse clicked");
		
		//check if in range of the image one  -- FIX UP SIZE LATER !!!!!!!
			//changes output image to be the image user selected on the side 
		if( e.getX() >= 50 && e.getX() <= 480 && e.getY() >= 30 && e.getY() <= 216) {
			System.out.println("in image 1 ");
			outputImage = imageOne;
			repaint(); 
		} else if (e.getX() >= 50 && e.getX() <=480 && e.getY() >= 250 && e.getY() <= 466) {
			System.out.println("in image 2");
			outputImage = imageTwo;
			repaint();
		} else if (e.getX() >= 50 && e.getX() <=480 && e.getY() >=470 && e.getY() <=693) {
			System.out.println("In image 3");
			outputImage = imageThree;
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
		
}

public void checkRGB(BufferedImage src) {
	BufferedImage result = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
	for(int i=0; i<result.getWidth(); i++) {
		for(int j=0; j< result.getHeight(); j++) {
			int rgb = result.getRGB(i, j);
			//System.out.println(rgb);
		}
}
	
}

	
public BufferedImage createDepthMap(BufferedImage src) {
	BufferedImage result = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
	//greyscale depth map
	
	for(int i=0; i<result.getWidth(); i++) {
		for(int j=0; j< result.getHeight(); j++) {
			int rgb = result.getRGB(i, j);
			int a = getAlpha(rgb);
			int average = (getRed(rgb) + getBlue(rgb) + getGreen(rgb) )/3;
			
			int r = getRed(rgb);
			//System.out.println(rgb);
			//System.out.println(average);
			rgb = (a <<24) | (average<<16) | (average<< 8) | average; //NOT WORKING ???????? 
			int newrgb = new Color( average <<24, average <<16, average << 8).getRGB();
			result.setRGB(i,  j, rgb);
			
		}
	}
	return result;
}


public BufferedImage depthMap(BufferedImage src) {
	BufferedImage result = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
	//greyscale depth map
	
	for(int i=0; i<result.getWidth(); i++) {
		for(int j=0; j< result.getHeight(); j++) {
			int rgb = result.getRGB(i, j);
			
			int r = (rgb >> 16) & 0xFF;
	        int g = (rgb >> 8) & 0xFF;
	        int b = (rgb & 0xFF);
	        
	     // Normalize and gamma correct:
	        float rr = (float) Math.pow(r / 255.0, 2.2);
	        float gg =(float) Math.pow(g / 255.0, 2.2);
	        float bb = (float)Math.pow(b / 255.0, 2.2);

	        // Calculate luminance:
	        float lum = (float) (0.2126 * rr + 0.7152 * gg + 0.0722 * bb);

	        // Gamma compand and rescale to byte range:
	        int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
	        int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel; 
	        result.setRGB(i, j, gray);
		}
		}
	return result;
}


public BufferedImage composite(BufferedImage src1, BufferedImage src2, BufferedImage src1_depth, BufferedImage src2_depth) {
	BufferedImage result = new BufferedImage(src1.getWidth(), src1.getHeight(), src1.getType());
	
	//	TO DO - FINISH METHOD 	
	
	for(int i=0; i<result.getWidth(); i++) {
		for(int j=0; j< result.getHeight(); j++) {
			int rgb = src1_depth.getRGB(i, j);
			int rgb2 = src2_depth.getRGB(i, j);
			int rgb3 = src1.getRGB(i, j);
			int rgb4 = src2.getRGB(i, j);
			
			float[] hsb = new float[3];
			float[] hsb2 = new float[3];
			
			int red = getRed(rgb);
			
		}
	}
		
	return result;
}


//hover - show depth map of the image 
//for right click - lighter and left click darker for depth map of an element 
//use bounding boxes instead of stroke 


protected int getAlpha(int pixel) {
	return (pixel >>> 24) & 0xFF;
}


protected int getRed(int pixel) {
	return (pixel >>> 16) & 0xFF;
}

protected int getGreen(int pixel) {
	return (pixel >>> 8) & 0xFF;
}

protected int getBlue(int pixel) {
	return pixel & 0xFF;
}

protected int getRed2(int pixel) {
	return (new Color(pixel).getRed());
}
public void paint(Graphics g) {
	
	//if working with different images, this may need to be adjusted
	int w = width ; 
	int h = height ;
	
	//image titles
//	g.drawString("Image 1", 50, 70);
//	
//	g.drawString("Image 2", 50, 250);
//	g.drawString("Image 3", 50, 370);
	Graphics2D g2 = (Graphics2D) g;
	AffineTransform at = new AffineTransform();
	AffineTransform oldForm = g2.getTransform(); //storing the old transform position to rotate back to after verticle text
	
	
	at.rotate(-Math.PI /2);
	g2.setTransform(at);
	g2.drawString("Select an Image to Paint Depth", -380, 40);
	
	g2.setTransform(oldForm); //putting transform back to old 
	
	//draw images 
	g.drawImage(imageOne,50,30,w/8, h/8,this);
	g.drawImage(imageTwo,50,250,w/8, h/8,this);
	g.drawImage(imageThree,50,470,w/8, h/8,this);
	
	//g.drawImage(imageThree,50,470,w/8, h/8,this);
	
	//output image 
	g.drawImage(outputImage,560,200,w/4, h/4,this);
	//g.drawImage(imageOneDepth,560,200,w/4, h/4,this);
	g2.drawString("Resulting Image", 560, 180);
	
	this.setSize(1500, 700);


    g.setColor(Color.BLACK);
    Font f1 = new Font("Verdana", Font.PLAIN, 13); 
    g.setFont(f1); 
    
    
    
}
// =======================================================//

public static void main(String[] args) {

	IAT455_Course_Project img = new IAT455_Course_Project();// instantiate this object
	
	img.repaint();// render the image

}// end main
}
// =======================================================//
