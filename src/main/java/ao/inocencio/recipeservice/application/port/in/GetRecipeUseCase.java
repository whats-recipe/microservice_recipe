package ao.inocencio.recipeservice.application.port.in;

import org.springframework.stereotype.Service;

import ao.inocencio.recipeservice.application.port.dto.RecipeResponse;
import ao.inocencio.recipeservice.application.port.dto.RecipeSearchQuery;
@Service
public interface GetRecipeUseCase {
    RecipeResponse getRecipeById(String recipeId);
    java.util.List<RecipeResponse> getAllRecipes();
    java.util.List<RecipeResponse> searchRecipes(RecipeSearchQuery query);
}