/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.controllers;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spring.project.server.controllertest.AbstractTest;
import spring.project.server.model.Confederation;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Veljko
 */
class ConfederationControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void getConfederations() throws Exception {
        final String uri = "/api/confederations";
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        final String content = mvcResult.getResponse().getContentAsString();
        final List<Confederation> confederations = (List) Arrays.asList(super.mapFromJson(content, Confederation[].class));
        final ConfederataionComparator confederataionComparator = new ConfederataionComparator();
        Collections.sort(confederations, confederataionComparator);

        final List<Confederation> testConfederations = createAllConfederations();
        Collections.sort(testConfederations, confederataionComparator);
        Assertions.assertEquals(confederations, testConfederations);
    }

    private List<Confederation> createAllConfederations() {
        final List<Confederation> confederations = new LinkedList<>();

        final Confederation confederationEurope = new Confederation();
        confederationEurope.setId(1);
        confederationEurope.setName("EUROPE");
        confederationEurope.setStrength(1);
        confederations.add(confederationEurope);

        final Confederation confederationSouthAmerica =
                new Confederation();
        confederationSouthAmerica.setId(2);
        confederationSouthAmerica.setName("SOUTHAMERICA");
        confederationSouthAmerica.setStrength(1);
        confederations.add(confederationSouthAmerica);

        final Confederation confederationAfrica = new Confederation();
        confederationAfrica.setId(3);
        confederationAfrica.setName("AFRICA");
        confederationAfrica.setStrength(0.86);
        confederations.add(confederationAfrica);

        final Confederation confederationAsia = new Confederation();
        confederationAsia.setId(4);
        confederationAsia.setName("ASIA");
        confederationAsia.setStrength(0.86);
        confederations.add(confederationAsia);

        final Confederation confederationNorthAmerica = new Confederation();
        confederationNorthAmerica.setId(5);
        confederationNorthAmerica.setName("NORTHAMERICA");
        confederationNorthAmerica.setStrength(0.84);
        confederations.add(confederationNorthAmerica);

        final Confederation confederationOceania = new Confederation();
        confederationOceania.setId(6);
        confederationOceania.setName("OCEANIA");
        confederationOceania.setStrength(0.84);
        confederations.add(confederationOceania);

        return confederations;
    }

    static class ConfederataionComparator implements Comparator<Confederation> {

        @Override
        public int compare(final Confederation a, final Confederation b) {
            return a.getName().compareTo(b.getName());
        }
    }

}
