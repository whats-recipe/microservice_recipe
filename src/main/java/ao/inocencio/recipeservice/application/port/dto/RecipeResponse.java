package ao.inocencio.recipeservice.application.port.dto;

import java.util.Set;
import ao.inocencio.recipeservice.domain.model.RecipeType;

public record RecipeResponse(
    String id,
    String name,
    String description,
    Set<IngredientDTO> ingredients, // Using a DTO for ingredients in response as well
    String preparationMethod,
    String imageUrl,
    RecipeType type
) {
    public record IngredientDTO(String name, String quantity) {}
}
