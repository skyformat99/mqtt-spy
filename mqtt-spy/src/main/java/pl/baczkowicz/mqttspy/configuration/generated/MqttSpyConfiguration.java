//
// Copyright (c) 2015 Kamil Baczkowicz
//
// CSOFF: a.*
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Timestamp removed by maven-replacer-plugin to avoid detecting changes - see the project POM for details
//


package pl.baczkowicz.mqttspy.configuration.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;
import pl.baczkowicz.spy.common.generated.Formatting;


/**
 * <p>Java class for MqttSpyConfiguration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MqttSpyConfiguration"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ConnectionGroups" type="{http://baczkowicz.pl/mqtt-spy-configuration}ConnectionGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Connectivity" type="{http://baczkowicz.pl/mqtt-spy-configuration}Connectivity"/&gt;
 *         &lt;element name="Formatting" type="{http://baczkowicz.pl/spy/common}Formatting" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
*/
@SuppressWarnings("all")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MqttSpyConfiguration", propOrder = {
    "connectionGroups",
    "connectivity",
    "formatting"
})
public class MqttSpyConfiguration implements Cloneable, CopyTo, Equals, HashCode, ToString
{

    @XmlElement(name = "ConnectionGroups")
    protected List<ConnectionGroup> connectionGroups;
    @XmlElement(name = "Connectivity", required = true)
    protected Connectivity connectivity;
    @XmlElement(name = "Formatting")
    protected Formatting formatting;

    /**
     * Default no-arg constructor
     * 
     */
    public MqttSpyConfiguration() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public MqttSpyConfiguration(final List<ConnectionGroup> connectionGroups, final Connectivity connectivity, final Formatting formatting) {
        this.connectionGroups = connectionGroups;
        this.connectivity = connectivity;
        this.formatting = formatting;
    }

    /**
     * Gets the value of the connectionGroups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the connectionGroups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConnectionGroups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConnectionGroup }
     * 
     * 
     */
    public List<ConnectionGroup> getConnectionGroups() {
        if (connectionGroups == null) {
            connectionGroups = new ArrayList<ConnectionGroup>();
        }
        return this.connectionGroups;
    }

    /**
     * Gets the value of the connectivity property.
     * 
     * @return
     *     possible object is
     *     {@link Connectivity }
     *     
     */
    public Connectivity getConnectivity() {
        return connectivity;
    }

    /**
     * Sets the value of the connectivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Connectivity }
     *     
     */
    public void setConnectivity(Connectivity value) {
        this.connectivity = value;
    }

    /**
     * Gets the value of the formatting property.
     * 
     * @return
     *     possible object is
     *     {@link Formatting }
     *     
     */
    public Formatting getFormatting() {
        return formatting;
    }

    /**
     * Sets the value of the formatting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Formatting }
     *     
     */
    public void setFormatting(Formatting value) {
        this.formatting = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            List<ConnectionGroup> theConnectionGroups;
            theConnectionGroups = (((this.connectionGroups!= null)&&(!this.connectionGroups.isEmpty()))?this.getConnectionGroups():null);
            strategy.appendField(locator, this, "connectionGroups", buffer, theConnectionGroups);
        }
        {
            Connectivity theConnectivity;
            theConnectivity = this.getConnectivity();
            strategy.appendField(locator, this, "connectivity", buffer, theConnectivity);
        }
        {
            Formatting theFormatting;
            theFormatting = this.getFormatting();
            strategy.appendField(locator, this, "formatting", buffer, theFormatting);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof MqttSpyConfiguration)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final MqttSpyConfiguration that = ((MqttSpyConfiguration) object);
        {
            List<ConnectionGroup> lhsConnectionGroups;
            lhsConnectionGroups = (((this.connectionGroups!= null)&&(!this.connectionGroups.isEmpty()))?this.getConnectionGroups():null);
            List<ConnectionGroup> rhsConnectionGroups;
            rhsConnectionGroups = (((that.connectionGroups!= null)&&(!that.connectionGroups.isEmpty()))?that.getConnectionGroups():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "connectionGroups", lhsConnectionGroups), LocatorUtils.property(thatLocator, "connectionGroups", rhsConnectionGroups), lhsConnectionGroups, rhsConnectionGroups)) {
                return false;
            }
        }
        {
            Connectivity lhsConnectivity;
            lhsConnectivity = this.getConnectivity();
            Connectivity rhsConnectivity;
            rhsConnectivity = that.getConnectivity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "connectivity", lhsConnectivity), LocatorUtils.property(thatLocator, "connectivity", rhsConnectivity), lhsConnectivity, rhsConnectivity)) {
                return false;
            }
        }
        {
            Formatting lhsFormatting;
            lhsFormatting = this.getFormatting();
            Formatting rhsFormatting;
            rhsFormatting = that.getFormatting();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "formatting", lhsFormatting), LocatorUtils.property(thatLocator, "formatting", rhsFormatting), lhsFormatting, rhsFormatting)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<ConnectionGroup> theConnectionGroups;
            theConnectionGroups = (((this.connectionGroups!= null)&&(!this.connectionGroups.isEmpty()))?this.getConnectionGroups():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "connectionGroups", theConnectionGroups), currentHashCode, theConnectionGroups);
        }
        {
            Connectivity theConnectivity;
            theConnectivity = this.getConnectivity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "connectivity", theConnectivity), currentHashCode, theConnectivity);
        }
        {
            Formatting theFormatting;
            theFormatting = this.getFormatting();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "formatting", theFormatting), currentHashCode, theFormatting);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof MqttSpyConfiguration) {
            final MqttSpyConfiguration copy = ((MqttSpyConfiguration) draftCopy);
            if ((this.connectionGroups!= null)&&(!this.connectionGroups.isEmpty())) {
                List<ConnectionGroup> sourceConnectionGroups;
                sourceConnectionGroups = (((this.connectionGroups!= null)&&(!this.connectionGroups.isEmpty()))?this.getConnectionGroups():null);
                @SuppressWarnings("unchecked")
                List<ConnectionGroup> copyConnectionGroups = ((List<ConnectionGroup> ) strategy.copy(LocatorUtils.property(locator, "connectionGroups", sourceConnectionGroups), sourceConnectionGroups));
                copy.connectionGroups = null;
                if (copyConnectionGroups!= null) {
                    List<ConnectionGroup> uniqueConnectionGroupsl = copy.getConnectionGroups();
                    uniqueConnectionGroupsl.addAll(copyConnectionGroups);
                }
            } else {
                copy.connectionGroups = null;
            }
            if (this.connectivity!= null) {
                Connectivity sourceConnectivity;
                sourceConnectivity = this.getConnectivity();
                Connectivity copyConnectivity = ((Connectivity) strategy.copy(LocatorUtils.property(locator, "connectivity", sourceConnectivity), sourceConnectivity));
                copy.setConnectivity(copyConnectivity);
            } else {
                copy.connectivity = null;
            }
            if (this.formatting!= null) {
                Formatting sourceFormatting;
                sourceFormatting = this.getFormatting();
                Formatting copyFormatting = ((Formatting) strategy.copy(LocatorUtils.property(locator, "formatting", sourceFormatting), sourceFormatting));
                copy.setFormatting(copyFormatting);
            } else {
                copy.formatting = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new MqttSpyConfiguration();
    }

}
