package ir.maktab.web;

import ir.maktab.dto.AddressDto;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("/map")
public class ExternalWebApi {
    private final RestTemplate restTemplate;

    public ExternalWebApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


//    @GetMapping(value = "/address",produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public String get(@RequestParam("lat") String lat,@RequestParam("lng")String lng){
//        String url = "https://map.ir/reverse/no?lat="+lat+"&lon="+lng;
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        headers.set("x-api-key", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYxOTE4MGM3MWU1NzExODE0NDAzZTk3MTFiZjcxN2ZhODIwMTM5ZjViM2Y3MDNhYjVlOTcxZmRkZDZmMTMwNGYxOTRiMTliMjgwYjcwOTZjIn0.eyJhdWQiOiIxNDYyNiIsImp0aSI6IjYxOTE4MGM3MWU1NzExODE0NDAzZTk3MTFiZjcxN2ZhODIwMTM5ZjViM2Y3MDNhYjVlOTcxZmRkZDZmMTMwNGYxOTRiMTliMjgwYjcwOTZjIiwiaWF0IjoxNjI0NzM1NzI1LCJuYmYiOjE2MjQ3MzU3MjUsImV4cCI6MTYyNzMyNzcyNSwic3ViIjoiIiwic2NvcGVzIjpbImJhc2ljIl19.F1pDeSlV3lhpispxOjHdX0pwY80XYyzEMmqJYdRNbMm30LMMtWiswYSNjpgC11VOPEG9fJiljF355qmX_ZIq93xaOO2Ff1gNw6FUgackl5TW534wwP678L5zs-xFDncESn90lqKa4OCZh-TH_u5bq7BZMSymZVby-hRVQOd8l1K9Z2RYfgUYF5EoUPxMqffIERbPZJcniu0nSVHijegPaAqiO9uScr5kwhaHOh8Oar4HI-dNz2jvVGOaMU3ozafIhRDIp4vZuFp4LW-zpUbaaMQf2wtBDSzAfYqCd5BfU1Zt4ASPwTnnu-NPayKlvlXC36Ce45GCZ_O7JYZqSVq8fw");
//        HttpEntity request = new HttpEntity(headers);
//
//
//        ResponseEntity<AddressDto> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                request,
//                AddressDto.class
//        );
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            System.out.println("Request Successful.");
//            System.out.println(response.getBody());
//            AddressDto body = response.getBody();
//        } else {
//            System.out.println("Request Failed");
//            System.out.println(response.getStatusCode());
//        }
//        return "redirect:/order/createOrder";
//    }
}
