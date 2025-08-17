package ao.inocencio.recipeservice.domain.model;

import ao.inocencio.recipeservice.domain.exception.DomainValidationException;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ingredient")
@Data
public class Ingredient {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    protected Ingredient() {}

    public Ingredient(String name, String quantity) {
        if (name == null || name.isBlank()) {
            throw new DomainValidationException("Ingredient name cannot be empty.");
        }
        this.name = name;
        this.quantity = quantity;
    }

    // Package-private setter (Recipe controla a associação)
    void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    /*public UUID getId() { return  id; }
    public String getName() { return name; }
    public String getQuantity() { return quantity; }
    public Recipe getRecipe() { return recipe; } */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
