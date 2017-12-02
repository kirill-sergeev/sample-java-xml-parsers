package com.sergeev.itroi.repository.xml;

import com.sergeev.itroi.config.Property;
import com.sergeev.itroi.entity.Ticket;
import com.sergeev.itroi.parser.XmlMarshaller;
import com.sergeev.itroi.parser.XmlUnmarshaller;
import com.sergeev.itroi.repository.TicketRepository;
import com.sergeev.itroi.util.wrapper.TicketArray;
import com.sergeev.itroi.util.wrapper.Wrapper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Dependent
public class TicketXmlRepository extends AbstractXmlRepository<Ticket, Integer> implements TicketRepository {

    private static final String FILE = "tickets.xml";

    private AtomicInteger nextId;
    private String pathToStorage;

    @Inject
    public TicketXmlRepository(XmlMarshaller<Wrapper<Ticket>> marshaller,
                               XmlUnmarshaller<Wrapper<Ticket>> unmarshaller,
                               @Property("path") String pathToStorage) {
        super(marshaller, unmarshaller, pathToStorage);
        this.pathToStorage = pathToStorage;
    }

    @Override
    protected Integer getNextId() {
        if (nextId == null) {
            List<Ticket> storedEntities = getAll();
            storedEntities.sort(Comparator.comparing(Ticket::getId));
            int currentId = storedEntities.isEmpty() ? 0 : storedEntities.get(storedEntities.size() - 1).getId();
            nextId = new AtomicInteger(currentId);
        }
        return nextId.incrementAndGet();
    }

    @Override
    protected Wrapper<Ticket> emptyWrapper() {
        return new TicketArray();
    }

    @Override
    protected String getPathToFile() {
        return Paths.get(pathToStorage, FILE).toString();
    }
}
