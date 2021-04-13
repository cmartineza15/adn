package co.xmen.detector.domain.repository;

import co.xmen.detector.domain.entity.Adn;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AdnRepository extends ReactiveCrudRepository<Adn,String> {
    Mono<Adn> findByAdn(String adn);
}
