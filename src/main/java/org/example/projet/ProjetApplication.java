package org.example.projet;

import lombok.extern.slf4j.Slf4j;
import org.example.projet.entities.Category;
import org.example.projet.entities.Project;

import org.example.projet.model.Employee;
import org.example.projet.repositories.ProjectRepository;
import org.example.projet.repositories.CategoryRepository;
import org.example.projet.restClients.EmployeeRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class ProjetApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProjectRepository projectRepository,
                            CategoryRepository categoryRepository,
                            EmployeeRestClient employeeRestClient) {
        return args -> {
            log.info("Initializing database with test data...");

            // Récupération d'un employé depuis le microservice externe
            Employee employee1 = employeeRestClient.getEmployeeById(1L);
            Employee employee2 = employeeRestClient.getEmployeeById(2L);

            log.info("Employee 1: {}", employee1.getName());
            log.info("Employee 2: {}", employee2.getName());

            // Création des projets avec assignation des employés récupérés
            Project project1 = new Project();
            project1.setProjectName("Projet E-Commerce");
            project1.setEmployeeId(employee1.getId());
            project1.setEmployee(employee1);

            Project project2 = new Project();
            project2.setProjectName("Projet Gestion RH");
            project2.setEmployeeId(employee2.getId());
            project2.setEmployee(employee2);

            projectRepository.saveAll(List.of(project1, project2));

            // Création de tâches pour les projets
            Category category1 = new Category();
            category1.setCategoryName("Créer la base de données");
            category1.setProjects(List.of(project1));


            Category category2 = new Category();
            category2.setCategoryName("Développer l'interface utilisateur");
            category2.setProjects(List.of(project2));

            Category category3 = new Category();
            category3.setCategoryName("Configurer le système de paie");


            projectRepository.saveAll(List.of(project1, project2));

            log.info("Database initialized successfully!");
        };
    }
}
