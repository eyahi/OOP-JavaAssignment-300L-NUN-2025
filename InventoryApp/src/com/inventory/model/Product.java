package com.inventory.model;

public class Product{
    private String productId;
    private String name;
    private String description;
    private double price;
    private int stockquantity;

public Product(String productId,String name,String description,double price,int stockquantity){
    this.productId= productId;
    this.name= name;
    this.description= description;
    this.price=price;
    this.stockquantity=stockquantity;

}
public String getProductId(){
    return productId;
};
public String getName(){
    return name;
}
public String getDescription(){
    return description;
}
public double getPrice(){
    return price;
}
public int getStockquantity(){
    return stockquantity;
}
public void setName(String name){
    this.name=name;
};
public void setDescription(String description){
    this.description =description;
};
public void setPrice(double price){
    this.price=price;
};
public void setStockquantity(int stockquantity){
    this.stockquantity= stockquantity;
};

@ Override
public String toString(){
    return ("ProductID: "+ productId + " Name: " + name + " Description: " + description + " Price: " + price + "Stockquantity: " + stockquantity );
}


}