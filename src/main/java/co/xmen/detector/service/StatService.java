package co.xmen.detector.service;


import co.xmen.detector.domain.dto.ResumeStat;
import co.xmen.detector.domain.entity.Stat;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Servicio de datos de las estadisticas.
 * @author camilo martinez
 * @version 13/04/2021
 */
public interface StatService {
    /**
     * crea o actualiza la información de las estadisticas
     * @param stat estadisticas a crear o actualizar
     * @return Stat
     */
    Mono<Stat> save(Stat stat);

    /**
     * Buscar una estadistica por ID
     * @param stat
     * @return
     */
    Mono<Stat> findById(Stat stat);

    /**
     * Calcula el insumo inicial para generar las estadisticas
     * @return
     */
    Mono<List<ResumeStat>> statsMutants();
}
