package br.com.controleVendas.vendas.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author Cloves José
 *
 */
public class PasswordUtils {
	
	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
	
	public PasswordUtils() {}
	
	public static String gerarBCrypt(String senha) {
		if(senha == null) {
			return senha;
		} else {
			log.info("Gerando hash com o BCrypt");
			BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
			return bCryptEncoder.encode(senha);
		}
	}
}
