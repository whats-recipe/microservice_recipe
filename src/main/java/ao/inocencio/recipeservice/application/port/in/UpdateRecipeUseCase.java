package ao.inocencio.recipeservice.application.port.in;

import ao.inocencio.recipeservice.application.port.dto.RecipeResponse;
import ao.inocencio.recipeservice.application.port.dto.UpdateRecipeCommand;


public interface UpdateRecipeUseCase {
RecipeResponse updateRecipe(String recipeId, UpdateRecipeCommand command);
}