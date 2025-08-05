package exercise.exception;

// BEGIN
public class ResourceAlreadyExistsException extends  RuntimeException{
    public ResourceAlreadyExistsException(String string){
        super(string);
    }
}
// END
