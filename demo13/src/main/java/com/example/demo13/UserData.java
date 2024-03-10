package com.example.demo13;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@ManagedBean
@SessionScoped
public class UserData implements Serializable {
    private ResourceBundle bundle;
    private String message ;
    private String language= "fr";
    private String name;
    private String email;
    private static final EmployeeDAOimpl employeeDAO = new EmployeeDAOimpl();
    private boolean showAddForm = false;

    private List<Employee> employees; // Liste des employés

    private int currentPage; // Page actuelle
    private int pageSize; // Taille de la page

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public UserData() {
        employees = employeeDAO.getAllEmployees();
        currentPage = 0;
        pageSize = 5;
    }

    // Méthode pour obtenir les employés pour la page actuelle
    public List<Employee> getCurrentPageEmployees() {
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, employees.size());
        return employees.subList(start, end);
    }


    // Méthode pour passer à la page précédente
    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
        }
    }

    // Méthode pour passer à la page suivante
    public void nextPage() {
        int maxPages = (int) Math.ceil((double) employees.size() / pageSize);
        if (currentPage < maxPages - 1) {
            currentPage++;
        }
    }

    // Autres méthodes et getters/setters...
    public boolean isShowAddForm() {
        return showAddForm;
    }

    public void setShowAddForm(boolean showAddForm) {
        this.showAddForm = showAddForm;
    }

    public String showAddForm() {
        this.showAddForm = true;
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void addEmployee() {
        Employee employee = new Employee(name, email);
        employee.setEdit(false);
        employeeDAO.addEmployee(employee);
        employees = employeeDAO.getAllEmployees();
        this.showAddForm = !showAddForm;
    }

    public void deleteEmployee(Employee e) {
        employeeDAO.deleteEmployee(e);
        employees = employeeDAO.getAllEmployees();
    }

    public void editEmployee(Employee e) {

        e.setEdit(true);
    }

    public void saveEmployee() {
        for (Employee employee : employees) {
            updateEmployee(employee);
            employee.setEdit(false);
        }
        employees = employeeDAO.getAllEmployees();
    }

    public void updateEmployee(Employee e) {
        employeeDAO.updateEmployee(e);
        employees = employeeDAO.getAllEmployees();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public void changeLang(){
        if( this.language.equals("fr") ){
            this.language = "en";
        }
        else{
            this.language = "fr";
        }
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
    public String getLanguage(){
        return language;
    }
}