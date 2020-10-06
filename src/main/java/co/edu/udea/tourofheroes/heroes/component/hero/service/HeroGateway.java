package co.edu.udea.tourofheroes.heroes.component.hero.service;

import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;

import javax.validation.constraints.NotNull;

public interface HeroGateway {

    Hero save(@NotNull Hero heroToCreate);

    Hero findById(@NotNull Long id);

    void deleteById(@NotNull Long id);
}
