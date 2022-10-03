package org.cnss;

public class AgentManagement {
    private String email;
    private String password;
    private boolean verified;


    //    *************************************************************** constructors *********************
    public AgentManagement(){}
    public AgentManagement(String email, String password, boolean verified) {
        this.email = email;
        this.password = password;
        this.verified = verified;
    }


    //    *************************************************************** getters *********************
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVerified() {
        return verified;
    }


    //    *************************************************************** setters *********************
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
