package br.com.mateusfilpo.points_of_interest.service.exception;

import java.util.List;

public class InvalidDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private List<String> msgs;

    public InvalidDataException(List<String> msgs) {
        super();
        this.msgs = msgs;
    }

    public List<String> getMsgs() {
        return msgs;
    }
}
