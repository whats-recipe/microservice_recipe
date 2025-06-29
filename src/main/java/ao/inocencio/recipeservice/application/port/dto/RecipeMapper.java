package ao.inocencio.recipeservice.application.port.dto;

import java.util.Set;

import ao.inocencio.recipeservice.domain.model.Ingredient;
    // Mapper for converting between domain models and DTOs
public class RecipeMapper {

    public static RecipeResponse toRecipeResponse(ao.inocencio.recipeservice.domain.model.Recipe recipe) {
        Set<RecipeResponse.IngredientDTO> ingredientDTOs = new java.util.HashSet<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredientDTOs.add(new RecipeResponse.IngredientDTO(ingredient.getName(), ingredient.getQuantity()));
        }

        return new RecipeResponse(
            recipe.getId().toString(),
            recipe.getName(),
            recipe.getDescription(),
            ingredientDTOs,
            recipe.getPreparationMethod(),
            recipe.getImageUrl(),
            recipe.getType()
        );
    }

    // Add method to convert CreateRecipeCommand to Domain Recipe
    public static ao.inocencio.recipeservice.domain.model.Recipe toDomainRecipe(CreateRecipeCommand command) {
         Set<Ingredient> ingredients = new java.util.HashSet<>();
         for (Ingredient ingredient : command.ingredients()) { // Assuming Ingredient from domain model can be used directly
             ingredients.add(new Ingredient(ingredient.getName(), ingredient.getQuantity()));
         }
        return ao.inocencio.recipeservice.domain.model.Recipe.createNew(
            command.name(), command.description(), ingredients,
            command.preparationMethod(), command.imageUrl(), command.type()
        );
    }

    // Add method to update Domain Recipe from UpdateRecipeCommand
    public static void updateDomainRecipe(ao.inocencio.recipeservice.domain.model.Recipe recipe, UpdateRecipeCommand command) {
        recipe.updateDetails(command.name(), command.description(), command.preparationMethod(), command.imageUrl(), command.type());

        Set<Ingredient> ingredients = new java.util.HashSet<>();
        for (Ingredient ingredient : command.ingredients()) {
            ingredients.add(new Ingredient(ingredient.getName(), ingredient.getQuantity()));
        }
        recipe.updateIngredients(ingredients);
    }
}
