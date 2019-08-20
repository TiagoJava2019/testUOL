package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.Client;
import com.example.demo.service.ClientService;

/**
 * 
 * @author t.almeida
 *
 */
@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	/**
	 * receber o endereço do web service
	 * 
	 * @param request
	 * @return
	 */
	private ResponseEntity<String> getAddress(HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		String ipAddress = "";
		String url;

		if (request.getHeader("X-FORWARDED-FOR") == null) {
			ipAddress = request.getRemoteAddr();
		}

		if (ipAddress.equals("0:0:0:0:0:0:0:1")) {
			url = "https://ipvigilante.com/json/177.140.234.239";
		} else {
			url = "https://ipvigilante.com/json/" + ipAddress;
		}

		return restTemplate.getForEntity(url, String.class);
	}

	/**
	 * Listar todos os clientes
	 * 
	 * @return
	 */
	@GetMapping("/listAll")
	@ResponseBody
	public List<Client> listAl() {
		List<Client> clients = clientService.listAll();

		return clients;
	}

	/**
	 * listar clientes por id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/list/{id}")
	@ResponseBody
	public Client list(@PathVariable("id") Long id) {
		return clientService.findById(id);
	}

	/**
	 * cria registros de clientes
	 * 
	 * @param name
	 * @param age
	 * @param request
	 * @return
	 */
	@PostMapping("/create")
	@ResponseBody
	public String create(@RequestParam("name") String name, @RequestParam("age") int age, HttpServletRequest request) {
		Client client = new Client();
		ResponseEntity<String> address = getAddress(request);

		client.setName(name);
		client.setAge(age);
		client.setAddress(address.getBody());

		clientService.save(client);

		return "Registro Salvo com sucesso!";
	}

	/**
	 * atualiza registros de clientes por id
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @return
	 */
	@PostMapping("/update/{id}")
	@ResponseBody
	public String update(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("age") int age) {
		Client client = clientService.findById(id);

		if (null != client) {
			client.setName(name);
			client.setAge(age);

			clientService.update(client);
			return "Cliente atualizado com sucesso!";
		} else {
			return "Cliente não encontrado! O Update não foi efetuado...";
		}
	}

	/**
	 * deleta registros de clientes
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {
		Client client = clientService.findById(id);

		if (null != client) {
			clientService.delete(id);
			return "Cliente deletado com sucesso!";
		} else {
			return "Cliente não encontrado! O Delete não foi efetuado...";
		}
	}
}