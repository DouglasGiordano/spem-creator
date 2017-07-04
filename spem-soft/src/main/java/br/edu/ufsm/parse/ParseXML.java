/*
 */
package br.edu.ufsm.parse;

import br.edu.ufsm.model.ContentPackage;
import br.edu.ufsm.model.MethodLibrary;
import br.edu.ufsm.model.MethodPackage;
import br.edu.ufsm.model.MethodPlugin;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 *
 * @author douglas giordano, eduardo bruning
 */
public class ParseXML {
    
    public static MethodLibrary parse(String xml){
        XStream xstream = new XStream(new DomDriver());
        xstream.autodetectAnnotations(true);
        xstream.alias("uma:MethodLibrary", MethodLibrary.class);
        xstream.alias("MethodPackage", MethodPackage.class);
        xstream.alias("MethodPlugin", MethodPlugin.class);
        xstream.alias("MethodPackage", ContentPackage.class);
        xstream.ignoreUnknownElements();
        MethodLibrary estructure = (MethodLibrary) xstream.fromXML(xml);
        return estructure;
    }
    
}
