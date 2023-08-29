package pl.wasko.filmixbackend.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.filmixbackend.model.DTO.RateDTO;
import pl.wasko.filmixbackend.model.Rate;
import pl.wasko.filmixbackend.service.RateService;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rates")
@RequiredArgsConstructor
public class RateController {
    private static final Logger logger = LoggerFactory.getLogger(RateController.class);
    private final RateService rateService;

    @GetMapping
    ResponseEntity<List<Rate>> getAll(){
        logger.warn("Exposing all the rates");
        return ResponseEntity.ok(rateService.findAll());
    }
    @GetMapping("/{id}")
    ResponseEntity<Optional<Rate>> getRateById(@PathVariable Long id){
        logger.warn("Exposing rate with id {} ", id);
        return ResponseEntity.ok(rateService.findById(id));
    }
    @GetMapping("rate/{rate}")
    ResponseEntity<List<Rate>> getRatesByRate(@PathVariable int rate){
        logger.warn("Exposing rates by rate value");
        return ResponseEntity.ok(rateService.findRatesByRating(rate));
    }
    @PostMapping
    ResponseEntity<?> createRate(@Valid @RequestBody RateDTO rateDTO){
        RateDTO createdRate = rateService.createRate(rateDTO);
        return ResponseEntity.ok(createdRate);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateRate(@PathVariable Long id, @RequestBody @Valid Rate toUpdate) {
        if (!rateService.existsById(id))
            return ResponseEntity.notFound().build();

        rateService.findById(id)
                .ifPresent(rate -> {
                    rate.updateFrom(toUpdate);
                    rateService.save(rate);
                });
        logger.warn("Rate with id -> {} updated", id);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRate(@PathVariable Long id){
        rateService.deleteById(id);
        logger.warn("Rate with id -> {} deleted", id);

        return ResponseEntity.noContent().build();
    }
    @PostMapping("/add")
    public ResponseEntity<?> addRates(@RequestBody List<RateDTO> ratesDTO) {
        for (RateDTO rateDTO : ratesDTO) {
            rateService.createRate(rateDTO);
        }
        return ResponseEntity.ok(ratesDTO);
    }

}
