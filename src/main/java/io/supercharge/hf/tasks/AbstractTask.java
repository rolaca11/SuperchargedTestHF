package io.supercharge.hf.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractTask implements Task {

    List<String> parameters = new ArrayList<>();

    @Override
    public void setParameters(String... parameters) {
        this.parameters = Arrays.asList(parameters);
    }
}
