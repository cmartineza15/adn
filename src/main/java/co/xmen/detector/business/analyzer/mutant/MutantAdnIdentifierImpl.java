package co.xmen.detector.business.analyzer.mutant;

import co.xmen.detector.business.analyzer.AdnIdentifier;
import co.xmen.detector.business.analyzer.AdnLexicalAnalyzer;
import co.xmen.detector.business.analyzer.PatternTokenizer;
import co.xmen.detector.business.analyzer.PatternValidator;
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

    public Mono<Boolean> identifier(String[] adn) {
        return Mono.fromCallable(() -> {
            List<List<Character>> partner = adnLexicalAnalyzer.execute(adn).block();
            List<List<List<Character>>> list = patternTokenizer.execute(partner).collectList().block();
            for (List<List<Character>> lists : list) {
                if (this.patternValidator.isValid(lists)) {
                    return true;
                }
            }
            return false;
        });
    }
}
