package rahm.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Orders implements Serializable{
    private List<Order> orderData;
    private static Database db = new Database();
    
    public Orders(){
        orderData = new ArrayList<Order>();
    }
    
    public List<Order> getAllOrders(){
        return orderData = db.getAllOrders();
    }
    
    public List<Order> getUserOrders(String username){
        return orderData = db.getUserOrders(username);
    }
    
    public List<Order> addOrder(String username, Order order){
        db.removeAllCartItems(username);
        return db.addOrder(username, order.getOItemNumber(), order.getQty(), order.getUnitPrice(),order.getCardName(),order.getCardNumber(),order.getCSV(),order.getExpDate(),order.getEmail());
    }
}
