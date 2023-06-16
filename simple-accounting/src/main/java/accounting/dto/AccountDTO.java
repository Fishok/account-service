package accounting.dto;

import lombok.Data;

@Data
public class AccountDTO {
	private String id;
	private String accountNumber;
	private String accoundHolder;
	private double balance;
}
