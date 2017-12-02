package com.sergeev.itroi.service;

import com.sergeev.itroi.config.Property;
import com.sergeev.itroi.config.RepositoryConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;

@ApplicationScoped
public class TestRepositoryConfig extends RepositoryConfig {

    @Produces
    @Specializes
    @Property("path")
    public String getPathToStorage() {
        return "out/test";
    }
}
