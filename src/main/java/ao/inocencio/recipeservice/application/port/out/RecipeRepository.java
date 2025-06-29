package ao.inocencio.recipeservice.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ao.inocencio.recipeservice.domain.model.Recipe;
import ao.inocencio.recipeservice.domain.model.RecipeType;

public interface RecipeRepository {
    Recipe save(Recipe recipe);
    Optional<Recipe> findById(UUID id);
    List<Recipe> findAll();
    void deleteById(UUID id);
    List<Recipe> findByNameContainingIgnoreCase(String name);
    List<Recipe> findByType(RecipeType type);
}
