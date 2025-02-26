package org.jsonparser;

import org.jsonparser.converttoxml.ConvertToXML;
import org.jsonparser.converttoxml.ConvertToXml2;
import org.jsonparser.parsetree.Node;

import java.io.IOException;
import java.io.InputStreamReader;

public class JSON {
    public static void main(String[] args) throws IOException {
        JSONParser parser= new JSONParser(new InputStreamReader(System.in));
        Node ab= parser.parse();
        System.out.println("\nSuccessful parsing!");
        ConvertToXML convertToXML= new ConvertToXML();
        String str= convertToXML.exportToXml(ab);
        convertToXML.writeToFile(str);
    }
}
