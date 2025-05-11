package ao.inocencio.recipeservice.adapter.inbound;

import ao.inocencio.recipeservice.application.dto.RecipeDTO;
import ao.inocencio.recipeservice.application.mapper.RecipeMapper;
import ao.inocencio.recipeservice.application.service.RecipeManagementService;
import ao.inocencio.recipeservice.domain.model.Recipe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeManagementService recipeManagementService;
    private final RecipeMapper recipeMapper;

    public RecipeController(RecipeManagementService recipeManagementService, RecipeMapper recipeMapper) {
        this.recipeManagementService = recipeManagementService;
        this.recipeMapper = recipeMapper;
    }
    @GetMapping("/{id}")
    public RecipeDTO getRecipe(@PathVariable String id) {
        Recipe recipe = recipeManagementService.getRecipeById(id);
        return recipeMapper.toDTO(recipe);
    }
}
