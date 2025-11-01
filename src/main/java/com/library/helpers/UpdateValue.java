package com.library.helpers;

import java.util.function.Consumer;

public class UpdateValue<T> {
    public static <T> void updateIfNotNull(Consumer<T> setter, T newValue) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }
}
