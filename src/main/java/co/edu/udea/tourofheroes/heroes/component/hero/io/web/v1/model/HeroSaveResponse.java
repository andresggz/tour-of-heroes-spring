package co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model;

import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroSaveCmd;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroSaveResponse {

    private Long id;

    private String name;

    public static HeroSaveResponse fromModel(Hero hero){
        return HeroSaveResponse.builder()
                .id(hero.getId())
                .name(hero.getName()).build();
    }
}
