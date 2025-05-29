package ready_to_marry.partnerservice.common.exception.payment;

import lombok.Getter;
import ready_to_marry.partnerservice.common.exception.ErrorCode;

@Getter
public class InfraException extends RuntimeException {

    private final int code;

    public InfraException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
}