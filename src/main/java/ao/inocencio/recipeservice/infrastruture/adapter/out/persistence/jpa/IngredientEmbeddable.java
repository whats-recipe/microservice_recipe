package ao.inocencio.recipeservice.infrastruture.adapter.out.persistence.jpa;

import ao.inocencio.recipeservice.domain.model.Ingredient;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class IngredientEmbeddable {
private String name;
    private String quantity;

    public IngredientEmbeddable() {} // JPA requires no-arg constructor
    public IngredientEmbeddable(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static IngredientEmbeddable fromDomain(ao.inocencio.recipeservice.domain.model.Ingredient ingredient) {
        return new IngredientEmbeddable(ingredient.getName(), ingredient.getQuantity());
    }

    public Ingredient toDomain() {
        return new ao.inocencio.recipeservice.domain.model.Ingredient(this.name, this.quantity);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
}
