package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.dto.FlowerDTO;

import java.util.List;

public interface IflowerService {


    void createFlower(FlowerDTO flowerDTO);


    void updateFlower(FlowerDTO flowerDTO);

    void deleteFlower(Integer id);

    FlowerDTO getFlowerById(Integer id);



    List<FlowerDTO> getAllFlowers();


}

