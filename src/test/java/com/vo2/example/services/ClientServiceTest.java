package com.vo2.example.services;

import com.vo2.example.model.Address;
import com.vo2.example.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by VO2 on 04/04/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:config/Context.xml")
@Transactional
@Rollback(false)
public class ClientServiceTest {

    @Autowired
    private IClientService clientService;

    @Test
    public void getClientByIdShouldReturnExistentClient() throws Exception {
        //GIVEN : un id d'un client déjà dans la DB
        Long idClient = 1L;

        //WHEN : on charge par service le client
        System.out.println("\n\n++++++++++++++++ begin getClientById ");
        Client vo2 = clientService.getClientById(idClient);

        System.out.println("\n\n++++++++++++++++ (switch between eager and lazy) related persons : " + vo2.getPersons());
        System.out.println("\n\n++++++++++++++++ end getClientById ");

        //THEN : on vérifie que c'est le bon client
        assertThat(vo2, is(notNullValue()));
        assertEquals(vo2.getId(), idClient);
        assertThat(vo2.getName(), equalTo("VO2 Group"));
        System.out.println("\n\n++++++++++++++++ vo2 : " + vo2);

        //et la relation address ..
        Address address = vo2.getAddress();
        assertThat(address, is(notNullValue()));
        assertThat(address.getId(), equalTo(1L));
        assertThat(address.getCity(), equalTo("Paris"));
        System.out.println("\n\n++++++++++++++++ address : " + address);

        //on peut naviguer à travers les entités..
        assertThat(address.getCountry(), is(notNullValue()));
        assertThat(address.getCountry().getName(), equalTo("France"));
        System.out.println("\n\n++++++++++++++++ country : " + address.getCountry());
    }

}