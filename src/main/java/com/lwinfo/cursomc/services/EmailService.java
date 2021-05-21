package com.lwinfo.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.lwinfo.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
