package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectriz;
import co.com.sofkau.entrenamiento.curso.events.CursoCreado;
import co.com.sofkau.entrenamiento.curso.events.DirectrizAgregadaAMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AgregarDirectrizUseCaseTest {

    @InjectMocks
    private AgregarDirectrizUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void agregarUnaDirectrizHappyPass(){
        //arrange
        CursoId cursoId = CursoId.of("d");
        MentoriaId mentoriaId = MentoriaId.of("mentoriaId");
        Directriz directriz = new Directriz("Directriz");
        var command = new AgregarDirectriz(cursoId,mentoriaId, directriz);

        when(repository.getEventsBy("d")).thenReturn(history());
        useCase.addRepository(repository);
        //act

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getCoursoId().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var event = (DirectrizAgregadaAMentoria)events.get(0);
        Assertions.assertEquals("Directriz", event.getDirectiz().value());

    }

    private List<DomainEvent> history() {
        Nombre nombre = new Nombre("DDD");
        Descripcion descripcion = new Descripcion("Curso complementario para el training");
        var event = new CursoCreado(
                nombre,
                descripcion
        );
        MentoriaId mentoriaId = MentoriaId.of("mentoriaId");
        Nombre nombreMentoria = new Nombre("NombreMentoria");
        Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
        var evento = new MentoriaCreada(
                mentoriaId,
                nombreMentoria,
                fecha
        );
        event.setAggregateRootId("xxxxx");
        return List.of(event,evento);
    }

}