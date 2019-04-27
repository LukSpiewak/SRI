package pjatk.sri.monitoring;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
class FormulaInfoDTO implements Serializable {

    private Long engineTemperature;
    private Long oilTemperature;
    private LocalDateTime currentTime;

    public static FormulaInfoDTO creafeFromMap(Map<String, Object> map) {
        return FormulaInfoDTO.builder()
                .engineTemperature((Long)map.get("engineTemperature"))
                .oilTemperature((Long)map.get("oilTemperature"))
                .currentTime(LocalDateTime.parse((String)map.get("currentTime")))
                .build();
    }

    public Map<String, Object> asMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("engineTemperature", engineTemperature);
        map.put("oilTemperature", oilTemperature);
        map.put("currentTime", currentTime.toString());
        return map;
    }
}
