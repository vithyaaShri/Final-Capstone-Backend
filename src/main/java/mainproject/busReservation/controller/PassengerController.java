package mainproject.busReservation.controller;

import lombok.AllArgsConstructor;
import mainproject.busReservation.dto.PassengerDto;
import mainproject.busReservation.entity.Passenger;
import mainproject.busReservation.repository.PassengerRepository;
import mainproject.busReservation.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private PassengerRepository passengerRepository;
    //PostMapping is to add the passenger details to database
    @PostMapping
    public ResponseEntity<PassengerDto> savePassenger(@RequestBody PassengerDto passengerDto)
    {
        PassengerDto savedPassenger=passengerService.addPassenger(passengerDto);
        return new ResponseEntity<>(savedPassenger, HttpStatus.CREATED);
    }
    //GetMapping is to get all the passengers details
    @GetMapping
    public ResponseEntity<List<PassengerDto>> getAllPassenger()
    {
        List<PassengerDto> allUser=passengerService.getAllPassenger();
        return new ResponseEntity<List<PassengerDto>>(allUser,HttpStatus.OK);
    }
    //Get Passenger Based on id
    @GetMapping("/{passengerId}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable("passengerId")Long userId)
    {
        PassengerDto getUser=passengerService.getPassengerById(userId);
        return new ResponseEntity<PassengerDto>(getUser,HttpStatus.OK);
    }
    //Delete Passenger Based On id
    @DeleteMapping("/{id}")
    public  String deletePassenger(@PathVariable("id")Long userId)
    {
        passengerService.deletePassenger(userId);
        return "Passenger is deleted";
    }
    //To get Passenger detail based on email
    @GetMapping("/email/{email}")
    public ResponseEntity<PassengerDto> getPassengerbyEmail(@PathVariable String email)
    {
        PassengerDto passengerDto=passengerService.findByEmail(email);
        return new ResponseEntity<PassengerDto>(passengerDto,HttpStatus.OK);
    }
    //To Change Passenger detail based on id
    @PutMapping("/{id}")
    public ResponseEntity<PassengerDto> updatePassenger(@PathVariable("id") Long id, @RequestBody PassengerDto passengerDto)
    {
        PassengerDto updatePassenger=passengerService.updatePassenger(passengerDto,id);
        return new ResponseEntity<>(updatePassenger,HttpStatus.OK);
    }
    //Cancel Ticket Based On Ticket Details
    @PutMapping("/canceled/{pnr_details}")
    public String updateCancelStatus(@PathVariable Long pnr_details) {
        Passenger cancel = passengerRepository.findById(pnr_details).get();
        System.out.println(pnr_details);

        System.out.println(cancel.getId());
        if (cancel.getId() == pnr_details) {
            cancel.setCancelStat(true);
            passengerRepository.save(cancel);
            System.out.println(cancel.isCancelStat());
            return cancel.getPassengerName() + " ticket is cancelled";

        }
        return null;
    }
    //Cancel Ticket Based On id
    @PutMapping("cancelticket/{id}")
    public ResponseEntity<PassengerDto> cancelTicket(@PathVariable("id") Long id){
        PassengerDto updatedPassenger=passengerService.cancelTicket(id);
        return ResponseEntity.ok(updatedPassenger);

    }

    }


