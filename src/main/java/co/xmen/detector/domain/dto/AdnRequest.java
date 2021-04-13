package co.xmen.detector.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdnRequest {
    @NotNull(message = "La cadena de ADN no puede estar vacia")
    String [] dna;
}
