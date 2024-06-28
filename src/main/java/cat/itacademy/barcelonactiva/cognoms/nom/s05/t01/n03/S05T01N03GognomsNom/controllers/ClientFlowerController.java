package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.services.ClientFlowerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ClientFlower")

public class ClientFlowerController {
    @Autowired
    private ClientFlowerServiceImpl flowerService;

    @Operation(summary = "Get list of all flowers", description = "Returns a list of all flowers in the system")
    @GetMapping("/getAll")
    public ResponseEntity<List<FlowerDTO>> getAll(){
        return ResponseEntity.ok().body(flowerService.getAllFlowers());
    }

    @Operation(summary = "Get a flower by ID", description = "Returns a single flower based on the provided ID")
    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOneFlower(@PathVariable("id") Integer id){
        FlowerDTO flowerDTO =flowerService.getFlowerById(id);
        return ResponseEntity.ok().body(flowerDTO);
    }

    @Operation(summary = "Delete a flower", description = "Deletes a flower based on the provided ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlowerNow(@PathVariable(value="id") Integer id){
        flowerService.deleteFlower(id);
        return ResponseEntity.noContent().build();

    }

}