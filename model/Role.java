package model;

public class Role {
    private int roleId;
    private String roleName;

    // Constructor
    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    // Getters and Setters
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Role [RoleID=" + roleId + ", RoleName=" + roleName + "]";
    }
}

