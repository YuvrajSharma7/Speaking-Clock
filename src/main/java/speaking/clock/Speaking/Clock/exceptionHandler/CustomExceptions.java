package speaking.clock.Speaking.Clock.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomExceptions extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " can't be null or missing";
        logger.error(ex.getLocalizedMessage());
        Map<String, Object> responseStatusObject = new HashMap<>();
        responseStatusObject.put("success", false);
        responseStatusObject.put("message", error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseStatusObject);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String requiredType = ex.getRequiredType().getName();
        String error =
                ex.getName() + " must be of type " + requiredType.substring(ex.getRequiredType().getName().lastIndexOf(".") + 1);

        logger.error(ex.getLocalizedMessage());
        Map<String, Object> responseStatusObject = new HashMap<>();
        responseStatusObject.put("success", false);
        responseStatusObject.put("message", error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseStatusObject);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            errors.add(propertyPath.substring(propertyPath.lastIndexOf(".") + 1) + " " + violation.getMessage());
        }
        logger.error(ex.getLocalizedMessage());
        Map<String, Object> responseStatusObject = new HashMap<>();
        responseStatusObject.put("success", false);
        responseStatusObject.put("message", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseStatusObject);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> exception(RuntimeException e) {
        Map<String, Object> responseStatusObject = new HashMap<>();
        responseStatusObject.put("error", true);
        responseStatusObject.put("message", e.getLocalizedMessage());
        logger.error(e.getMessage(),e);
        logger.debug(e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseStatusObject);
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exception(Exception e) {
        Map<String, Object> responseStatusObject = new HashMap<>();
        responseStatusObject.put("error", true);
        responseStatusObject.put("message", e.getLocalizedMessage());
        logger.error(e.getMessage(),e);
        logger.debug(e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseStatusObject);
    }
}
