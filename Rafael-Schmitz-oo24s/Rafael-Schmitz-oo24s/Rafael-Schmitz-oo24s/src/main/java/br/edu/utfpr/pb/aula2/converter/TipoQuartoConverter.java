package br.edu.utfpr.pb.aula2.converter;

import br.edu.utfpr.model.TipoQuarto;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

//@Converter(autoApply = true)
@Convert
public class TipoQuartoConverter implements 
                    AttributeConverter<TipoQuarto, Integer>{

    @Override
    public Integer convertToDatabaseColumn(TipoQuarto value) {
        return value.getId();
    }

    @Override
    public TipoQuarto convertToEntityAttribute(Integer value) {
        return TipoQuarto.findById(value);
    }
    
}
