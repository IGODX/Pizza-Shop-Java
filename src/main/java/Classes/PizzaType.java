package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class PizzaType implements Serializable {

    private int id;

    private String typeName;

    private ArrayList<CustomPizzaIngredient> customPizzaIngredients = new ArrayList<>();

    public ArrayList<CustomPizzaIngredient> getCustomPizzaIngredients() {
        return customPizzaIngredients;
    }

    public void setCustomPizzaIngredients(ArrayList<CustomPizzaIngredient> customPizzaIngredients) {
        this.customPizzaIngredients = customPizzaIngredients;
    }

    public boolean addIngredient(CustomPizzaIngredient ingredient){
       return this.customPizzaIngredients.add(ingredient);
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public PizzaType(int id, String typeName){
        this.id = id;
        this.typeName = typeName;
    }
}
