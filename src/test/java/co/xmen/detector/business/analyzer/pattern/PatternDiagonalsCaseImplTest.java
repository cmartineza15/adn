package co.xmen.detector.business.analyzer.pattern;

import co.xmen.detector.config.AdnConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class PatternDiagonalsCaseImplTest {

    AdnConfig adnConfig;
    PatternDiagonalsCaseImpl diagonalsCase;

    @BeforeEach
    void setUp() {
        adnConfig = new AdnConfig();
        adnConfig.setMutantSucces(4);
        adnConfig.setSize(6);
        adnConfig.setPattern("[ATCG]+");
        diagonalsCase = new PatternDiagonalsCaseImpl(adnConfig);
    }

    @Test
    void separateDiagonals() {
        String[] adn = {"ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAA"};
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

        StepVerifier.create(diagonalsCase.separate(matrixAdn,adnConfig.getMutantSucces()).log()
        .collectList())
                .expectNextMatches( matrix -> {
                    for (int i = 0; i < matrix.size(); i++) {
                        List<Character> row = matrix.get(i);
                        List<Character> rowComp = diagonals.get(i);
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
}