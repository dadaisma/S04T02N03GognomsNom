package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.domain.Flower;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.exceptiones.ClientErrorException;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.exceptiones.FlowerNotFoundException;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.exceptiones.InvalidFlowerDataException;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.repository.IclientFlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.http.HttpStatus;


import java.util.List;

@Service



public class ClientFlowerServiceImpl implements IflowerService {

    @Autowired
    private WebClient webClient;





    public void createFlower(FlowerDTO flowerDTO) {
        try {
            FlowerDTO createdFlowerDTO = webClient
                    .post()
                    .uri(uriBuilder -> uriBuilder.path("/add")
                            .build())
                    .bodyValue(flowerDTO)
                    .retrieve()
                    .bodyToMono(FlowerDTO.class)
                    .block();

            // Handle the response as needed (optional)
            // e.g., log success, process returned DTO, etc.
        } catch (WebClientResponseException ex) {
            // Handle WebClientResponseException for specific status codes
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new InvalidFlowerDataException("Invalid flower data: " + ex.getRawStatusCode());
            } else if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new FlowerNotFoundException("Flower not found: " + ex.getRawStatusCode());
            } else if (ex.getStatusCode().is4xxClientError()) {
                throw new ClientErrorException("Client error: " + ex.getRawStatusCode());
            }
           // else if (ex.getStatusCode().is5xxServerError()) {
          //     throw new ServerErrorException("Server error: " + ex.getRawStatusCode());
         //   }
        else {
                throw new RuntimeException("Unexpected error: " + ex.getRawStatusCode());
            }
        }}



    public void updateFlower( FlowerDTO flowerDTO ) {
        FlowerDTO updatedflowerDTO = webClient
                .put()
                .uri(uriBuilder -> uriBuilder.path("/update/{id}")
                        .build(flowerDTO.getPkFlowerID()))
                .bodyValue(flowerDTO)  // Set the request body
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new InvalidFlowerDataException("Failed to update flower with ID " + flowerDTO.getPkFlowerID())))
                .bodyToMono(FlowerDTO.class)
                .block();
    }


    public void deleteFlower(Integer flowerId) {
                 webClient
                .delete()
                .uri(uriBuilder -> uriBuilder.path("/delete/{id}")
                        .build(flowerId))
                .retrieve()
                         .onStatus(status -> status.value() == 404,
                         response -> Mono.error(new InvalidFlowerDataException("Failed to delete flower with ID " + flowerId)))
                         .toBodilessEntity()
                .block();

    }


    public FlowerDTO getFlowerById(Integer id) {
        FlowerDTO flowerDTO = webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/getOne/{id}")
                        .build(id))
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new InvalidFlowerDataException("Failed to find flower with ID " + id)))
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