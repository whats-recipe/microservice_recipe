package ao.inocencio.recipeservice.application.port.in;

import ao.inocencio.recipeservice.application.port.dto.CreateRecipeCommand;
import ao.inocencio.recipeservice.application.port.dto.RecipeResponse;

public interface CreateRecipeUseCase {
    RecipeResponse createRecipe(CreateRecipeCommand command);
}
