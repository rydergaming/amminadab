import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
/*
* Copyright (C) 2015 Szilágyi István, ryd.spes@gmail.com
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
* */
public class AmmiProcess {
    static JFrame frame = new JFrame("Amminadab Java GUI");
    static JLabel label = new JLabel("");
    static JTextArea debugArea = new JTextArea();
    static JTextArea respArea = new JTextArea();
    static JScrollPane debugPane = new JScrollPane(debugArea);
    static JScrollPane respPane = new JScrollPane(respArea);
    static JTextField input = new JTextField();
	static OutputStream stdin;
	static BufferedWriter writer;
    static List<String> commandArg = new ArrayList<String>();
	  public static void setUpUi() {
		  
		  	respArea.append("Copyright (C) 2015 Szilágyi István, ryd.spes@gmail.com\n");
		  	respArea.append(
		  			"This program is free software: you can redistribute it and/or modify\n" +
		  			"it under the terms of the GNU General Public License as published by\n" +
		  			"the Free Software Foundation, either version 3 of the License, or\n" +
		  			"(at your option) any later version.\n\n");
		  	respArea.append(
		  			"This program is distributed in the hope that it will be useful,\n" +
		  			"but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
		  			"MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" +
		  			"GNU General Public License for more details.\n\n"+
		  			"You should have received a copy of the GNU General Public License\n" +
		  			"along with this program.  If not, see <http://www.gnu.org/licenses/>. ");
		    respPane.setBorder(BorderFactory.createEmptyBorder());
		    respPane.setBounds(0, 0, 800, 300);

		    debugPane.setBorder(BorderFactory.createEmptyBorder());			    
		    debugPane.setBounds(0, 400, 800, 90);
		   
		    input.setText("");
		    input.setSize(800, 20);
		    respPane.setPreferredSize(new Dimension(800, 400));
		    debugPane.setPreferredSize(new Dimension(800,200));
		    //debugPane.setBackground(Color.BLUE);
		    label.setFont(new Font("Impact", Font.BOLD, 14));
		    frame.add(respPane,BorderLayout.NORTH);
		    frame.add(debugPane,BorderLayout.CENTER);
		    frame.add(input,BorderLayout.SOUTH);
		    
		    frame.setSize(800, 600);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		    
		    debugArea.setEditable(false);
		    //debugArea.setPreferredSize(new Dimension(600, 100));
		    debugArea.setBackground(new Color(160,0,0));
		    debugArea.setFont(new Font("Impact", Font.PLAIN, 14));
		    debugArea.setForeground(Color.WHITE);
		  
		    respArea.setEditable(false);
		    respArea.setFont(new Font("Impact", Font.BOLD, 14));
		    //respArea.setPreferredSize(new Dimension(600, 100));
		    respArea.setBackground(new Color(0,160,0));
		    
		    respPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		    respPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    debugPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		    debugPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		    DefaultCaret debugCaret = (DefaultCaret)debugArea.getCaret();
		    DefaultCaret respCaret = (DefaultCaret)respArea.getCaret();
		    debugCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		    respCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		    
		    
		    input.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String str = input.getText()+"\n";
					System.out.println(str);
					try{
						writer.write(str);
						writer.flush();
					} catch (IOException err) {
						System.out.println("Error sending the text");
					}
					input.setEditable(false);
					thread = new TripletThread(input.getText() + ".triplets");
					running = true;
				}
			});	
	  }
    
    static ProcessBuilder tripLength;
	static Process tripProcess;
    static InputStream tIs;
    static InputStreamReader tIsr;
    static BufferedReader tBr;
    
    static boolean running = false;
    static TripletThread thread;

	  
	  public static void main(String args[]) 
			     throws InterruptedException,IOException 
			  {
			    List<String> command = new ArrayList<String>();
			    command.add("./samu");
			 /*   LoggedPrintStream lpsOut = LoggedPrintStream.create(System.out);
			    LoggedPrintStream lpsErr = LoggedPrintStream.create(System.err);*/

		    	//samu.delete();
		    	String line;
		        commandArg.add("wc");
		        commandArg.add("-l");		    	
    	
		    	setUpUi();
			    ProcessBuilder builder = new ProcessBuilder(command);
			    builder.redirectErrorStream(true);
			    //builder.inheritIO();
			    final Process process = builder.start();

			    InputStream is = process.getInputStream();
			    InputStreamReader isr = new InputStreamReader(is);
			    BufferedReader br = new BufferedReader(isr);



			    

			    
		    	stdin = process.getOutputStream();
		    	writer = new BufferedWriter(new OutputStreamWriter(stdin));

			    
		        int i=1;
			    //int ch = 0;
			    //writer.write("test\n");
			    //writer.flush();
			    //TripletThread thread = new TripletThread("giTale1.triplets");
			    //thread.start();*/
			   // while ((line = br.readLine()) !=null) {
			    while (i==1) {
			    	//System.out.println(line);
			    	line = br.readLine();
			    	if(running)
			    		thread.readTriplet();
			    	//input.setText(tBr.readLine());

			    	if (debugArea.getDocument().getLength()> 1000)
			    		{
			    			try {
			    				debugArea.getDocument().remove(0, 100);
			    			} catch(BadLocationException e) {
			    				System.out.println("Document too stronk");
			    			}
			    		}
			    	if (respArea.getDocument().getLength()> 1000)
		    		{
		    			try {
		    				respArea.getDocument().remove(0, 100);
		    			} catch(BadLocationException e) {
		    				System.out.println("Document too stronk");
		    			}
		    		}
	    			//int check = checkLine(line);
	    			
	    			//if (line.length() > 8)
	    			if (line.contains("Amminadab") && line.contains(">"))
	    				{
	    					String resp;
	    					resp = line.split(">")[1];
	    					respArea.append("\n" + resp);
	    				}
			    	debugArea.append("\n"+ line);
			  //  	final String tmp = input.getText();
		    	
			    	/*bw.write("Car is blue");
			    	bw.flush();*/

			    	//debugArea.setCaretPosition(debugArea.getDocument().getLength());

			    }

			    process.destroy();
			    return;
			  }



}
