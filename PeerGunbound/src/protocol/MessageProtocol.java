/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package protocol;



/**
 *
 * @author DELL
 */
public class MessageProtocol {

   

    public static final int Ready_State = 0;
    public static final int WaitHandShake_State = 1;

    private static int Cur_State;

    public MessageProtocol() {
        Cur_State = Ready_State;
    }
    public static char GetMessageCode(char[] input) {
        return (input[19]);
    }

//    public MessageProtocol(TrackerController _trackerController) {
//        trackerController = _trackerController;
//
//    }

    public static String Process(char[] input) {
        String output = null;
        char code = GetMessageCode(input);
        switch(Cur_State) {
            case Ready_State: {
                if (code == Message.HandShake_Code) {
                    output = Buildstring(input);
                    
                } else {
                    output = Message.Failed_ResponseMessage().toString();
                }
                break;
            }

        }
        return output;
    }
    public static String Buildstring(char[] chars) {
       String output = "";
       StringBuilder SB = new StringBuilder();
       for (int i=0; i < chars.length; i++) {
           SB.append(chars[i]);
       }
       output = SB.toString();
       return output;
    }
}

