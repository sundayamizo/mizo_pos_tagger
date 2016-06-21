package viewFile;
import java.io.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class viewFile extends JApplet{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	/*-------------------------------*/
	  String path;
	  String mpath;
	  String newline = "\n";
	  JButton openBtn = new JButton("Open");
	  public JTextArea txArea;
	  public JLabel jlab;
	  /*-------------------------------*/
	  
	 BufferedReader BReader;
	
 
  private JTextField txField = new JTextField(15);

  private JComboBox<String> cBoxW = new JComboBox<String>();
 
  
  private JComboBox<String> cBoxT = new JComboBox<String>();




  private int count = 0;
  public void init(){
	     txArea = new JTextArea(30,40);
		 txArea.setMargin(new Insets(10, 10, 10, 10));
		 txArea.setEditable(false);
		 this.setSize(new Dimension(500,500));
		 repaint();
		 
    
    
    /*********************************************************/
    
    /*-------------------------------*/
    openBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
           JFileChooser chooser = new JFileChooser();
           chooser.setMultiSelectionEnabled(true);
           int option = chooser.showOpenDialog(viewFile.this);
           if (option == JFileChooser.APPROVE_OPTION) {
           File file = chooser.getSelectedFile();
           path = file.getAbsolutePath();
           
           String pat[] = path.split(":");
           mpath = pat[0]+":\\"+ pat[1];
           txArea.append("You have Selected: "+mpath);
           txArea.append("\n");
           txArea.append("Contents of the file :");
           txArea.append("\n");
           try {
				BReader = new BufferedReader(new FileReader(file));
				
           } catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
           try {
				 String line = BReader.readLine();
				 String s[] = line.split("\\.");
				 for(int i =0;i<s.length;i++){
		            	txArea.append(s[i]+"."+"\n");
		            	
		            }
				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
          
          
        	   
        		   
      		/**********************************/
        	 
          
      		  /*******************************/
      	catch (Exception e1) {
      		
      		e1.printStackTrace();
      	}
           /********************/
           
          }
          else {
            AbstractButton statusbar = null;
			statusbar.setText("You canceled.");
          }
        }
      }); //end of openBtn
    
	 
	  
		
	
 
    
    /*-------------------------------*/
    
   
     
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(openBtn);
    cp.add(txField);
    cp.add(cBoxW);
    cp.add(cBoxT);
    cp.add(txArea);
   
  }
  

public static void run(JApplet applet, int width, int height) {
JFrame frame = new JFrame();

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().add(applet);
frame.pack();
applet.init();
applet.start();
frame.setVisible(true);


}
public static void main(String[] arg){
    run(new viewFile(), 200, 125);
      
}//end of main

}
