package co.com.sofkau.entrenamiento.curso.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.entrenamiento.curso.values.CursoId;
import co.com.sofkau.entrenamiento.curso.values.Directriz;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;

public class AgregarDirectriz extends Command {
    private final CursoId cursoId;
    private final MentoriaId mentoriaId;
    private final Directriz directriz;

    public AgregarDirectriz(CursoId cursoId, MentoriaId mentoriaId, Directriz directriz) {
        this.cursoId = cursoId;
        this.mentoriaId = mentoriaId;
        this.directriz = directriz;
    }

    public MentoriaId getMentoriaId() {
        return mentoriaId;
    }

    public Directriz getDirectiz() {
        return directriz;
    }

    public CursoId getCoursoId() { return cursoId;
    }
}
