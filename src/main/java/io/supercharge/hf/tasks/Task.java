package io.supercharge.hf.tasks;

import io.supercharge.hf.Session;

public interface Task {

    void setParameters(String... parameters);

    void execute(Session session);
}
