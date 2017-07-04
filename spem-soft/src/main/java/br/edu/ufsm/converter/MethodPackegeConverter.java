/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.converter;

import br.edu.ufsm.model.ContentPackage;
import br.edu.ufsm.model.MethodPackage;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author douglas giordano, eduardo bruning
 */
public class MethodPackegeConverter extends ReflectionConverter {

    private Map<Class, String> aliases = new HashMap<Class, String>(2);
    private Map<String, Class> classes = new HashMap<String, Class>(2);

    public MethodPackegeConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
        super(mapper, reflectionProvider);
        aliases.put(ContentPackage.class, "uma:ContentPackage");
        classes.put("uma:ContentPackage", ContentPackage.class);
    }

    @Override
    public boolean canConvert(Class type) {
        final Class clazz = classes.get(type);
        if(clazz==null){
            return false;
        }
        return MethodPackage.class.isAssignableFrom(type);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Object query = null;
        try {
            final String type = reader.getAttribute("xsi:type");
            final Class clazz = classes.get(type);
            if(clazz==null){
                return null;
            }
            query = clazz.getConstructor().
                    newInstance();
        }
        catch (Throwable ex) { // for space economy
            ex.printStackTrace();
        }
        super.doUnmarshal(query, reader, context);
        return query;
    }
}