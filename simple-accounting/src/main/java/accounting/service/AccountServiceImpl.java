package accounting.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import accounting.dao.AccountRepository;
import accounting.dto.AccountDTO;
import accounting.model.Account;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository repository;
	private final ModelMapper modelMapper;

	@Override
	public AccountDTO createAccount(AccountDTO accountDTO) {
		Account account = modelMapper.map(accountDTO, Account.class);
		Account savedAccount = repository.save(account);
		return modelMapper.map(savedAccount, AccountDTO.class);
	}

	@Override
	public AccountDTO getAccountById(String id) {
		Account account = repository.findById(id).orElse(null);
		return modelMapper.map(account, AccountDTO.class);
	}

	@Override
	public AccountDTO getAccountByNumber(String accountNumber) {
		Account account = repository.findByAccountNumber(accountNumber);
		return modelMapper.map(account, AccountDTO.class);
	}

	@Override
	public List<AccountDTO> getAllAccounts() {
		List<Account> accounts = repository.findAll();
		return accounts.stream().map(account -> modelMapper.map(account, AccountDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public AccountDTO updateAccount(String id, AccountDTO accountDTO) {
		Account existingAccount = repository.findById(id).orElse(null);
		if (existingAccount != null) {
			existingAccount.setAccountNumber(accountDTO.getAccountNumber());
			existingAccount.setAccountHolder(accountDTO.getAccoundHolder());
			existingAccount.setBalance(accountDTO.getBalance());
			Account updatedAccount = repository.save(existingAccount);
			return modelMapper.map(updatedAccount, AccountDTO.class);
		}
		return null;
	}

	@Override
	public void deleteAccount(String id) {
		repository.deleteById(id);

	}

	@Override
	public AccountDTO depositAmount(String id, double amount) {
		Account account = repository.findById(id).orElse(null);
		if (account != null) {
			account.setBalance(account.getBalance() + amount);
			Account updatedAccount = repository.save(account);
			return modelMapper.map(updatedAccount, AccountDTO.class);
		}
		return null;
	}

	@Override
	public AccountDTO withdrawAmount(String id, double amount) {
		Account account = repository.findById(id).orElse(null);
		if (account != null) {
			double balanceAfterWithdrawal = account.getBalance() - amount;
			if (balanceAfterWithdrawal >= 0) {
				account.setBalance(balanceAfterWithdrawal);
				Account updatedAccount = repository.save(account);
				return modelMapper.map(updatedAccount, AccountDTO.class);
			}
		}
		return null;
	}

	@Override
	public List<AccountDTO> getAccountByAmountRange(double minRange, double maxRange) {
		List<Account> accounts = repository.findByBalanceBetween(minRange, maxRange);
		return accounts.stream()
				.map(account -> modelMapper.map(accounts, AccountDTO.class))
				.collect(Collectors.toList());
	}

}
