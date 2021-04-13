package co.xmen.detector.service.impl;

import co.xmen.detector.domain.entity.Adn;
import co.xmen.detector.domain.repository.AdnRepository;
import co.xmen.detector.service.AdnService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AdnServiceImpl implements AdnService {
    private AdnRepository adnRepository;

    public Mono<Adn> save(Adn adn){
        return adnRepository.save(adn);
    }

    public Mono<Adn> findById(Adn adn){
        return adnRepository.findById(adn.getAdn());
    }

    public Mono<Adn> findByAdn(String adn){
        return adnRepository.findByAdn(adn);
    }
}
