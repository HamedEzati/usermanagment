package ir.hamed.firstproject.model;

public class UserStatus {
    private String message;
    private boolean state;

    public UserStatus() {
    }

    public UserStatus(String message, boolean state) {
        this.message = message;
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
