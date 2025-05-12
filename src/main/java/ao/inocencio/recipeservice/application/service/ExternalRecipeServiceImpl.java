package ao.inocencio.recipeservice.application.service;

import ao.inocencio.recipeservice.domain.model.Recipe;
import ao.inocencio.recipeservice.domain.service.ExternalRecipeServicePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalRecipeServiceImpl  implements ExternalRecipeServicePort {
    @Override
    public List<Recipe> fecthRecipes(int count) {
        // Lógica para chamar um serviço externo e obter a lista de receitas
        System.out.println("Fetching " + count + " recipes from external service");
        return List.of(); // Substitua pela sua implementação real
    }

    @Override
    public Recipe fecthRecipeById(String id) {
        // Lógica para chamar um serviço externo e obter uma receita por ID
        System.out.println("Fetching recipe with ID " + id + " from external service");
        return null; // Substitua pela sua implementação real
    }
}
