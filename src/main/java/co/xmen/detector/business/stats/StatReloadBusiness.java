package co.xmen.detector.business.stats;

import co.xmen.detector.domain.entity.Stat;
import reactor.core.publisher.Mono;

public interface StatReloadBusiness {
    Mono<Stat> updateMutantStats();
}
