package ao.inocencio.recipeservice.domain.service;

import ao.inocencio.recipeservice.domain.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExternalRecipeServicePort {
    List<Recipe> fecthRecipes(int count);
    Recipe fecthRecipeById(String id);
}
