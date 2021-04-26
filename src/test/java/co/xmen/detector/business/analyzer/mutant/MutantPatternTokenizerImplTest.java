package co.xmen.detector.business.analyzer.mutant;

import co.xmen.detector.business.analyzer.pattern.PatternDiagonalsCaseImpl;
import co.xmen.detector.business.analyzer.pattern.PatternHVCaseImpl;
import co.xmen.detector.config.AdnConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import({PatternDiagonalsCaseImpl.class, PatternHVCaseImpl.class,AdnConfig.class,MutantPatternTokenizerImpl.class})
class MutantPatternTokenizerImplTest {

    @Autowired
    private MutantPatternTokenizerImpl mutantAdnLexicalAnalyzer;


    @BeforeEach
    void setUp() {

    }

    @Test
    void execute() {
        String[] adn = {"ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA"};
        List<List<Character>> matrixAdn = new ArrayList<>();
        for (String andPart : adn) {
            matrixAdn.add(andPart.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        List<List<Character>> diagonals = new ArrayList<>();
        diagonals.add("TCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("ATCGA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("AGCTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("AAGCT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("CGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("ATCG".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("GCTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("AAGC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("AAGCTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("TTTTTT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("CCCCCC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("GGGGGG".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        diagonals.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));


        StepVerifier.create(mutantAdnLexicalAnalyzer.execute(matrixAdn).collectList())
                .expectNextMatches(listaResultado -> this.validarLista(listaResultado, diagonals))
                .verifyComplete()
        ;

    }

    private boolean validarLista(List<List<Character>> listaResultado, List<List<Character>> listaEsperada) {
        for (int i = 0; i < listaResultado.size(); i++) {
            List<Character> row = listaResultado.get(i);
            List<Character> rowComp = listaEsperada.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (!rowComp.get(j).equals(row.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}