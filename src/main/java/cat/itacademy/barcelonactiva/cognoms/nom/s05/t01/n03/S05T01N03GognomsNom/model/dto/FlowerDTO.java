package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class FlowerDTO {
    private Integer pkFlowerID;
    @NotNull
    private String nameFlower;
    @NotNull
    private String countryFlower;
    private String typeFlower;

    private static final List<String> UE_COUNTRIES = Arrays.asList(
            "Romania", "Sweden", "Germany", "Austria", "Belgium",
            "Cyprus", "Slovakia", "Slovenia", "Spain", "Estonia",
            "Finland", "France", "Greece", "Netherlands", "Hungary",
            "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg",
            "Malta", "Poland", "Portugal", "Czech Republic");




    public FlowerDTO( String nameFlower, String countryFlower) {

        this.nameFlower = nameFlower;
        this.countryFlower = countryFlower;
        this.typeFlower = calcTypeFlower();
    }

    public FlowerDTO(Integer pkFlowerID, String nameFlower, String countryFlower) {
        this.pkFlowerID = pkFlowerID;
        this.nameFlower = nameFlower;
        this.countryFlower = countryFlower;
        this.typeFlower = calcTypeFlower();
    }

    private String calcTypeFlower() {
        if (UE_COUNTRIES.contains(countryFlower)) {
            return "UE";
        } else {
            return "NOT UE";
        }
    }


}

