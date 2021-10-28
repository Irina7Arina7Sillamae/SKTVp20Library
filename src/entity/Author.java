
package entity;

import java.io.Serializable;


public class Author implements Serializable{
    private String firstname;
    private String lastname;

    public Author() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return  firstname  + lastname  ;
    }
    
    
}