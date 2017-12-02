package com.sergeev.itroi.entity;

public interface Codifiable<T extends Comparable<T>> extends Identifiable<T> {

    T getCode();

    void setCode(T code);

    default T getId() {
        return getCode();
    }

    default void setId(T id) {
        setCode(id);
    }
}
