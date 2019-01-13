package org.sec.core.nashorn;

import org.sec.utils.HttpClient4Util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author zhouwei
 * @since 2017/5/17
 */
public class JsTest {

    public static void main(String[] args) {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("JavaScript");
        if (engine != null) {
            try {
                String url = "http://test.com/static/scripts/test.js";
                String playerJS = HttpClient4Util.get(url);
                // System.out.println(playerJS);
                engine.eval(playerJS);
                engine.eval("test('test')");
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
    }

}
