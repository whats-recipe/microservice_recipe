package ao.inocencio.recipeservice.domain.exception;

public class RecipeNotFoundExpeception extends RuntimeException {
    public RecipeNotFoundExpeception(String message) {
        super(message);
    }

    public RecipeNotFoundExpeception(String message, Throwable cause) {
        super(message, cause);
    }
}
