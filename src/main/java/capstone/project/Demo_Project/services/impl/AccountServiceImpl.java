package capstone.project.Demo_Project.services.impl;

import capstone.project.Demo_Project.domain.entities.Account;
import capstone.project.Demo_Project.domain.request.AccountRequestDto;
import capstone.project.Demo_Project.domain.response.AccountResponseDto;
import capstone.project.Demo_Project.domain.response.PaginationResponse;
import capstone.project.Demo_Project.errorhandling.DemoException;
import capstone.project.Demo_Project.errorhandling.ErrorTypes;
import capstone.project.Demo_Project.repositories.AccountRepository;
import capstone.project.Demo_Project.services.AccountService;
import capstone.project.Demo_Project.utilities.BeanUtility;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private  static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountResponseDto findAccountByUserName(String userName) {
        Account account = accountRepository.findByUserName(userName);
        if (ObjectUtils.isEmpty(account)) {
            logger.error("Can not found account with account name: {}", userName);
            throw new DemoException(ErrorTypes.ACCOUNT_NOT_FOUND, userName);
        }
        return BeanUtility.converValue(account, AccountResponseDto.class);
    }

    @Override
    public AccountResponseDto findAccountByAccountId(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (!account.isPresent()) {
            logger.error("Can not found account with account name: {}", accountId);
            throw new DemoException(ErrorTypes.ACCOUNT_NOT_FOUND, accountId.intValue());
        }
        return BeanUtility.converValue(account.get(), AccountResponseDto.class);
    }

    @Override
    public AccountResponseDto create(AccountRequestDto request) {
        logger.info("Starting create account!");
        if (ObjectUtils.isEmpty(request)) {
            logger.error("Request must not be empty!");
            throw new DemoException(ErrorTypes.INVALID_REQUEST);

        }
        Account accountDuplicate = accountRepository.findByUserName(request.getUserName());
        if (ObjectUtils.isNotEmpty(accountDuplicate)) {
            logger.error("Account with account name: {} existing!", request.getUserName());
            throw new DemoException(ErrorTypes.ACCOUNT_WITH_NAME_ALREADY_EXISTS, request.getUserName());
        }
        Account account = BeanUtility.converValue(request, Account.class);
        Account newAccount = accountRepository.save(account);
        logger.info("Account created success!");
        return BeanUtility.converValue(newAccount, AccountResponseDto.class);
    }

    @Override
    public AccountResponseDto update(Long id, AccountRequestDto request) {
        logger.info("Starting update account!");
        if (ObjectUtils.isEmpty(request)) {
            logger.error("Request is empty with account id {}", id);
            return null;
        }
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (!existingAccount.isPresent()) {
            logger.error("Can not found account with id {}", id);
            return null;
        }
        Account account = BeanUtility.converValue(request, Account.class);
        account.setId(id);
        accountRepository.save(account);
        logger.info("Update account successfully!");
        return BeanUtility.converValue(account, AccountResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (!existingAccount.isPresent()) {
            logger.error("Can not found account with id {}", id);
            return;
        }
        accountRepository.deleteById(id);
        logger.info("Delete account successfully!");
    }

    @Override
    public PaginationResponse filter(String filter, String key, Pageable pageable) {
        Page<Account> page = null;
        switch (filter) {
            case "status":
                page = accountRepository.findAllByStatusEquals(Integer.parseInt(key), pageable);
                break;
            case "user_name":
                page = accountRepository.findAllByUserNameContains(key, pageable);
                break;
            default:
                page = accountRepository.findAll(pageable);
        }

        return PaginationResponse.builder()
                .total(page.getTotalElements())
                .numberOfPage(page.getTotalPages())
                .items(page.getContent())
                .build();
    }
}
