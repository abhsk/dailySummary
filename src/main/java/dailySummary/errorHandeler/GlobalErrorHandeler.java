package dailySummary.errorHandeler;

import dailySummary.error.NotAMemberError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalErrorHandeler {
    @ExceptionHandler(NotAMemberError.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity handleUserNotFoundException(final NotAMemberError ex) {
        return new ResponseEntity(ex, HttpStatus.NOT_FOUND);
    }
}
