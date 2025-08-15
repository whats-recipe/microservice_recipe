package ao.inocencio.recipeservice.application.port.in;

import org.springframework.stereotype.Service;

@Service
public interface DeleteRecipeUseCase {
void deleteRecipe(String recipeId);
}
