package capstone.project.Demo_Project.controller;

import capstone.project.Demo_Project.domain.request.AccountRequestDto;
import capstone.project.Demo_Project.domain.response.AccountResponseDto;
import capstone.project.Demo_Project.domain.response.PaginationResponse;
import capstone.project.Demo_Project.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public AccountResponseDto getById(@Valid @PathVariable Long id) {
        return accountService.findAccountByAccountId(id);
    }

    @GetMapping("/filter/{name}")
    public AccountResponseDto getByName(@Valid @PathVariable String name) {
        return accountService.findAccountByUserName(name);
    }

    @PostMapping("")
    public AccountResponseDto create(@RequestBody AccountRequestDto accountRequestDto) {
        return accountService.create(accountRequestDto);
    }

    @PutMapping("/{id}")
    public AccountResponseDto update(@PathVariable Long id, @Valid @RequestBody AccountRequestDto accountRequestDto) {
        return accountService.update(id, accountRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
    }

    @GetMapping("")
    public PaginationResponse filter(@RequestParam("filterBy") String filter, @RequestParam("key") String key,
                                     Pageable pageable){
        return accountService.filter(filter, key, pageable);
    }
}
