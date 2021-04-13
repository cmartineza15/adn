package co.xmen.detector.business.stats;

import co.xmen.detector.domain.entity.Stat;
import reactor.core.publisher.Mono;

public interface StatConsultBusiness {
    Mono<Stat> mutantStats();
}
