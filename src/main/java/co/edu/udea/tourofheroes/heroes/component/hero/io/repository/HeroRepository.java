package co.edu.udea.tourofheroes.heroes.component.hero.io.repository;

import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends PagingAndSortingRepository<Hero, Long>, JpaSpecificationExecutor<Hero> {
}
