package ao.inocencio.recipeservice.application.port.in;

import ao.inocencio.recipeservice.application.port.dto.RecipeResponse;
import ao.inocencio.recipeservice.application.port.dto.RecipeSearchQuery;

public interface GetRecipeUseCase {
    RecipeResponse getRecipeById(String recipeId);
    java.util.List<RecipeResponse> getAllRecipes();
    java.util.List<RecipeResponse> searchRecipes(RecipeSearchQuery query);
}