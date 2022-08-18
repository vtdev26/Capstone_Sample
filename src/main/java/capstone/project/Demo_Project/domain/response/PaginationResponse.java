package capstone.project.Demo_Project.domain.response;

import capstone.project.Demo_Project.domain.entities.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@Builder
public class PaginationResponse {

    @JsonProperty("total")
    private long total;

    @JsonProperty("numberOfPage")
    private int numberOfPage;

    @JsonProperty("items")
    private List<Account> items;
}
