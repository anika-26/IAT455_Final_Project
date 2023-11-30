import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Buttons extends IAT455_Course_Project {

    public Buttons() {
        super(); // Call the constructor of the base class
        System.out.println("Adding buttons...");
        this.setLayout(null); // If using absolute positioning
       
        this.setSize(1500, 800); // Make sure the frame is large enough
        this.setVisible(true); 
    
        int buttonWidth = 150;
        int buttonHeight = 30;
        int xPosition = this.getWidth() - buttonWidth - 30; // 30 pixels from the right edge
        int yPosition = this.getHeight() - buttonHeight - 150; // 150 pixels from the bottom

        // Add the download button
        JButton downloadButton = new JButton("Download Image");
        downloadButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight); // Position adjusted
        this.add(downloadButton);
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveImage();
            }
        });

        // Add the refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(xPosition, yPosition - buttonHeight - 10, buttonWidth, buttonHeight); // Position adjusted
        this.add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
    }

    private void saveImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                ImageIO.write(outputImage, "png", fileToSave);
                System.out.println("Image saved as " + fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void refresh() {
        selectionOne = null;
        selectionTwo = null;
        selectDepthOne = null;
        selectDepthTwo = null;
        outputImage = null;
        repaint();
    }

    public static void main(String[] args) {
        new Buttons().setVisible(true);
    }
}
