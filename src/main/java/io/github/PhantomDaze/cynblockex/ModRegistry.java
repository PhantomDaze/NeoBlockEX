package io.github.PhantomDaze.cynblockex;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ModRegistry<T> {
    private final Map<String, T> values = new LinkedHashMap<>();

    public void put(String name, T value) {
        values.put(name, value);
    }

    public T get(String name) {
        return values.get(name);
    }

    public List<T> all() {
        return List.copyOf(values.values());
    }

    public List<String> names() {
        return List.copyOf(values.keySet());
    }
}
