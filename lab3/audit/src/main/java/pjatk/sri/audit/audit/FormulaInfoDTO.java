package pjatk.sri.audit.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormulaInfoDTO implements Serializable {

    private Long engineTemperature;
    private Long oilTemperature;
    private LocalDateTime currentTime;
}
