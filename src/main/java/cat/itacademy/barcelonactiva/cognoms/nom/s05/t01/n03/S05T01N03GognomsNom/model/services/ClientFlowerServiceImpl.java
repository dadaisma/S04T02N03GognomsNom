package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.domain.Flower;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.repository.IclientFlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service



public class ClientFlowerServiceImpl implements IflowerService {

    @Autowired
    private WebClient webClient;





    public void createFlower(FlowerDTO flowerDTO) {
        FlowerDTO createdflowerDTO = webClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/add")
                        .build())
                .bodyValue(flowerDTO)  // Set the request body
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .block();
       // return flowerDTO;
      //  if (flowerDTO.getNameFlower() == null || flowerDTO.getCountryFlower() == null) {
       //     throw new InvalidFlowerDataException("Name and country cannot be null");
     //   }
     //   IclientFlowerRepository.save(toEntity(flowerDTO));
    }



    public void updateFlower( FlowerDTO flowerDTO ) {
        FlowerDTO updatedflowerDTO = webClient
                .put()
                .uri(uriBuilder -> uriBuilder.path("/update/{id}")
                        .build(flowerDTO.getPkFlowerID()))
                .bodyValue(flowerDTO)  // Set the request body
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .block();


       // Optional<Flower> existingFlowerOptional = IclientFlowerRepository.findById(flowerDTO.getPkFlowerID());

       // if (!existingFlowerOptional.isPresent()) {
       //     throw new EntityNotFoundException("Update Flower Failed: Invalid ID: " + flowerDTO.getPkFlowerID() +
       //             " -> DOESN'T EXIST in DataBase");
     //   }

        // If the entity exists, proceed with updating its fields
      //  Flower existingFlower = existingFlowerOptional.get();
      //  existingFlower.setNameFlower(flowerDTO.getNameFlower());
      //  existingFlower.setCountryFlower(flowerDTO.getCountryFlower());


      //  IclientFlowerRepository.save(existingFlower);
    }


    public void deleteFlower(Integer flowerId) {
        FlowerDTO flowerDTO = webClient
                .delete()
                .uri(uriBuilder -> uriBuilder.path("/delete/{id}")
                        .build(flowerId))
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .block();
     //   if(!IclientFlowerRepository.findById(flowerId).isPresent()){
      //      throw new EntityNotFoundException("Update Flower Failed: Invalid ID: "+ flowerId+
      //              " -> DOESN'T EXIST in DataBase");
      //  }
     //   IclientFlowerRepository.deleteById(flowerId);
    }


    public FlowerDTO getFlowerById(Integer id) {
        FlowerDTO flowerDTO = webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/getOne/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .block();

     return flowerDTO;
    }



    public List<FlowerDTO> getAllFlowers(){
        Mono<List<FlowerDTO>> flowerListMono = webClient
                .get()
                .uri("/getAll")
                .retrieve()
                .bodyToFlux(FlowerDTO.class)
                .collectList();
        List<FlowerDTO> flowerList = flowerListMono.block();

        return flowerList;
    }

}