package co.xmen.detector.service;


import co.xmen.detector.domain.dto.ResumeStat;
import co.xmen.detector.domain.entity.Stat;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StatService {

    Mono<Stat> save(Stat stat);

    Mono<Stat> findById(Stat stat);

    Mono<List<ResumeStat>> statsMutants();
}
