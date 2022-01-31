package app.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Policy implements Serializable {
    private String number;
    private PolicyStatus status;
    List<Insurable> insurables;
}
