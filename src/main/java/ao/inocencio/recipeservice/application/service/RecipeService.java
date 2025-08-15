package ao.inocencio.recipeservice.application.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.inocencio.recipeservice.application.exception.RecipeNotFoundException;
import ao.inocencio.recipeservice.application.port.dto.CreateRecipeCommand;
import ao.inocencio.recipeservice.application.port.dto.RecipeMapper;
import ao.inocencio.recipeservice.application.port.dto.RecipeResponse;
import ao.inocencio.recipeservice.application.port.dto.RecipeSearchQuery;
import ao.inocencio.recipeservice.application.port.dto.UpdateRecipeCommand;
import ao.inocencio.recipeservice.application.port.in.CreateRecipeUseCase;
import ao.inocencio.recipeservice.application.port.in.DeleteRecipeUseCase;
import ao.inocencio.recipeservice.application.port.in.GetRecipeUseCase;
import ao.inocencio.recipeservice.application.port.in.UpdateRecipeUseCase;
import ao.inocencio.recipeservice.application.port.out.RecipeRepository;
import ao.inocencio.recipeservice.domain.exception.DomainValidationException;
import ao.inocencio.recipeservice.domain.model.Recipe;

@Service
public class RecipeService implements CreateRecipeUseCase, GetRecipeUseCase, UpdateRecipeUseCase, DeleteRecipeUseCase {
    private final RecipeRepository recipeRepository;

 
 public RecipeService(RecipeRepository recipeRepository){
    this.recipeRepository=recipeRepository;
 }

 @Override
 public RecipeResponse createRecipe(CreateRecipeCommand command) {
    try {
        Recipe newRecipe = RecipeMapper.toDomainRecipe(command);

        Recipe savedRecipe = recipeRepository.save(newRecipe);
        return RecipeMapper.toRecipeResponse(savedRecipe);
}   catch (DomainValidationException e) {
            throw new ao.inocencio.recipeservice.application.exception.InvalidRecipeDataException(e.getMessage());
      }
    }

    @Override
    public RecipeResponse getRecipeById(String recipeId) {
        UUID uuid = UUID.fromString(recipeId);
        Recipe recipe = recipeRepository.findById(uuid)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with ID: " + recipeId));
        return RecipeMapper.toRecipeResponse(recipe);
    }

    @Override
    public List<RecipeResponse> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(RecipeMapper::toRecipeResponse)
                .collect(Collectors.toList());
    }
    @Override
    public List<RecipeResponse> searchRecipes(RecipeSearchQuery query) {
        // This logic can become complex; might need to push down to repository
        // or use a specification pattern if search criteria grow.
        List<Recipe> recipes;
        if (query.nameKeyword() != null && !query.nameKeyword().isBlank()) {
            recipes = recipeRepository.findByNameContainingIgnoreCase(query.nameKeyword());
        } else if (query.type() != null) {
            recipes = recipeRepository.findByType(query.type());
        } else {
            recipes = recipeRepository.findAll(); // Fallback
        }
        return recipes.stream()
                .map(RecipeMapper::toRecipeResponse)
                .collect(Collectors.toList());
    }
    @Override
    public RecipeResponse updateRecipe(String recipeId, UpdateRecipeCommand command) {
        UUID uuid = UUID.fromString(recipeId);
        Recipe existingRecipe = recipeRepository.findById(uuid)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with ID: " + recipeId));

        try {
            RecipeMapper.updateDomainRecipe(existingRecipe, command);
            Recipe updatedRecipe = recipeRepository.save(existingRecipe); // Save existing instance
            return RecipeMapper.toRecipeResponse(updatedRecipe);
        } catch (DomainValidationException e) {
            throw new ao.inocencio.recipeservice.application.exception.InvalidRecipeDataException(e.getMessage());
        }
    }

    @Override
    public void deleteRecipe(String recipeId) {
        UUID uuid = UUID.fromString(recipeId);
        if (!recipeRepository.findById(uuid).isPresent()) {
            throw new RecipeNotFoundException("Recipe not found with ID: " + recipeId);
        }
        recipeRepository.deleteById(uuid);
    }
 }
 
 

