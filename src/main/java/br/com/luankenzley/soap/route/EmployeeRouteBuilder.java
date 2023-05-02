package br.com.luankenzley.soap.route;

import javax.xml.bind.JAXBContext;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import br.com.luankenzley.soap.model.Employee;

public class EmployeeRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		// XML Data Format
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(Employee.class);
		xmlDataFormat.setContext(con);
		
		// JSON Data Format
		JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Employee.class);

		
		from("file:C:/inputFolder")
		.unmarshal(xmlDataFormat)
		.log("LENDO SOAP ----" + "${body}")
		.marshal(jsonDataFormat)
		.log("TRANSFORMANDO EM JSON ----" + "${body}");
		
	}

}
