package co.edu.udea.tourofheroes.heroes.component.hero.io.gateway;

import co.edu.udea.tourofheroes.heroes.component.hero.io.repository.HeroRepository;
import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import co.edu.udea.tourofheroes.heroes.component.hero.service.HeroGateway;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroQuerySearchCmd;
import co.edu.udea.tourofheroes.heroes.component.shared.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class HeroGatewayImpl implements HeroGateway {

    private final HeroRepository heroRepository;

    private static final String RESOURCE_NOT_FOUND = "Hero not found";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Hero save(@NotNull Hero heroToCreate) {
        logger.debug("Begin create: heroToCreate = {}", heroToCreate);

        final Hero heroCreated = heroRepository.save(heroToCreate);

        logger.debug("End create: heroToCreate = {}", heroCreated);

        return heroCreated;
    }

    @Override
    public Hero findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        final Hero heroFound = heroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: heroFound = {}", heroFound);
        return heroFound;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin delete: id = {}", id);

        findById(id);
        heroRepository.deleteById(id);

        logger.debug("End delete: id = {}", id);
    }

    @Override
    public Hero update(@NotNull Hero heroToUpdate) {
        logger.debug("Begin update: heroToUpdate = {}", heroToUpdate);

        final Hero heroUpdated = heroRepository.save(heroToUpdate);

        logger.debug("End update: heroUpdated = {}", heroUpdated);
        return heroUpdated;
    }

    @Override
    public Page<Hero> findByParameters(@NotNull HeroQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        Specification<Hero> specification = buildCriteria(queryCriteria);

        final Page<Hero> heroesFound = heroRepository.findAll(specification, pageable);

        logger.debug("End findByParameters: heroesFound = {}", heroesFound);

        return heroesFound;
    }

    private Specification<Hero> buildCriteria(@NotNull HeroQuerySearchCmd queryCriteria){
        logger.debug("Begin buildCriteria: queryCriteria = {}", queryCriteria);

        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(nonNull(queryCriteria.getName())){
                predicates
                        .add(criteriaBuilder.and(
                                criteriaBuilder.like(root.get("name"), queryCriteria.getName() + "%")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
