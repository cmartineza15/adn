package co.xmen.detector.controller;

import co.xmen.detector.domain.entity.Stat;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface StatController {

    @ApiOperation(value = "Obtiene estadisticas del mutante", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,response = Stat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Void.class, message = "Respuesta ok")
    })
    Mono<ResponseEntity> findStatMutant();

    @ApiOperation(value = "Obtiene y actualiza las estadisticas del mutante", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,response = Stat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Void.class, message = "Respuesta ok")
    })
    Mono<ResponseEntity> updateStat();
}
