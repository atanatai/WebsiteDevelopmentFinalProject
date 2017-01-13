/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rahm.data;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 *
 * @author Atan
 */
public class Item implements Serializable {
    
    private String itemName;
    private int itemNumber;
    private float itemPrice;
    private int quantity;
    private String itemDescription;
    private String itemLink;

    public Item() {
        itemName="";
        itemNumber=0;
        itemPrice=0;
        quantity=0;
        itemDescription="";
        itemLink="";
    }

    public Item(String itemName, int itemNumber, float itemPrice, int quantity, String itemDescription, String itemLink) {
        this.itemName = itemName;
        this.itemNumber = itemNumber;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.itemDescription = itemDescription;
        this.itemLink = itemLink;
    }

    public String getItemLink(){
        return itemLink;
    }
    
    public void setItemLink(String itemLink){
        this.itemLink = itemLink;
    }
    
    public String getItemName() {
        return itemName;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public String getItemPrice() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(itemPrice);
    }
    
    public float getItemPriceRaw() {
        return (itemPrice);
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    
    
}
