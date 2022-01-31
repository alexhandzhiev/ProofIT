package app.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurable {
    String name;
    List<SubInsurable> subInsurables;
}
