package co.xmen.detector.business.stats;

import co.xmen.detector.domain.entity.Stat;
import reactor.core.publisher.Mono;

/**
 * Consulta las estadisticas
 * @author Camilo Martinez
 * @version 12/04/2021
 */
public interface StatConsultBusiness {
    /**
     * Estadisticas del analiis mutante
     * @return Mono<Stat> Consulta de estadisticas
     */
    Mono<Stat> mutantStats();
}
