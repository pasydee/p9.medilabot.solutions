package medilabo.solutions.front.model;

import lombok.Data;

@Data
public class Patient {
    private Long id;
    private String firstname;
    private String lastname;
    private String birthdate;
    private String gender;
    private String address;
    private String phone;
}
