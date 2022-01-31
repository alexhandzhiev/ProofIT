package app.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubInsurable {
    String name;
    RiskType riskType;
    BigDecimal insuranceSum;
}
