package ao.inocencio.recipeservice.infrastruture.adapter.out.persistence.jpa;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import ao.inocencio.recipeservice.domain.model.RecipeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
public class RecipeJpaEntity {
@Id
private UUID id;
private String name;
    private String description;
    @ElementCollection // For simple collections of Value Objects
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    private Set<IngredientEmbeddable> ingredients = new HashSet<>();
    @Column(columnDefinition = "TEXT") // For longer text like preparation method
    private String preparationMethod;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private RecipeType type;
    private Instant createdAt;
    private Instant updatedAt;
}
