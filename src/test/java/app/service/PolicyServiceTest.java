package app.service;

import app.PolicyFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class PolicyServiceTest {

    @Autowired
    PolicyService policyService;

    @DisplayName("Calculate Policy Low Fire/Theft Coefficients")
    @Test
    void testCalculateLowCoefficient() {
        BigDecimal actual = policyService.calculate(PolicyFixture.policyLowCoeff);
        assertAll("calculation should be correct",
                () -> assertEquals(new BigDecimal("2.28"), actual)
        );
    }

    @DisplayName("Calculate Policy High Fire/Theft Coefficients")
    @Test
    void testCalculateHighCoefficient() {
        BigDecimal actual = policyService.calculate(PolicyFixture.policyHighCoeff);
        assertAll("calculation should be correct",
                () -> assertEquals(new BigDecimal("17.13"), actual)
        );
    }

    @DisplayName("Calculate Policy High/Fire-Low/Theft Coefficients")
    @Test
    void testCalculateMixedCoefficient() {
        BigDecimal actual = policyService.calculate(PolicyFixture.policyHighFireLowTheftCoeff);
        assertAll("calculation should be correct",
                () -> assertEquals(new BigDecimal("12.88"), actual)
        );
    }
}
