package ao.inocencio.recipeservice.application.service;

import org.springframework.stereotype.Service;

import ao.inocencio.recipeservice.application.port.dto.CreateRecipeCommand;
import ao.inocencio.recipeservice.application.port.dto.RecipeMapper;
import ao.inocencio.recipeservice.application.port.dto.RecipeResponse;
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
        } catch (DomainValidationException e) {
            throw new ao.inocencio.recipeservice.application.exception.InvalidRecipeDataException(e.getMessage());
        }
    }

 }

