package capstone.project.Demo_Project.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;

public class BeanUtility {
    public static <T> T converValue(Object source, Class<T> targetClass) {
        ObjectMapper objectMapper = ObjectMapperUtility.createObjectMapper(false, true);
        return objectMapper.convertValue(source, targetClass);
    }

    public static <T> T mapOject(InputStream inputStream, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = ObjectMapperUtility.createObjectMapper(false, true);
            return objectMapper.readValue(inputStream, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //TODO
        }
        return null;
    }
}
