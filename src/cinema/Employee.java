package cinema;
public class Employee extends User {
    public Employee(int id, String username, String password) {
        this.id = id;
        this.username=username;
        this.password=password;
        this.account_type="admin";
    }
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getAccountType() {
        return account_type;
    }

}
