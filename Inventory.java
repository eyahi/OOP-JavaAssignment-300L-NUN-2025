// import javax.swing.*;
// import javax.swing.border.Border;

// import java.awt.Color;
// import java.awt.Component;
// import java.awt.GridLayout;
// import java.awt.Dimension;
public class Inventory{

    public static void main (String[] args) {
        // String textInvent = """

        //         Inventory Mangement System

        //         """;
        // 17 to 84 GUI
        // JLabel panelInventoryLabel = new JLabel(textInvent, JLabel.CENTER);

        // //Border for Inventory Management System label
        // Border blackline = BorderFactory.createLineBorder(Color.BLACK, 5);
        // panelInventoryLabel.setBorder(blackline);

        // // panelInventoryLabel.setMinimumSize(new Dimension(200, 200));
        // // panelInventoryLabel.setPreferredSize(new Dimension(200, 200));
        // // panelInventoryLabel.setMaximumSize(new Dimension(200, 200));
        // panelInventoryLabel.setSize(585, 20);

        // // Panels for add button, entry field and delete button
        // JPanel mainPanel = new JPanel();
        // JPanel internalPanel = new JPanel();
        // internalPanel.setLayout(new GridLayout(1, 3, 15, 15));

        // // add button, entry field and delete button
        // JButton add_button = new JButton("Add Inventory");
        // JTextField entryInventory = new JTextField();
        // JButton delete_button = new JButton("Delete Inventory");

        // internalPanel.add(add_button);
        // internalPanel.add(entryInventory);
        // internalPanel.add(delete_button);

        
    
        // JPanel panelHeaders = new JPanel();
        // panelHeaders.setSize(600, 150);
        // panelHeaders.setLayout(new GridLayout(1, 5, 5, 5));
        // JPanel panelContent = new JPanel(); 

        // // Main frame
        // JFrame frame = new JFrame();
        

        // frame.setSize(600, 600);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        // frame.add(panelInventoryLabel);
        // frame.add(mainPanel);
        // frame.add(panelHeaders);
        // frame.add(panelContent);
        // // frame.add(panelContent);
        
        // //Label for name of inventory
        // JLabel label = new JLabel("Name");
        // panelHeaders.add(label);

        // //Label for quantity of inventory
        // JLabel label2 = new JLabel("Quantity left");
        // panelHeaders.add(label2);

        // //Empty label for add button
        // JLabel label3 = new JLabel("ADD");
        // label3.setBackground(Color.BLUE);
        // panelHeaders.add(label3);

        // //Empty label for price entry
        // JLabel label4 = new JLabel("Quantity");
        // panelHeaders.add(label4);

        //  //Empty label for subtract button
        // JLabel label5 = new JLabel("SUBTRACT");
        // label5.setBackground(Color.RED);
        // panelHeaders.add(label5);

        // frame.setVisible(true);

   
    
    // Reference.inv_array[0] = "sherif";
    // for (String inv : Reference.inv_array){
    //     if (inv == null) {
    //         continue;
    //     }
    //     else{
    //     System.out.println(inv);
    //     }
    // }

    
    
    // add_invent("Hello");
    // add_invent("Hi");
    // add_invent("Good day");
    Inventory_Items obj1 = new Inventory_Items();
    obj1.displayAll();
    obj1.add_invent("apple");
    obj1.displayAll();
    obj1.add_invent("milo");
    obj1.displayAll();
    obj1.add_invent("milk");
    obj1.displayAll();
    obj1.add_invent("egg");
    obj1.displayAll();
    obj1.add_invent("fanta");
    obj1.displayAll();
    // obj1.delete_invent("e");

    obj1.add("milo", 4);
    obj1.subtract("milo", 1);
    obj1.add("fanta", 7);
    obj1.displayAll();
    
    
    }
    
}

class Main_Window{
    public Main_Window(){

    };
}

class Inventory_Items {

    //Arrays for storing the inventory and their respective quantitites
    private String[] inv_array = {};
    private int[] quant_array = {};
    
    //Method for adding new item in inventory

    public void add_invent(String item){
      
        String[] new_inv_array = new String[inv_array.length + 1];
        System.arraycopy(inv_array, 0, new_inv_array, 0, inv_array.length);
        new_inv_array[new_inv_array.length-1] = item;
        this.inv_array = new_inv_array;

        int[] new_quant_array = new int[quant_array.length + 1];
        System.arraycopy(quant_array, 0, new_quant_array, 0, quant_array.length);
        new_quant_array[new_quant_array.length-1] = 0;
        this.quant_array = new_quant_array;

        
    }

    //Method for displaying all in inventory

    public void displayAll(){
        int n = 0;
        for (String inv : this.inv_array){       
            int m = 0;
            for (int quant:this.quant_array){
                if (m == n){
                    System.out.println(inv + " : " + quant);
                    }

                m = m + 1;
                }
            n = n + 1;
            }
    }

    //Method for deleting an item in inventory

    public void delete_invent(String item){
        int n = 0;
        for (String i : this.inv_array){
            if (item == i){
                break;
            }
            n = n + 1;

        }
        String[] new_inv_array = new String[this.inv_array.length-1];
        System.arraycopy(inv_array, 0, new_inv_array, 0, n);

        int[] new_quant_array = new int[this.quant_array.length-1];
        System.arraycopy(quant_array, 0, new_quant_array, 0, n);
    

        int m = 0;
        for (String k : this.inv_array){ 
            if (m > n && m < new_inv_array.length + 1){
                 
                new_inv_array[m-1] = k;
            }
            m = m + 1;
           
        }

        int l = 0;
        for (int k : this.quant_array){ 
            if (l > n && l < new_quant_array.length + 1){
                 // int i = n; n <= new_inv_array.length; i++
                // System.out.println(l);
                new_quant_array[l-1] = k;
            }
            l = l + 1;
           
        }
       
        this.quant_array = new_quant_array;

       
    }

    public void add(String item, int addedQuantity){
        int n = 0;
         for (String i : this.inv_array){
            if (i == item){
                break;
            }
            n = n + 1;
         }
        

        this.quant_array[n] = this.quant_array[n] + addedQuantity;
        System.out.println("Added " + addedQuantity + " to " + item);
        System.out.println("Quantity available now is " + this.quant_array[n]);
    }

    public void subtract(String item, int subtractedQuantity){
         int n = 0;
         for (String i : this.inv_array){
            if (i == item){
                break;
            }
            n = n + 1;
         }
        
        if (this.quant_array[n] < subtractedQuantity){
            System.out.println("Not enough in inventory");
        }
        else{
        this.quant_array[n] = this.quant_array[n] - subtractedQuantity;
        System.out.println("Removed " + subtractedQuantity + " from " + item);
        System.out.println("Quantity available now is " + this.quant_array[n]);
        }

        
   }
    
}

