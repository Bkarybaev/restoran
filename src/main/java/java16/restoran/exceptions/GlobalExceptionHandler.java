package java16.restoran.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NumberTuuraEmes.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handle(NumberTuuraEmes e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .status(HttpStatus.CONFLICT)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(MethodArgumentNotValidException e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
    @ExceptionHandler(NotFount.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(NotFount e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handle(UserException e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .status(HttpStatus.CONFLICT)
                .build();
    }
    @ExceptionHandler(ExceptionAge.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handle(ExceptionAge e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .status(HttpStatus.CONFLICT)
                .build();
    }
}
