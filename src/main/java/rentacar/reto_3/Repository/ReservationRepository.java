package rentacar.reto_3.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rentacar.reto_3.Model.Client;
import rentacar.reto_3.Model.DTOs.TotalAndClient;
import rentacar.reto_3.Model.Reservation;
import rentacar.reto_3.Repository.CRUD.ReservationCrudRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {
@Autowired
    private ReservationCrudRepository reservationCrudRepository;

public List<Reservation> findAll(){
    return (List<Reservation>) reservationCrudRepository.findAll();
}
public Optional<Reservation> getReservation(int id){
    return reservationCrudRepository.findById(id);
}
public Reservation save(Reservation reservation){
    return reservationCrudRepository.save(reservation);
}
public void delete(Reservation reservation){
    reservationCrudRepository.delete(reservation);
}

//Reto 5 Métodos

public List<Reservation> getReservationsBetweenDates(Date Fecha_Inicio, Date Fecha_Final){
    return reservationCrudRepository.findAllByStartDateAfterAndDevolutionDateBefore(Fecha_Inicio,Fecha_Final);}

public List<Reservation> getReservationByStatus(String status){
    return reservationCrudRepository.findAllByStatus(status);}

public List<Object[]> getTotalReservationsByClient (){
   return reservationCrudRepository.getTotalReservationByClient();

}
}
