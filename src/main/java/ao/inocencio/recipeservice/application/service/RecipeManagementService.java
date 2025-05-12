package ao.inocencio.recipeservice.application.service;

import ao.inocencio.recipeservice.domain.exception.RecipeNotFoundExpeception;
import ao.inocencio.recipeservice.domain.model.Recipe;
import ao.inocencio.recipeservice.domain.ports.RecipeRepositoryPort;
import ao.inocencio.recipeservice.domain.service.ExternalRecipeServicePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeManagementService {
    private final RecipeRepositoryPort recipeRepositoryPort;
    private final ExternalRecipeServicePort externalRecipeServicePort;

    public RecipeManagementService(RecipeRepositoryPort recipeRepositoryPort, ExternalRecipeServicePort externalRecipeServicePort) {
        this.recipeRepositoryPort = recipeRepositoryPort;
        this.externalRecipeServicePort = externalRecipeServicePort;
    }

    @Transactional
    public List<Recipe> importRecipesFromExternalService(int count) {
        List<Recipe> externalRecipes = externalRecipeServicePort.fecthRecipes(count);
    //Filter existing recipes
    List<Recipe> newRecipes = externalRecipes.stream()
            .filter(recipe -> !recipeRepositoryPort.existsByExternalId(recipe.getExternalId()))
            .toList();

    return newRecipes.stream()
            .map(recipeRepositoryPort::save)
            .toList();
    }
    public List<Recipe> getAllRecipes(){
        return recipeRepositoryPort.findAll();
    }
    public Recipe getRecipeById(String id) {
        return recipeRepositoryPort.findById(id)
                .orElseThrow(() -> new RecipeNotFoundExpeception("Recipe not found with id: " + id));
    }
}
