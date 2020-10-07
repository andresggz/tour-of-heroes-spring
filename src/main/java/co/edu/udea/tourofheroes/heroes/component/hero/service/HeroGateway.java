package co.edu.udea.tourofheroes.heroes.component.hero.service;

import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroQuerySearchCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface HeroGateway {

    Hero save(@NotNull Hero heroToCreate);

    Hero findById(@NotNull Long id);

    void deleteById(@NotNull Long id);

    Hero update(@NotNull Hero heroToUpdate);

    Page<Hero> findByParameters(@NotNull HeroQuerySearchCmd queryCriteria, @NotNull Pageable pageable);
}
