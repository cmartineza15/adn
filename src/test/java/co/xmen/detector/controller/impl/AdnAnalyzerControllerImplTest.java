package co.xmen.detector.controller.impl;

import co.xmen.detector.domain.dto.AdnRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdnAnalyzerControllerImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void validateMutantOk() {
        String [] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        webTestClient
                .post()
                .uri("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(AdnRequest.builder()
                        .dna(dna)
                        .build()),AdnRequest.class
                ).exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void validateHumanOk() {
        String [] dna ={"ATCGAT", "TCGATC", "AGCCAT", "CTCGTA", "ATCGAA", "ATAGAA"};
        webTestClient
                .post()
                .uri("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(AdnRequest.builder()
                        .dna(dna)
                        .build()),AdnRequest.class
                ).exchange()
                .expectStatus()
                .isForbidden();
    }

    @Test
    void validateDnaMiss() {
        String [] dna =null;
        webTestClient
                .post()
                .uri("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(AdnRequest.builder()
                        .dna(dna)
                        .build()),AdnRequest.class
                ).exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void validateDnaWithError() {
        String [] dna ={"ATCSGAT", "TCGATC", "AGCCAT", "CTCGTA", "ATCGAA", "ATAGAA"};
        webTestClient
                .post()
                .uri("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(AdnRequest.builder()
                        .dna(dna)
                        .build()),AdnRequest.class
                ).exchange()
                .expectStatus()
                .is5xxServerError();
    }
}