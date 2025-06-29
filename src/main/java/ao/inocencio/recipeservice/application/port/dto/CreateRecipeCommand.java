package ao.inocencio.recipeservice.application.port.dto;

import java.util.Set;
import ao.inocencio.recipeservice.domain.model.Ingredient;
import ao.inocencio.recipeservice.domain.model.RecipeType;

public record CreateRecipeCommand(    String name,
    String description,
    Set<Ingredient> ingredients, // Or a specific DTO for ingredients if needed
    String preparationMethod,
    String imageUrl,
    RecipeType type) {}
