package ru.clevertec.gordievich.infrastructure.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YamlFile {

    private DbSetting db;
    private ReceiptSetting receipt;

}
