package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.exceptiones.ClientErrorException;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.exceptiones.FlowerNotFoundException;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03GognomsNom.model.exceptiones.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

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

            // Handle successful creation if needed
        } catch (WebClientResponseException ex) {
            handleWebClientResponseException(ex);
        }
    }

    public void updateFlower(FlowerDTO flowerDTO) {
        try {
            FlowerDTO updatedflowerDTO = webClient
                    .put()
                    .uri(uriBuilder -> uriBuilder.path("/update/{id}")
                            .build(flowerDTO.getPkFlowerID()))
                    .bodyValue(flowerDTO)  // Set the request body
                    .retrieve()
                    .onStatus(status -> status.value() == 404,
                            response -> Mono.error(new FlowerNotFoundException("Flower with ID " + flowerDTO.getPkFlowerID() + " not found.")))
                    .bodyToMono(FlowerDTO.class)
                    .block();
        } catch (WebClientResponseException ex) {
            handleWebClientResponseException(ex);
        }
    }

    public void deleteFlower(Integer flowerId) {
        try {
            webClient
                    .delete()
                    .uri(uriBuilder -> uriBuilder.path("/delete/{id}")
                            .build(flowerId))
                    .retrieve()
                    .onStatus(status -> status.value() == 404,
                            response -> Mono.error(new FlowerNotFoundException("Flower with ID " + flowerId + " not found.")))
                    .toBodilessEntity()
                    .block();
        } catch (WebClientResponseException ex) {
            handleWebClientResponseException(ex);
        }
    }

    public FlowerDTO getFlowerById(Integer id) {
        try {
            FlowerDTO flowerDTO = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder.path("/getOne/{id}")
                            .build(id))
                    .retrieve()
                    .onStatus(status -> status.value() == 404,
                            response -> Mono.error(new FlowerNotFoundException("Flower with ID " + id + " not found.")))
                    .bodyToMono(FlowerDTO.class)
                    .block();
            return flowerDTO;
        } catch (WebClientResponseException ex) {
            handleWebClientResponseException(ex);
            return null; // or throw exception depending on your design
        }
    }

    public List<FlowerDTO> getAllFlowers() {
        try {
            Mono<List<FlowerDTO>> flowerListMono = webClient
                    .get()
                    .uri("/getAll")
                    .retrieve()
                    .bodyToFlux(FlowerDTO.class)
                    .collectList();
            return flowerListMono.block();
        } catch (WebClientResponseException ex) {
            handleWebClientResponseException(ex);
            return null; // or throw exception depending on your design
        }
    }

    private void handleWebClientResponseException(WebClientResponseException ex) {
        if (ex.getStatusCode().is4xxClientError()) {
            throw new ClientErrorException(ex.getMessage(), ex);
        } else if (ex.getStatusCode().is5xxServerError()) {
            throw new ServerErrorException(ex.getMessage(), ex);
        } else {
            throw new RuntimeException("Unexpected error: " + ex.getRawStatusCode(), ex);
        }
    }
}
