package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomPizzaIngredient implements Serializable {

    private int id;

    private String ingredientName;

    private int typeId;

    public CustomPizzaIngredient(int id, String ingredientName, int typeId){
        this.id = id;
        this.ingredientName = ingredientName;
        this.typeId = typeId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getId() {
        return id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}

