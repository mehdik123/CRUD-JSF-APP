package com.example.demo13;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOimpl implements EmployeeDAO {
    private  final Db db;

    public EmployeeDAOimpl() {
        db = new Db();
    }

    public void addEmployee(Employee employee) {
        try (Connection connection = db.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM personne");
             PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO personne (name, email) VALUES (?, ?)")) {

            // Récupérer tous les enregistrements de la table personne
            ResultSet resultSet = selectStatement.executeQuery();

            boolean emailExists = false;
            while (resultSet.next()) {
                // Comparer l'email de chaque enregistrement avec l'email de l'employé
                if (employee.getEmail().equalsIgnoreCase(resultSet.getString("email"))) {
                    emailExists = true;
                    break; // L'email existe déjà, arrêter la boucle
                }
            }

            if (emailExists) {
                // L'email existe déjà, gérer cette situation
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "L'adresse email est déjà utilisée."));
            } else {
                // Si l'email n'existe pas, procéder à l'insertion
                insertStatement.setString(1, employee.getName());
                insertStatement.setString(2, employee.getEmail());
                insertStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions SQL
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur est survenue lors de l'ajout de l'employé."));
        }
    }


    public void deleteEmployee(Employee employee) {
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM personne WHERE id = ?")) {
            statement.setLong(1, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        System.out.println(employee);
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE personne SET name = ?, email = ? WHERE id = ?")) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setLong(3, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM personne");
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


}

