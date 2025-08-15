package ao.inocencio.recipeservice.domain.model;

import ao.inocencio.recipeservice.domain.exception.DomainValidationException;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
public class Recipe {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Embedded
    @JdbcTypeCode(SqlTypes.JSON)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
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

    // JPA precisa do construtor padrão
    protected Recipe() {}

    // Construtor privado para centralizar validação
    private Recipe(UUID id, String name, String description, Set<Ingredient> ingredients,
                   String preparationMethod, String imageUrl, RecipeType type,
                   Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = new HashSet<>(ingredients);
        this.preparationMethod = preparationMethod;
        this.imageUrl = imageUrl;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    // Factory method para criar novas receitas
    public static Recipe createNew(String name, String description, Set<Ingredient> ingredients,
                                   String preparationMethod, String imageUrl, RecipeType type) {
        return new Recipe(UUID.randomUUID(), name, description, ingredients, preparationMethod,
                          imageUrl, type, Instant.now(), Instant.now());
    }

    // Atualizar detalhes da receita
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

    // Atualizar ingredientes
    public void updateIngredients(Set<Ingredient> newIngredients) {
        if (newIngredients == null || newIngredients.isEmpty()) {
            throw new DomainValidationException("Recipe must contain ingredients.");
        }
        this.ingredients = new HashSet<>(newIngredients);
        this.updatedAt = Instant.now();
        validateState();
    }

    // Getters imutáveis
    public UUID getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public Set<Ingredient> getIngredients() { return Collections.unmodifiableSet(ingredients); }

    public String getPreparationMethod() { return preparationMethod; }

    public String getImageUrl() { return imageUrl; }

    public RecipeType getType() { return type; }

    public Instant getCreatedAt() { return createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
