package co.xmen.detector.business.analyzer.mutant;

import co.xmen.detector.config.AdnConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MutantPatternValidatorImplTest {
    AdnConfig adnConfig;
    MutantPatternValidatorImpl mutantPatternValidator;

    @BeforeEach
    void setUp() {
        adnConfig = new AdnConfig();
        adnConfig.setMutantSucces(4);
        adnConfig.setSize(6);
        adnConfig.setPattern("[ATCG]+");
        mutantPatternValidator = new MutantPatternValidatorImpl(adnConfig);
    }

    @Test
    void isValidMutantCase1() {

        String[] adn = {"ATCGAT", "TCGATC", "AGCCAT", "CTCGTA", "ATCGAA", "ATCGAA"};
        List<List<Character>> matrixAdn = new ArrayList<>();
        for (String andPart : adn) {
            matrixAdn.add(andPart.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        List<List<Character>> patternAdn = new ArrayList<>();
        patternAdn.add("CGCCCC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCG".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TGCGA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ACCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TGCTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("CAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("GGGC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("AACTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TTCCTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("CAGCT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TTGC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TCGATC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("AGCCAT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("CTCGTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATACAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TCGTTT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("GACGGG".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATATAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TCTAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));

        Assertions.assertEquals(mutantPatternValidator.isValid(patternAdn),true);
    }


    @Test
    void isValidMutantCase2() {

        String[] adn = {"ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA"};
        List<List<Character>> matrixAdn = new ArrayList<>();
        for (String andPart : adn) {
            matrixAdn.add(andPart.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        List<List<Character>> patternAdn = new ArrayList<>();
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TTTTTT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("CCCCCC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("GGGGGG".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TTTTTT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("CCCCCC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("GGGGGG".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("AAAAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));

        Assertions.assertEquals(mutantPatternValidator.isValid(patternAdn),true);
    }

    @Test
    void isValidNoMutantCase1() {

        String[] adn = {"ATCGAT", "TCGATC", "AGCCAT", "CTCGTA", "ATCGAA", "ATCGAA"};
        List<List<Character>> matrixAdn = new ArrayList<>();
        for (String andPart : adn) {
            matrixAdn.add(andPart.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }

        List<List<Character>> patternAdn = new ArrayList<>();
        patternAdn.add("ATCG".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TGCGA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ACCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TGCTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("CAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("GGGC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("AACTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TTCCTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("CAGCT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TTGC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TCGATC".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("AGCCAT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("CTCGTA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATCGAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATACAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TCGTTT".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("CGCCCA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("GACGGG".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("ATATAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        patternAdn.add("TCTAAA".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));

        Assertions.assertEquals(mutantPatternValidator.isValid(patternAdn),false);
    }
}