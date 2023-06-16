package accounting.service;

import java.util.List;

import accounting.dto.AccountDTO;

public interface AccountService {

	AccountDTO createAccount(AccountDTO accountDTO);

	AccountDTO getAccountById(String id);

	AccountDTO getAccountByNumber(String accountNumber);

	List<AccountDTO> getAllAccounts();

	AccountDTO updateAccount(String id, AccountDTO accountDTO);
	
	AccountDTO depositAmount(String id, double amount);
	
	AccountDTO withdrawAmount(String id, double amount);

	List<AccountDTO> getAccountByAmountRange(double minRange, double maxRange);
	
	void deleteAccount(String id);
}
