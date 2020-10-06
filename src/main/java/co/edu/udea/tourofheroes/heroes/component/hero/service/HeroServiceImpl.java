package co.edu.udea.tourofheroes.heroes.component.hero.service;

import co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model.HeroSaveRequest;
import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroQuerySearchCmd;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
@Transactional
public class HeroServiceImpl implements HeroService {

    private final HeroGateway heroGateway;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public Hero create(@NotNull HeroSaveCmd heroToCreateCmd) {
        logger.debug("Begin create: heroToCreateCmd = {}", heroToCreateCmd);

        final Hero heroToCreate = HeroSaveCmd.toModel(heroToCreateCmd);

        final Hero heroCreated = heroGateway.save(heroToCreate);

        logger.debug("End create: heroCreated = {}", heroCreated);
        return heroCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public Hero findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        final Hero heroFound = heroGateway.findById(id);

        logger.debug("End findById: heroFound = {}", heroFound);
        return heroFound;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin delete: id = {}", id);

        heroGateway.deleteById(id);

        logger.debug("Begin delete: id = {}", id);
    }

    @Override
    public Hero update(@NotNull Long id, @NotNull HeroSaveCmd heroToUpdateCmd) {
        logger.debug("Begin update: id = {}, heroToUpdateCmd = {}", id, heroToUpdateCmd);

        final Hero heroInDataBase = findById(id);

        final Hero heroToUpdate = heroInDataBase.toBuilder().name(heroToUpdateCmd.getName())
                .build();

        final Hero heroUpdated = heroGateway.update(heroToUpdate);

        logger.debug("End update: heroUpdated = {}", heroUpdated);
        return heroUpdated;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Hero> findByParameters(@NotNull HeroQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        final Page<Hero> heroesFound = heroGateway.findByParameters(queryCriteria, pageable);

        logger.debug("End findByParameters: heroesFound = {}", heroesFound);
        return heroesFound;
    }


}
