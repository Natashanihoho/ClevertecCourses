package ru.clevertec.gordievich.infrastructure.property;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DbSetting {

    private String url;
    private String username;
    private String password;
    private Pool pool;

    @Getter
    @Setter
    public static class Pool {
        private String size;
    }
}
