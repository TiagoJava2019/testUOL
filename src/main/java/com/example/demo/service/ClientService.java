package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ClientDao;
import com.example.demo.domain.Client;

@Service
public class ClientService implements IClientService {

	@Autowired
	private ClientDao dao;

	@Transactional(readOnly = false)
	@Override
	public void save(Client user) {
		dao.save(user);
	}

	@Transactional(readOnly = false)
	@Override
	public void update(Client user) {
		dao.update(user);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Client findById(Long id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Client> listAll() {
		return dao.listAll();
	}
}
