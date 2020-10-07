package co.edu.udea.tourofheroes.heroes.component.hero.service.model;

import co.edu.udea.tourofheroes.heroes.component.hero.model.Hero;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroSaveCmd {

    @NotNull
    private String name;

    public static Hero toModel(HeroSaveCmd heroSaveCmd){
        return Hero.builder().name(heroSaveCmd.getName()).build();
    }
}
