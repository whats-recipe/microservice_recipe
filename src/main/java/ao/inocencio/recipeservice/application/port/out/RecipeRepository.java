package ao.inocencio.recipeservice.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ao.inocencio.recipeservice.domain.model.Recipe;
import ao.inocencio.recipeservice.domain.model.RecipeType;
@Repository
public interface RecipeRepository extends JpaRepository <Recipe, UUID> {
    Recipe save(Recipe recipe);
    Optional<Recipe> findById(UUID id);
    List<Recipe> findAll();
    void deleteById(UUID id);
    List<Recipe> findByNameContainingIgnoreCase(String name);
    List<Recipe> findByType(RecipeType type);
}
