package ao.inocencio.recipeservice.application.port.dto;

import java.util.Set;

import ao.inocencio.recipeservice.domain.model.Ingredient;
import ao.inocencio.recipeservice.domain.model.RecipeType;

public record UpdateRecipeCommand(
    String name,
    String description,
    Set<Ingredient> ingredients,
    String preparationMethod,
    String imageUrl,
    RecipeType type
) {}
