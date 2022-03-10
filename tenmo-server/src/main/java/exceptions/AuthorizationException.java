package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Don't break me please.")
public class AuthorizationException extends Exception{
    private static final long serialVersionUID = 1L;

}
