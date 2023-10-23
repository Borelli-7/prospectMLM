package dev.kaly7dev.prospectmlm;

import dev.kaly7dev.prospectmlm.notifications.Notif;
import dev.kaly7dev.prospectmlm.notifications.NotifRepo;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@SpringBootApplication
@EnableR2dbcRepositories
public class ProspectMlmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProspectMlmApplication.class, args);
	}

/*	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("init.sql")));

		return initializer;
	}
	@Bean
	CommandLineRunner initDataBase(NotifRepo notifRepo){
		return args -> {
			var notif1= new Notif();
			notif1.setTitle("notif 1");
			notif1.setMessage("message 1");
			notifRepo.save(notif1);
		};
	}*/


}
