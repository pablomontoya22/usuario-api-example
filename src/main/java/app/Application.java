package app;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Client;
import app.entities.Concessionaire;
import app.entities.State;
import app.repositories.ClientRepository;
import app.repositories.ConcessionaireRepository;
import app.repositories.StateRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ConcessionaireRepository concessionaireRepository;

	@Override
	protected SpringApplicationBuilder
		configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void stating() {
    	System.out.
    	println("***************** Iniciando SPRING BOOT APP *****************");
    }

    @Transactional
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	String[] states = new String[] {"Amazonas", "Anzoátegui", "Apure", "Aragua",
			"Barinas", "Bolívar", "Carabobo", "Cojedes", "Delta Amacuro",
			"Distrito Capital", "Falcón", "Guárico", "Lara", "Mérida", "Miranda",
			"Monagas", "Nueva Esparta", "Portuguesa", "Sucre", "Táchira", "Trujillo",
			"Vargas", "Yaracuy", "Zulia", "Dependencias Federales"};
    	String [][] defaultClients = new String [][] {
    		{"Pedro", "Perez", "V-1000", "pedro@automovil.com"},
    		{"Maria", "Lopez", "V-2000", "maria@automovil.com"},
    		{"Juan", "Gomez", "V-3000", "juan@automovil.com"},
    		{"Jose", "Jimenez", "V-4000", "jose@automovil.com"} };
    	State caracas = null, maracay = null, teques = null;
    	Concessionaire concessionaire;
    	int i = 0;
    	for (String name : states)
    		switch (name) {
	    		case "Distrito Capital":
	    			caracas = stateRepository.save(new State(name));
	    			break;
	    		case "Aragua":
	    			maracay = stateRepository.save(new State(name));
	    			break;
	    		case "Miranda":
	    			teques = stateRepository.save(new State(name));
	    			break;
	    		default:
	    			stateRepository.save(new State(name));
    		}
    	for (State state : new State[]{caracas, maracay, teques}) {
    		concessionaire = new Concessionaire();
    		concessionaire.setAddress("Centro");
    		concessionaire.setState(state);
    		concessionaire.setClients(new ArrayList<Client>(0));
    		concessionaire = concessionaireRepository.save(concessionaire);
    		switch (concessionaire.getState().getName()) {
	    		case "Distrito Capital":
	    			concessionaire.getClients().add(clientRepository.save(
	    					new Client(defaultClients[i][0], defaultClients[i][1],
	    	    					defaultClients[i][2], defaultClients[i++][3],
	    	    					concessionaire)));
	    			concessionaire.getClients().add(clientRepository.save(
	    					new Client(defaultClients[i][0], defaultClients[i][1],
	    	    					defaultClients[i][2], defaultClients[i++][3],
	    	    					concessionaire)));
	    			break;
	    		case "Aragua":
	    			concessionaire.getClients().add(clientRepository.save(
	    					new Client(defaultClients[i][0], defaultClients[i][1],
	    							defaultClients[i][2], defaultClients[i++][3],
	    							concessionaire)));
	    			break;
	    		case "Miranda":
	    			concessionaire.getClients().add(clientRepository.save(
	    					new Client(defaultClients[i][0], defaultClients[i][1],
	    	    					defaultClients[i][2], defaultClients[i++][3],
	    	    					concessionaire)));
			}
    		concessionaireRepository.save(concessionaire);
    	}
        System.out.
    	println("***************** Corriendo SPRING BOOT APP *****************");
    }
}
