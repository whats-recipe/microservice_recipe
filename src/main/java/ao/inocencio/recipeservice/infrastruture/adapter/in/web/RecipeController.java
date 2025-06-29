package ao.inocencio.recipeservice.infrastruture.adapter.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.inocencio.recipeservice.application.port.dto.CreateRecipeCommand;
import ao.inocencio.recipeservice.application.port.dto.RecipeResponse;
import ao.inocencio.recipeservice.application.port.dto.RecipeSearchQuery;
import ao.inocencio.recipeservice.application.port.dto.UpdateRecipeCommand;
import ao.inocencio.recipeservice.application.port.in.CreateRecipeUseCase;
import ao.inocencio.recipeservice.application.port.in.DeleteRecipeUseCase;
import ao.inocencio.recipeservice.application.port.in.GetRecipeUseCase;
import ao.inocencio.recipeservice.application.port.in.UpdateRecipeUseCase;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final CreateRecipeUseCase createRecipeUseCase;
    private final GetRecipeUseCase getRecipeUseCase;
    private final UpdateRecipeUseCase updateRecipeUseCase;
    private final DeleteRecipeUseCase deleteRecipeUseCase;
    
    public RecipeController(CreateRecipeUseCase createRecipeUseCase, GetRecipeUseCase getRecipeUseCase,
            UpdateRecipeUseCase updateRecipeUseCase, DeleteRecipeUseCase deleteRecipeUseCase) {
        this.createRecipeUseCase = createRecipeUseCase;
        this.getRecipeUseCase = getRecipeUseCase;
        this.updateRecipeUseCase = updateRecipeUseCase;
        this.deleteRecipeUseCase = deleteRecipeUseCase;
    }
    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe (@RequestBody  CreateRecipeCommand command) {
        RecipeResponse recipeResponse = createRecipeUseCase.createRecipe(command);
        return new ResponseEntity<>(recipeResponse, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable String id) {
        RecipeResponse recipeResponse = getRecipeUseCase.getRecipeById(id);
        return ResponseEntity.ok(recipeResponse);
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
        List<RecipeResponse> recipes = getRecipeUseCase.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<RecipeResponse>> searchRecipes (@RequestParam(required = false) String nameKeyword,
                                                               @RequestParam(required = false) String type,
                                                               @RequestParam(required = false) Set<String> ingredientNames) { 
    RecipeSearchQuery query = new RecipeSearchQuery(
            nameKeyword,
            type != null ? ao.inocencio.recipeservice.domain.model.RecipeType.valueOf(type.toUpperCase()) : null,
            ingredientNames
        );
        List<RecipeResponse> recipes = getRecipeUseCase.searchRecipes(query);
        return ResponseEntity.ok(recipes);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponse> updateRecipe(@PathVariable String id, @RequestBody UpdateRecipeCommand command) {
        RecipeResponse updatedRecipe = updateRecipeUseCase.updateRecipe(id, command);
        return ResponseEntity.ok(updatedRecipe);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable String id) {
        deleteRecipeUseCase.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    

}
