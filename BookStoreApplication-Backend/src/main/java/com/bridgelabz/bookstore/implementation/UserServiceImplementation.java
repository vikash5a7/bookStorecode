/**
 * 
 */
package com.bridgelabz.bookstore.implementation;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.repository.IUserRepository;
import com.bridgelabz.bookstore.request.LoginInformation;
import com.bridgelabz.bookstore.request.PasswordUpdate;
import com.bridgelabz.bookstore.response.EmailData;
import com.bridgelabz.bookstore.response.MailResponse;
import com.bridgelabz.bookstore.service.UserServices;
import com.bridgelabz.bookstore.util.EmailProviderService;
import com.bridgelabz.bookstore.util.JwtGenerator;
import com.bridgelabz.bookstore.util.MailServiceProvider;

//import com.bridgelabz.bookstore.util.RabbitMQSender;




@Service
public class UserServiceImplementation implements UserServices {
	private Users users = new Users();
	@Autowired
	private IUserRepository repository;

	@Autowired
	private BCryptPasswordEncoder encryption;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JwtGenerator generate;

	@Autowired
	private MailResponse response;
	
	@Autowired
	private EmailProviderService em;
	@Autowired
	private EmailData emailData;

	
	@Override
	@Transactional
	public boolean register(UserDto information) {
		Users user = repository.getUser(information.getEmail());
		if (user == null) {
			users = modelMapper.map(information, Users.class);
			users.setCreatedDate(LocalDateTime.now());
			String epassword = encryption.encode(information.getPassword());
			// setting the some extra information and encrypting the password
			users.setPassword(epassword);
			System.out.println("password is" + epassword);
			users.setVerified(false);
			// calling the save method
			users = repository.save(users);
			String mailResponse = 
					"http://localhost:8080/user/verify/"+
					generate.jwtToken(users.getUserId());
			// setting the data to mail

//			mailObject.setEmail(information.getEmail());
//			mailObject.setMessage(mailResponse);
//			mailObject.setSubject("Verification");
//			rabbitMQSender.send(mailObject);
					emailData.setEmail(users.getEmail());
					emailData.setSubject("your Registration is successful");
					emailData.setBody(mailResponse);
					em.sendMail(emailData.getEmail(), emailData.getSubject(), emailData.getBody());
			System.out.println(mailResponse);
			return true;
		} else {
			throw new UserException("user already exist with the same mail id");
		}
	}

	@Override
	public Users login(LoginInformation information) {
		Users user = repository.getUser(information.getEmail());
		if (user != null) {
			String userRole = information.getRole();
			String fetchRole = user.getRole();
			if (fetchRole.equals(userRole)) {
				Users userInfo = verifyPassword(user, information);
				return userInfo;
			} else if (fetchRole.equals(userRole)) {
				Users userInfo = verifyPassword(user, information);
				return userInfo;
			} else if (fetchRole.equals(userRole)) {
				Users userInfo = verifyPassword(user, information);
				return userInfo;
			} else {
				throw new UserException("Your are not Authorized person");
			}
		} else {
			throw new UserException("User Not present enter valid your email id");
		}

	}

	/**
	 * This is validate the token based on there role and 
	 * @param role
	 * @param token
	 * @return
	 */
	public boolean isValidToken(String role, String token) {
		long id;
		try {
			id = (long) generate.parseJWT(token);
			Users information = repository.getUserById(id);
			String userRole = information.getRole();
			System.out.println("actual Role is " + userRole);
			System.out.println("expected role is" + role);
			String fetchRole = role;
			if (fetchRole.equals("admin")) {
				return true;
			} else if (fetchRole.equals("seller") && !userRole.equals("admin")) {
				return true;
			} else if (fetchRole.equals(userRole)) {
				return true;
			} else {
				throw new UserException("Your are not Authorized person");
			}
		} catch (Exception e) {
			throw new UserException("user is not present");
		}
	}

	public Users verifyPassword(Users user, LoginInformation information) {
		if ((user.isVerified() == true)) {
			if (encryption.matches(information.getPassword(), user.getPassword())) {
				System.out.println(generate.jwtToken(user.getUserId()));
				return user;
			} else {
				throw new UserException("Invalid password");
			}
		} else {
			String mailResponse = response.formMessage("http://localhost:8080/user/verify",
					generate.jwtToken(user.getUserId()));
			MailServiceProvider.sendEmail(information.getEmail(), "verification", mailResponse);
			return user;
		}
	}

	/**
	 * Verifying the user based on there token
	 * 
	 * @param id
	 * @return generated token
	 */

	@Transactional
	@Override
	public boolean verify(String token) throws Exception {
		Long id = (long) generate.parseJWT(token);
		System.out.println("User id: " + id);
		repository.verify(id);
		return true;
	}

	/**
	 * checking the user is present or or not if present then it's will send a email
	 * to verify
	 *
	 * @param email
	 * @return boolean value
	 */
	@Override
	public boolean isUserExist(String email) {
		try {
			Users user = repository.getUser(email);
			if (user.isVerified() == true) {
				String mailResponse = response.formMessage("http://localhost:4200/update-password",
						generate.jwtToken(user.getUserId()));
				System.out.println(mailResponse);
				MailServiceProvider.sendEmail(user.getEmail(), "Reset Your Password", mailResponse);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new UserException("User doesn't exist");
		}
	}

	@Transactional
	@Override
	public boolean update(PasswordUpdate information, String token) {
		System.out.println("User information" + information.toString());
		if (information.getNewPassword().equals(information.getConfirmPassword())) {
			Long id = null;
			try {
				id = (long) generate.parseJWT(token);
				System.out.println("User id " + id);
				Users UpdateUser = repository.getUser(information.getEmail());
				System.out.println("updated user info" + UpdateUser);
				if (id == UpdateUser.getUserId()) {
					String epassword = encryption.encode(information.getConfirmPassword());
					information.setConfirmPassword(epassword);

					return repository.upDate(information, id);
				} else {
					throw new UserException("Please Enter valid Email ");
				}
			} catch (Exception e) {
				throw new UserException("invalid credentials");
			}
		} else {
			System.out.println("Password Not match");
			throw new UserException("invalid password");
		}
	}

	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * by this we can get the single user
	 *
	 * @param it's taking the token
	 * @return returning the single user
	 */
	@Transactional
	@Override
	public Users getSingleUser(String token) {
		Long id;
		try {
			id = (long) generate.parseJWT(token);
		} catch (Exception e) {
			throw new UserException("User doesn't exist");
		}
		
		if(isValidToken("admin", token)) {
		Users user = repository.getUserById(id);
		return user;
		}else {
			throw new UserException("token is not valid");
		}
	}

}
