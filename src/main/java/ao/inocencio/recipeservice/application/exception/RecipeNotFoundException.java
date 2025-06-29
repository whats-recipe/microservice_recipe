package ao.inocencio.recipeservice.application.exception;

public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException(String message) {
        super(message);
    }
}
