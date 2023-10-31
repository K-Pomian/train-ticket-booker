package pl.pomian.trainticketbooker.models;

import java.math.BigDecimal;

public enum Discount {
    NORMAL, STUDENT, ELDER, DISABLED;

    public BigDecimal getDiscountPercentage() {
        return switch (this) {
            case NORMAL -> BigDecimal.ONE;
            case STUDENT -> BigDecimal.valueOf(49, 2);
            case ELDER -> BigDecimal.valueOf(55, 2);
            case DISABLED -> BigDecimal.valueOf(75, 2);
        };
    }
}
