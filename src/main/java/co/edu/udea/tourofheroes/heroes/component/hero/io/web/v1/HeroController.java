package co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1;

import co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model.HeroSaveRequest;
import co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model.HeroSaveResponse;
import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import co.edu.udea.tourofheroes.heroes.component.hero.service.HeroService;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping("/heroes")
@RequiredArgsConstructor
public class HeroController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final HeroService heroService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody HeroSaveRequest heroToCreate){
        logger.debug("Begin create: heroToCreate = {}", heroToCreate);

        final HeroSaveCmd heroToCreateCmd = HeroSaveRequest.toModel(heroToCreate);

        final Hero heroCreated = heroService.create(heroToCreateCmd);

        URI location = fromUriString("/api/v1/heroes").path("/{}id")
                .buildAndExpand(heroCreated.getId()).toUri();

        logger.debug("End create: heroCreated = {}", heroCreated);

        return ResponseEntity.created(location).build();

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HeroSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin findById: id = {}", id);

        final Hero heroFound = heroService.findById(id);

        logger.debug("End findById: heroFound = {}", heroFound);

        return ResponseEntity.ok(HeroSaveResponse.fromModel(heroFound));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin delete: id = {}", id);

        heroService.deleteById(id);

        logger.debug("End delete: id = {}", id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
