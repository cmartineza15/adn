package co.xmen.detector.business.analyzer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Aisla el adn asociado a los patrones de identificación
 * @author Camilo Martinez
 * @version 12/04/2021
 */
public interface PatternTokenizer {

    /**
     * Aisla el adn asociado a patrones asociados a la identificación especifica
     * de casos.
     * @param matrixAdn Matriz de adn valida
     * @return List<Character> Valores de adn asociados a los patrones del caso especifico.
     */
    Flux<List<Character>> execute(List<List<Character>> matrixAdn);
}
