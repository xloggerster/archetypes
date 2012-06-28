#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import ch.ralscha.extdirectspring.bean.ExtDirectResponseBuilder;

@Component
@Lazy
public class FormPostExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) {

		LoggerFactory.getLogger(FormPostExceptionHandler.class).error("error", ex);
		ExtDirectResponseBuilder.create(request, response).setException(ex).buildAndWrite();
		return null;

	}

}
