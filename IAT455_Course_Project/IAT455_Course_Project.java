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


//if user clicks the bound box and the colour is a certain colour - then adjust the depth of those pixels 
/////


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
	
	BufferedImage testpenguins;
	
	BufferedImage selectionOne, selectionTwo, selectDepthOne, selectDepthTwo;
	
	//int to count how many clicks of mouse 
	int count =0;
	int selection=0;

	int width; // width of the image
	int height; // height of the image
	
	public IAT455_Course_Project() {
		// constructor
		// Get an image from the specified file in the current directory on the
		// local hard disk.
		try {
			//load original images 
			imageOne = ImageIO.read(new File("penguins.jpg"));
			imageTwo = ImageIO.read(new File("lake.jpg"));
			imageThree = ImageIO.read(new File("boatimage.jpg"));
			imageFour = ImageIO.read(new File("beachimage.jpg"));
			imageFive = ImageIO.read(new File("treesunset.jpg"));
			
		
			//load corresponding depth images
			imageOneDepth = ImageIO.read(new File("penguinsdepth.png"));
			imageTwoDepth = ImageIO.read(new File("lake_depth.png"));
			imageThreeDepth = ImageIO.read(new File("boatimage_depth.png"));
			imageFourDepth = ImageIO.read(new File("beachimage_depth.png"));
			imageFiveDepth = ImageIO.read(new File("treesunset_depth.png"));
			
			
			

		} catch (Exception e) {
			System.out.println("Cannot load the provided image");
		}
		
		this.setTitle("IAT 455 Course Project");
		this.setVisible(true);
		
		width = imageOne.getWidth();
		height = imageOne.getHeight();
		addMouseListener(new MouseClickedListener());
		
		
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
			count+=1;
			System.out.println(count);
			
		
			
			
			
			
		if( e.getX() >= 50 && e.getX() <= width/8 && e.getY() >= 50 && e.getY() <= 50+67.5) {
			System.out.println("in image 1 ");
			
			if(count ==1) {
				selectionOne = imageOne;
				//selection = 1;
				selectDepthOne = imageOneDepth;
			} else if(count ==2) {
				//selection = 2; //switching cases here -- might want to shift some of this stuff down into the swith case idk 
				selectionTwo = imageOne;
				selectDepthTwo = imageOneDepth;
				count =0;
			}
			
		} else if (e.getX() >= 50 && e.getX() <=width/8 && e.getY() >= 180 && e.getY() <= 180+67.6) {
			System.out.println("in image 2");
			if(count ==1) {
				selectionOne = imageTwo;
				selectDepthOne = imageTwoDepth;
				//switch case
				//selection = 3;
			} else if(count ==2) {
				selectionTwo = imageTwo;
				selectDepthTwo = imageTwoDepth;
				//selection =4;
				count =0;
			}
			
		} else if (e.getX() >= 50 && e.getX() <=width/8 && e.getY() >=310 && e.getY() <=310+67.5) {
			System.out.println("In image 3");
		
			if(count ==1) {
				selectionOne = imageThree;
				selectDepthOne = imageThreeDepth;
				//selection=5;
		
			} else if(count ==2) {
				selectionTwo = imageThree;
				selectDepthTwo = imageThreeDepth;
				//selection = 6;
				count =0;
			}
		} else if (e.getX() >= 50 && e.getX() <=width/8 && e.getY() >=440 && e.getY() <=440+67.5) {
			System.out.println("In image 4");
		
			if(count ==1) {
				selectionOne = imageFour;
				selectDepthOne = imageFourDepth;
				//selection = 7;
				
			} else if(count ==2) {
				selectionTwo = imageFour;
				selectDepthTwo = imageFourDepth;
				//selection =8;
				count =0;
				
			}
		} 
		else if (e.getX() >= 50 && e.getX() <=width/8 && e.getY() >=570 && e.getY() <=570+67.5) {
			System.out.println("In image 5");
		
			if(count ==1) {
				selectionOne = imageFive;
				selectDepthOne = imageFiveDepth;
				selection = 9;
			} else if(count ==2) {
				selectionTwo = imageFive;
				selectDepthTwo = imageFiveDepth;
				count =0;
				selection = 10;
			}
		} 
		
			
		
		
		if(selectionOne != null && selectionTwo!= null && selectDepthOne!= null && selectDepthTwo!=null) {
			outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
			
			repaint();
			
			//HERE neq approavh
			
			//CHECKS IF THE USER SELECTED THE DEPTHSELECTONE IMAGE
			if(e.getX() >= 400 && e.getX() <= 400+width && e.getY() >= 50 && e.getY() <= 50+height) {
				if(selectDepthOne == imageOneDepth) {
					selection =1;
				} 
				if(selectDepthOne == imageTwoDepth) {
					selection = 3;
				}
				if(selectDepthOne == imageThreeDepth) {
					selection =5;
				}
				if(selectDepthOne == imageFourDepth) {
					selection = 7;
				}
				if(selectDepthOne == imageFiveDepth) {
					selection = 9;
				}
			}  if (e.getX() >= 900 && e.getX() <=900+width && e.getY() >= 50 && e.getY() <=50+height) {
				//in selection 2 - user pressed 
				
				if(selectDepthTwo == imageOneDepth) {
					selection = 2;
				} 
				if(selectDepthTwo == imageTwoDepth) {
					selection = 4;
				} if(selectDepthTwo == imageThreeDepth) {
					selection = 6;
				}
				if(selectDepthTwo == imageFourDepth) {
					selection = 8;
				}if(selectDepthTwo == imageFiveDepth) {
					selection = 10;
				}
			}
			
			//need to check which selection and will change coordinates bc of that 
	
			
			switch(selection) {
			case 1:
				//case that selection one is penguin image (image one)
				if(e.getX() >= 460 && e.getX() <= 635 && e.getY() >= 120 && e.getY() <=286) {
					System.out.println("mouse in penguins select 1");
					
					//now check which mouse button it was 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						
						//increase brightness 
						selectDepthOne = leftClick(imageOneDepth, new Color(151, 151,151).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//decrease brightness 
						selectDepthOne = rightClick(imageOneDepth, new Color(151, 151,151).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
			case 2:
				//case that selection two is image one (penguin image) 
				if(e.getX() >= 962 && e.getX() <= 1137 && e.getY() >= 120 && e.getY() <=286) {
					System.out.println("mouse in penguins selection two");
					
					
					//now check which mouse button it was 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//increase brightness 
						selectDepthTwo = leftClick(imageOneDepth, new Color(151, 151,151).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//decrease brightness 
						selectDepthTwo = rightClick(imageOneDepth, new Color(151, 151,151).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
				
			case 3:
				//lake image first selection  -- not fully working atm need to make the whole tree trunk same colour 
				if(e.getX() >= 400  && e.getX() <=880 && e.getY() <=298 && e.getY() >= 216) {
					//System.out.println(e.getX());
				//	System.out.println(e.getY());
					
					//now check which mouse button it was 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//increase brightness 

						selectDepthOne = leftClick(imageTwoDepth, new Color(200, 200,200).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//decrease brightness 
						selectDepthOne = rightClick(imageTwoDepth,  new Color(200, 200,200).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
				
			case 4:
				// will be for case that selection two is lake image
				if(e.getX() >= 900  && e.getX() <= 1380 && e.getY() <=298 && e.getY() >= 216) {
					//System.out.println(e.getX());
					//System.out.println(e.getY());
					//now check which mouse button it was 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//increase brightness 

						selectDepthTwo = leftClick(imageTwoDepth, new Color(200, 200,200).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//decrease brightness 
						selectDepthTwo = rightClick(imageTwoDepth,  new Color(200, 200,200).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
				
			//case for the boat image COLOUR SEARCH NEEDS FIXING 
			case 5:
				if(e.getX() >= 505 && e.getX() <= 646 && e.getY() >= 212 && e.getY() <= 277) {
					//System.out.println(e.getX());
					//System.out.println(e.getY());
					//now check which mouse button
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//increase brightness 

						selectDepthOne = leftClick(imageThreeDepth, new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//decrease brightness 
						selectDepthOne = rightClick(imageThreeDepth,  new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
			
				//CASE FOR THE BOAT IMAGE ON SELECTION TWO 
			case 6:
				if(e.getX() >= 1005 && e.getX() <= 1146 && e.getY() >= 212 && e.getY() <= 277) {
					//then tstuff
					System.out.println("boundary hit 6");
					
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//increase brightness 

						selectDepthTwo = leftClick(imageThreeDepth, new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//decrease brightness 
						selectDepthTwo = rightClick(imageThreeDepth,  new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
			
				//case for the beach image on selection one
			case 7:
				System.out.println("case 7");
				System.out.println(e.getX());
				System.out.println(e.getY());
				
				if(e.getX() >= 657 && e.getX() <= 827 && e.getY() >= 205 && e.getY() <= 305) {
					//then tstuff
					System.out.println("boundary hit 7");
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//increase brightness 

						selectDepthOne = leftClick(imageFourDepth, new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//decrease brightness 
						selectDepthOne = rightClick(imageFourDepth,  new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
				
				//case for beach image in selection two
			case 8: 
				System.out.println("case 8");
				System.out.println(e.getX());
				System.out.println(e.getY());
				
				if(e.getX() >= 837 && e.getX() <= 997 && e.getY() >= 205 && e.getY() <= 305) {
					//then stuff
					System.out.println("boundary hit 8");
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//increase brightness 

						selectDepthTwo = leftClick(imageFourDepth, new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//decrease brightness 
						selectDepthTwo = rightClick(imageFourDepth,  new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				
				break;
				
			
			//case for tree sunset image in selection one	
			case 9:
				System.out.println("case 9");
				System.out.println(e.getX());
				System.out.println(e.getY());
				if(e.getX() >= 629 && e.getX() <= 850 && e.getY() >= 106 && e.getY() <= 740) {
					//then stuff
					System.out.println("boundary hit 9");
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//increase brightness 

						selectDepthOne = leftClick(imageFiveDepth, new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//decrease brightness 
						selectDepthOne = rightClick(imageFiveDepth,  new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				} 
				break;
				
			//case for tree sunset image in selection two
			case 10:
				System.out.println("case 10");
				System.out.println(e.getX());
				System.out.println(e.getY());
				if(e.getX() >= 1128 && e.getX() <= 1349 && e.getY() >= 106 && e.getY() <= 740) {
					//then stuff
					System.out.println("boundary hit 10");
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//increase brightness 

						selectDepthTwo = leftClick(imageFiveDepth, new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//decrease brightness 
						selectDepthTwo = rightClick(imageFiveDepth,  new Color(189, 189,189).getRGB());
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
				
			}
			
			
			
			
			
			
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
		
		//make sure that they have selected images - that they arent null
		//check here for the pixel colour?? - then bounding boxes 
		//rgb(151, 151, 151) -- for the penguins depth map 
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
		
}

//method to increase brightness of object 
public BufferedImage leftClick(BufferedImage src, int elementColour) {
	BufferedImage result = new BufferedImage(src.getWidth(),
			src.getHeight(), src.getType());
	
	for (int i = 0; i < result.getWidth(); i++) {
		for (int j = 0; j < result.getHeight(); j++) {
			int rgb = src.getRGB(i, j);
			int r= getRed(rgb);
			int g = getGreen(rgb);
			int b = getBlue(rgb);
			
			int penguins = new Color(151, 151,151).getRGB();
			if(rgb == elementColour) {
				int newR = clip(r *2);
				int newG = clip(g*2);
				int newB = clip(b*2);
				result.setRGB(i, j, new Color(newR, newG, newB).getRGB());
			}
		}
		}
	
	return result;
}

//method to decrease the brightness of depth image
public BufferedImage rightClick(BufferedImage src, int elementColour) {
	BufferedImage result = new BufferedImage(src.getWidth(),
			src.getHeight(), src.getType());
	
	for (int i = 0; i < result.getWidth(); i++) {
		for (int j = 0; j < result.getHeight(); j++) {
			int rgb = src.getRGB(i, j);
			int r= getRed(rgb);
			int g = getGreen(rgb);
			int b = getBlue(rgb);
			
			//int penguins = new Color(151, 151,151).getRGB();
			if(rgb == elementColour) {
				int newR = clip(r /2);
				int newG = clip(g/2);
				int newB = clip(b/2);
				result.setRGB(i, j, new Color(newR, newG, newB).getRGB());
			}
		}
		}
	
	return result;
}


public BufferedImage composite(BufferedImage src1, BufferedImage src2,
		BufferedImage src1_depth, BufferedImage src2_depth) {

	BufferedImage result = new BufferedImage(src1.getWidth(),
			src1.getHeight(), src1.getType());
	
	// Complete this method
	for (int i = 0; i < result.getWidth(); i++) {
		for (int j = 0; j < result.getHeight(); j++) {
			int rgb1 = src1_depth.getRGB(i, j);
			int rgb2 = src2_depth.getRGB(i, j);
			int rgb3 = src1.getRGB(i, j);
			int rgb4 = src2.getRGB(i, j);
			
			float[] hsb = new float[3];
			float[] hsb2 = new float[3];
			int red = getRed(rgb1);
			int green = getGreen(rgb1);
			int blue = getBlue(rgb1);
			
			int red2 = getRed(rgb2);
			int green2 = getGreen(rgb2);
			int blue2 = getBlue(rgb2);
			
			hsb = Color.RGBtoHSB(red, green, blue, hsb);
			hsb2 = Color.RGBtoHSB(red2, green2, blue2, hsb2);
			
			if(hsb[2] > hsb2[2]) {
				result.setRGB(i, j, rgb3);
			} else {
				result.setRGB(i,j,rgb4);
			}
			
;
		}
	}
	
	
	return result;
}


//hover - show depth map of the image 
//for right click - lighter and left click darker for depth map of an element 
//use bounding boxes instead of stroke 
private int clip(int v) {
	v = v > 255 ? 255 : v;
	v = v <0 ? 0 :v;
	return v;
}

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
	int w = width/4 ; 
	int h = height/4 ;
	

	
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
	g.drawImage(imageOne,50,50,w/4, h/4,this);
	g.drawImage(imageOneDepth,w/4 +80,50,w/4, h/4,this);
	
	
	g.drawImage(imageTwo,50,180,w/4, h/4,this);
	g.drawImage(imageTwoDepth,w/4 +80,180,w/4, h/4,this);
	
	g.drawImage(imageThree,50,310,w/4, h/4,this);
	g.drawImage(imageThreeDepth,w/4 +80,310,w/4, h/4,this);
	
	g.drawImage(imageFour,50,440,w/4, h/4,this);
	g.drawImage(imageFourDepth,w/4 +80,440,w/4, h/4,this);
	
	g.drawImage(imageFive,50,570,w/4, h/4,this);
	g.drawImage(imageFiveDepth,w/4 +80,570,w/4, h/4,this);
	
	
	//draw the two depth maps 
	g.drawImage(selectDepthOne,400,50,w, h,this);
	g.drawImage(selectDepthTwo,900,50,w, h,this);
	
	g.drawImage(testpenguins,900,50,w, h,this); // remove later
	//output image 
	g.drawImage(outputImage,650,400,w, h,this);
	//g.drawImage(imageOneDepth,560,200,w/4, h/4,this);
	g2.drawString("Resulting Image", 650, 390);
	
	this.setSize(1500, 700);


    g.setColor(Color.BLACK);
    Font f1 = new Font("Verdana", Font.PLAIN, 13); 
    g.setFont(f1); 
    
    g.drawString("Image 1", 50, 45);
    g.drawString("Depth Map", w/4+80, 45);
    g.drawString("Image 2", 50, 175);
    g.drawString("Depth Map", w/4+80, 175);
    
    g.drawString("Image 3", 50, 305);
    g.drawString("Depth Map", w/4+80, 305);
    g.drawString("Image 4", 50, 435);
    g.drawString("Depth Map", w/4+80, 435);
    g.drawString("Image 5", 50, 565);
    g.drawString("Depth Map", w/4+80, 565);
    
    
    
}
// =======================================================//

public static void main(String[] args) {

	IAT455_Course_Project img = new IAT455_Course_Project();// instantiate this object
	
	img.repaint();// render the image

}// end main
}
// =======================================================//
