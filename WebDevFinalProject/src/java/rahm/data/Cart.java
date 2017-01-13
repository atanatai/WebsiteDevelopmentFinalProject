
package rahm.data;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Atan
 */
public class Cart implements Serializable {
    private List<Item> cartItems;
    private static Database db = new Database();
    
    public Cart(){
        cartItems = new ArrayList<Item>();
    }
    
    public List<Item> getAllAvailableItems(){
        return db.getAllItems();
    }
    
    public int getCartSize(String username){
        int cartSize = 0;
        cartItems = db.getUserCart(username);
        for (Item item : cartItems){
            cartSize += item.getQuantity();
        }
        return cartSize;
    }
    
    public int getUniqueCartSize(String username){
        cartItems = db.getUserCart(username);
        if(cartItems.size() > 0){
            return cartItems.size();
        }
        else{
            return 0;
        }
    }
    
    public List<Item> getCartItems(String username){
        cartItems = db.getUserCart(username);
        return this.cartItems;
    }
    /**
    public void setCartItems(String username, List<Item> cartItems){
        this.cartItems = cartItems;
    }
    **/
    public void addItem(String username, Item i){
        cartItems = db.getUserCart(username);
        for (Item item : cartItems){
            if(item.getItemNumber() == i.getItemNumber()){
                //item.setQuantity(item.getQuantity() + i.getQuantity());
                int newQty = item.getQuantity()+i.getQuantity();
                db.updateItem(username, item.getItemNumber(), newQty);
                return;
            }
        }
        //cartItems.add(i);
        db.addItem(username, i);
    }
    
    public void removeCartItem(String username, int itemNumber){
        cartItems = db.getUserCart(username);
        for (Item item : cartItems){
            if(item.getItemNumber() == itemNumber){
                //cartItems.remove(item);
                db.removeItem(username, itemNumber);
            }
        }
    }
    
    public void updateCartItem(String username, int itemId, int newQuantity){
        cartItems = db.getUserCart(username);
        for (Item item : cartItems){
            if(item.getItemNumber() == itemId){
                if(newQuantity == 0){
                //cartItems.remove(item);
                db.removeItem(username, itemId);
                return;
                }
                db.updateItem(username, itemId, newQuantity);
            }
            /**
            else if(item.getItemNumber() == itemId){
                //item.setQuantity(newQuantity);
                
            }
            * **/
        }
    }
    
    public String getTotalCost(String username){
        cartItems = db.getUserCart(username);
        float cartTotal = 0;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        for (Item item : cartItems){
            cartTotal += item.getQuantity() * item.getItemPriceRaw();
        }
        return currencyFormat.format(cartTotal);
    }
    
}
