/*

 IAT455 - Course project 
 Anika Richards, Anneke Kluft, Jigang Zhou

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
import javax.swing.JButton;
import javax.swing.JFileChooser;


class IAT455_Course_Project extends Frame { 
	
	//setting up image variables 
	BufferedImage imageOne;
	BufferedImage imageTwo;
	BufferedImage imageThree;
	BufferedImage imageFour;
	BufferedImage imageFive;
	
	BufferedImage outputImage; 
	
	//depth images
	BufferedImage imageOneDepth;
	BufferedImage imageTwoDepth;
	BufferedImage imageThreeDepth;
	BufferedImage imageFourDepth;
	BufferedImage imageFiveDepth;

	//to store user selections 
	BufferedImage selectionOne, selectionTwo, selectDepthOne, selectDepthTwo;
	
	//int to count how many clicks of mouse - used for determine whether a selected image is the first or second image (for displayal and case switches later)
	int count =0;
	int selection=0; //variable used for switch case 

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
		
		
		//getting width and height of image one - all of the images are same size 
		width = imageOne.getWidth();
		height = imageOne.getHeight();
		
		//setting up the mouse listener to listen for mouse events 
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
	
	
	
//class for mouse clicked listener 	
class MouseClickedListener extends MouseAdapter{
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		System.out.println(count);
		
		
		//checks if the user has clicked the mouse in the area of the first image by getting the mouse's x and y coordinates and ensuring these are in the bounds of the image
		if( e.getX() >= 50 && e.getX() <= width/8 && e.getY() >= 50 && e.getY() <= 50+67.5) {
			
			count+=1; // adding one to the count tracker to keep track of how many times the user clicked mouse on an image 
			
			System.out.println("in image 1 ");
			
			//if count is 1 then this is their first selected image so sets the selection one variable to image one and the first selection depth image to image one depth 
			if(count ==1) {
				selectionOne = imageOne;
				selectDepthOne = imageOneDepth;
			} 
			//if count is 2 then this is their second selected image so sets the selection two variable to image one and the second selection depth image to image one depth 
			else if(count ==2) {
				selectionTwo = imageOne;
				selectDepthTwo = imageOneDepth;
				count =0; //resets the counter 
			}
			
		}  
		
		//checks if the user has clicked the mouse in the area of the second image by getting the mouse's x and y coordinates and ensuring these are in the bounds of the image
		if (e.getX() >= 50 && e.getX() <=width/8 && e.getY() >= 180 && e.getY() <= 180+67.6) {
			
			count+=1;// adding one to the count tracker to keep track of how many times the user clicked mouse on an image 
			
			System.out.println("in image 2");
			
			//if count is 1 then this is their first selected image so sets the selection one variable to image two and the first selection depth image to image two depth
			if(count ==1) {
				selectionOne = imageTwo;
				selectDepthOne = imageTwoDepth;
			} 
			//if count is 2 then this is their second selected image so sets the selection two variable to image two and the second selection depth image to image two depth 
			else if(count ==2) {
				selectionTwo = imageTwo;
				selectDepthTwo = imageTwoDepth;
				count =0; // resets the counter 
			}
			
		}  
		
		//checks if the user has clicked the mouse in the area of the third image by getting the mouse's x and y coordinates and ensuring these are in the bounds of the image
		if (e.getX() >= 50 && e.getX() <=width/8 && e.getY() >=310 && e.getY() <=310+67.5) {
			
			count+=1;// adding one to the count tracker to keep track of how many times the user clicked mouse on an image 
			
			System.out.println("In image 3");
		
			//if count is 1 then this is their first selected image so sets the selection one variable to image three and the first selection depth image to image three depth
			if(count ==1) {
				selectionOne = imageThree;
				selectDepthOne = imageThreeDepth;
		
			} 
			//if count is 2 then this is their second selected image so sets the selection two variable to image three and the second selection depth image to image three depth 
			else if(count ==2) {
				selectionTwo = imageThree;
				selectDepthTwo = imageThreeDepth;
				count =0; // resets the counter
			}
		}  
		
		//checks if the user has clicked the mouse in the area of the fourth image by getting the mouse's x and y coordinates and ensuring these are in the bounds of the image
		if (e.getX() >= 50 && e.getX() <=width/8 && e.getY() >=440 && e.getY() <=440+67.5) {
			
			count+=1;// adding one to the count tracker to keep track of how many times the user clicked mouse on an image 
			
			System.out.println("In image 4");
		
			//if count is 1 then this is their first selected image so sets the selection one variable to image four and the first selection depth image to image four depth
			if(count ==1) {
				selectionOne = imageFour;
				selectDepthOne = imageFourDepth;
				
			} 
			//if count is 2 then this is their second selected image so sets the selection two variable to image four and the second selection depth image to image four depth 
			else if(count ==2) {
				selectionTwo = imageFour;
				selectDepthTwo = imageFourDepth;
				count =0; //resets the counter
			}
		} 
		//checks if the user has clicked the mouse in the area of the fifth image by getting the mouse's x and y coordinates and ensuring these are in the bounds of the image
		if (e.getX() >= 50 && e.getX() <=width/8 && e.getY() >=570 && e.getY() <=570+67.5) {
			
			System.out.println("In image 5");
			
			count+=1;// adding one to the count tracker to keep track of how many times the user clicked mouse on an image 
			
			//if count is 1 then this is their first selected image so sets the selection one variable to image five and the first selection depth image to image five depth
			if(count ==1) {
				selectionOne = imageFive;
				selectDepthOne = imageFiveDepth;
			} 
			//if count is 2 then this is their first selected image so sets the selection one variable to image five and the first selection depth image to image five depth
			else if(count ==2) {
				selectionTwo = imageFive;
				selectDepthTwo = imageFiveDepth;
				count =0; //resets the counter
			
			}
		} 
		
		//checks to make sure that the user has selected two images (aka the selected image variables and their coresponding depth images)
		if(selectionOne != null && selectionTwo!= null && selectDepthOne!= null && selectDepthTwo!=null) {
			
			//calls the composite method to combine the two images that the user has selected 
			outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
			
			repaint(); //repaints scene to show the changes 
			
			//checks if the user clicked on the first or left depth image (for editing the depth of elements )  
			// then checks which of the images the selected depth image one is and will set the appropriate case for that 
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
			}  
			
			//checks if the user clicked on the second or right depth image (for editing the depth of elements )
			// then checks which of the images the selected depth image one is and will set the appropriate case for that 
			if (e.getX() >= 900 && e.getX() <=900+width && e.getY() >= 50 && e.getY() <=50+height) {
				
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
	
			//switch case to handle the different cases of the specific images and which side they are on (whether they are selection one or two)
			switch(selection) {
			
			//case that selection one is penguin image (image one)
			case 1:
				
				//checks whether the mouse is in the boundary of the penguins element in the image 
				if(e.getX() >= 460 && e.getX() <= 635 && e.getY() >= 120 && e.getY() <=286) {
					
					System.out.println("mouse in penguins select 1");
					
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthOne = leftClick(imageOneDepth, new Color(151, 151,151).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						
						//repaints scene 
						repaint();
						
					} 
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthOne = rightClick(imageOneDepth, new Color(151, 151,151).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						
						//repaints scene 
						repaint();
					}
				}
				break;
			
			//case that selection two is image one
			case 2:
				
				//checks whether the mouse is in the boundary of the penguins element in the image 
				if(e.getX() >= 962 && e.getX() <= 1137 && e.getY() >= 120 && e.getY() <=286) {
					System.out.println("mouse in penguins selection two");
					
					
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthTwo = leftClick(imageOneDepth, new Color(151, 151,151).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} 
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthTwo = rightClick(imageOneDepth, new Color(151, 151,151).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
			
			//case that the second image is selection one 
			case 3:
				
				
				//checks if mouse is in boundary of the tree element 
				if(e.getX() >= 400  && e.getX() <=880 && e.getY() <=298 && e.getY() >= 216) {
				
					
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthOne = leftClick(imageTwoDepth, new Color(242, 242,242).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} 
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthOne = rightClick(imageTwoDepth,  new Color(242, 242,242).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
			
			//case that selection two is image two 
			case 4:
				//checks if in boundary of tree element 
				if(e.getX() >= 900  && e.getX() <= 1380 && e.getY() <=298 && e.getY() >= 216) {
					
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthTwo = leftClick(imageTwoDepth, new Color(242, 242,242).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					}
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels )  
						selectDepthTwo = rightClick(imageTwoDepth,  new Color(242, 242,242).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
				
			//case for if selection one is the third image
			case 5:
				
				//checks if click is in boundary of the boat 
				if(e.getX() >= 505 && e.getX() <= 646 && e.getY() >= 212 && e.getY() <= 277) {
				
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthOne = leftClick(imageThreeDepth, new Color(199, 199,199).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} 
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels )  
						selectDepthOne = rightClick(imageThreeDepth,  new Color(199, 199,199).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
			
			//case for if selection two is image three 
			case 6:
				
				//checks if click is in boundary of the boat 
				if(e.getX() >= 1005 && e.getX() <= 1146 && e.getY() >= 212 && e.getY() <= 277) {
					
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthTwo = leftClick(imageThreeDepth, new Color(199, 199,199).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} 
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels )  
						selectDepthTwo = rightClick(imageThreeDepth,  new Color(199, 199,199).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
			
			// case where selection one is image four 
			case 7:
				
				//checks if click is in boundary of the boat 
				if(e.getX() >= 657 && e.getX() <= 827 && e.getY() >= 205 && e.getY() <= 305) {
					
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthOne = leftClick(imageFourDepth, new Color(255, 255,255).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} 
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels )  
						selectDepthOne = rightClick(imageFourDepth,  new Color(255, 255,255).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				break;
				
			//case for image four in selection two
			case 8: 
				
				//checks if click is in boundary of the boat 
				if(e.getX() >= 837 && e.getX() <= 997 && e.getY() >= 205 && e.getY() <= 305) {
				
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
				
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthTwo = leftClick(imageFourDepth, new Color(255, 255,255).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} 
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels )  
						selectDepthTwo = rightClick(imageFourDepth,  new Color(255, 255,255).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				
				break;
				
			
			//case for image five in selection one
			case 9:
				
				//checks if click is in the tree 
				if(e.getX() >= 629 && e.getX() <= 850 && e.getY() >= 106 && e.getY() <= 740) {
					
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map 
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthOne = leftClick(imageFiveDepth, new Color(221, 221,221).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} 
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels )  
						selectDepthOne = rightClick(imageFiveDepth,  new Color(221, 221,221).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				} 
				//checking for second tree
				if(e.getX() >= 102 && e.getX() <= 565 && e.getY() >= 203 && e.getY() <= 255) {
					
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthOne = leftClick(imageFiveDepth, new Color(167, 167,167).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					}
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels )  
						selectDepthOne = rightClick(imageFiveDepth,  new Color(167, 167,167).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
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
									
					//checks if mouse button click was the left button -- if left it will increase the brightness of selected element in image depth map
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthTwo = leftClick(imageFiveDepth, new Color(221, 221,221).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					} 
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels )  
						selectDepthTwo = rightClick(imageFiveDepth,  new Color(221, 221,221).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
					}
				}
				//checking for second tree
				if(e.getX() >= 989 && e.getX() <= 1068 && e.getY() >= 203 && e.getY() <= 255) {
					if(e.getButton() == MouseEvent.BUTTON1) {
						System.out.println("left click");
						
						//calls method left click which will increase the brightness of the pixels of the element in image (based on colour of pixels ) 
						selectDepthOne = leftClick(imageFiveDepth, new Color(167, 167,167).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
						outputImage = composite(selectionOne, selectionTwo, selectDepthOne, selectDepthTwo);
						repaint();
						
					}
					//checks if mouse button click was the right button -- if right it will decrease the brightness of selected element in image depth map 
					else if (e.getButton() == MouseEvent.BUTTON3) {
						System.out.println("right click");
						
						//calls method right click which will decrease the brightness of the pixels of the element in image (based on colour of pixels )  
						selectDepthOne = rightClick(imageFiveDepth,  new Color(167, 167, 167).getRGB());
						
						//calls the composite method to combine the images again with the new depth map
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
			
}

//method to increase brightness of pixels of an element 
//takes an image and RGB colour as parameters 
public BufferedImage leftClick(BufferedImage src, int elementColour) {
	BufferedImage result = new BufferedImage(src.getWidth(),
			src.getHeight(), src.getType());
	
	//iterates through every pixel in the image and gets the rgb and specific rgb channels of the image 
	
	for (int i = 0; i < result.getWidth(); i++) {
		for (int j = 0; j < result.getHeight(); j++) {
			int rgb = src.getRGB(i, j);
			int r= getRed(rgb);
			int g = getGreen(rgb);
			int b = getBlue(rgb);
			
			//checks for pixels that match the passed in colour (meaning they are the element to be brightned) 
			//increases the brightness of each pixel by channel and sets the result to have the new RGB
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

//method to decrease the brightness of element in depth image
//takes an rgb colour and an image as parameters 
public BufferedImage rightClick(BufferedImage src, int elementColour) {
	BufferedImage result = new BufferedImage(src.getWidth(),
			src.getHeight(), src.getType());
	
	//iterates through every pixel in the image and gets the rgb and specific rgb channels of the image 
	for (int i = 0; i < result.getWidth(); i++) {
		for (int j = 0; j < result.getHeight(); j++) {
			int rgb = src.getRGB(i, j);
			int r= getRed(rgb);
			int g = getGreen(rgb);
			int b = getBlue(rgb);
			
			//checks for pixels that match the passed in colour (meaning they are the element to be darkened) 
			//decreases the brightness of each pixel by channel and sets the result to have the new RGB
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

//method to combine images 
//takes in four images as parameters - 2 normal images and their respective depth images 
public BufferedImage composite(BufferedImage src1, BufferedImage src2,
		BufferedImage src1_depth, BufferedImage src2_depth) {

	BufferedImage result = new BufferedImage(src1.getWidth(),
			src1.getHeight(), src1.getType());
	
	//iterates through every pixel in each of the images and gets the rgb values of each of them 
	for (int i = 0; i < result.getWidth(); i++) {
		for (int j = 0; j < result.getHeight(); j++) {
			int rgb1 = src1_depth.getRGB(i, j);
			int rgb2 = src2_depth.getRGB(i, j);
			int rgb3 = src1.getRGB(i, j);
			int rgb4 = src2.getRGB(i, j);
			
			//gets the hsb value and rgb channels of the depth images 
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
			
			//checks which is brighter to decide whether to place the pixels in comparison to each other 
			//brighter = in front 
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
	super.paint(g);
	
	int w = width/4 ; 
	int h = height/4 ;
	
	
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

	//output image 
	g.drawImage(outputImage,650,400,w, h,this);
	
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
