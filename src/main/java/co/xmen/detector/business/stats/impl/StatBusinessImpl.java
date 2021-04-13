package co.xmen.detector.business.stats.impl;

import co.xmen.detector.business.stats.StatConsultBusiness;
import co.xmen.detector.business.stats.StatReloadBusiness;
import co.xmen.detector.domain.dto.ResumeStat;
import co.xmen.detector.domain.entity.Stat;
import co.xmen.detector.domain.type.StatType;
import co.xmen.detector.service.StatService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log
@Service
@AllArgsConstructor
public class StatBusinessImpl implements StatConsultBusiness, StatReloadBusiness {
    private StatService statService;

    public Mono<Stat> mutantStats(){
        return statService.findById(new Stat(StatType.MUTANTS.id()));
    }

    public Mono<Stat> updateMutantStats(){
        Mono<Stat> resumeStatMono = statService.statsMutants().log()
                .flatMap(list ->{
                    Stat stat = new Stat();
                    for (ResumeStat resumeStat : list) {
                        if(resumeStat.getIsmutant()){
                            stat.setCount_mutant_dna(resumeStat.getTot());
                        }else {
                            stat.setCount_humna_dna(resumeStat.getTot());
                        }
                    }
                    if(stat.getCount_humna_dna() != null && stat.getCount_humna_dna().compareTo(0L) == 0){
                        stat.setRatio(stat.getCount_mutant_dna()/stat.getCount_humna_dna());
                    }else {
                        stat.setRatio(stat.getCount_mutant_dna());
                    }
                    return Mono.just(stat);
                });

        return statService.findById(new Stat(StatType.MUTANTS.id()))
                .switchIfEmpty(resumeStatMono)
                .flatMap(
                bdStat ->{
                    Stat stat = resumeStatMono.share().block();
                    bdStat.setCount_mutant_dna(stat.getCount_mutant_dna());
                    bdStat.setRatio(stat.getRatio());
                    bdStat.setCount_humna_dna(stat.getCount_humna_dna());
                    return statService.save(bdStat);
                }
        );
    }


}

