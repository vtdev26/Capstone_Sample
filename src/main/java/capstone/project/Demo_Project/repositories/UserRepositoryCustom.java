package capstone.project.Demo_Project.repositories;

import capstone.project.Demo_Project.domain.filter.UserFilterRequest;
import capstone.project.Demo_Project.domain.response.PaginationResponse;

public interface UserRepositoryCustom {
    PaginationResponse getUsers(UserFilterRequest userFilterRequest);
}
