import java.io.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import MizoPOS.ReadString;
import MizoPOS.Append;


public class ComboBoxes extends JApplet{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	/*-------------------------------*/
	  String path;
	  String mpath;
	  String newline = "\n";
	  JButton openBtn = new JButton("Open");
	  JButton nextBtn = new JButton("Next");
	  public JTextArea txArea;
	  public JLabel jlab;
	  final JLabel statusbar = new JLabel("Output of your selection will go here");
	 

	  /*-------------------------------*/
	  MizoPOS.ReadString rs;
	  MizoPOS.Append appnd;

	  String line;
	  String lineArr[];
	
	  String words[];

	  int cnt = 0;

	
	 File f = new File("NLPMIZO.txt");
	 File g = new File("NLPMIZO_tag.txt");
	 FileWriter fWriter;
	 FileWriter gWriter;
	 BufferedWriter fBWriter;
	 BufferedWriter gBWriter;
	 BufferedReader BReader;
	
 private String[] description = { "NN", "NNP", "NNS",
   "NNPS", "PRP", "PRP$", "IN", "UH","WDT", "WD","WRP","LS" };
	
  private JTextField txField = new JTextField(15);

  private JComboBox<String> cBoxW = new JComboBox<String>();
 //JTextField tt = new JTextField();
  
  private JComboBox<String> cBoxT = new JComboBox<String>();

  private JButton submitBtn = new JButton("Submit");
  
  private JButton exitBtn = new JButton("Exit");


  private int count = 0;
  public void init(){
	     txArea = new JTextArea(30,40);
		 txArea.setMargin(new Insets(10, 10, 10, 10));
		 txArea.setEditable(false);
		 this.setSize(new Dimension(500,500));
		 repaint();
		 

	
    /*********************************************************/
    exitBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);
			
		}
	});
    
    
    /*********************************************************/
    
    /*-------------------------------*/
    openBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
           JFileChooser chooser = new JFileChooser();
           chooser.setMultiSelectionEnabled(true);
           int option = chooser.showOpenDialog(ComboBoxes.this);
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
				 line = BReader.readLine();
				 String s[] = line.split("\\.");
				 for(int i =0;i<s.length;i++){
		            	txArea.append(s[i]+"."+"\n");
		            	
		            }
				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
          
           /***************/
           try {
      		
        	   appnd = new Append(mpath);
        	   lineArr = appnd.Read();
        	   words = lineArr[count].split(" ");
	      		 for(int j = 0;j<words.length;j++)
	      			cBoxW.addItem(words[j]);
	      		count++;
	      		
        	   
        		   
      		/**********************************/
        	   //for the next button
        	   nextBtn.addActionListener(new ActionListener() {
        		
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			// TODO Auto-generated method stub
        			
        			if(e.getSource()==nextBtn){
        			
        				cBoxW.removeAllItems();
        			 words = lineArr[count].split(" ");
        			 for(int k = 0;k<words.length;k++){ 
        				 cBoxW.addItem(words[k]);
        			 }
        			count++;
        			}
        			
        		}
        	});
          
      		  /*******************************/
      	} catch (Exception e1) {
      		
      		e1.printStackTrace();
      	}
           /********************/
           
          }
          else {
            statusbar.setText("You canceled.");
          }
        }
      }); //end of openBtn
    
	 
	  
		
	
 for (int i = 0; i < description.length; i++)
  cBoxT.addItem(description[cnt++]);
  txField.setEditable(false);
   
    
    
    /*-------------------------------*/
    submitBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ex){
    	  
    		  if(ex.getSource()==submitBtn)
    		  	{
    			  try 
    			  {
    				   fWriter = new FileWriter(f, true);
    				   gWriter = new FileWriter(g, true);
    				   fBWriter = new BufferedWriter(fWriter);
    				   gBWriter = new BufferedWriter(gWriter);
    		   
    		   String word =cBoxW.getSelectedItem()+"_"+cBoxT.getSelectedItem();
    		   String tag= (String)cBoxT.getSelectedItem();
    		   txField.setText(word);
    		   txArea.append(word);
    		   txArea.append("\n");
    		   String content1="<beg>";
    		   String content2="<end>";
    		fBWriter.write(content1);
			fBWriter.write(word);
			fBWriter.write("\n");
			fBWriter.write(content2);
			fBWriter.close();
			gBWriter.write(tag);
			gBWriter.write(" ");
			gBWriter.close();
			cBoxW.removeItem(cBoxW.getSelectedItem());
			//tt.removeAll();
		
				
    			  }
    		 
    		  catch(IOException e1) {
  				e1.printStackTrace();
  				
				
			}
  				catch (Exception e2) {
				e2.printStackTrace();
			}
    		
    	  }//end of if  
    	  }
    		  
      
    });//end of submitBtn
   
     
    Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(openBtn);
    cp.add(txField);
    cp.add(cBoxW);
    cp.add(nextBtn);
    cp.add(cBoxT);
    cp.add(submitBtn);
    cp.add(exitBtn);
    cp.add(txArea);
   
  }//end of init() method
  public static void main(String[] arg){
      run(new ComboBoxes(), 200, 125);
        
}//end of main

public static void run(JApplet applet, int width, int height) {
JFrame frame = new JFrame();

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().add(applet);
frame.pack();
applet.init();
applet.start();
frame.setVisible(true);


}//end of run()

}//end of ComboBox class 
