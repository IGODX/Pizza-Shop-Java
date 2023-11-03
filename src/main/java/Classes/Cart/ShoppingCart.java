package Classes.Cart;

import Classes.Pizza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Serializable {
    private Map<Integer,CartItem> items = new HashMap();

    public void addItem(Pizza pizza, int quantity) {
        items.put(pizza.getId(),new CartItem(pizza, quantity, 0));
    }

    public void removeItem(int id) {
        items.remove(id);
    }

    public Map<Integer,CartItem> getItems() {
        return items;
    }

    public CartItem getCartItem(int id){
        return items.get(id);
    }

    public void clear(){
        items.clear();
    }
    public double getTotalPrice() {
        return 0;
    }

}
