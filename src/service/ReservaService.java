package service;


import model.*;
import repository.InMemoryRepository;


import java.util.List;


public class ReservaService {
    private InMemoryRepository<Reserva> repo = new InMemoryRepository<>();


    public void criarReserva(Reserva r) { repo.salvar(r); System.out.println("Reserva criada: " + r); }
    public List<Reserva> listarReservas() { return repo.listar(); }
    public void cancelarReserva(String id) {
        Reserva alvo = null;
        for (Reserva r : repo.listar()) if (r.getId().equals(id)) { alvo = r; break; }
        if (alvo != null) repo.remover(alvo);
    }
}