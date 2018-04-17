package vn.com.codedao.facecook.model.login;

/**
 * Created by Bruce Wayne on 13/04/2018.
 */

public class Mlogin {
    private String status;
    private String id;
    private String token;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
