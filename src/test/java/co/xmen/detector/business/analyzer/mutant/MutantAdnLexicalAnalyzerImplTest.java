package co.xmen.detector.business.analyzer.mutant;

import co.xmen.detector.config.AdnConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class MutantAdnLexicalAnalyzerImplTest {

    private AdnConfig adnConfig;
    private MutantAdnLexicalAnalyzerImpl mutantAdnLexicalAnalyzer;

    @BeforeEach
    void setUp() {
        adnConfig = new AdnConfig();
        adnConfig.setMutantSucces(4);
        adnConfig.setSize(6);
        adnConfig.setPattern("[ATCG]+");
        mutantAdnLexicalAnalyzer = new MutantAdnLexicalAnalyzerImpl(this.adnConfig);
    }

    /**
     * Valore valido de cadena de adn
     */
    @Test
    void executeValidAdn() {
        String[] adn = {"ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAA"};
        List<List<Character>> lists = new ArrayList<>();
        for (String andPart : adn) {
            lists.add(andPart.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        StepVerifier.create(mutantAdnLexicalAnalyzer.execute(adn).log())
        .expectNextMatches( matrix -> {
            for (int i = 0; i < matrix.size(); i++) {
                List<Character> row = matrix.get(i);
                List<Character> rowComp = lists.get(i);
                for (int j = 0; j < row.size(); j++) {
                    if (!rowComp.get(j).equals(row.get(j))) {
                        return false;
                    }
                }
            }


            return true;
        })
        .verifyComplete();
    }

    /**
     * error en el tamaño del array
     */
    @Test
    void executeErrorLengthAdn() {
        String[] adn = {"ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAA"};
        List<List<Character>> lists = new ArrayList<>();
        for (String andPart : adn) {
            lists.add(andPart.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        StepVerifier.create(mutantAdnLexicalAnalyzer.execute(adn))
                .expectErrorMessage( "Tamaño de la cadena erroneo")
                .verify();
    }

    /**
     * error en el tanaño de un String dentro del array.
     */
    @Test
    void executeErrorPartLengthAdn() {
        String[] adn = {"ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAAAA"};
        List<List<Character>> lists = new ArrayList<>();
        for (String andPart : adn) {
            lists.add(andPart.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        StepVerifier.create(mutantAdnLexicalAnalyzer.execute(adn).log())
                .expectErrorMessage("Tamaño erroneo en la cadena: ATCGAAAA")
                .verify();
    }

    /**
     * Caracter invalido diferente a las letras permitidas ATCG
     */
    @Test
    void executeErrorPatternAdn() {
        String[] adn = {"ATCGAA","ATCGAA","HTCGAA","ATCGAA","ATCGAA","ATCGAA"};
        List<List<Character>> lists = new ArrayList<>();
        for (String andPart : adn) {
            lists.add(andPart.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        StepVerifier.create(mutantAdnLexicalAnalyzer.execute(adn).log())
                .expectErrorMessage( "Caracter invalido en la cadena: HTCGAA")
                .verify();
    }
}