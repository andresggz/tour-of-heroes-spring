package co.edu.udea.tourofheroes.heroes.component.hero.service;

import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Hero create(HeroSaveCmd heroToCreateCmd) {
        logger.debug("Begin create: heroToCreatecmd = {}", heroToCreateCmd);

        final Hero heroToCreate = HeroSaveCmd.toModel(heroToCreateCmd);

        final Hero heroCreated = heroGateway.save(heroToCreate);

        logger.debug("End create: heroCreated = {}", heroCreated);
        return null;
    }

    @Transactional(readOnly = true)
    @Override
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
}
