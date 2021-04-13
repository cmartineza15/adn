package co.xmen.detector.business.analyzer.mutant;

import co.xmen.detector.business.analyzer.AdnLexicalAnalyzer;
import co.xmen.detector.config.AdnConfig;
import co.xmen.detector.domain.exception.ProcessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MutantAdnLexicalAnalyzerImpl implements AdnLexicalAnalyzer {

    private AdnConfig adnConfig;

    @Override
    public Mono<List<List<Character>>> execute(String[] adn) {
        return Mono.fromCallable(() ->{
            if (adnConfig.getSize() != adn.length) {
                throw new ProcessException("Tamaño de la cadena erroneo");
            }
            List<List<Character>> list = new ArrayList<>();
            for (String partAdn : adn) {
                if (adnConfig.getSize() != partAdn.length()) {
                    throw new ProcessException("Tamaño erroneo en la cadena: " + partAdn);
                }

                if (!Pattern.matches(adnConfig.getPattern(), partAdn)) {
                    throw new ProcessException("Caracter invalido en la cadena: " + partAdn);
                }

                list.add(partAdn.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
            }
            return list;
        });
    }
}
