
package entity;


public class Reader {
    
    private String firstName;
    private String lastName;
    private String phone;

    public Reader() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Reader{" + "firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + '}';
    }
    
   
    
    
    
}
