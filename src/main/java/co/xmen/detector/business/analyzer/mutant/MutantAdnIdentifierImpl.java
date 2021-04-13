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

import java.util.List;

@Service
@AllArgsConstructor
public class MutantAdnIdentifierImpl implements AdnIdentifier {

    private AdnLexicalAnalyzer adnLexicalAnalyzer;
    private PatternTokenizer patternTokenizer;
    private PatternValidator patternValidator;
    private AdnService service;

    public Mono<Boolean> identifier(String[] adn) {
        List<List<Character>> partner = adnLexicalAnalyzer.execute(adn).share().block();
        List<List<List<Character>>> list = patternTokenizer.execute(partner).collectList().share().block();
        boolean isMutant = false;
        for (List<List<Character>> lists : list) {
            if (this.patternValidator.isValid(lists)) {
                isMutant = true;
                break;
            }
        }
        String adnJoin = String.join("", adn);
        final boolean mutant = isMutant;
        Adn adnObj = new Adn();
        adnObj.setAdn(String.join("", adn));
        adnObj.setIsmutant(isMutant);
        return service.findByAdn(adnJoin)
                .switchIfEmpty(service.save(adnObj))
                .map(adnR -> adnR.isIsmutant());

    }
}
