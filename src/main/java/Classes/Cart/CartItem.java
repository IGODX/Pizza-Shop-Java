package Classes.Cart;

import Classes.Pizza;

import java.io.Serializable;

public class CartItem implements Serializable {
        private Pizza pizza;
        private int quantity;
        private double price;

        public CartItem(Pizza pizza, int quantity, double price){
            this.pizza = pizza;
            this.quantity = quantity;
            this.price = price;
        }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProduct(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
