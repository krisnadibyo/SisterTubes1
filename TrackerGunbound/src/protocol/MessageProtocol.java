/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package protocol;

import controller.TrackerControllerThread;

/**
 *
 * @author DELL
 */
public class MessageProtocol {

    TrackerControllerThread trackerController;

    public static final int Ready_State = 0;
    public static final int WaitHandShake_State = 1;

    private static int Cur_State = Ready_State;

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
                    output = input.toString();
                } else {
                    output = Message.Failed_ResponseMessage().toString();
                }
                break;
            }

        }
        return output;
    }
}
