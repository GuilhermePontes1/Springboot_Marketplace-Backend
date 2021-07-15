package com.guilherme.SpringBoot_Marketplace;

import com.guilherme.SpringBoot_Marketplace.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot_Marketplace implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot_Marketplace.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	s3Service.uploadFile("C:\\Users\\Guilh\\Desktop\\Arquivos Pessoais\\Guilherme.png");

	}
}
