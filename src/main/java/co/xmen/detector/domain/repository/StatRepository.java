package co.xmen.detector.domain.repository;

import co.xmen.detector.domain.dto.ResumeStat;
import co.xmen.detector.domain.entity.Stat;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StatRepository extends ReactiveCrudRepository<Stat, Integer> {
    @Query("SELECT ismutant, sum(1) as tot FROM ADN GROUP BY isMutant")
    Flux<ResumeStat> resumeInit();
}
