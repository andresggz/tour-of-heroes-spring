package co.edu.udea.tourofheroes.heroes.component.hero.io.web.v1.model;

import co.edu.udea.tourofheroes.heroes.component.hero.service.model.HeroSaveCmd;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroSaveRequest {

        @NotNull
        private String name;

        public static HeroSaveCmd toModel(HeroSaveRequest heroSaveRequest){
            return HeroSaveCmd.builder().
                    name(heroSaveRequest.getName()).build();
        }
}
