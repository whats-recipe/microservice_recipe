package ao.inocencio.recipeservice.domain.exception;

public class DomainValidationException  extends RuntimeException {

    public DomainValidationException(String message){
        super(message);
    }
    

}
