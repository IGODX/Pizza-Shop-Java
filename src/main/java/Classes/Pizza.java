package Classes;

import java.io.Serializable;

public class Pizza implements Serializable {
    private int id;

    private String pizzaName;
    private String photoUrl;

    private boolean isCustom;

    private String[] selectedIngredients;

    public String[] getSelectedIngredients() {
        return selectedIngredients;
    }

    public void setSelectedIngredients(String[] selectedIngredients) {
        this.selectedIngredients = selectedIngredients;
    }
    public Pizza(int id, String pizzaName, String photoUrl, boolean isCustom){
        this.id = id;
        this.pizzaName = pizzaName;
        this.photoUrl = photoUrl;
        this.isCustom = isCustom;
    }

    public Pizza(int id, String pizzaName, String photoUrl, boolean isCustom, String[] selectedIngredients){
        this(id, pizzaName, photoUrl, isCustom);
        this.selectedIngredients = selectedIngredients;
    }
    public int getId() {
        return id;
    }
    public boolean getIsCustom() {
        return isCustom;
    }
    public boolean setIsCustom() {
        return isCustom;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }
}
