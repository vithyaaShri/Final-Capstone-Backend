package mainproject.busReservation.controller;

import lombok.AllArgsConstructor;
import mainproject.busReservation.dto.BusDto;
import mainproject.busReservation.entity.Bus;
import mainproject.busReservation.repository.BusRepository;
import mainproject.busReservation.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/bus")
public class BusController {
    @Autowired
    private BusService busService;
    @Autowired
    private BusRepository busRepository;
    //Postmapping is to add the new bus details to database
    @PostMapping
    public ResponseEntity<BusDto> saveBus(@RequestBody BusDto busDto)
    {
        BusDto savedBus=busService.addBus(busDto);
        return new ResponseEntity<>(savedBus, HttpStatus.CREATED);
    }
    //GetMapping is to get all the bus details
    @GetMapping
    public ResponseEntity<List<BusDto>> getAllBus()
    {
        List<BusDto> allBus=busService.getAllBus();
        return new ResponseEntity<List<BusDto>>(allBus,HttpStatus.OK);
    }
    //To get Single Bus Detail Based On Id
    @GetMapping("/{id}")
    public ResponseEntity<BusDto> getBus(@PathVariable("id")Long busId)
    {
        BusDto bus=busService.getBusById(busId);
        return new ResponseEntity<BusDto>(bus,HttpStatus.OK);
    }
    //Delete Bus Based on Id
    @DeleteMapping("/{id}")
    public  String deleteBus(@PathVariable("id")Long busId)
    {
        busService.deleteBus(busId);
        return "Bus is deleted";
    }
    //Will Update Bus Detail Based On id
    @PutMapping("/{id}")
    public ResponseEntity<BusDto> updateBus(@PathVariable("id") Long id, @RequestBody  BusDto busDto)
    {
        BusDto updatedBus=busService.updateBus(busDto,id);
        return new ResponseEntity<>(updatedBus,HttpStatus.OK);
    }

    //This Will Update the Ticket Count when Ticket is Booked to get remaining seat Details
    @PutMapping("/updateAvailableTickets/{bus_id}/{number}")
    void updateAvailableTickets(@PathVariable("bus_id") Long bus_id,@PathVariable("number") int number) {
        List<Bus> list = busRepository.findAll();
        System.out.println(number);

        for(Bus bus : list) {
            if(Objects.equals(bus.getId(), bus_id)) {
                System.out.println(bus.getAvailable_seats());
                bus.setAvailable_seats(bus.getAvailable_seats() - number);
                busRepository.save(bus);
                System.out.println(bus.getAvailable_seats());
            }
        }
    }
}
