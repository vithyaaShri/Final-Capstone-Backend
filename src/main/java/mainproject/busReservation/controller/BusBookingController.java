package mainproject.busReservation.controller;

import mainproject.busReservation.dto.BusBookingDto;
import mainproject.busReservation.service.BusBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/busBooking")
@CrossOrigin
public class BusBookingController {
    @Autowired
    private BusBookingService busService;

    //Post Mapping to Post Booking Details and Passenger Detail
    @PostMapping
    public ResponseEntity<BusBookingDto> saveBooking(@RequestBody BusBookingDto busDto)
    {
        BusBookingDto savedBus=busService.add(busDto);
        return new ResponseEntity<>(savedBus, HttpStatus.CREATED);
    }
    //GetMapping is to get all the bus details.
    @GetMapping
    public ResponseEntity<List<BusBookingDto>> getAllBus()
    {
        List<BusBookingDto> allBus=busService.getAll();
        return new ResponseEntity<List<BusBookingDto>>(allBus,HttpStatus.OK);
    }
    //Get Single User Details
    @GetMapping("/passenger/{id}")
    public ResponseEntity<BusBookingDto> getbypassenger_id(@PathVariable("id") Long id)
    {
        BusBookingDto passenger=busService.getByPassengerId(id);
        return new ResponseEntity<BusBookingDto>(passenger,HttpStatus.OK);
    }
}
