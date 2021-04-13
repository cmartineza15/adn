package co.xmen.detector.business.analyzer;

import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Analiza la cadena de adn que tenga el tamaño y las letras asociadas
 * @author Camilo Martinez
 * @version 12/04/2021
 */
public interface AdnLexicalAnalyzer {

    /**
     * Valida el formato y tamaño de la cadena de adn, el valor debe ser diferente a nulo
     * @param adn Cadena de adn
     * @return List<List<Character>> matriz adn validada
     * @throws co.xmen.detector.domain.exception.ProcessException
     */
    Mono<List<List<Character>>> execute(String [] adn);
}
