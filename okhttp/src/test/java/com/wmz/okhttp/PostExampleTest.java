package com.wmz.okhttp;

import junit.framework.TestCase;

/**
 * Created by wmz on 24/5/16.
 */
public class PostExampleTest extends TestCase {

    private PostExample postExample;
    public void setUp() throws Exception {
        super.setUp();
        postExample = new PostExample();
    }

    public void testPost() throws Exception {
        String json = bowlingJson("Jesse","Jake");
        String response = postExample.post("http://www.roundsapp.com/post", json);
        System.out.println(response);
    }

    String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }
}