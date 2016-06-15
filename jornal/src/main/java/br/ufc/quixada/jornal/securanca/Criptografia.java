package br.ufc.quixada.jornal.securanca;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class Criptografia {

	public String criptografarSenha(String msg) {
		return new Base64().encodeToString(msg.getBytes());
	}
}