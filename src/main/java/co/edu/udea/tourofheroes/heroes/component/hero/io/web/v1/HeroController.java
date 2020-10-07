package co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1;

import co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model.HeroListResponse;
import co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model.HeroQuerySearchRequest;
import co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model.HeroSaveRequest;
import co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model.HeroSaveResponse;
import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import co.edu.udea.tourofheroes.heroes.component.hero.service.HeroService;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroQuerySearchCmd;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroSaveCmd;
import co.edu.udea.tourofheroes.heroes.component.shared.model.ResponsePagination;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping(value = "/api/v1/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class HeroController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final HeroService heroService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Void> create(@Valid @RequestBody @NotNull HeroSaveRequest heroToCreate){
        logger.debug("Begin create: heroToCreate = {}", heroToCreate);

        final HeroSaveCmd heroToCreateCmd = HeroSaveRequest.toModel(heroToCreate);

        final Hero heroCreated = heroService.create(heroToCreateCmd);

        URI location = fromUriString("/api/v1/heroes").path("/{id}")
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
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin delete: id = {}", id);

        heroService.deleteById(id);

        logger.debug("End delete: id = {}", id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<HeroSaveResponse> update(@Valid @PathVariable("id") @NotNull Long id, @Valid @RequestBody @NotNull HeroSaveRequest heroToUpdate){
        logger.debug("Begin update: id = {}, heroToUpdate = {}", id, heroToUpdate);

        final HeroSaveCmd heroToUpdateCmd = HeroSaveRequest.toModel(heroToUpdate);

        Hero heroUpdated = heroService.update(id, heroToUpdateCmd);

        logger.debug("End update: heroUpdated = {}", heroUpdated);

        return ResponseEntity.ok(HeroSaveResponse.fromModel(heroUpdated));
    }

    @GetMapping
    public ResponsePagination<HeroListResponse> findByParameters(@Valid @NotNull HeroQuerySearchRequest queryCriteria,
                                                               @PageableDefault(page = 0, size = 10,
                                                               direction = Sort.Direction.DESC, sort = "id") Pageable pageable){
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        HeroQuerySearchCmd queryCriteriaCmd = HeroQuerySearchRequest.toModel(queryCriteria);

        Page<Hero> heroesFound = heroService.findByParameters(queryCriteriaCmd, pageable);

        List<HeroListResponse> heroesFoundList = heroesFound.stream().map(HeroListResponse::fromModel)
                .collect(Collectors.toList());

        logger.debug("End findByParameters: heroesFound = {}", heroesFound);

        return ResponsePagination.fromObject(heroesFoundList, heroesFound.getTotalElements(), heroesFound.getNumber(),
                heroesFoundList.size());
    }



}
