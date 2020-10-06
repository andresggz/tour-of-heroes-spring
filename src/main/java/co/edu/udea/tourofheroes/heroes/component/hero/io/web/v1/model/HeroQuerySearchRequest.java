package co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model;

import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroQuerySearchCmd;
import lombok.*;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroQuerySearchRequest {

    private String name;

    public static HeroQuerySearchCmd toModel(HeroQuerySearchRequest queryCriteria){
        return HeroQuerySearchCmd.builder().name(queryCriteria.getName()).build();
    }
}
