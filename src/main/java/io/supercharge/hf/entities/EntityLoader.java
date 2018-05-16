package io.supercharge.hf.entities;

import io.supercharge.hf.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityLoader<T extends Entity> {
    
    private Session session;
    
    private Class<T> entityClass;

    private Map<String, Object> parameters = new HashMap<>();
    
    protected EntityLoader(Session session, Class<T> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
        
    }

    public static <U extends Entity> EntityLoader<U> loadEntity(Session session, Class<U> entityClass) {
        return new EntityLoader<>(session, entityClass);
    }

    public void setParameter(String name, Object val) {
        parameters.put(name, val);
    }

    public T firstResult() {
        return resultList().stream().findFirst().orElse(null);
    }

    public List<T> resultList() {
        Stream<T> stream = session.getEntities(entityClass).stream();

        for(Map.Entry<String, Object> param: parameters.entrySet()) {
            stream = stream.filter(f -> InstanceUtils.equals(f.getValue(param.getKey()), param.getValue()));
        }

        return stream.collect(Collectors.toList());
    }
}
