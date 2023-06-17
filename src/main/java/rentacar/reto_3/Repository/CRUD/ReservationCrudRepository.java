package rentacar.reto_3.Repository.CRUD;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rentacar.reto_3.Model.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
    //Crud repository junto con JPA nos permite hacer consultas con estructura SQL desde un método

/*    Reporte 1
     SELECT * from reservation WHERE startDate AFTER fechaA AND devolutionDate BEFORE fechaB -> fechaA es el inicio del intervalo
     fechaB es el final del intervalo
     Con el nombre de la función se puede programar el comportamiento de la consulta. Nos ayuda a autocompletar la función*/
    public List<Reservation> findAllByStartDateAfterAndDevolutionDateBefore(Date Fecha_Inicio, Date Fecha_Final);
//    Colocamos en orden las variables dentro del parentesis, jpa se encarga de ubicarlos según estén las comparaciones

    //Reporte 2
    //SELECT * FROM Reservation WHERE status = "ValorQueNecesitamos"
    public List<Reservation> findAllByStatus(String status); //La consulta de arriba es la misma del método

    //Reporte 3
    // SELECT client, COUNT(client) FROM Reservation GROUP BY client ORDER BY COUNT(client) DESC;
    //En este caso no podemos realizar las peticiones como lo veniamos haciendo, por tanto vamos a usar la siguiente estructura
    //Retorna una lista de parejas de dos valores
    //[client1, total_cliente1]
    //[Client2, total_cliente2]
    //etc...
    @Query("SELECT c.client, COUNT (c.client) FROM Reservation AS c GROUP BY c.client ORDER BY COUNT (c.client) DESC")
    public List<Object[]> getTotalReservationByClient(); //Este método ejecuta la sentencia de arriba
    //Usamos el tipo Object para no especificar los tipos de datos y esto se representa en un arreglo de objetos.

}
