package dailySummary.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailySummary {
    private String teamEmail;
    private String summary = "";
    private String category;
    private String teamName;
    private String identity;
    private String openQuestion = "";
    private String otherUpdate = "";
}
