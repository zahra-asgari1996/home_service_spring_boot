package ir.maktab.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping
public class ExternalWebApi {
    private final RestTemplate restTemplate;

    public ExternalWebApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/reverse/no")
    public ResponseEntity<?> get(){
        return null;
    }
}
