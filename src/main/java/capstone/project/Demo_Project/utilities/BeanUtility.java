package capstone.project.Demo_Project.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BeanUtility {
    private static final Logger logger = LogManager.getLogger(BeanUtility.class);

    public static <T> T cloneObject(T target) throws IOException {
        ObjectMapper mapper = ObjectMapperUtility.createObjectMapper(true, true);
        return mapper.readValue(
                mapper.writeValueAsString(target),
                mapper.getTypeFactory().constructType(target.getClass())
        );
    }

    public static <T> List<T> cloneObjects(List<?> sources, Class<T> clazz) {
        ObjectMapper objectMapper = ObjectMapperUtility.createObjectMapper(true, true);
        List<T> myObjects = null;
        try {
            myObjects = objectMapper.readValue(
                    objectMapper.writeValueAsString(sources),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (myObjects == null) {
            return new ArrayList<>();
        }
        return myObjects;
    }

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
