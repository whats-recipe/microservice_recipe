package ao.inocencio.recipeservice.application.port.in;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import ao.inocencio.recipeservice.application.port.dto.CreateRecipeCommand;
import ao.inocencio.recipeservice.application.port.dto.RecipeResponse;
@Service
public interface CreateRecipeUseCase {
    RecipeResponse createRecipe(CreateRecipeCommand command);
}
