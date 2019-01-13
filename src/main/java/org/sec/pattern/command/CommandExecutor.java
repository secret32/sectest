package org.sec.pattern.command;

/**
 * Created by Administrator on 2014/5/26.
 */
public class CommandExecutor implements Command {

    Soldier tom;

    public CommandExecutor(Soldier s){
        this.tom = s;
    }

    @Override
    public void create() {
        tom.crash();
    }

    @Override
    public void destroy() {
        tom.rebuild();
    }
}
