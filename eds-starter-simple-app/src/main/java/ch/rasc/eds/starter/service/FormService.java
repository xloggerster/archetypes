package ch.rasc.eds.starter.service;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.FORM_LOAD;
import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.FORM_POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectResponseBuilder;
import ch.rasc.eds.starter.FormBean;

@Controller
public class FormService {

	@ExtDirectMethod(FORM_LOAD)
	public FormBean getFormData() {
		FormBean bean = new FormBean();
		bean.setFirstName("Karl");
		bean.setLastName("Pilkington");
		bean.setTime("");
		return bean;
	}

	@ExtDirectMethod(FORM_POST)
	@RequestMapping(value = "/handleFormSubmit", method = RequestMethod.POST)
	public void handleFormSubmit(FormBean bean, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(bean);

		ExtDirectResponseBuilder.create(request, response).buildAndWrite();
	}

}
