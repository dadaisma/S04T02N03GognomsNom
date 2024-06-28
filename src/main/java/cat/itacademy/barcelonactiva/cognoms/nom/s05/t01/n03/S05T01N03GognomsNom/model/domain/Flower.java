package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.domain;



public class Flower {



    private Integer pkFlowerId;

    private String nameFlower;

    private String countryFlower;

    // Default constructor
    public Flower(){}
    public Flower(Integer pkFlowerID, String nameFlower, String countryFlower) {
    }

    // Parameterized constructor
    public Flower(String nameFlower, String countryFlower) {
        this.nameFlower = nameFlower;
        this.countryFlower = countryFlower;
    }

    public Integer getPkFlowerId() {
        return pkFlowerId;
    }

    public void setPkFlowerId(Integer pkFlowerId) {
        this.pkFlowerId = pkFlowerId;
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
}