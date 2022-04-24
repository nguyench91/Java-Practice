/******************************************************************
 *  NAME:				Christopher Nguyen 10236311
 *  CLASS:				TR AM
 *  COURSE:             CSC231 Computer Science and Programming II
 *	Lab:			    Number 3
 *	FILE:				FastFood.java
 *	TARGET:				Java 6.0 and 7.0
 ******************************************************************/

// Import Core Java packages
import java.awt.*;
import java.awt.event.*;

public class FastFood extends Frame {

	// Initial Frame size
	static final int WIDTH = 400;                // frame width
	static final int HEIGHT = 300;               // frame height

    // Arrays of selection items
    static final String[] JUNK_FOODS = {"Burger", "Hotdog", "Fries", "Pizza"};
    static final int[] JUNK_FOODS_PRICE = {199, 150, 99, 299};
    static final String[] DESSERTS = {"Ice cream", "Pie", "Cake", "Donut"};
    static final int[] DESSERTS_PRICE = {149, 249, 299, 99};
    static final String[] FRUIT_FOODS = {"Pear", "Apple", "Peach", "Banana"};
    static final int[] FRUIT_FOODS_PRICE = {89, 79, 99, 69};
    static final String[] BEVERAGES = {"Soda", "Coffee", "Milk", "Water"};
    static final int[] BEVERAGES_PRICE = {85, 95, 75, 65};

    // List and Button control
    List junkFoodList;
    List dessertList;
    List fruitItemsList;
    List beverageList;
    List itemsOrderedList;
    Button addJunkFood;
    Button addDessert;
    Button addFruit;
    Button addBeverage;
    Button deleteItem;
    Button clearAll;


    // Total amount and display
    Label amountLabel;
    int amount = 0;

