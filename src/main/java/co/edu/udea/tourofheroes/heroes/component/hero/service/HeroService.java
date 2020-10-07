package co.edu.udea.tourofheroes.heroes.component.hero.service;

import co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model.HeroSaveRequest;
import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroQuerySearchCmd;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroSaveCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface HeroService {

    Hero create(@NotNull HeroSaveCmd heroToCreateCmd);

    Hero findById(@NotNull Long id);

    void deleteById(@NotNull Long id);

    Hero update(@NotNull Long id, @NotNull HeroSaveCmd heroToUpdateCmd);

    Page<Hero> findByParameters(@NotNull HeroQuerySearchCmd queryCriteria, @NotNull Pageable pageable);
}
