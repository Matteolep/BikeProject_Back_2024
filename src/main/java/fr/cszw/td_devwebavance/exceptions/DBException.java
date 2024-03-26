package fr.cszw.td_devwebavance.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DBException extends Exception {

    private String message;
}
