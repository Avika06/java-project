package javafiles.services;

import java.security.SecureRandom;

public class OTPService {

    public static int generateOTP() {
        SecureRandom random = new SecureRandom();
        return 100000 + random.nextInt(900000);
    }
}
