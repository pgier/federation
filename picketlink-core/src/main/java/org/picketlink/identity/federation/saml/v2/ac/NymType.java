//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-661
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2008.12.08 at 05:45:20 PM CST
//

package org.picketlink.identity.federation.saml.v2.ac;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for nymType.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 *
 * <pre>
 * &lt;simpleType name="nymType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="anonymity"/>
 *     &lt;enumeration value="verinymity"/>
 *     &lt;enumeration value="pseudonymity"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "nymType")
@XmlEnum
public enum NymType {

    @XmlEnumValue("anonymity")
    ANONYMITY("anonymity"), @XmlEnumValue("verinymity")
    VERINYMITY("verinymity"), @XmlEnumValue("pseudonymity")
    PSEUDONYMITY("pseudonymity");
    private final String value;

    NymType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NymType fromValue(String v) {
        for (NymType c : NymType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
