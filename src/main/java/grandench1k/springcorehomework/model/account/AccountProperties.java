package grandench1k.springcorehomework.model.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {
    private final int defaultMoneyAmount;
    private final double transferCommission;

    public AccountProperties(
            @Value("${account.default-amount}") int defaultMoneyAmount,
            @Value("${account.transfer-commission}") double transferCommission) {
        this.defaultMoneyAmount = defaultMoneyAmount;
        this.transferCommission = transferCommission;
    }

    public int getDefaultMoneyAmount() {
        return defaultMoneyAmount;
    }

    public double getTransferCommission() {
        return transferCommission;
    }
}
