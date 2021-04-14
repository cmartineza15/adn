package co.xmen.detector.business.analyzer.mutant;

import co.xmen.detector.business.analyzer.pattern.PatternDiagonalsCaseImpl;
import co.xmen.detector.business.analyzer.pattern.PatternHVCaseImpl;
import co.xmen.detector.config.AdnConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Import({PatternDiagonalsCaseImpl.class, PatternHVCaseImpl.class,AdnConfig.class,MutantPatternTokenizerImpl.class})
class MutantPatternTokenizerImplTest {

    @Autowired
    private MutantPatternTokenizerImpl mutantAdnLexicalAnalyzer;
    @Mock
    AdnConfig adnConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute() {
        Mockito.when(adnConfig.getSize()).thenReturn(6);
        Mockito.when(adnConfig.getMutantSucces()).thenReturn(4);
        Mockito.when(adnConfig.getPattern()).thenReturn("[ATCG]+");


        String[] adn = {"ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA"};
        List<List<Character>> matrixAdn = new ArrayList<>();
        for (String andPart : adn) {
            matrixAdn.add(andPart.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        List<List<Character>> diagonals = new ArrayList<>();
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