package com.sergeev.itroi.repository.xml;

import com.sergeev.itroi.entity.Identifiable;
import com.sergeev.itroi.parser.XmlMarshaller;
import com.sergeev.itroi.parser.XmlUnmarshaller;
import com.sergeev.itroi.repository.CrudRepository;
import com.sergeev.itroi.repository.PersistenceException;
import com.sergeev.itroi.util.wrapper.Wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractXmlRepository<T extends Identifiable<I>, I extends Comparable<I>> implements CrudRepository<T, I> {

    private static final String ENCODING = "UTF-8";

    private XmlMarshaller<Wrapper<T>> marshaller;
    private XmlUnmarshaller<Wrapper<T>> unmarshaller;

    public AbstractXmlRepository(XmlMarshaller<Wrapper<T>> marshaller,
                                 XmlUnmarshaller<Wrapper<T>> unmarshaller,
                                 String pathToStorage) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
        prepareDirectory(pathToStorage);
    }

    @Override
    public void save(T entity) {
        saveAll(Collections.singletonList(entity));
    }

    @Override
    public void saveAll(List<T> entities) {
        Wrapper<T> wrapper = getAllInternal();
        for (T entity : entities) {
            entity.setId(getNextId());
        }
        wrapper.getItems().addAll(entities);
        write(wrapper);
    }

    @Override
    public void remove(I id) {
        Wrapper<T> wrapper = getAllInternal();
        wrapper.getItems().removeIf(it -> it.getId().equals(id));
        if (!(wrapper.getItems().isEmpty() && new File(getPathToFile()).delete())) {
            write(wrapper);
        }
    }

    @Override
    public Optional<T> getOne(I id) {
        List<T> entities = getAll();
        return entities
            .stream()
            .filter(it -> id.equals(it.getId()))
            .findFirst();
    }

    @Override
    public List<T> getAll() {
        return getAllInternal().getItems();
    }

    private Wrapper<T> getAllInternal() {
        try (Reader reader = new InputStreamReader(new FileInputStream(getPathToFile()), ENCODING)) {
            return unmarshaller.unmarshal(reader, false);
        } catch (Exception e) {
            return emptyWrapper();
        }
    }

    private void write(Wrapper<T> entities) {
        try (Writer jaxbWriter = new PrintWriter(getPathToFile(), ENCODING)) {
            marshaller.marshal(entities, jaxbWriter);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    private void prepareDirectory(String pathToStorage) {
        try {
            Path path = Paths.get(pathToStorage);
            if (!path.toFile().exists()) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    protected abstract Wrapper<T> emptyWrapper();

    protected abstract String getPathToFile();

    protected abstract I getNextId();
}
