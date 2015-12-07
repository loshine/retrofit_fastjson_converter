import com.alibaba.fastjson.serializer.SerializeConfig;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

public final class FastjsonConverterFactory extends Converter.Factory {

    public static FastjsonConverterFactory create() {
        return new FastjsonConverterFactory(SerializeConfig.getGlobalInstance());
    }

    /**
     * Create an instance using {@code mapper} for conversion.
     */
    public static FastjsonConverterFactory create(SerializeConfig serializeConfig) {
        return new FastjsonConverterFactory(serializeConfig);
    }

    private final SerializeConfig serializeConfig;

    private FastjsonConverterFactory(SerializeConfig serializeConfig) {
        if (serializeConfig == null) throw new NullPointerException("mapper == null");
        this.serializeConfig = serializeConfig;
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new FastjsonResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new FastjsonRequestBodyConverter<>(serializeConfig);
    }
}
