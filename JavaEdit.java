/******************************************************************
 *  NAME:				Christopher Nguyen 10236311
 *  CLASS:				TR AM
 *  COURSE:             CSC231 Computer Science and Programming II
 *	Lab:			    Number 7
 *	FILE:				JavaEdit.java
 *	TARGET:				Java 6.0 and 7.0
 ******************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.KeyEvent.*;
import java.awt.event.InputEvent.*;

public class JavaEdit extends Frame implements ActionListener {

	String clipBoard;
	String fileName;
	TextArea text;
	MenuItem newMI, openMI, saveMI, saveAsMI, exitMI, selallMI;
	MenuItem cutMI, copyMI, deleteMI, pasteMI;
	MenuItem aboutMI;

    /**
     * Constructor
     */
	public JavaEdit() {
		super("JavaEdit");          // set frame title
		setLayout(new BorderLayout());  // set layout

		// create menu bar
		MenuBar menubar = new MenuBar();
		setMenuBar(menubar);

		// create file menu
		Menu fileMenu = new Menu("File");
		menubar.add(fileMenu);
		newMI = fileMenu.add(new MenuItem("New"));
		newMI.addActionListener(this);
		
		openMI = fileMenu.add(new MenuItem("Open"));
		openMI.addActionListener(this);
		fileMenu.addSeparator();
		
		saveMI = fileMenu.add(new MenuItem("Save"));
		saveMI.addActionListener(this);
		
		saveAsMI = fileMenu.add(new MenuItem("Save As ..."));
		saveAsMI.addActionListener(this);
		fileMenu.addSeparator();


		fileMenu.addSeparator();
		exitMI = fileMenu.add(new MenuItem("Exit"));
		exitMI.addActionListener(this);

		// create edit menu
		Menu editMenu = new Menu("Edit");
		menubar.add(editMenu);
		selallMI = editMenu.add(new MenuItem("Select All"));
		selallMI.addActionListener(this);
		
		cutMI = editMenu.add(new MenuItem("Cut"));
		cutMI.addActionListener(this);
		
		copyMI = editMenu.add(new MenuItem("Copy"));
		copyMI.addActionListener(this);
		
		pasteMI = editMenu.add(new MenuItem("Paste"));
		pasteMI.addActionListener(this);
		
		deleteMI = editMenu.add(new MenuItem("Delete"));
		deleteMI.addActionListener(this);
		
		Menu helpMenu = new Menu("Help");
		menubar.add(helpMenu);
		aboutMI = helpMenu.add(new MenuItem("About"));
		aboutMI.addActionListener(this);

        // create text editing area
	    text = new TextArea();
	    add(text, BorderLayout.CENTER);
	}

    // implementing ActionListener
	
	
	public void actionPerformed(ActionEvent event) {
	    Object source = event.getSource();
	    if(source == newMI) {
    		clearText();
	    	fileName = null;
		    setTitle("JavaEdit");   // reset frame title
		}
		else if(source == openMI) {
			doOpen();
		}
	    else if(source == exitMI) {
	        System.exit(0);
	    }
		else if(source == cutMI) {
			doCopy();
			doDelete();
		}
		else if(source == copyMI) {
			doCopy();
		}
		else if(source == pasteMI) {
			doPaste();
		}
		else if(source == deleteMI) {
			doDelete();
		}
		else if(source == selallMI) {
			doSelall();
			}
		else if(source == saveMI) {
			doSave();
			}
		else if(source == saveAsMI) {
			doSaveas();
			}


		else if(source == aboutMI) {
			MessageDialog dialog = new MessageDialog(this, "About",
			" JavaEdit in Java, Version 2.0, 2017");
			dialog.setVisible(true);
			} 
	}

	private void doSaveas() {
		FileDialog fDialog = new FileDialog(this, "Save As ...", FileDialog.SAVE);
	    fDialog.setVisible(true);

	       fileName = fDialog.getFile();
	       String name = fileName.toString();
	       
	       if (name == null){
	    	   return;
	       }
	       else {
	    	   fileName = name;
	       }

	         setTitle("JavaEdit: " + name); 
	         fDialog.setFile(name);


	         FileWriter fileCharStream = null;  
	         try {

	            fileCharStream = new FileWriter(fileName); 
	         
	               fileCharStream.write(text.getText());

	         } catch (IOException excpt) {
	             MessageDialog dialog = new MessageDialog(this, "Error Message",
	                    fileName);
	             dialog.setVisible(true);
	             return;
	         } finally {
	            closeFileWriter(fileCharStream); 
	         }
	}
	
	private void doSave() {
		if(fileName == null  )
	    {
	        doSaveas();
	    }
	    FileWriter out = null;
	         try {
	               out =new FileWriter(fileName);

	              out.write(text.getText());

	            }
	            catch (IOException excpt) {
	                MessageDialog dialog = new MessageDialog(this, "Error Message",
	                        fileName);
	             dialog.setVisible(true);
	             return;
	            } finally {
	                closeFileWriter(out);}
	}
	
	         static void closeFileWriter(FileWriter fileName) {
	             try {
	                if (fileName != null) { 

	                   fileName.close(); 

	                }
	             } catch (IOException closeExcpt) {
	                    closeExcpt.getMessage();
	             }

	             return;
	          }
	         
	private void doSelall(){
		
		text.selectAll();
	}

	/**
	 * method to specify and open a file
	 */
	private void doOpen() {
	    // display file selection dialog
		FileDialog fDialog = new FileDialog(this, "Open ...", FileDialog.LOAD);
		fDialog.setVisible(true);
		// Get the file name chosen by the user
		String name = fDialog.getFile();
		// If user canceled file selection, return without doing anything.
		if(name == null)
			return;
		fileName = fDialog.getDirectory() + name;

		// Try to create a file reader from the chosen file.
		FileReader reader=null;
        try {
			reader = new FileReader(fileName);
	    } catch (FileNotFoundException ex) {
	        MessageDialog dialog = new MessageDialog(this, "Error Message",
	                                                 "File Not Found: "+fileName);
                dialog.setVisible(true);
                return;
	    }
		BufferedReader bReader = new BufferedReader(reader);

		// Try to read from the file one line at a time.
		StringBuffer textBuffer = new StringBuffer();
		try {
		    String textLine = bReader.readLine();
			while (textLine != null) {
				textBuffer.append(textLine + '\n');
				textLine = bReader.readLine();
			}
			bReader.close();
			reader.close();
		} catch (IOException ioe) {
	        MessageDialog dialog = new MessageDialog(this, "Error Message",
	                                   "Error reading file: "+ioe.toString());
            dialog.setVisible(true);
            return;
		}
		setTitle("JavaEdit: " +name);   // reset frame title
		text.setText(textBuffer.toString());
	}

	/**
	 * method to clear text editing area
	 */
	private void clearText() {
		text.setText("");
	}

    /**
     * method to copy selected text to the clipBoard
     */
	private void doCopy() {
		clipBoard = new String(text.getSelectedText());
	}

    /**
     * method to delete selected text
     */
	private void doDelete() {
		text.replaceRange("", text.getSelectionStart(), text.getSelectionEnd());
	}

	/**
	 * method to replace current selection with the contents of the clipBoard
	 */
	private void doPaste() {
	    if(clipBoard != null) {
		    text.replaceRange(clipBoard, text.getSelectionStart(),
		                      text.getSelectionEnd());
		}
	   
	    
	}

	/**
	 * class for message dialog
	 */
	class MessageDialog extends Dialog implements ActionListener {
	    private Label message;
	    private Button okButton;

	    // Constructor
	    public MessageDialog(Frame parent, String title, String messageString) {
	        super(parent, title, true);
	        setSize(400, 100);
	        setLocation(150, 150);
	        setLayout(new BorderLayout());

	        message = new Label(messageString, Label.CENTER);
	        add(message, BorderLayout.CENTER);

            Panel panel = new Panel(new FlowLayout(FlowLayout.CENTER));
            add(panel, BorderLayout.SOUTH);
	        okButton = new Button(" OK ");
	        okButton.addActionListener(this);
	        panel.add(okButton);
	    }

	    // implementing ActionListener
	    public void actionPerformed(ActionEvent event) {
	        setVisible(false);
	        dispose();
	    }
	}

    /**
     * the main method
     */
    public static void main(String[] argv) {
        // create frame
    	
        JavaEdit frame = new JavaEdit();
	    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(size.width-80, size.height-80);
        frame.setLocation(20, 20);

        // add window closing listener
        frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	            System.exit(0);
	        }
	    });

        // show the frame
        frame.pack();
        frame.setVisible(true);
    }
}
