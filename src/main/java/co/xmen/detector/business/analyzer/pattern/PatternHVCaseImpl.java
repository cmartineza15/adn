package co.xmen.detector.business.analyzer.pattern;

import co.xmen.detector.business.analyzer.PatternCase;
import co.xmen.detector.config.AdnConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Aisla las columnas verticales y horizontales de la matriz, asociado a un valor minimo
 *
 * @author Camilo Martinez
 * @version 12/04/2021
 */
@Component
@Qualifier("PatternHVCase")
@AllArgsConstructor
public class PatternHVCaseImpl implements PatternCase {

    private AdnConfig adnConfig;

    @Override
    public Flux<List<Character>> separate(List<List<Character>> matrixAdn, Integer minValue) {
        List<List<Character>> response = new LinkedList<>();
        int iterat = matrixAdn.size() - minValue;
        for (int i = 0; i < matrixAdn.size(); i++) {
            List<Character> vertical = new LinkedList<>();
            List<Character> horizontal = new LinkedList<>();
            for (int j = 0; j < (minValue + iterat); j++) {
                horizontal.add(matrixAdn.get(i).get(j));
                vertical.add(matrixAdn.get(j).get(i));
            }
            response.add(horizontal);
            response.add(vertical);
        }
        return Flux.fromIterable(response);
    }
}
