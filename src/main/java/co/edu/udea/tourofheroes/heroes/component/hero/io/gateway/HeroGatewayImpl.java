package co.edu.udea.tourofheroes.heroes.component.hero.io.gateway;

import co.edu.udea.tourofheroes.heroes.component.hero.io.repository.HeroRepository;
import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import co.edu.udea.tourofheroes.heroes.component.hero.service.HeroGateway;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

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
                .orElseThrow(() -> new RuntimeException(RESOURCE_NOT_FOUND));

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
}
