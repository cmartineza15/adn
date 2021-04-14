package co.xmen.detector.business.stats;

import co.xmen.detector.domain.entity.Stat;
import reactor.core.publisher.Mono;

/**
 * Actualiza las estadisiticas
 * @author camilo martinez
 * @version 13/04/2021
 */
public interface StatReloadBusiness {
    /**
     * Actualiza y consulta las estadisticas.
     * @return Stat Devuelve estadisticas actualizadas.
     */
    Mono<Stat> updateMutantStats();
}
