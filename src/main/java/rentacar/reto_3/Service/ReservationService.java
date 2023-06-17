package rentacar.reto_3.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rentacar.reto_3.Model.Client;
import rentacar.reto_3.Model.DTOs.CompletedAndCancelled;
import rentacar.reto_3.Model.DTOs.TotalAndClient;
import rentacar.reto_3.Model.Reservation;
import rentacar.reto_3.Repository.ReservationRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation) {
        //Validaciones:
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reservationFinded = getReservation(reservation.getIdReservation());
            if (reservationFinded.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> reservationFinded = getReservation(reservation.getIdReservation());
            if (reservationFinded.isPresent()) {
                if (reservation.getStartDate() != null) {
                    reservationFinded.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    reservationFinded.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    reservationFinded.get().setStatus(reservation.getStatus());
                }
                return reservationRepository.save(reservationFinded.get());
            }else {
                return reservation;
            }
        }else {
            return reservation;
        }
    }

    public boolean deleteReservation (int id){
        Boolean respuesta= getReservation(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return respuesta;
    }

    //Reto 5

    public List<Reservation> getReservationBetweenDatesReport(String FechaA, String FechaB ){
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); //Objeto para poder parsear los datos String recibidos en el parametro
        Date a= new Date();
        Date b = new Date();
        try {
            a= parser.parse(FechaA);
            b= parser.parse((FechaB));

        }catch (ParseException exception){
            exception.printStackTrace();
        }

        if (a.before(b)){
            return reservationRepository.getReservationsBetweenDates(a,b);
        }else{
            return new ArrayList<>();
        }
    }

    public CompletedAndCancelled getReservationStatusReport(){
        List<Reservation> completed= reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled= reservationRepository.getReservationByStatus("cancelled");
        Long cantidadCompletada = (long) completed.size();
        Long cantidadCancelada = (long) cancelled.size();
        CompletedAndCancelled respuesta= new CompletedAndCancelled(cantidadCompletada,cantidadCancelada);
        return respuesta;
    }

    public List<TotalAndClient> getTopClientsReport(){
        //Objeto de Transferencia de Datos DTO, su única función es transferir datos
        List<TotalAndClient> respuesta = new ArrayList<>();
        List<Object[]> reporte= reservationRepository.getTotalReservationsByClient();
        //Realizamos un for para recorrer el reporte e insertar en respuesta
        for (Object[] pareja :
                reporte) {
            respuesta.add(new TotalAndClient ((Long) pareja[1], (Client) pareja[0])); //Casteamos
        }
        return respuesta;
    }

    
}
