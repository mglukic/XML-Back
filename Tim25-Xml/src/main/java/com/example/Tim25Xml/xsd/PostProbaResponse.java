//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.04 at 09:18:29 PM CEST 
//


package com.example.Tim25Xml.xsd;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prima" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "prima"
})
@XmlRootElement(name = "postProbaResponse")
public class PostProbaResponse {

    @XmlElement(required = true)
    protected String prima;

    /**
     * Gets the value of the prima property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrima() {
        return prima;
    }

    /**
     * Sets the value of the prima property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrima(String value) {
        this.prima = value;
    }

}
