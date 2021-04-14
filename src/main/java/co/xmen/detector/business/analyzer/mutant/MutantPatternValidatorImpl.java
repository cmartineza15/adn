package co.xmen.detector.business.analyzer.mutant;

import co.xmen.detector.business.analyzer.PatternValidator;
import co.xmen.detector.config.AdnConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MutantPatternValidatorImpl implements PatternValidator {

    private AdnConfig adnConfig;

    @Override
    public boolean isValid(List<List<Character>> adnPattern) {
        int repeat = adnConfig.getMutantSucces() - 1;
        int cont = 0, errors;
        for (List<Character> characters : adnPattern) {
            Character prev = '-';
            cont = 0;
            errors = 0;
            int totErrors = (characters.size() - repeat) + 1;
            for (int i = 0; i < characters.size() && cont < repeat
                    && errors <= totErrors; i++) {
                if (prev.equals(characters.get(i))) {
                    cont++;
                } else {
                    cont = 0;
                    errors++;
                }
                prev = characters.get(i);
            }
            if (cont == repeat) {
                break;
            }
        }
        return cont == repeat;
    }
}
