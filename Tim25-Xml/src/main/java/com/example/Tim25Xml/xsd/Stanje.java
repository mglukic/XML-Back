//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.08 at 11:24:28 AM CEST 
//


package com.example.Tim25Xml.xsd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Stanje.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Stanje"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PENDING"/&gt;
 *     &lt;enumeration value="RESERVED"/&gt;
 *     &lt;enumeration value="PAID"/&gt;
 *     &lt;enumeration value="CANCELED"/&gt;
 *     &lt;enumeration value="WAITING_REVIEW"/&gt;
 *     &lt;enumeration value="REVIEWED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Stanje")
@XmlEnum
public enum Stanje {

    PENDING,
    RESERVED,
    PAID,
    CANCELED,
    WAITING_REVIEW,
    REVIEWED;

    public String value() {
        return name();
    }

    public static Stanje fromValue(String v) {
        return valueOf(v);
    }

}
