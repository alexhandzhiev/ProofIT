package app.service;

import app.model.Insurable;
import app.model.Policy;
import app.model.SubInsurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PolicyService {

    @Value("${app.risk.fire.threshold}")
    BigDecimal RISK_FIRE_THRESHOLD;
    @Value("${app.risk.fire.coefficient.low}")
    BigDecimal COEFFICIENT_FIRE_LOW;
    @Value("${app.risk.fire.coefficient.high}")
    BigDecimal COEFFICIENT_FIRE_HIGH;

    @Value("${app.risk.theft.threshold}")
    BigDecimal RISK_THEFT_THRESHOLD;
    @Value("${app.risk.theft.coefficient.low}")
    BigDecimal COEFFICIENT_THEFT_LOW;
    @Value("${app.risk.theft.coefficient.high}")
    BigDecimal COEFFICIENT_THEFT_HIGH;

    /**
     * Calculation formula:
     * PREMIUM = PREMIUM_FIRE + PREMIUM_THEFT
     * PREMIUM_FIRE = SUM_INSURED_FIRE * COEFFICIENT_FIRE
     * SUM_INSURED_FIRE - total sum insured of all policy's sub-objects with type "Fire"
     * COEFFICIENT_FIRE - by default 0.014 but if SUM_INSURED_FIRE is over 100 then 0.024
     * PREMIUM_THEFT = SUM_INSURED_THEFT * COEFFICIENT_THEFT
     * SUM_INSURED_THEFT - total sum insured of all policy's sub-objects with type "Theft"
     * COEFFICIENT_THEFT - by default 0.11 but if SUM_INSURED_THEFT equal or greater than 15 then 0.05
     *
     * @param policy
     * @return BigDecimal
     */
    public BigDecimal calculate(Policy policy) {
        BigDecimal totalPremium = BigDecimal.ZERO;
        for (Insurable insurable : policy.getInsurables()) {
            for (SubInsurable subInsurable : insurable.getSubInsurables()) {
                BigDecimal premium = BigDecimal.ZERO;
                switch (subInsurable.getRiskType()) {
                    case FIRE:
                        premium = calculateFirePremium(subInsurable);
                        break;
                    case THEFT:
                        premium = calculateTheftPremium(subInsurable);
                        break;
                }
                totalPremium = totalPremium.add(premium);
            }
        }
        return totalPremium.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    private BigDecimal calculateTheftPremium(SubInsurable subInsurable) {
        if (RISK_THEFT_THRESHOLD.compareTo(subInsurable.getInsuranceSum()) < 0) {
            return subInsurable.getInsuranceSum().multiply(COEFFICIENT_THEFT_HIGH);
        } else {
            return subInsurable.getInsuranceSum().multiply(COEFFICIENT_THEFT_LOW);
        }
    }

    private BigDecimal calculateFirePremium(SubInsurable subInsurable) {
        if (RISK_FIRE_THRESHOLD.compareTo(subInsurable.getInsuranceSum()) < 0) {
            return subInsurable.getInsuranceSum().multiply(COEFFICIENT_FIRE_HIGH);
        } else {
            return subInsurable.getInsuranceSum().multiply(COEFFICIENT_FIRE_LOW);
        }
    }
}
