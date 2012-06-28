#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.FORM_POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectResponseBuilder;
import ${package}.FormBean;

@Controller
public class FormSubmitController {

	@ExtDirectMethod(FORM_POST)
	@RequestMapping(value = "/handleFormSubmit", method = RequestMethod.POST)
	public void handleFormSubmit(final FormBean bean, final MultipartFile screenshot, final HttpServletRequest request,
			final HttpServletResponse response) {

		String result = "Server received: ${symbol_escape}n" + bean.toString();
		result += "${symbol_escape}n";

		if (!screenshot.isEmpty()) {
			result += "ContentType: " + screenshot.getContentType() + "${symbol_escape}n";
			result += "Size: " + screenshot.getSize() + "${symbol_escape}n";
			result += "Name: " + screenshot.getOriginalFilename();
		}

		ExtDirectResponseBuilder.create(request, response).addResultProperty("response", result).buildAndWrite();
	}

}
