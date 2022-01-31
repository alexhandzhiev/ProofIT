package app.controller;

import app.model.Policy;
import app.service.PolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Housing Policy related endpoints
 */
@RestController
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    /**
     * @param policy mandatory parameter - a policy incoming as a JSON
     * @return String
     */
    @PostMapping("/calculate")
    @ResponseBody
    public ResponseEntity calculate(@RequestBody Policy policy) {
        BigDecimal totalInsurance = policyService.calculate(policy);
        return ResponseEntity.status(HttpStatus.OK).body(totalInsurance);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleError() {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
}
