package pjatk.sri.formula;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

class FormulaInfoDTO implements Serializable {

    private Long engineTemperature;
    private Long oilTemperature;
    private LocalDateTime currentTime;

    FormulaInfoDTO(Long engineTemperature, Long oilTemperature, LocalDateTime currentTime) {
        this.engineTemperature = engineTemperature;
        this.oilTemperature = oilTemperature;
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "{" +
                "engineTemperature=" + engineTemperature +
                ", oilTemperature=" + oilTemperature +
                ", currentTime=" + currentTime.toString() +
                "}";
    }

    public Map<String, Object> asMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("engineTemperature", engineTemperature);
        map.put("oilTemperature", oilTemperature);
        map.put("currentTime", currentTime.toString());
        return map;
    }
}
