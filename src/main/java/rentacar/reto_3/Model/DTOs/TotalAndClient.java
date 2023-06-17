package rentacar.reto_3.Model.DTOs;

import rentacar.reto_3.Model.Client;

public class TotalAndClient {
    //Este objeto nos permite armar el JSON como lo queremos enviar o recibir.
    private long total;
    private Client client;

    public TotalAndClient(long total, Client client) {
        this.total = total;
        this.client = client;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
