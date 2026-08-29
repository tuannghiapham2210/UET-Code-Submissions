//problem B: this class has to do many things at once, this violates the single responsibility principle

//original code: 
// class UserService {
//     public User findById(int id) { ... }
//     public void sendWelcomeEmail(User user) { ... }
//     public void sendPasswordResetEmail(User user) { ... }
//     public void renderUserProfile(User user) { ... }
//     public String exportUserToCsv(User user) { ... }
// }


//refactored: use the extract class technique, splitted responsibilities into specialized classes
public class UserRepository {
    public User findById(int id){
        return new User(id);
    }
}

class UserNotificationService {
    public void sendWelcomeEmail(User user){
        //logic here
    }

    public void sendPasswordResetEmail(User user){
        //logic here
    }
}

class UserProfileRenderer {
    public void renderUserProfile(User user){
        //logic here
    }
}

class UserDataExporter {
    public String exportUserToCsv(User user){
        return "csv_data";
    }
}


class User {
    //attribute
    private int id;
    
    //constructor
    public User(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}