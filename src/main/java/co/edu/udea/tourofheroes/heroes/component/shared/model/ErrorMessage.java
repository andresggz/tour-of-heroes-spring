package co.edu.udea.tourofheroes.heroes.component.shared.model;

import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Generated
@Getter
@ToString
public class ErrorMessage {

    private String exception;

    private String message;

    private String path;

    private LocalDateTime date;

    public ErrorMessage(Exception exception, String path){
        this.exception = exception.getClass().getName();
        this.message = exception.getMessage();
        this.path = path;
        this.date = LocalDateTime.now();
    }

}
