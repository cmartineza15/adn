package co.xmen.detector.business.analyzer.pattern;

import co.xmen.detector.config.AdnConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class PatternHVCaseImplTest {
    AdnConfig adnConfig;
    PatternHVCaseImpl patternHVCase;

    @BeforeEach
    void setUp() {
        adnConfig = new AdnConfig();
        adnConfig.setMutantSucces(4);
        adnConfig.setSize(6);
        adnConfig.setPattern("[ATCG]+");
        patternHVCase = new PatternHVCaseImpl(adnConfig);
    }
    @Test
    void separate() {
        String[] adn = {"ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAA","ATCGAA"};
        List<List<Character>> matrixAdn = new ArrayList<>();
        for (String andPart : adn) {
            matrixAdn.add(andPart.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        List<List<Character>> columnsAndRows = new ArrayList<>();

        columnsAndRows.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("TTTTTT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("CCCCCC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("GGGGGG".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        columnsAndRows.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));

        StepVerifier.create(patternHVCase.separate(matrixAdn,adnConfig.getMutantSucces()).log().collectList())
                .expectNextMatches( matrix -> {
                    for (int i = 0; i < matrix.size(); i++) {
                        List<Character> row = matrix.get(i);
                        List<Character> rowComp = columnsAndRows.get(i);
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