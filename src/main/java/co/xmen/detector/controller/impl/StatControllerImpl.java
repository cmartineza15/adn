package co.xmen.detector.controller.impl;

import co.xmen.detector.business.stats.StatConsultBusiness;
import co.xmen.detector.business.stats.StatReloadBusiness;
import co.xmen.detector.controller.StatController;
import co.xmen.detector.domain.entity.Stat;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor
public class StatControllerImpl implements StatController {
    private StatConsultBusiness statConsultBusiness;
    private StatReloadBusiness statReloadBusiness;

    @GetMapping
    @ResponseBody
    public Mono<ResponseEntity> findStatMutant(){
        return statConsultBusiness.mutantStats().map(ResponseEntity::ok);
    }

    @GetMapping("/reload")
    @ResponseBody
    public Mono<ResponseEntity> updateStat(){
        return statReloadBusiness.updateMutantStats().map(ResponseEntity::ok);
    }
}
