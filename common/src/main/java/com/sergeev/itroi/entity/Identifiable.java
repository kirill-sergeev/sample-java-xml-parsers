package com.sergeev.itroi.entity;

public interface Identifiable<T extends Comparable<T>> extends Comparable<Identifiable<T>> {

    T getId();

    void setId(T id);

    @Override
    default int compareTo(Identifiable<T> o) {
        return getId().compareTo(o.getId());
    }
}
