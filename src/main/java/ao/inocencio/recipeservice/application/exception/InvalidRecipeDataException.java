package ao.inocencio.recipeservice.application.exception;

public class InvalidRecipeDataException  extends RuntimeException{
    public InvalidRecipeDataException(String message) {
        super(message);
    }
}
