/******************************************************************
 * Name:		Christopher Nguyen	10236311
 * Class:		TR AM
 *  COURSE:     CSC231 Computer Science and Programming II
 *  Lab:        Number 1
 *  FILE:		Welcome.java
 *  TARGET:		Java 8
 ******************************************************************/

// Import Core Java packages
import java.awt.*;
import java.awt.event.*;

public class Welcome extends Frame {

	// Constants and variables used in the program
	static final int WIDTH = 450;                // frame width
	static final int HEIGHT = 300;               // frame height
	static final int SMALL_SIZE = 16;            // small font size
	static final int MEDIUM_SIZE = 20;           // medium font size
	static final int LARGE_SIZE = 24;            // large font size
	static final int TINY_SIZE = 7;				//new tiny font size
	static final int HUGE_SIZE = 41;			//new huge font size
	static final String COLOR_NAMES[] = {"Red", "Blue", "Green", "Black", "Orange"};
	static final Color COLORS[] = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK, Color.ORANGE};
	static final String INITIAL_FACE = Font.DIALOG;      // initial typeface
	static final int INITIAL_STYLE = Font.PLAIN;         // initial type style
	static final int INITIAL_SIZE = LARGE_SIZE;          // initial type size

	String typeFace = INITIAL_FACE;     // current typeface
	int typeStyle = INITIAL_STYLE;      // current type style
	int typeSize = INITIAL_SIZE;        // current type size

	Label text;                            // display text
	Checkbox small;                        // checkbox for small font
	Checkbox medium;                       // checkbox for medium font
	Checkbox large;                        // checkbox for large font
	Checkbox tiny;
	Checkbox huge;
	Choice colorChoice;                    // choice box for text color
	Button resetButton;                    // button to reset font and color

    /**
     * Constructor
     */
	public Welcome() {
	    setTitle("Welcome");             // set frame title

        // create text
	    text = new Label("Welcome to the World of Java!");
          text.setAlignment(Label.CENTER);
	    text.setFont(new Font(typeFace, typeStyle, typeSize));
	    text.setForeground(COLORS[0]);
	    add(text, BorderLayout.CENTER);

	    Panel controlPanel = new Panel();
	    controlPanel.setLayout(new GridLayout(0, 1));
	    add(controlPanel, BorderLayout.SOUTH);

        // create choice box
        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(panel);
        Label label = new Label("Select a text color");
        panel.add(label);
        colorChoice = new Choice();
        colorChoice.add(COLOR_NAMES[0]);
        colorChoice.add(COLOR_NAMES[1]);
        colorChoice.add(COLOR_NAMES[2]);
        colorChoice.add(COLOR_NAMES[3]);
        colorChoice.add(COLOR_NAMES[4]);
        panel.add(colorChoice);
        colorChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
	            text.setForeground(COLORS[colorChoice.getSelectedIndex()]);
            }
        });

        // create radio buttons
	    panel = new Panel();
	    panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    controlPanel.add(panel);
	    label = new Label("Select a type size");
	    panel.add(label);
	    CheckboxGroup sizeGroup = new CheckboxGroup();
	    
	    tiny = new Checkbox("Tiny", sizeGroup, true);
	    panel.add(tiny);
	    tiny.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent event) {
	            if(tiny.getState()) {
	                typeSize = TINY_SIZE;
	                fontChange(typeFace, typeStyle, typeSize);
	            }
	        }
	    });
	    
	    small = new Checkbox("Small", sizeGroup, false);
	    panel.add(small);
	    small.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent event) {
	            if(small.getState()) {
	                typeSize = SMALL_SIZE;
	                fontChange(typeFace, typeStyle, typeSize);
	            }
	        }
	    });
	    medium = new Checkbox("Medium", sizeGroup, false);
	    panel.add(medium);
	    medium.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent event) {
	            if(medium.getState()) {
	                typeSize = MEDIUM_SIZE;
	                fontChange(typeFace, typeStyle, typeSize);
	            }
	        }
	    });
	    large = new Checkbox("Large", sizeGroup, true);
	    panel.add(large);
	    large.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent event) {
	            if(large.getState()) {
	                typeSize = LARGE_SIZE;
	                fontChange(typeFace, typeStyle, typeSize);
	            }
	        }
	    });
	    
	    huge = new Checkbox("Huge", sizeGroup, true);
	    panel.add(huge);
	    huge.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent event) {
	            if(huge.getState()) {
	                typeSize = HUGE_SIZE;
	                fontChange(typeFace, typeStyle, typeSize);
	            }
	        }
	    });
	    
	    panel = new Panel();
	    panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    controlPanel.add(panel);
	    label = new Label("Select a font");
	    panel.add(label);
	    CheckboxGroup styleGroup = new CheckboxGroup();
	    
	    Checkbox ITALIC = new Checkbox("Italic", styleGroup, true);
	    panel.add(ITALIC);
	    ITALIC.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent event) {
	            if(ITALIC.getState()) {
	               typeStyle = Font.ITALIC;
	                fontChange(typeFace, typeStyle, typeSize);
	            }
	        }
	    });
	    
	    Checkbox BOLD = new Checkbox("Bold", styleGroup, true);
	    panel.add(BOLD);
	    BOLD.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent event) {
	            if(BOLD.getState()) {
	               typeStyle = Font.BOLD;
	                fontChange(typeFace, typeStyle, typeSize);
	            }
	        }
	    });
	    
	    Checkbox PLAIN = new Checkbox("Plain", styleGroup, true);
	    panel.add(PLAIN);
	    PLAIN.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent event) {
	            if(PLAIN.getState()) {
	               typeStyle = Font.PLAIN;
	                fontChange(typeFace, typeStyle, typeSize);
	            }
	        }
	    });
	    

        // create reset button
        panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        controlPanel.add(panel);
		resetButton = new Button("Reset");
		panel.add(resetButton);
		resetButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		        large.setState(true);          // reset radio button
		        PLAIN.setState(true);
		        colorChoice.select(0);         // reset color choice box
	            text.setForeground(COLORS[0]);  // reset text color
	            typeSize = INITIAL_SIZE;       // reset type size
	            typeStyle = INITIAL_STYLE;
	            fontChange(typeFace, typeStyle, typeSize);      // reset text font
	        }
		    
		});
		Button exitButton = new Button("Exit");
		panel.add(exitButton);
		exitButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		        System.exit(0);    
	        }
		    
		});
	} // end of constructor

    /**
     *  Method to change the text font
     */
    private void fontChange(String typeFace, int typeStyle, int typeSize) {
        Font font = new Font(typeFace, typeStyle, typeSize);
	    text.setFont(font);
    }

    /**
     * the main method
     */
    public static void main(String[] argv) {
        // Create frame
        Welcome welcome = new Welcome();
	    welcome.setBackground(Color.LIGHT_GRAY);
        welcome.setSize(WIDTH, HEIGHT);
        welcome.setLocation(150, 100);

        // add window closing listener
        welcome.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });

        // Show the frame
        welcome.setVisible(true);
    }
}
