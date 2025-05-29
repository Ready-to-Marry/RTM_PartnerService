package ready_to_marry.partnerservice.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 1xxx: 비즈니스 오류
    DUPLICATE_BUSINESS_NUM(1001, "business number duplicated"),
    NO_SEARCH_RESULT(1002, "no search result"),
    NO_SEARCH_TERM(1003, "can't blank search term"),

    // 2xxx: 인프라(시스템) 오류
    POSTGRES_SAVE_FAILURE(2001, "Can't save to postgres");

    private final int code;
    private final String message;
}