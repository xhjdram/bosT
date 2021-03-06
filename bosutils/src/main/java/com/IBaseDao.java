
package com;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IBaseDao", targetNamespace = "http://chayuan.com/")
@XmlSeeAlso({

})
public interface IBaseDao {


    /**
     * 
     * @return
     *     returns java.util.List<com.chayuan.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://chayuan.com/", className = "com.chayuan.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://chayuan.com/", className = "com.chayuan.FindAllResponse")
    public List<Customer> findAll();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<com.chayuan.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAssociate", targetNamespace = "http://chayuan.com/", className = "com.chayuan.FindAssociate")
    @ResponseWrapper(localName = "findAssociateResponse", targetNamespace = "http://chayuan.com/", className = "com.chayuan.FindAssociateResponse")
    public List<Customer> findAssociate(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findDecidedZoneIdByAddress", targetNamespace = "http://chayuan.com/", className = "com.chayuan.FindDecidedZoneIdByAddress")
    @ResponseWrapper(localName = "findDecidedZoneIdByAddressResponse", targetNamespace = "http://chayuan.com/", className = "com.chayuan.FindDecidedZoneIdByAddressResponse")
    public String findDecidedZoneIdByAddress(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns com.chayuan.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findCustomerByPhoneNumber", targetNamespace = "http://chayuan.com/", className = "com.chayuan.FindCustomerByPhoneNumber")
    @ResponseWrapper(localName = "findCustomerByPhoneNumberResponse", targetNamespace = "http://chayuan.com/", className = "com.chayuan.FindCustomerByPhoneNumberResponse")
    public Customer findCustomerByPhoneNumber(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<com.chayuan.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findNotAssociate", targetNamespace = "http://chayuan.com/", className = "com.chayuan.FindNotAssociate")
    @ResponseWrapper(localName = "findNotAssociateResponse", targetNamespace = "http://chayuan.com/", className = "com.chayuan.FindNotAssociateResponse")
    public List<Customer> findNotAssociate();

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "assigncustomerstodecidedzone", targetNamespace = "http://chayuan.com/", className = "com.chayuan.Assigncustomerstodecidedzone")
    @ResponseWrapper(localName = "assigncustomerstodecidedzoneResponse", targetNamespace = "http://chayuan.com/", className = "com.chayuan.AssigncustomerstodecidedzoneResponse")
    public void assigncustomerstodecidedzone(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    List<Integer> arg1);

}
