package sn.hsl.notelabback.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import sn.hsl.notelabback.web.tools.response.ApiMessage;

@Data
public class NotelabException extends RuntimeException {
    private HttpStatus status;
    private String error;
    private String message;

    public NotelabException(ApiMessage apiMessage) {
        this.status = HttpStatus.resolve(apiMessage.getStatus());
        this.error = apiMessage.getError();
        this.message = apiMessage.getMessage();
    }

    public NotelabException(ApiMessage apiMessage, Object ...args) {
        this.status = HttpStatus.resolve(apiMessage.getStatus());
        this.error = apiMessage.getError();
        this.message = String.format(apiMessage.getMessage(), args);
    }
}
