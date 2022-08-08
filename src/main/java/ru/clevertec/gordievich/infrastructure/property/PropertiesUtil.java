package ru.clevertec.gordievich.infrastructure.property;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;

public class PropertiesUtil {

    private static final Yaml YAML_PARSER = new Yaml(new Constructor(YamlFile.class));
    private static final String PATH_FILL = "application.yaml";
    private static YamlFile yamlFile;

    static {
        loadProperties();
    }

    public static YamlFile getYamlFile() {
        return yamlFile;
    }

    private static void loadProperties() {
        try (InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PATH_FILL)) {
            yamlFile = YAML_PARSER.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
