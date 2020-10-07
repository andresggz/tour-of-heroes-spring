package co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model;

import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import lombok.*;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroListResponse {

    private Long id;

    private String name;

    public static HeroListResponse fromModel(Hero hero){
        return HeroListResponse.builder().id(hero.getId()).name(hero.getName())
                .build();
    }
}
