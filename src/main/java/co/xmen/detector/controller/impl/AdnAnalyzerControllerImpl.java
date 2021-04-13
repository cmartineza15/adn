package co.xmen.detector.controller.impl;

import co.xmen.detector.business.analyzer.AdnIdentifier;
import co.xmen.detector.controller.AdnAnalyzerController;
import co.xmen.detector.domain.dto.AdnRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mutant")
@AllArgsConstructor
public class AdnAnalyzerControllerImpl implements AdnAnalyzerController {

    private AdnIdentifier adnIdentifier;

    @PostMapping
    public Mono<ResponseEntity> validate(@RequestBody
                                         @Validated AdnRequest adnRequest) {
        return adnIdentifier.identifier(adnRequest.getDna())
                .map(isMutant -> {
                    if (isMutant)
                        return ResponseEntity.ok().build();
                    else
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                });
    }

}
