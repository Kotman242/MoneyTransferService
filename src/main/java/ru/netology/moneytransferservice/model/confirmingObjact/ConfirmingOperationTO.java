package ru.netology.moneytransferservice.model.confirmingObjact;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@JsonAutoDetect
public final class ConfirmingOperationTO {
    private final long operationId;
    @JsonProperty("code")
    private final String verificationCode;

    public ConfirmingOperationTO(long operationId, @JsonProperty("code") String verificationCode) {
        this.operationId = operationId;
        this.verificationCode = verificationCode;
        log.debug("ConfirmingOperationTO created. OperationId: " + operationId);
    }

    public long operationId() {
        return operationId;
    }

    @JsonProperty("code")
    public String verificationCode() {
        return verificationCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ConfirmingOperationTO) obj;
        return this.operationId == that.operationId &&
                Objects.equals(this.verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId, verificationCode);
    }

    @Override
    public String toString() {
        return "ConfirmingOperationTO[" +
                "operationId=" + operationId + ", " +
                "verificationCode=" + verificationCode + ']';
    }

}
