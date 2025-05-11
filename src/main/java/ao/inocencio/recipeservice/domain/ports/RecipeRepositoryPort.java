package ao.inocencio.recipeservice.domain.ports;

import ao.inocencio.recipeservice.domain.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepositoryPort {
Recipe save (Recipe recipe);
Optional<Recipe> findById(String id);
List<Recipe> findAll();
void delete(String id);
boolean existsByExternalId(String externalId);
}
