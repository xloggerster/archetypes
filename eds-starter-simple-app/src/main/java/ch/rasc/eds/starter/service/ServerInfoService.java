package ch.rasc.eds.starter.service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import org.springframework.stereotype.Service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;

@Service
public class ServerInfoService {

	@ExtDirectMethod
	public String getInfo() {
		OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

		String infoString = "Arch: %s\nProcessors: %d\nOS Name: %s\nOS Version: %s\n";

		return String.format(infoString, osBean.getArch(), osBean.getAvailableProcessors(), osBean.getName(),
				osBean.getVersion());
	}
}
