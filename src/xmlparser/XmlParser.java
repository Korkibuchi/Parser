
package xmlparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;


public class XmlParser {

    
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
       XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream("tesB.xml"));
       Stack<String> st = new Stack<>();
        boolean lnFlag = false,flag = false;
        
        int evt, att=0;
        while (reader.hasNext()) {
            
            evt = reader.next();
            
            if (evt == XMLEvent.START_ELEMENT && (att = reader.getAttributeCount())> 0 ){
                   st.push(reader.getName().getLocalPart());
                   for (String string : st) {
                    System.out.print("/" + string);}
                    for (int i = 0; i < att; i++) {
                        System.out.print("-[" + reader.getAttributeName(i)+ "]:\"" + reader.getAttributeValue(i)+"\"");
                        lnFlag=true;
                }
                    
                    flag = false;
                   } else if (evt == XMLEvent.START_ELEMENT && (att = reader.getAttributeCount())== 0 ) {
                st.push(reader.getName().getLocalPart());
                if (flag){
                    for (String string : st) {
                    System.out.print("/" + string);}
                    
                    }
               
                flag = true;
                
            }
            
            if (evt == XMLEvent.END_ELEMENT) {
                    st.pop();
                    if (!flag)System.out.println("-----");
                    flag = false;
                }
            if (reader.hasText() && reader.getText().trim().length() > 0 && evt != XMLEvent.COMMENT) {
                    
                    if (att>0){System.out.print(": " + reader.getText()); System.out.println("");lnFlag=false; }
                    
                    else {
                        if(lnFlag){System.out.println(""); lnFlag=false;}
                        for (String string : st) {
                        System.out.print("/" + string);}
                    System.out.println(": " + reader.getText());
                }}
                   
    }
    }
}
