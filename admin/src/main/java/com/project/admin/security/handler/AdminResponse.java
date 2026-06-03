package com.project.admin.security.handler;

public record AdminResponse(boolean success, int statusCode , String message) {

    public static AdminResponse ok() {
        return new AdminResponse(true,200,"Sign-in was successful.");
    }

    public static AdminResponse fail() {
        return new AdminResponse(false,400,"Invalid password");
    }

    public static AdminResponse signOut() {
        return new AdminResponse(true, 200,"Sign-out was successful.");
    }


}
