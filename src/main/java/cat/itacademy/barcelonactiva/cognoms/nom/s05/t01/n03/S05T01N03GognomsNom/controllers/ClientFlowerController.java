package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.services.ClientFlowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ClientFlower")
public class ClientFlowerController {
    @Autowired
    private ClientFlowerServiceImpl flowerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<FlowerDTO>> getAll(){
        return ResponseEntity.ok().body(flowerService.getAllFlowers());
    }

}