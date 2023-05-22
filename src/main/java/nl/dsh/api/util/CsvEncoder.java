package nl.dsh.api.util;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.Encoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;

import nl.dsh.api.dao.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CsvEncoder<T> implements Encoder<T> {

    private final List<Class<?>> compatibleTypes;
    private final CsvMapper mapper;

    public CsvEncoder() {
        compatibleTypes = List.of(new Class<?>[]{
                Account.class,
                Login200Response.class,
                LoginRequest.class,
                Message.class,
                MessageFrom.class,
                PostMessageRequest.class,
                Property.class,
                Stats.class
        });
        mapper = CsvMapper.builder()
                .enable(CsvGenerator.Feature.ALWAYS_QUOTE_EMPTY_STRINGS)
                .addModule(new JavaTimeModule())
                .build();
    }

    @Override
    public boolean canEncode(ResolvableType elementType, MimeType mimeType) {
//        return Boolean.TRUE.equals(compatibleTypes.any(aClass -> aClass.equals(elementType.resolve())).block());
        return compatibleTypes.contains(elementType.resolve()) && getEncodableMimeTypes().contains(mimeType);
    }

    @Override
    public Flux<DataBuffer> encode(Publisher<? extends T> inputStream, DataBufferFactory bufferFactory, ResolvableType elementType, MimeType mimeType, Map<String, Object> hints) {
        log.info("Start encoding to CSV");
        CsvSchema schema = mapper.schemaFor(elementType.resolve());
        return Flux.from(inputStream)
                .switchOnFirst((signal, flux) ->
                    Flux.concat(
                        Flux.just(
                            getDataBuffer(bufferFactory, mapper.schemaFor(elementType.resolve()).withHeader(), signal.get())),
                        flux.mapNotNull(
                            elem -> getDataBuffer(bufferFactory, schema, elem)
                        )));
    }

    private DataBuffer getDataBuffer(DataBufferFactory bufferFactory, CsvSchema schema, Object elem) {
        DataBuffer buf = bufferFactory.allocateBuffer();
        try {
            SequenceWriter writer = mapper.writer().with(schema).writeValues(buf.asOutputStream());
            writer.write(elem);
            return buf;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public DataBuffer encodeValue(T value, DataBufferFactory bufferFactory, ResolvableType valueType, MimeType mimeType, Map<String, Object> hints) {
//        return Encoder.super.encodeValue(value, bufferFactory, valueType, mimeType, hints);
        log.info("Encoding single item to CSV");
        DataBuffer buf = bufferFactory.allocateBuffer();
        CsvSchema schema = mapper.schemaFor(valueType.resolve()).withHeader();
        SequenceWriter writer = mapper.writer().with(schema).writeValues(buf.asOutputStream());
        writer.write(value);
        return buf;
    }

    @Override
    public List<MimeType> getEncodableMimeTypes() {
        return List.of(new MimeType("text", "csv"));
    }
}
