package accounting.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import accounting.dto.AccountDTO;
import accounting.service.AccountService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountService accountService;

	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
		AccountDTO createdAccount = accountService.createAccount(accountDTO);
		return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable String id) {
		AccountDTO account = accountService.getAccountById(id);
		if (account != null) {
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/number/{accountNumber}")
	public ResponseEntity<AccountDTO> getAccountByNumber(@PathVariable String accountNumber) {
		AccountDTO account = accountService.getAccountByNumber(accountNumber);
		if (account != null) {
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<AccountDTO>> getAllAccounts() {
		List<AccountDTO> accounts = accountService.getAllAccounts();
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AccountDTO> updateAccount(@PathVariable String id, @RequestBody AccountDTO accountDTO) {
		AccountDTO updatedAccount = accountService.updateAccount(id, accountDTO);
		if (updatedAccount != null) {
			return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
		accountService.deleteAccount(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{id}/deposit/{amount}")
	public ResponseEntity<AccountDTO> depositAmount(@PathVariable String id, @PathVariable double amount) {
		AccountDTO account = accountService.withdrawAmount(id, amount);
		if (account != null) {
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping("/{id}/withdraw/{amount}")
	public ResponseEntity<AccountDTO> withdrawAmount(@PathVariable String id, @PathVariable double amount) {
		AccountDTO account = accountService.withdrawAmount(id, amount);
		if (account != null) {
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/balance")
	public ResponseEntity<List<AccountDTO>> getAccountsByBalanceRAnge(@RequestParam("minBalance") double minBalance,
			@RequestParam("maxBalance") double maxBalance) {
		List<AccountDTO> accounts = accountService.getAccountByAmountRange(minBalance, maxBalance);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

}
