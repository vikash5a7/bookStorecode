package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.UpdateAddressDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.IAdressService;

@RestController
@CrossOrigin("*")
public class AddressController {
	@Autowired
	private IAdressService addressService;

	@PostMapping("/address/add")
	public ResponseEntity<Response> addAddress(@RequestBody AddressDto address, @RequestHeader String token)
			throws Exception {
		System.out.println("adress is" + address);
		Address addres = addressService.addAddress(address, token);

		if (addres != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, addres));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("not added", 400, addres));

	}

	@PutMapping("/address/updateAddress")
	public ResponseEntity<Response> updateAddress(@RequestHeader String token, @RequestBody UpdateAddressDto address) {

		Address addres = addressService.updateAddress(address, token);
		if (addres != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, addres));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, addres));

	}

	@DeleteMapping("/address/delete")
	public ResponseEntity<Response> deleteAddress(@RequestParam Long addressId, @RequestHeader String token) {
		Users message = addressService.deleteAddress(token, addressId);

		if (message != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, message));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, message));
	}

	@GetMapping("/address/users")
	public ResponseEntity<Response> getAddressByUserId(@RequestHeader String token) {
		List<Address> result = addressService.getAddressByUserId(token);
		System.out.println("-----------result" + result);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, result));
		}
		return null;
	}

}
