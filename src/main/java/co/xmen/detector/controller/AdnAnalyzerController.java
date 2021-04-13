package co.xmen.detector.controller;

import co.xmen.detector.domain.dto.AdnRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface AdnAnalyzerController {

    @ApiOperation(value = "Valida si el ADN es Mutante.", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Void.class, message = "Es mutante"),
            @ApiResponse(code = 403, response = Void.class, message = "No es mutante")
    })

    Mono<ResponseEntity> validate(@RequestBody @Validated
                                          AdnRequest adnRequest);
}
