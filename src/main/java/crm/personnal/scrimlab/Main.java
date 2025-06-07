package crm.personnal.scrimlab;

import crm.personnal.scrimlab.config.utils.JwtUtil;

public class Main {
    public static void main(String[] args) {
        JwtUtil util = new JwtUtil();
        util.jwtSecret = "M1JPKw++/dyUy++9SaLEB3o0Ud1+tthYHD3kafMk2Q+C7krTBUtZxyN1YwmXnaPY";
        util.init();

        String token = util.generateToken("leo@example.com");
        System.out.println("TOKEN : " + token);

        String username = util.extractUsername(token);
        System.out.println("USERNAME : " + username);
    }
}
