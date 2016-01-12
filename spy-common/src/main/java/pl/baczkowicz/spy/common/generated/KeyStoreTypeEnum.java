//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 09:51:14 PM GMT 
//


package pl.baczkowicz.spy.common.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KeyStoreTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="KeyStoreTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DEFAULT"/&gt;
 *     &lt;enumeration value="JKS"/&gt;
 *     &lt;enumeration value="JCEKS"/&gt;
 *     &lt;enumeration value="PKCS12"/&gt;
 *     &lt;enumeration value="BKS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "KeyStoreTypeEnum")
@XmlEnum
public enum KeyStoreTypeEnum {

    DEFAULT("DEFAULT"),
    JKS("JKS"),
    JCEKS("JCEKS"),
    @XmlEnumValue("PKCS12")
    PKCS_12("PKCS12"),
    BKS("BKS");
    private final String value;

    KeyStoreTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static KeyStoreTypeEnum fromValue(String v) {
        for (KeyStoreTypeEnum c: KeyStoreTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
