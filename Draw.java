/******************************************************************
 *  NAME:				Christopher Nguyen 10236311
 *  CLASS:				TR AM
 *  COURSE:             CSC231 Computer Science and Programming II
 *	Lab:			    Number 4
 *	FILE:				Draw.java
 *	TARGET:				Java 6.0 and 7.0
 *****************************************************************/

// Import Core Java packages
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;

public class Draw extends JFrame implements ActionListener, ItemListener {

	// Initial Frame size
	static final int WIDTH = 1000;                // frame width
	static final int HEIGHT = 700;               // frame height

    // Color choices
    static final String COLOR_NAMES[] = {"None", "Red", "Blue", "Green"};
    static final Color COLORS[] = {null, Color.red, Color.blue, Color.green};

    // Button control
    JButton circle;
    JButton roundRec;
    JButton threeDRec;
    JButton line;
    JButton square;
    JButton oval;
    
    JButton clear;
    JButton reset;
    JButton deleteShape;

    // Color choice box
    Choice colorChoice;

    // the canvas
    DrawCanvas canvas;

    /**
     * Constructor
     */
	public Draw() {
	    super("Java Draw");
        setLayout(new BorderLayout());

        // create panel for controls
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        add(topPanel, BorderLayout.CENTER);

        // create button control
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(buttonPanel);
        

        circle = new JButton("Circle");
        buttonPanel.add(circle);
        roundRec = new JButton("Rounded Rectangle");
        buttonPanel.add(roundRec);
        threeDRec = new JButton("3D Rectangle");
        buttonPanel.add(threeDRec);

        // add button listener
        circle.addActionListener(this);
        roundRec.addActionListener(this);
        threeDRec.addActionListener(this);
        
        
        topPanel.add(buttonPanel);

        line = new JButton("Line");
        buttonPanel.add(line);
        square = new JButton("Square");
        buttonPanel.add(square);
        oval = new JButton("Oval");
        buttonPanel.add(oval);

        // add button listener
        line.addActionListener(this);
        square.addActionListener(this);
        oval.addActionListener(this);


        // create panel for color choices
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(colorPanel);
        JLabel label = new JLabel("Filled Color:");
        colorPanel.add(label);
        colorChoice = new Choice();
        for(int i=0; i<COLOR_NAMES.length; i++) {
            colorChoice.add(COLOR_NAMES[i]);
        }
        colorPanel.add(colorChoice);
        colorChoice.addItemListener(this);
        
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(buttonPanel2);

        clear = new JButton("Clear");
        buttonPanel2.add(clear);
        reset = new JButton("Reset");
        buttonPanel2.add(reset);

        // add button listener
        clear.addActionListener(this);
        reset.addActionListener(this);

        // create the canvas
        canvas = new DrawCanvas();
        add(canvas, BorderLayout.CENTER);
        
     
	} // end of constructor


    /**
     *  Implementing ActionListener
     */
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == circle) {  // circle button
            canvas.setShape(DrawCanvas.CIRCLE);
        }
        else if(event.getSource() == roundRec) {  // rounded rectangle button
            canvas.setShape(DrawCanvas.ROUNDED_RECTANGLE);
        }
        else if(event.getSource() == threeDRec) { // 3D rectangle button
            canvas.setShape(DrawCanvas.RECTANGLE_3D);
        }
        else if(event.getSource() == oval) { 
            canvas.setShape(DrawCanvas.OVAL);
        }
        else if(event.getSource() == square) { 
            canvas.setShape(DrawCanvas.SQUARE);
        }
        else if(event.getSource() == line) { 
            canvas.setShape(DrawCanvas.LINE);
        }
        
        else if(event.getSource() == clear){	//clear button
        	canvas.setShape(DrawCanvas.CLEAR);
            canvas.repaint();     
        }
        else if(event.getSource() == reset) { // square button
       	 canvas.setShape(DrawCanvas.CLEAR);
            canvas.repaint();     
            canvas.setShape(DrawCanvas.CIRCLE);
            canvas.repaint();    
            canvas.setFilledColor(null);
            colorChoice.select(0);
        }
    }

    /**
     * Implementing ItemListener
     */
    public void itemStateChanged(ItemEvent event) {
        Color color = COLORS[colorChoice.getSelectedIndex()];
        canvas.setFilledColor(color);
    }

    /**
     * the main method
     */
    public static void main(String[] argv) {
        // Create a frame
        Draw Jframe = new Draw();
        Jframe.setSize(WIDTH, HEIGHT);
        Jframe.setLocation(150, 100);

        // add window closing listener
        Jframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });

        // Show the frame
        Jframe.setVisible(true);
    }
}
