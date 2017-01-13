/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rahm.data;

import java.io.Serializable;

/**
 *
 * @author Atan
 */
public class Order implements Serializable{
    private int ONumber;
    private String ODate;
    private String OUName;
    private int OItemNumber;
    private int Qty;
    private float UnitPrice;
    private String OComplete;
    private String CardName;
    private double CardNumber;
    private int CSV;
    private String ExpDate;
    private String Email;

    public Order() {
    }

    public Order(String ODate, String OUName, int OItemNumber, int Qty, float UnitPrice, String OComplete, String CardName, double CardNumber, int CSV, String ExpDate, String Email) {
        this.ONumber = ONumber;
        this.ODate = ODate;
        this.OUName = OUName;
        this.OItemNumber = OItemNumber;
        this.Qty = Qty;
        this.UnitPrice = UnitPrice;
        this.OComplete = OComplete;
        this.CardName = CardName;
        this.CardNumber = CardNumber;
        this.CSV = CSV;
        this.ExpDate = ExpDate;
        this.Email = Email;
    }

    public int getONumber() {
        return ONumber;
    }

    public String getODate() {
        return ODate;
    }

    public String getOUName() {
        return OUName;
    }

    public int getOItemNumber() {
        return OItemNumber;
    }

    public int getQty() {
        return Qty;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public String getOComplete() {
        return OComplete;
    }

    public void setONumber(int ONumber) {
        this.ONumber = ONumber;
    }

    public void setODate(String ODate) {
        this.ODate = ODate;
    }

    public void setOUName(String OUName) {
        this.OUName = OUName;
    }

    public void setOItemNumber(int OItemNumber) {
        this.OItemNumber = OItemNumber;
    }

    public void setQty(int Qty) {
        this.Qty = Qty;
    }

    public void setUnitPrice(float UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public void setOComplete(String OComplete) {
        this.OComplete = OComplete;
    }

    public String getCardName() {
        return CardName;
    }

    public double getCardNumber() {
        return CardNumber;
    }

    public int getCSV() {
        return CSV;
    }

    public String getExpDate() {
        return ExpDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setCardName(String CardName) {
        this.CardName = CardName;
    }

    public void setCardNumber(double CardNumber) {
        this.CardNumber = CardNumber;
    }

    public void setCSV(int CSV) {
        this.CSV = CSV;
    }

    public void setExpDate(String ExpDate) {
        this.ExpDate = ExpDate;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
    
    
    
}
