package co.xmen.detector.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adn {
    @Id
    private Long id;
    private String adn;
    private boolean ismutant;
}
