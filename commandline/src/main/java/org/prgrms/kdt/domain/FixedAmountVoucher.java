package org.prgrms.kdt.domain;

import org.prgrms.kdt.exception.ErrorCode;
import org.prgrms.kdt.exception.WrongRangeInputException;

import java.util.UUID;

public class FixedAmountVoucher implements Voucher {
    private static final int ZERO = 0;

    private final UUID voucherId;
    private final long amount;

    public FixedAmountVoucher(UUID voucherId, String amount) {
        validate(amount);
        this.voucherId = voucherId;
        this.amount = Long.parseLong(amount);
    }

    public FixedAmountVoucher(UUID voucherId, long amount) {
        this.voucherId = voucherId;
        this.amount = amount;
    }

    @Override
    public UUID getVoucherId() {
        return voucherId;
    }

    @Override
    public long discount(long beforeDiscount) {
        return beforeDiscount - amount;
    }

    @Override
    public void validate(String discountDegree) {
        Voucher.isNumeric(discountDegree);

        Long longDiscountValue = Long.parseLong(discountDegree);
        if (longDiscountValue < ZERO) {
            throw new WrongRangeInputException(ErrorCode.WRONG_RANGE_INPUT.getMessage());
        }
    }

    @Override
    public String toString() {
        return "FixedAmountVoucher{" +
                "voucherId=" + voucherId +
                ", amount=" + amount +
                '}';
    }
}
