package capstone.project.Demo_Project.repositories;

import capstone.project.Demo_Project.domain.filter.AccountFilterRequest;
import capstone.project.Demo_Project.domain.response.PaginationResponse;

public interface AccountRepositoryCustom {
    PaginationResponse getAccounts(AccountFilterRequest accountFilterRequest);
}
