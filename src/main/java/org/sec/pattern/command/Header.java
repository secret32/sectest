package org.sec.pattern.command;

/**
 * Created by Administrator on 2014/5/26.
 */
public class Header {

    Command cmd;

    public Header(Command cmd){
        this.cmd = cmd;
    }

    public void attract() {
        try {
            cmd.create();
        } catch (Exception e) {
            cmd.destroy();
        }
    }

    public static void main(String[] args) {
        Header header = new Header(new CommandExecutor(new Soldier("jerry")));
        header.attract();
    }

}
