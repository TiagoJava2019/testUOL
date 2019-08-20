package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Client;

public interface IClientService {

	public void save(Client user);

	public void update(Client user);

	public void delete(Long id);

	public Client findById(Long id);

	public List<Client> listAll();
}