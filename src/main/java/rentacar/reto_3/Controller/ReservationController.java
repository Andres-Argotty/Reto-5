package rentacar.reto_3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import rentacar.reto_3.Model.DTOs.CompletedAndCancelled;
import rentacar.reto_3.Model.DTOs.TotalAndClient;
import rentacar.reto_3.Model.Reservation;
import rentacar.reto_3.Service.ReservationService;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", methods ={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @GetMapping("/all") // Al ser una petición Get utilizamos el GetMapping, la ruta sería localhost.../api/Reservation/all
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }

    @GetMapping("/{id}") //Para poder enviar el id a la hora de realizar la petición ej. /api/Reservation/12
    public Optional<Reservation> getReservation (@PathVariable int id){ //PathVariable especifica que el valor del {id} sea el que
        return reservationService.getReservation(id);        //Entra por parametro desde la url.
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    //Necesitamos especificar el cuerpo de la petición
    public Reservation save (@RequestBody Reservation reservation){
        return reservationService.save(reservation);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update (@RequestBody Reservation reservation){
        return reservationService.update(reservation);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteReservation (@PathVariable int id){
        return reservationService.deleteReservation(id);
    }

    //reto 5
    @GetMapping("/report-dates/{fecha1}/{fecha2}")
    public List<Reservation> getReservationBetweenDatesReport(@PathVariable("fecha1") String fecha1, @PathVariable("fecha2")
    String fecha2)
    {
        return reservationService.getReservationBetweenDatesReport(fecha1,fecha2);
    }
    @GetMapping("/report-status")
    public CompletedAndCancelled getReservationsStatusReport(){
        return reservationService.getReservationStatusReport();
    }
    @GetMapping("/report-clients")
    public List<TotalAndClient> getTopClientsReport(){
        return  reservationService.getTopClientsReport();
    }

}