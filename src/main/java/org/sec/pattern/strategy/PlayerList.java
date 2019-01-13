package org.sec.pattern.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhou_wei on 2014/10/29.
 */
public class PlayerList {

    private List<Object> players;

    public PlayerList() {
        players = new ArrayList<>();
        players.add("Kobe Bryant");
        players.add("LeBron James");
        players.add("Kevin Durant");
    }

    public void outputRank(RankRule rr) {
        if (players != null) {
            Collections.sort(players, rr);
            players.forEach(p -> System.out.println(p));
        }
    }

    public static void main(String[] args) {
        PlayerList list = new PlayerList();
        list.outputRank(RankByHashCode.getInstance());
        list.outputRank(RankByNameLength.getInstance());
    }

}
