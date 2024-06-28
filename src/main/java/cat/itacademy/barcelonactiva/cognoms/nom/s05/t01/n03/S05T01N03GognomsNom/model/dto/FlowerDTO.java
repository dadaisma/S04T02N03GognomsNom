package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;

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

    public FlowerDTO() {

    }


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

    public Integer getPkFlowerID() {
        return pkFlowerID;
    }

    public void setPkFlowerID(Integer pkFlowerID) {
        this.pkFlowerID = pkFlowerID;
    }

    public String getNameFlower() {
        return nameFlower;
    }

    public void setNameFlower(String nameFlower) {
        this.nameFlower = nameFlower;
    }

    public String getCountryFlower() {
        return countryFlower;
    }

    public void setCountryFlower(String countryFlower) {
        this.countryFlower = countryFlower;
    }

    public String getTypeFlower() {
        return typeFlower;
    }

    public void setTypeFlower(String typeFlower) {
        this.typeFlower = typeFlower;
    }
}

