package co.xmen.detector.service.impl;

import co.xmen.detector.domain.dto.ResumeStat;
import co.xmen.detector.domain.entity.Stat;
import co.xmen.detector.domain.repository.ResumeStatRepository;
import co.xmen.detector.domain.repository.StatRepository;
import co.xmen.detector.service.StatService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StatServiceImpl implements StatService {
    private StatRepository statRepository;
    private ResumeStatRepository resumeStat;


    public Mono<Stat> save(Stat stat){
        return statRepository.save(stat);
    }

    public Mono<Stat> findById(Stat stat){
        return statRepository.findById(stat.getId());
    }

    public Mono<List<ResumeStat>> statsMutants(){
        return Mono.just(resumeStat.resumeInit().collectList()
                .share()
                .block());
    }
}
