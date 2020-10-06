package co.edu.udea.tourofheroes.heroes.component.hero.service;

import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroSaveCmd;

import javax.validation.constraints.NotNull;

public interface HeroService {

    Hero create(@NotNull HeroSaveCmd heroToCreateCmd);

    Hero findById(@NotNull Long id);

    void deleteById(@NotNull Long id);
}
