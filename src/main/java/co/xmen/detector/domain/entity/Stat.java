package co.xmen.detector.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
@NoArgsConstructor
public class Stat {
    @Id
    private Integer id;
    private Long count_mutant_dna;
    private Long count_humna_dna;
    private double ratio;

    public Stat(Integer id){
        this.id = id;
    }
}
