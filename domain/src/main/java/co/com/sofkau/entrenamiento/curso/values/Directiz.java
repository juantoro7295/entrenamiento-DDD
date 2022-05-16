package co.com.sofkau.entrenamiento.curso.values;

import co.com.sofka.domain.generic.ValueObject;

public class Directiz implements ValueObject<String> {
    private final String value;




    public Directiz(String value) {
        this.value = value;
    }

    @Override
    public String value() {

        return value;
    }
}
