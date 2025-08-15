package ao.inocencio.recipeservice.application.port.in;

import org.springframework.stereotype.Service;

import ao.inocencio.recipeservice.application.port.dto.RecipeResponse;
import ao.inocencio.recipeservice.application.port.dto.UpdateRecipeCommand;

@Service
public interface UpdateRecipeUseCase {
RecipeResponse updateRecipe(String recipeId, UpdateRecipeCommand command);
}