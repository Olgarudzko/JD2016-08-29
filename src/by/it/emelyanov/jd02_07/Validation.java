package by.it.emelyanov.jd02_07;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class Validation {
    public static void main(String[] args) {

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String src = System.getProperty("user.dir") + "/src/";
        String filePath = src + "by/it/emelyanov/jd02_07/Hotel+XSD.xml";
        String schemapath = src + "by/it/emelyanov/jd02_07/Hotel.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaFile = new File(schemapath);
        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(filePath);
            validator.validate(source);
            System.out.println(filePath + " валиден.");
        } catch (SAXException e) {
            System.out.println(filePath + " не валиден:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка валидации " + filePath + e.getMessage());
        }
    }
}
