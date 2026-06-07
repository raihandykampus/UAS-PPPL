package org.example.users;

public class UserFactory {
    public static UserConfig getUserProfile() {
        String currentUser = EnvConfig.ACTIVE_USER.trim().toLowerCase();

        switch (currentUser.toLowerCase()) {
            case "afif":
                return new Afif();
            case "siapa":
                System.out.println("siapa profile selected (Stub)");
//                return new Siapa();
            default:
                throw new IllegalArgumentException("Unknown user profile environment: " + currentUser);
        }
    }
}