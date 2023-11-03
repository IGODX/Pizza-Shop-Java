package Classes;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private int id;

    private String ingredientName;

    public int getId() {
        return id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Ingredient(int id, String ingredientName){
        this.id = id;
        this.ingredientName = ingredientName;
    }
}
