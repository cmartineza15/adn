package co.xmen.detector.business.analyzer;

import reactor.core.publisher.Mono;

public interface AdnIdentifier {
    Mono<Boolean> identifier(String[] adn);
}
