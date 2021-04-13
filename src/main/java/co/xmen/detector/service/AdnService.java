package co.xmen.detector.service;

import co.xmen.detector.domain.entity.Adn;
import reactor.core.publisher.Mono;

public interface AdnService {
    /**
     * Guarda adn.
     * @param adn cadena de adn, indicando el resultadodel analisis
     * @return Adn Adn almacenado en la tabla
     */
    Mono<Adn> save(Adn adn);

    /**
     * Busca adn por id
     * @param adn cadena de adn
     * @return adn
     */
    Mono<Adn> findById(Adn adn);

    /**
     * busca por cadena de ADN
     * @param adn
     * @return Adn
     */
    Mono<Adn> findByAdn(String adn);
}
