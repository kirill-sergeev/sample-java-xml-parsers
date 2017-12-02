package com.sergeev.itroi.service;

import com.sergeev.itroi.MockTicketData;
import com.sergeev.itroi.entity.Ticket;
import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class TicketServiceBeanIT {

    private static final String TEST_FOLDER_PATH = "out/test";
    private static final String BASE_PACKAGE = "com.sergeev.itroi";
    private static final String CLIENT_PACKAGE = "com.sergeev.itroi.client";

    private static List<Ticket> tickets = MockTicketData.ticketArrayInstance().getItems();
    private static Ticket ticket = tickets.get(0);
    @EJB
    private TicketServiceBean service;

    @BeforeClass
    @AfterClass
    public static void cleanDir() throws IOException {
        FileUtils.forceMkdir(new File(TEST_FOLDER_PATH));
        FileUtils.cleanDirectory(new File(TEST_FOLDER_PATH));
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap
            .create(JavaArchive.class)
            .addPackages(true, BASE_PACKAGE)
            .deletePackages(true, CLIENT_PACKAGE);
    }

    @Test
    @InSequence(1)
    public void shouldSaveAndLoadOneObject() throws Exception{
        service.save(ticket);

        assertEquals(ticket, service.getOne(ticket.getId()));
    }

    @Test
    @InSequence(2)
    public void shouldRemove() {
        assertTrue(service.getAll().contains(ticket));

        service.remove(ticket.getId());

        assertFalse(service.getAll().contains(ticket));
    }

    @Test
    @InSequence(3)
    public void shouldSaveAndLoadManyObjects() {
        assertTrue(service.getAll().isEmpty());

        service.saveAll(tickets);

        assertEquals(tickets, service.getAll());
    }
}
