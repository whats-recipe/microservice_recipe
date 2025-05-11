package ao.inocencio.recipeservice.application.dto;

public record RecipeDTO(
        String id,
        String externalId,
        int usedIngredientCount,
        int missedIngredientCount,
        int likes,
        int title,
        int image,
        int imageType
        ){}
