package controller;


import model.Reserva;
import service.ReservaService;


import java.util.List;


public class ReservaController {
    private ReservaService reservaService = new ReservaService();


    public void criarReserva(Reserva r) { reservaService.criarReserva(r); }
    public List<Reserva> listarReservas() { return reservaService.listarReservas(); }
}