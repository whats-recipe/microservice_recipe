package ao.inocencio.recipeservice.application.port.in;

import ao.inocencio.recipeservice.application.port.dto.RecipeResponse;

public interface UpdateRecipeUseCase {
RecipeResponse updateRecipe(String recipeId, UpdateRecipeCommand command);
}
