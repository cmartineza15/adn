package co.xmen.detector.business.analyzer.mutant;

import co.xmen.detector.business.analyzer.PatternCase;
import co.xmen.detector.business.analyzer.PatternTokenizer;
import co.xmen.detector.config.AdnConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MutantPatternTokenizerImpl implements PatternTokenizer {

    @NonNull
    private AdnConfig adnConfig;
    @NonNull
    @Qualifier("PatternDiagonalsCase")
    private PatternCase diagonals;
    @NonNull
    @Qualifier("PatternHVCase")
    private PatternCase columnsRows;
    /**
     * Aisla los valores de adn en la matriz asociado a los mutantes
     * Columnas verticales, horizontales y diagonales que comprendan
     * un valor mayor o igual al número minimo de repeticiónes
     * @param matrixAdn Matriz de adn valida
     * @return List<Character> Lista procesada del ADN asociado.
     */
    @Override
    public Flux<List<Character>> execute(List<List<Character>> matrixAdn) {
        return Flux.concat(diagonals.separate(matrixAdn,adnConfig.getMutantSucces()),
                columnsRows.separate(matrixAdn,adnConfig.getMutantSucces()));

    }

}
