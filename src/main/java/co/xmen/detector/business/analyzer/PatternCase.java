package co.xmen.detector.business.analyzer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Aisla el adn según los patron y el caso.
 * @author Camilo Martinez
 * @version 12/04/2021
 */
public interface PatternCase {
    /**
     * Separa la cadena adn asociado a los patrones de un caso especifico
     * @param matrixAdn Matriz valida de adn procesada
     * @return List<Character> cadena de ADN aislado asociado a los patrones.
     */
    Flux<List<Character>> separate(List<List<Character>> matrixAdn, Integer minVal);
}
