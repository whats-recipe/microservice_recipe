package ao.inocencio.recipeservice.domain.model;

import ao.inocencio.recipeservice.domain.exception.DomainValidationException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
@Data
public class Recipe {

    @Id
    @GeneratedValue
    private UUID id;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients = new HashSet<>();

    @Column(nullable = false, length = 5000)
    private String preparationMethod;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecipeType type;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    public Recipe() {}

    private Recipe(UUID id, String name, String description, Set<Ingredient> ingredients,
                   String preparationMethod, String imageUrl, RecipeType type,
                   Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.preparationMethod = preparationMethod;
        this.imageUrl = imageUrl;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        if (ingredients != null) {
            ingredients.forEach(this::addIngredient);
        }
        validateState();
    }

    private void validateState() {
        if (name == null || name.isBlank()) {
            throw new DomainValidationException("Recipe name cannot be empty.");
        }
        if (ingredients == null || ingredients.isEmpty()) {
            throw new DomainValidationException("Recipe must contain ingredients.");
        }
        if (preparationMethod == null || preparationMethod.isBlank()) {
            throw new DomainValidationException("Recipe preparation method cannot be empty.");
        }
        if (type == null) {
            throw new DomainValidationException("Recipe type cannot be null.");
        }
    }

    public static Recipe createNew(String name, String description, Set<Ingredient> ingredients,
                                   String preparationMethod, String imageUrl, RecipeType type) {
        return new Recipe(UUID.randomUUID(), name, description, ingredients, preparationMethod,
                imageUrl, type, Instant.now(), Instant.now());
    }

    public void updateDetails(String newName, String newDescription, String newPreparationMethod,
                              String newImageUrl, RecipeType newType) {
        if (newName == null || newName.isBlank()) {
            throw new DomainValidationException("Recipe name cannot be empty during update");
        }
        this.name = newName;
        this.description = newDescription;
        this.preparationMethod = newPreparationMethod;
        this.imageUrl = newImageUrl;
        this.type = newType;
        this.updatedAt = Instant.now();
        validateState();
    }

    public void updateIngredients(Set<Ingredient> newIngredients) {
        if (newIngredients == null || newIngredients.isEmpty()) {
            throw new DomainValidationException("Recipe must contain ingredients.");
        }
        this.ingredients.clear();
        newIngredients.forEach(this::addIngredient);
        this.updatedAt = Instant.now();
        validateState();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
        ingredient.setRecipe(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
