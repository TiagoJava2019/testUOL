package com.example.demo.dao;

/**
 * 
 * @author t.almeida
 *
 */
import java.util.List;

import com.example.demo.domain.Client;

public interface IClientDao {

	public void save(Client department);

	public void update(Client department);

	public void delete(Long id);

	public Client findById(Long id);

	public List<Client> listAll();
}