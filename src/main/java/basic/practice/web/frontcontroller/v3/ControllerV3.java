package basic.practice.web.frontcontroller.v3;

import basic.practice.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String,String> paramMap);

}
