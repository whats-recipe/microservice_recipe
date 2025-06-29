package ao.inocencio.recipeservice.domain.model;

import ao.inocencio.recipeservice.domain.exception.DomainValidationException;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Recipe {
    private UUID id;
    private String name;
    private String description;
    private Set<Ingredient> ingredients; // Value Objects within the Aggregate
    private String preparationMethod;
    private String imageUrl;
    private RecipeType type; // Value Object/Enum
    private Instant createdAt;
    private Instant updatedAt;

    public Recipe(UUID id, String name, String description, Set<Ingredient> ingredients, String preparationMethod, String imageUrl, RecipeType type, Instant createdAt, Instant updateAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = new HashSet<>(ingredients); // Defensive copy
        this.preparationMethod = preparationMethod;
        this.imageUrl = imageUrl;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        validateState();
    }
    private void validateState() {
        if (this.name == null || this.name.isBlank()) {
            throw new DomainValidationException("Recipe name cannot be empty.");
        }
        if (this.ingredients == null || this.ingredients.isEmpty()) {
            throw new DomainValidationException("Recipe must contain ingredients.");
        }
    }
    public void updateIngredients(Set<Ingredient> newIngredients) {
        if (newIngredients == null || newIngredients.isEmpty()) {
            throw new DomainValidationException("Recipe must contain ingredients.");
        }
         this.ingredients = new HashSet<>(newIngredients);
         this.updatedAt = Instant.now();
         validateState();
    }

    // Factory method to create new Recipes
    public static Recipe createNew(String name, String description, Set<Ingredient> ingredients, String preparationMethod, String imageUrl, RecipeType type) {
        if(name == null || name.isEmpty()) {
            throw new DomainValidationException("Recipe name cannot be empty");
        }
        if (ingredients == null || ingredients.isEmpty()) {
            throw new DomainValidationException("Recipe must have ingredients");
        }
        if (preparationMethod == null || preparationMethod.isBlank()) {
            throw new DomainValidationException("Recipe preparation method cannot be empty"); 
        }
        return new Recipe(
         UUID.randomUUID(),
         name, 
         description, 
         ingredients, 
         preparationMethod, 
         imageUrl,
         type,
         Instant.now(),
         Instant.now()
        );  
    }
    // Method to update recipe details (Domain behavior)
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

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Set<Ingredient> getIngredients() {return ingredients;}

    public void setIngredients(Set<Ingredient> ingredients) {this.ingredients = ingredients;}

    public String getPreparationMethod() {return preparationMethod;}

    public void setPreparationMethod(String preparationMethod) {this.preparationMethod = preparationMethod;}

    public String getImageUrl() {return imageUrl;}

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    public RecipeType getType() {return type;}

    public void setType(RecipeType type) {this.type = type;}

    public Instant getCreatedAt() {return createdAt;}

    public void setCreatedAt(Instant createdAt) {this.createdAt = createdAt;}

    public Instant getUpdatedAt() {return updatedAt;}

    public void setUpdatedAt(Instant updatedAt) {this.updatedAt = updatedAt;}

    @Override
    public boolean equals(Object o) {
        /* implementation based on ID */ return true; }
    @Override
    public int hashCode() {
        /* implementation based on ID */ return 0; }
}
