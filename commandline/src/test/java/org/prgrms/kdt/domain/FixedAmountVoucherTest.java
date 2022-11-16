package org.prgrms.kdt.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prgrms.kdt.exception.WrongRangeInputException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FixedAmountVoucherTest {

    @Test
    @DisplayName("amount는 0보다 작은 값이 들어 올 수 없다.")
    void validateTest() {
        assertThrows(WrongRangeInputException.class, () -> {
            Voucher voucher = new FixedAmountVoucher(UUID.randomUUID(), -1);
        });
    }
}