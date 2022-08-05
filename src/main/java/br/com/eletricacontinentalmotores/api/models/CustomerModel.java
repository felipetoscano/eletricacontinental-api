package br.com.eletricacontinentalmotores.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class CustomerModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 40, message = "O primeiro nome deve ter menos de 40 caracteres.")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(max = 80, message = "O último nome deve ter menos de 80 caracteres.")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Size(min = 11, max = 11, message = "O celular deve ter 11 caracteres.")
    @Column(name = "cellphone")
    private String cellphone;

    @Size(max = 100, message = "O e-mail deve ter menos de 100 caracteres.")
    @Column(name = "email")
    private String email;

    public CustomerModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