    /**
     * Constructor
     */
	public FastFood() {
	    setTitle("Fast Food To Go");
        setLayout(new BorderLayout());

        // create display for item selection
        Panel itemPanel = new Panel(new GridLayout(2, 1));
        add(itemPanel, BorderLayout.CENTER);

        // create display and control for junk food selection items
        Panel junkFoodPanel = new Panel(new BorderLayout());
        itemPanel.add(junkFoodPanel);
        Label junkFood = new Label("Fast Food Items", Label.CENTER);
        junkFood.setForeground(Color.red);
        junkFoodPanel.add(junkFood, BorderLayout.NORTH);
        junkFoodList = new List();
        for(int i=0; i<JUNK_FOODS.length; i++) {
            junkFoodList.add(JUNK_FOODS[i]+ "   $" + (float)JUNK_FOODS_PRICE[i]/100);
        }
        junkFoodPanel.add(junkFoodList, BorderLayout.CENTER);
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        junkFoodPanel.add(buttonPanel, BorderLayout.SOUTH);
        addJunkFood = new Button("Get Food Item");
        buttonPanel.add(addJunkFood);
        
        // create display and control for fruit selection items
        Panel fruitPanel = new Panel(new BorderLayout());
        itemPanel.add(fruitPanel);
        Label fruitItem = new Label("Fruit Items", Label.CENTER);
        fruitItem.setForeground(Color.red);
        junkFoodPanel.add(fruitItem, BorderLayout.NORTH);
        fruitItemsList = new List();
        for(int i=0; i<FRUIT_FOODS.length; i++) {
            junkFoodList.add(FRUIT_FOODS[i]+ "   $" + (float)FRUIT_FOODS_PRICE[i]/100);
        }
        fruitPanel.add(junkFoodList, BorderLayout.CENTER);
        Panel buttonPanel1 = new Panel(new FlowLayout(FlowLayout.CENTER));
        junkFoodPanel.add(buttonPanel1, BorderLayout.SOUTH);
        addJunkFood = new Button("Get Fruit Item");
        buttonPanel1.add(addFruit);
        
     // create display and control for beverage selection items
        Panel beveragePanel = new Panel(new BorderLayout());
        itemPanel.add(beveragePanel);
        Label beverageItem = new Label("Beverage Items", Label.CENTER);
        beverageItem.setForeground(Color.red);
        beveragePanel.add(beverageItem, BorderLayout.NORTH);
        beverageList = new List();
        for(int i=0; i<BEVERAGES.length; i++) {
            beverageList.add(BEVERAGES[i]+ "   $" + (float)BEVERAGES_PRICE[i]/100);
        }
        beveragePanel.add(beverageList, BorderLayout.CENTER);
        beveragePanel.add(buttonPanel, BorderLayout.SOUTH);
        addBeverage = new Button("Get Beverage Item");
        buttonPanel.add(addBeverage);

        // create display and control for dessert selection items
        Panel dessertPanel = new Panel(new BorderLayout());
        itemPanel.add(dessertPanel);
        Label dessertLabel = new Label("Dessert Items", Label.CENTER);
        dessertLabel.setForeground(Color.red);
        dessertPanel.add(dessertLabel, BorderLayout.NORTH);
        dessertList = new List();
        for(int i=0; i<DESSERTS.length; i++) {
            dessertList.add(DESSERTS[i]+ "   $" + (float)DESSERTS_PRICE[i]/100);
        }
        dessertPanel.add(dessertList, BorderLayout.CENTER);
        buttonPanel1 = new Panel(new FlowLayout(FlowLayout.CENTER));
        dessertPanel.add(buttonPanel1, BorderLayout.SOUTH);
        addDessert = new Button("Get Dessert Item");
        buttonPanel1.add(addDessert);

        // create display and control for items ordered and amount
        Panel orderedPanel = new Panel(new BorderLayout());
        add(orderedPanel, BorderLayout.EAST);

        // create display and control for items ordered
        Panel itemsOrderedPanel = new Panel(new BorderLayout());
        orderedPanel.add(itemsOrderedPanel, BorderLayout.CENTER);
        Label itemsOrdered = new Label("Items Ordered", Label.CENTER);
        itemsOrdered.setForeground(Color.red);
        itemsOrderedPanel.add(itemsOrdered, BorderLayout.NORTH);
        itemsOrderedList = new List();
        itemsOrderedPanel.add(itemsOrderedList, BorderLayout.CENTER);
        buttonPanel1 = new Panel(new FlowLayout(FlowLayout.CENTER));
        itemsOrderedPanel.add(buttonPanel1, BorderLayout.SOUTH);
        
        deleteItem = new Button("Delete Ordered Item");
        buttonPanel1.add(deleteItem);
        deleteItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		      
	        }});
        clearAll = new Button("Clear all items");
        buttonPanel1.add(clearAll);
        clearAll.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	itemsOrderedPanel.clear();
	        }});

        // create display for amount
        Panel amountPanel = new Panel(new BorderLayout());
        orderedPanel.add(amountPanel, BorderLayout.SOUTH);
        Label totalLabel = new Label("Total Amount: ");
        amountPanel.add(totalLabel, BorderLayout.WEST);
        amountLabel = new Label("$0.00");
        amountPanel.add(amountLabel, BorderLayout.CENTER);

        // create and add list selection listener
        SelectionListener selListener = new SelectionListener();
        junkFoodList.addActionListener(selListener);
        dessertList.addActionListener(selListener);
        beverageList.addActionListener(selListener);
        fruitItemsList.addActionListener(selListener);

        // create and add button listener
        ButtonListener buttonListener = new ButtonListener();
        addJunkFood.addActionListener(buttonListener);
        addDessert.addActionListener(buttonListener);
        addFruit.addActionListener(buttonListener);
        addBeverage.addActionListener(buttonListener);
	} // end of constructor

    /**
     *  Listener class for list selection
     */
    class SelectionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String selectedItem;
            int selectedIndex;
            int itemPrice;

            if(event.getSource() == junkFoodList) {  // select item from junk food list
                selectedIndex = junkFoodList.getSelectedIndex();
                selectedItem = junkFoodList.getSelectedItem();
                itemPrice = JUNK_FOODS_PRICE[selectedIndex];
                addOrderedItem(selectedItem, itemPrice);
            }
            else if(event.getSource() == dessertList) {  // select item from dessert list
                selectedIndex = dessertList.getSelectedIndex();
                selectedItem = dessertList.getSelectedItem();
                itemPrice = DESSERTS_PRICE[selectedIndex];
                addOrderedItem(selectedItem, itemPrice);
            }
            else if(event.getSource() == fruitItemsList) {  // select item from dessert list
                selectedIndex = fruitItemsList.getSelectedIndex();
                selectedItem = fruitItemsList.getSelectedItem();
                itemPrice = FRUIT_FOODS_PRICE[selectedIndex];
                addOrderedItem(selectedItem, itemPrice);
            }
            else if(event.getSource() == beverageList) {  // select item from dessert list
                selectedIndex = beverageList.getSelectedIndex();
                selectedItem = beverageList.getSelectedItem();
                itemPrice = BEVERAGES_PRICE[selectedIndex];
                addOrderedItem(selectedItem, itemPrice);
            }
        }}
  
    /**
     *  Listener class for button selection
     */
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String selectedItem;
            int selectedIndex;
            int itemPrice;

            if(event.getSource() == addJunkFood) {  // get item from junk food list
                selectedIndex = junkFoodList.getSelectedIndex();
                if(selectedIndex >= 0) {
                    selectedItem = junkFoodList.getSelectedItem();
                    itemPrice = JUNK_FOODS_PRICE[selectedIndex];
                    addOrderedItem(selectedItem, itemPrice);
                }
            }
            else if(event.getSource() == addDessert) {  // get item from dessert list
                selectedIndex = dessertList.getSelectedIndex();
                if(selectedIndex >= 0) {
                    selectedItem = dessertList.getSelectedItem();
                    itemPrice = DESSERTS_PRICE[selectedIndex];
                    addOrderedItem(selectedItem, itemPrice);
                }
                else if(event.getSource() == addBeverage) {  // get item from dessert list
                    selectedIndex = beverageList.getSelectedIndex();
                    if(selectedIndex >= 0) {
                        selectedItem = beverageList.getSelectedItem();
                        itemPrice = BEVERAGES_PRICE[selectedIndex];
                        addOrderedItem(selectedItem, itemPrice);
                    }
                    else if(event.getSource() == addFruit) {  // get item from dessert list
                        selectedIndex = fruitItemsList.getSelectedIndex();
                        if(selectedIndex >= 0) {
                            selectedItem = fruitItemsList.getSelectedItem();
                            itemPrice = FRUIT_FOODS_PRICE[selectedIndex];
                            addOrderedItem(selectedItem, itemPrice);
                        }
            }
        }}
    }

    /**
     *  method to add an ordered item
     */
    void addOrderedItem(String item, int price) {
        itemsOrderedList.add(item);
        amount += price;
        amountLabel.setText("$"+(float)amount/100);
    }

    /**
     * the main method
     */
    public void main(String[] argv) {
        // Create a frame
        FastFood frame = new FastFood();
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(150, 100);

        // add window closing listener
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
}}
