package com.vo2.example.services;

import com.vo2.example.model.Client;

import java.util.List;

/**
 * Created by VO2 on 04/04/2017.
 */
public interface IClientService {
    Client getClientById(Long id);
    List<Client> findAll();
}
