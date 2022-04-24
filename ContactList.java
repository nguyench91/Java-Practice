
/******************************************************************
 *  NAME:				Christopher Nguyen 10236311
 *  CLASS:				TR AM
 *  COURSE:             CSC231 Computer Science and Programming II
 *	Lab:			    Number 7
 *	FILE:				ContactList.java
 *	TARGET:				Java 7.0 and 8.0
 *****************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.Arrays;
import java.util.Vector;

public class ContactList extends JFrame implements ActionListener, ListSelectionListener {

	MenuItem newMI, openMI, saveMI, saveAsMI, exitMI;
    MenuItem searchMI, deleteMI, updateMI, newEntryMI, sortMI;
    String fileName;

    List entries;
    TextField lastName, firstName, phoneNumber;
    JList<String> listView;
	DefaultListModel<String> nameList = new DefaultListModel<String>();
	Vector<String> numberList = new Vector<String>();
	File currentFile = null;

    /**
     * Constructor
     */
	public ContactList() {
		super("Phone Contacts");          // set frame title
		setLayout(new BorderLayout());    // set layout

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
	        saveAsMI = fileMenu.add(new MenuItem("Save As ..."));
	        fileMenu.addSeparator();
	        exitMI = fileMenu.add(new MenuItem("Exit"));
	        exitMI.addActionListener(this);

	        // create edit menu
	        Menu editMenu = new Menu("Edit");
	        menubar.add(editMenu);
	        updateMI = editMenu.add(new MenuItem("Update"));
	        updateMI.addActionListener(this);
	        newEntryMI = editMenu.add(new MenuItem("New Entry"));
	        newEntryMI.addActionListener(this);
	        deleteMI = editMenu.add(new MenuItem("Delete"));
	        deleteMI.addActionListener(this);
	        editMenu.addSeparator();
	        searchMI = editMenu.add(new MenuItem("Search"));
	        searchMI.addActionListener(this);
	        sortMI = editMenu.add(new MenuItem("Sort"));
	        sortMI.addActionListener(this);


        // create phone list and controls
        JPanel listPanel = new JPanel(new BorderLayout());
        add(listPanel, BorderLayout.CENTER);
        JLabel label = new JLabel("Name List", JLabel.LEFT);
        listPanel.add(label, BorderLayout.NORTH);
        
        listView = new JList<String>(nameList);
        listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listView.addListSelectionListener(this);
        JScrollPane listScroller = new JScrollPane(listView);
        listPanel.add(listScroller, BorderLayout.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
	    add(panel, BorderLayout.WEST);
	    JPanel editPanel = new JPanel(new GridLayout(6, 1));
	    panel.add(editPanel, BorderLayout.NORTH);
	    label = new JLabel("Last Name", Label.LEFT);
	    editPanel.add(label);
	    lastName = new TextField();
	    editPanel.add(lastName);
	    label = new JLabel("First Name", Label.LEFT);
	    editPanel.add(label);
	    firstName = new TextField();
	    editPanel.add(firstName);
	    label = new JLabel("Phone Number", Label.LEFT);
	    editPanel.add(label);
	    phoneNumber = new TextField();
	    editPanel.add(phoneNumber);
	}

    // implementing ActionListener
	public void actionPerformed(ActionEvent event) {
	    Object source = event.getSource();
	    if(source == newMI) {
    		nameList.clear();
    		numberList.clear();
	    	currentFile = null;
	    	display(-1);
		    setTitle("Phone Contacts");   // reset frame title
		}
		else if(source == openMI) {
			doOpen();
		}
	    else if(source == exitMI) {
	        System.exit(0);
	    }
		else if(source == updateMI) {
		    int index = listView.getSelectedIndex();
		    String name = lastName.getText().trim() + " " + firstName.getText().trim();
		    String number = phoneNumber.getText().trim();
		    if(index < 0) {  // add a new entry
		        nameList.addElement(name);
		        numberList.addElement(number);
		        index = nameList.getSize()-1;
		    }
		else {  // update an existing entry
		        nameList.set(index, name);
		        numberList.set(index, number);        
		    }
		    listView.setSelectedIndex(index);
		    listView.ensureIndexIsVisible(index);
		}
		else if(source == newEntryMI) {
		    listView.clearSelection();
		    display(-1);
		}
		else if(source == searchMI) {
		    String searchName = JOptionPane.showInputDialog(this,
		                        "Please enter a name (last first) to search:");
		    System.out.println("Name to search: " + searchName);
		}
	 
	else if (source == deleteMI) {
         // just delete the selected entry
         int index = entries.getSelectedIndex();
         if (index >= 0) {
             entries.remove(index);
         }
     } else if (source == sortMI) {
         String[] sortedEntries = entries.getItems();
         Arrays.sort(sortedEntries);
         entries.removeAll();
         for (String entry : sortedEntries) {
             entries.add(entry);
         }
	}}

    /**
     * Implementing ListSelectionListener to display the selected entry
     */
    public void valueChanged(ListSelectionEvent e) {
        display(listView.getSelectedIndex());
    }

	/**
	 * method to specify and open a file
	 */
	private void doOpen() {
	    // display file selection dialog
		JFileChooser fChooser = new JFileChooser(new File("."));
		if(fChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			// Get the file name chosen by the user
			currentFile = fChooser.getSelectedFile();
		}
		else {	// If user canceled file selection, return without doing anything.
			return;
		}

		// Try to create a file reader from the chosen file.
		FileReader reader;
        try {
			reader = new FileReader(currentFile);
	    } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File Not Found: " + currentFile.getPath(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
            doOpen();
            return;
	    }
		BufferedReader bReader = new BufferedReader(reader);
        // remove items from before if any
   		nameList.clear();
        numberList.clear();
		// Try to read from the input file one line at a time.
		try {
		    int index;
		    String name, number;
		    String textLine = bReader.readLine();
			while (textLine != null) {
			    index = textLine.indexOf((int) ',');
			    if(index > 0) {
			        name = textLine.substring(0, index);
			        number = textLine.substring(index+1);
			        nameList.addElement(name.trim());
			        numberList.addElement(number.trim());
			    }
				textLine = bReader.readLine();
			}
			bReader.close();
			reader.close();
		} catch (IOException ioe) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + ioe.toString(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
		}
		setTitle("Phone Contacts: " + currentFile.getPath());   // reset frame title
		listView.setSelectedIndex(0);
        display(0);
	}

    /**
     * method to display the current entry
     */
	private void display(int index) {
	    if(index < 0) {
	        lastName.setText("");
	        firstName.setText("");
	        phoneNumber.setText("");
	    }
	    else {
	    	 String[] fields = entries.getItem(index).split("(,|\\s)");
	            if (fields.length > 0) {
	                lastName.setText(fields[0]);
	            }
	            if (fields.length > 1) {
	                firstName.setText(fields[1]);
	            }
	            if (fields.length > 2) {
	                phoneNumber.setText(fields[2]);
	            }
	    }
	}

    /**
     * the main method
     */
    public static void main(String[] argv) {
        // create frame
    	 System.out.println("Creating window ... ");
         ContactList frame = new ContactList();
         Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
         frame.setSize(size.width / 2, size.height / 2);
         frame.setLocation(100, 100);

         System.out.println("Your Screen Size: " + size.width + " (width) x "
                 + size.height + " (height)");

         // add window closing listener
         frame.addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent e) {
                 System.exit(0);
             }
         });

         // show the frame
         frame.setVisible(true);
     }
 }