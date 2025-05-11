package ao.inocencio.recipeservice.domain.service;

import ao.inocencio.recipeservice.domain.model.Recipe;

import java.util.List;

public interface ExternalRecipeServicePort {
    List<Recipe> fecthRecipes(int count);
    Recipe fecthRecipeById(String id);
}
