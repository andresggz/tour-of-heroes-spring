package co.edu.udea.tourofheroes.heroes.component.hero.io.repository;

import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends CrudRepository<Hero, Long> {
}
