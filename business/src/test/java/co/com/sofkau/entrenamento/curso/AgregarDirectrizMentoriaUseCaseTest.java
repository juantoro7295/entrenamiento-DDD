package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.Mentoria;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizMentoria;
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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AgregarDirectrizMentoriaUseCaseTest {

    @InjectMocks
    private AgregarDirectrizMentoriaUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void executeUseCase() {
        //Arrange
        CursoId cursoId = CursoId.of("xx");
        MentoriaId mentoriaId = MentoriaId.of("yy");
        Directiz directriz = new Directiz("esta es una directriz");

        var command = new AgregarDirectrizMentoria(cursoId, mentoriaId, directriz);
        when(repository.getEventsBy("xx")).thenReturn(history());
        useCase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getDirectiz().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //Assert
        var event = (DirectrizAgregadaAMentoria) events.get(0);
        assertEquals("esta es una directriz", event.getDirectiz().value());
    }

    private List<DomainEvent> history() {
        Nombre nombre = new Nombre("curso ensayo");
        Descripcion descripcion = new Descripcion(" useCase");
        var event = new CursoCreado(
                nombre,
                descripcion
        );

        MentoriaId mentoriaId = MentoriaId.of("yy");
        Nombre nombreMentoria = new Nombre("casos de usos");
        Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
        var event2 = new MentoriaCreada(
                mentoriaId,
                nombreMentoria,
                fecha

        );
        event.setAggregateRootId("xxxxx");
        return List.of(event, event2);
    }

}