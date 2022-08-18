package capstone.project.Demo_Project.services;

import capstone.project.Demo_Project.domain.request.AccountRequestDto;
import capstone.project.Demo_Project.domain.response.AccountResponseDto;
import capstone.project.Demo_Project.domain.response.PaginationResponse;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    AccountResponseDto findAccountByUserName(String userName);

    AccountResponseDto findAccountByAccountId(Long accountId);

    AccountResponseDto create(AccountRequestDto accountRequestDto);

    AccountResponseDto update(Long id, AccountRequestDto accountRequestDto);

    void deleteById(Long id);

    PaginationResponse filter(String filter, String key, Pageable pageable);
}
