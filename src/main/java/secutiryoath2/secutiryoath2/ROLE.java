package secutiryoath2.secutiryoath2;

public enum ROLE {
    //
    ADMIN("admin"), USER("user"), VIP("vip"), DBA("dba");

    private String name;

    public String getName() {
        return name;
    }

    private ROLE(String name) {
        this.name = name;
    }
}
