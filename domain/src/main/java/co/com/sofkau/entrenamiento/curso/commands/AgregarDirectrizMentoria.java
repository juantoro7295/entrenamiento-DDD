package co.com.sofkau.entrenamiento.curso.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.entrenamiento.curso.values.Directiz;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;

public class AgregarDirectrizMentoria extends Command {

    private final MentoriaId mentoriaId;
    private final Directiz directiz;


    public AgregarDirectrizMentoria(MentoriaId mentoriaId, Directiz directiz) {
        this.mentoriaId = mentoriaId;
        this.directiz = directiz;
    }


    public MentoriaId getMentoriaId() {
        return mentoriaId;
    }

    public Directiz getDirectiz() {
        return directiz;
    }


}
