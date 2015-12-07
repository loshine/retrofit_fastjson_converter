import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit.Converter;

public class FastjsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Type type;

    FastjsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        return JSON.parseObject(string, type);
    }
}
