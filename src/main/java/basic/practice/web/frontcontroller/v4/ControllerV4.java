package basic.practice.web.frontcontroller.v4;

import basic.practice.web.frontcontroller.ModelView;

import java.util.Map;
import java.util.Objects;

public interface ControllerV4 {
    String process(Map<String,String> paramMap, Map<String, Object> model);

}
