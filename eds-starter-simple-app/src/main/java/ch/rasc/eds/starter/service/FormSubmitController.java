package ch.rasc.eds.starter.service;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.FORM_POST;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectResponseBuilder;
import ch.rasc.eds.starter.FormBean;

@Controller
public class FormSubmitController {

	@ExtDirectMethod(FORM_POST)
	@RequestMapping(value = "/handleFormSubmit", method = RequestMethod.POST)
	public void handleFormSubmit(FormBean bean, MultipartFile screenshot, HttpServletRequest request,
			final HttpServletResponse response) {

		String result = "Server received: \n" + bean.toString();
		result += "\n";

		if (!screenshot.isEmpty()) {
			result += "ContentType: " + screenshot.getContentType() + "\n";
			result += "Size: " + screenshot.getSize() + "\n";
			result += "Name: " + screenshot.getOriginalFilename();
		}

		ExtDirectResponseBuilder.create(request, response).addResultProperty("response", result).buildAndWrite();
	}

	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		CustomDateEditor editor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, editor);
	}

}
