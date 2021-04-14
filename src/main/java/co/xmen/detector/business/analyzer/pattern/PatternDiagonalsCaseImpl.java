package co.xmen.detector.business.analyzer.pattern;

import co.xmen.detector.business.analyzer.PatternCase;
import co.xmen.detector.config.AdnConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * Aisla todas las diagonales posibles que su tamaño sean mayor o igual a un valor minimo
 * de una matriz cuadrada.
 *
 * @author Camilo Martinez
 * @version 12/04/2021
 */
@Component
@Qualifier("PatternDiagonalsCase")
@AllArgsConstructor
public class PatternDiagonalsCaseImpl implements PatternCase {

    private AdnConfig adnConfig;

    @Override
    public Flux<List<Character>> separate(List<List<Character>> matrixAdn, Integer minVal) {
        List<List<Character>> list = new ArrayList<>();
        int maxIter = adnConfig.getSize() - minVal;
        int size = adnConfig.getSize();
        for (int i = 0; i < maxIter; i++) {
            int k = 0;
            List<Character> diagT = new ArrayList<>();
            List<Character> diagB = new ArrayList<>();
            List<Character> diagIT = new ArrayList<>();
            List<Character> diagIB = new ArrayList<>();
            for (int j = (i + 1); j < size && k < size - 1; j++) {
                diagT.add(matrixAdn.get(k).get(j)); //diagonales superiores
                diagB.add(matrixAdn.get(j).get(k)); //diagonales inferiores
                diagIB.add(matrixAdn.get(j).get((size - 1) - k)); //diagonales invertidas superiores
                diagIT.add(matrixAdn.get(k).get((size - 1) - j)); //diagonales invertidas inferiores
                k++;
            }
            list.add(diagT);
            list.add(diagB);
            list.add(diagIT);
            list.add(diagIB);
        }
        List<Character> diag = new ArrayList<>();
        List<Character> diagI = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            diag.add(matrixAdn.get(i).get(i)); //diagonal
            diagI.add(matrixAdn.get(i).get((size - 1) - i)); //diagonal invertida
        }
        return Flux.fromIterable(list).concatWithValues(diag,diagI);
    }
}
