package co.xmen.detector.business.analyzer.mutant;

import co.xmen.detector.business.analyzer.AdnIdentifier;
import co.xmen.detector.business.analyzer.AdnLexicalAnalyzer;
import co.xmen.detector.business.analyzer.PatternTokenizer;
import co.xmen.detector.business.analyzer.PatternValidator;
import co.xmen.detector.domain.entity.Adn;
import co.xmen.detector.service.AdnService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MutantAdnIdentifierImpl implements AdnIdentifier {

    private AdnLexicalAnalyzer adnLexicalAnalyzer;
    private PatternTokenizer patternTokenizer;
    private PatternValidator patternValidator;
    private AdnService service;

    public Mono<Boolean> identifier(String[] adn) {
        return adnLexicalAnalyzer.execute(adn).flatMap(partner ->
                patternTokenizer.execute(partner).collectList().flatMap(list -> {
                    boolean isMutant = false;
                    if (this.patternValidator.isValid(list)) {
                        isMutant = true;
                    }
                    Adn adnObj = Adn.builder().adn(String.join("", adn)).ismutant(isMutant).build();
                    return service.findByAdn(adnObj.getAdn())
                            .switchIfEmpty(service.save(adnObj))
                            .map(Adn::isIsmutant);
                }));
    }
}
