package ao.inocencio.recipeservice.domain.ports;

import ao.inocencio.recipeservice.domain.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RecipeRepositoryPort {
    Recipe save(Recipe recipe);

    Optional<Recipe> findById(String id);

    List<Recipe> findAll();

    void delete(String id);

    boolean existsByExternalId(String externalId);
}
