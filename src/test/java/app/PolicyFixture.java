package app;

import app.model.*;

import java.math.BigDecimal;
import java.util.Arrays;

public class PolicyFixture {

    public static final SubInsurable subInsurable1 = SubInsurable.builder()
            .insuranceSum(new BigDecimal("100.00"))
            .riskType(RiskType.FIRE)
            .build();

    public static final SubInsurable subInsurable2 = SubInsurable.builder()
            .insuranceSum(new BigDecimal("8.00"))
            .riskType(RiskType.THEFT)
            .build();

    public static final SubInsurable subInsurable3 = SubInsurable.builder()
            .insuranceSum(new BigDecimal("500.00"))
            .riskType(RiskType.FIRE)
            .build();

    public static final SubInsurable subInsurable4 = SubInsurable.builder()
            .insuranceSum(new BigDecimal("102.51"))
            .riskType(RiskType.THEFT)
            .build();

    public static final Insurable insurable1 = Insurable.builder()
            .name("House")
            .subInsurables(Arrays.asList(subInsurable1, subInsurable2))
            .build();

    public static final Policy policyLowCoeff = Policy.builder()
            .number("Test-Policy-1")
            .status(PolicyStatus.REGISTERED)
            .insurables(Arrays.asList(insurable1))
            .build();

    public static final Insurable insurable2 = Insurable.builder()
            .name("Maison")
            .subInsurables(Arrays.asList(subInsurable3, subInsurable4))
            .build();

    public static final Insurable insurable3 = Insurable.builder()
            .name("Maison")
            .subInsurables(Arrays.asList(subInsurable3, subInsurable2))
            .build();

    public static final Policy policyHighCoeff = Policy.builder()
            .number("Test-Policy-2")
            .status(PolicyStatus.APPROVED)
            .insurables(Arrays.asList(insurable2))
            .build();

    public static final Policy policyHighFireLowTheftCoeff = Policy.builder()
            .number("Test-Policy-3")
            .status(PolicyStatus.REGISTERED)
            .insurables(Arrays.asList(insurable3))
            .build();
}
