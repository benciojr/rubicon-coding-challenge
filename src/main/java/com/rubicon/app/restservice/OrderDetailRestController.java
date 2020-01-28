package com.rubicon.app.restservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rubicon.app.api.service.OrdersService;
import com.rubicon.app.api.service.impl.WaterOrderingServiceImpl;
import com.rubicon.app.model.OrderDetail;
import com.rubicon.app.model.OrderStatus;
import com.rubicon.app.model.ResponseObject;
import java.util.List;
import java.util.ArrayList;

@RestController
public class OrderDetailRestController {

    @PostMapping(path="/placeOrder",
    consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public OrderDetail placeOrder(@RequestBody OrderDetail orderDetail) {
        OrdersService ordersService = new WaterOrderingServiceImpl();
        return ordersService.placeOrder(orderDetail);
    }

    @GetMapping("/cancelOrder")
    public Object cancelOrder(@RequestParam(value = "orderId", required=true) Long orderId) {

        OrdersService ordersService = new WaterOrderingServiceImpl();
        ResponseObject response = null;
        if (orderId == null || orderId < 0) {
            response = new ResponseObject("Error", "Missing or incorrect order id");
        } else {
            response = ordersService.cancelOrder(orderId);
        }
        return response;
    }

    @GetMapping("/viewOrders")
    public List<OrderDetail> viewOrders(@RequestParam(value = "searchBy", required=true) String searchBy,
            @RequestParam(value = "searchValue", required=false) String searchValue) {
        OrdersService ordersService = new WaterOrderingServiceImpl();
        return ordersService.viewOrders(searchBy, searchValue);
    }

}
