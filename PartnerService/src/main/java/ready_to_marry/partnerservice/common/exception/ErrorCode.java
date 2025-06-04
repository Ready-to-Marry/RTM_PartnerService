package ready_to_marry.partnerservice.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 1xxx: 비즈니스 오류
    DUPLICATE_BUSINESS_NUM(1501, "business number duplicated"),
    NO_SEARCH_RESULT(1502, "no search result"),
    NO_SEARCH_TERM(1503, "can't blank search term"),
    PARTNER_NOT_FOUND(1504, "partner not found"),
    DATA_FORMAT_ERROR(1505, "data format error"),
    NOT_FOUND_RESERVATION(1701, "Reservation not found"),
    RESERVATION_STATUS_NOT_ANSWERED(1703, "Reservation status must be ANSWERED"),
    UNAUTHORIZED_RESERVATION_ACCESS(1704, "You do not have permission to access this reservation"),

    // 2xxx: 인프라(시스템) 오류
    POSTGRES_SAVE_FAILURE(2501, "Can't save to postgres"),
    POSTGRES_DELETE_FAILURE(2502, "Can't delete from postgres"),
    DB_RETRIEVE_FAILURE(2503, "Can't retrieve from postgres");

    private final int code;
    private final String message;
}