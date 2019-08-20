package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Client;

/**
 * 
 * @author t.almeida
 *
 */
@Repository
public class ClientDao extends AbstractDao<Client, Long> implements IClientDao {

}