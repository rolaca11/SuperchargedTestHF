package io.supercharge.hf;

import io.supercharge.hf.entities.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Session {

    private List<Entity> persistence = new ArrayList<>();

    public <T extends Entity> List<T> getEntities(Class<T> entityClass) {
        //noinspection unchecked
        return persistence.stream().filter(f -> f.getClass().isAssignableFrom(entityClass)).map(m -> (T)m).collect(Collectors.toList());
    }

    public <T extends Entity> void update(T t) {
        persistence.remove(t);
        persistence.add(t);
    }

    public <T extends Entity> void persist(T t) {
        persistence.add(t);
    }
}
