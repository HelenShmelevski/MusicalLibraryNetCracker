package services;

import java.io.File;
import java.io.IOException;

public class JsonService<T extends Object> {
    private ObjectMapper objectMapper;
    public JsonService() {
        objectMapper = new ObjectMapper();
    }

    public T[] read(File file, Class<T[]> type)
    {
        T[] obj = null;
        try {
            obj = objectMapper.readValue(file, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void write(File file, T[] obj)
    {
        try {
            objectMapper.writeValue(file, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
