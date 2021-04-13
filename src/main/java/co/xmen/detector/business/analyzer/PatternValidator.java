package co.xmen.detector.business.analyzer;

import reactor.core.publisher.Flux;

import java.util.List;

public interface PatternValidator {

     boolean isValid(List<List<Character>> adnPattern);
}
