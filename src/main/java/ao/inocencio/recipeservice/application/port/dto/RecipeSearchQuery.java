package ao.inocencio.recipeservice.application.port.dto;

import java.util.Set;
import ao.inocencio.recipeservice.domain.model.RecipeType;

public record RecipeSearchQuery(
    String nameKeyword,
    RecipeType type,
    Set<String> ingredientNames
) {}
