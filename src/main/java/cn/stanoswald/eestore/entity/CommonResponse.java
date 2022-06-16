package cn.stanoswald.eestore.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class CommonResponse {

    public static class Builder {
        private HttpStatus status;
        private String message;
        private Map<String, Object> data;

        private static final MediaType APPLICATION_JSON_UTF8 =
                new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

        public Builder() {
            this.message = "";
        }

        public Builder(HttpStatus status) {
            this.status = status;
            this.message = "";
        }

        public Builder ok() {
            this.status = HttpStatus.OK;
            return this;
        }

        public Builder error() {
            this.status = HttpStatus.BAD_REQUEST;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Map<String, Object> dataMap) {
            data = dataMap;
            return this;
        }

        public Builder data(String k, Object v) {
            if (Optional.ofNullable(v).isPresent())
                data = Map.of(k, v);
            return this;
        }


        public Builder data(String dataString) {
            data = Map.of("result", dataString);
            return this;
        }

        public ResponseEntity<Object> build() {
            assert status != null;
            return ResponseEntity.status(status).contentType(APPLICATION_JSON_UTF8).body(
                    new LinkedHashMap<String, Object>() {{
                        put("code", status.value());
                        put("message", message);
                        put("data", data);
                    }}
            );
        }
    }

}
