package ir.maktab.web;


import ir.maktab.dto.*;
import ir.maktab.service.OfferService;
import ir.maktab.service.OrderService;
import ir.maktab.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/managerRestController")
@RestController
public class ManagerRestController {
    private final OrderService orderService;
    private final OfferService offerService;
    private final UserService userService;

    public ManagerRestController(OrderService orderService, OfferService offerService, UserService userService) {
        this.orderService = orderService;
        this.offerService = offerService;
        this.userService = userService;
    }

    @PostMapping(value = "/filterOrders",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> filterOrders(@RequestBody OrderHistoryFilterDto dto){
        List<OrderDto> list = orderService.filterOrders(dto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/filterOffers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> filterOffers(@RequestBody OfferHistoryDto dto){
        List<OfferDto> list = offerService.filterOffers(dto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/filterUsers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> filterUsers(@RequestBody UserHistoryDto dto){
        List<UserDto> list = userService.userHistory(dto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
