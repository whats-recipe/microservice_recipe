package ao.inocencio.recipeservice.infrastruture.adapter.exception;

public class GlobalExceptionHandler extends RuntimeException {
  public GlobalExceptionHandler(String message) {
    super(message);
  }
}
