package co.xmen.detector.business.analyzer.mutant;

import co.xmen.detector.business.analyzer.pattern.PatternDiagonalsCaseImpl;
import co.xmen.detector.business.analyzer.pattern.PatternHVCaseImpl;
import co.xmen.detector.config.AdnConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import({PatternDiagonalsCaseImpl.class, PatternHVCaseImpl.class, AdnConfig.class,MutantPatternTokenizerImpl.class,MutantAdnIdentifierImpl.class,MutantAdnLexicalAnalyzerImpl.class,MutantAdnIdentifierImpl.class,MutantPatternValidatorImpl.class})
public class MutantAdnIdentifierImplTest {


    @Autowired
    MutantAdnIdentifierImpl mutantAdnIdentifier;


    @Test
    void identifierIsMutant() {
            String[] adn = {"ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA"};
        StepVerifier.create(mutantAdnIdentifier.identifier(adn))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void identifierIsHuman() {
        String[] adn = {"ATCGAT", "TCGATC", "AGCCAT", "CTCGTA", "ATCGAA", "ATAGAA"};
        StepVerifier.create(mutantAdnIdentifier.identifier(adn))
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void identifierErrorLength() {
        String[] adn = {"ATCGATA", "TCGATC", "AGCCAT", "CTCGTA", "ATCGAA", "ATAGAA"};
        StepVerifier.create(mutantAdnIdentifier.identifier(adn))
                .expectErrorMessage("Tamaño erroneo en la cadena: ATCGATA")
                .verify();
    }

    @Test
    void identifierErrorSpace() {
        String[] adn = {"ATCGA ", "TCGATC", "AGCCAT", "CTCGTA", "ATCGAA", "ATAGAA"};
        StepVerifier.create(mutantAdnIdentifier.identifier(adn))
                .expectErrorMessage("Caracter invalido en la cadena: ATCGA ")
                .verify();
    }
}