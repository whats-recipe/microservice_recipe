package ao.inocencio.recipeservice.domain.model;

import ao.inocencio.recipeservice.domain.exception.DomainValidationException;

//Value Object: Identified by its attributes, immutable
public class Ingredient {
    private String name;
    private String quantity;

    public Ingredient(String name, String quantity) {
        if(name == null || name.isBlank()) {
            throw new DomainValidationException("Ingredient name cannot be empty.");
        }
        this.name = name;
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }
    public String getQuantity(){
        return quantity;
    }

    // Value Objects must override equals() and hashCode() for structural equality
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o; 
        return name.equals(that.name) && quantity.equals(that.quantity);      
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, quantity);
    }
}
