package FuntionCommandResolve;

import java.util.Map;

import FuntionReport.WriteReport;


public interface ISendAgreement {

	void sendcommand(Map<String, String> ParameterIntput,
			WriteReport LogReport) throws Exception;

}
